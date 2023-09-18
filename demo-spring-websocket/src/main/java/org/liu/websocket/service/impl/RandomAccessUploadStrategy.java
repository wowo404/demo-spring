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
import java.util.Objects;

/**
 * @Author lzs
 * @Date 2023/9/16 16:33
 **/
@Service
@Slf4j
public class RandomAccessUploadStrategy extends SliceUploadTemplate {

    @Value("${upload.chunkSize:1}")
    private long defaultChunkSize;

    @Override
    public boolean upload(FileUploadRequestDTO param) {
        RandomAccessFile accessTmpFile = null;
        try {
            String uploadDirPath = SpringContextHolder.getBean(FilePathUtil.class).getPath(param);
            File tmpFile = super.createTmpFile(param);
            accessTmpFile = new RandomAccessFile(tmpFile, "rw");
            //这个必须与前端设定的值一致
            long chunkSize = Objects.isNull(param.getChunkSize()) ? defaultChunkSize * 1024 * 1024
                    : param.getChunkSize();
            long offset = chunkSize * param.getChunk();
            //定位到该分片的偏移量
            accessTmpFile.seek(offset);
            //写入该分片数据
            accessTmpFile.write(param.getFile().getBytes());
            boolean isOk = super.checkAndSetUploadProgress(param, uploadDirPath);
            return isOk;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            FileUtil.close(accessTmpFile);
        }
        return false;
    }

}
