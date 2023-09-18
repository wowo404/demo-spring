package org.liu.websocket.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.liu.websocket.model.FileUploadRequestDTO;
import org.liu.websocket.spring.SpringContextHolder;
import org.liu.websocket.util.FilePathUtil;
import org.liu.websocket.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Objects;

/**
 * @Author lzs
 * @Date 2023/9/16 16:29
 **/
@Service
@Slf4j
public class MappedByteBufferUploadStrategy extends SliceUploadTemplate {

    @Value("${upload.chunkSize:1}")
    private long defaultChunkSize;

    @Override
    public boolean upload(FileUploadRequestDTO param) {

        RandomAccessFile tempRaf = null;
        FileChannel fileChannel = null;
        MappedByteBuffer mappedByteBuffer = null;
        try {
            String uploadDirPath = SpringContextHolder.getBean(FilePathUtil.class).getPath(param);
            File tmpFile = super.createTmpFile(param);
            tempRaf = new RandomAccessFile(tmpFile, "rw");
            fileChannel = tempRaf.getChannel();

            long chunkSize = Objects.isNull(param.getChunkSize()) ? defaultChunkSize * 1024 * 1024
                    : param.getChunkSize();
            //写入该分片数据
            long offset = chunkSize * param.getChunk();
            byte[] fileData = param.getFile().getBytes();
            mappedByteBuffer = fileChannel
                    .map(FileChannel.MapMode.READ_WRITE, offset, fileData.length);
            mappedByteBuffer.put(fileData);
            boolean isOk = super.checkAndSetUploadProgress(param, uploadDirPath);
            return isOk;

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            FileUtil.freedMappedByteBuffer(mappedByteBuffer);
            FileUtil.close(fileChannel);
            FileUtil.close(tempRaf);
        }

        return false;
    }

}