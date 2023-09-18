package org.liu.websocket.controller;

import org.liu.websocket.model.FileUploadRequestDTO;
import org.liu.websocket.service.SliceUploadStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 此controller运行不通，从网上拷贝过来只为了学习使用RandomAccessFile类和MappedByteBuffer类实现文件分片上传
 * RandomAccessFile：
 * MappedByteBuffer：https://www.jianshu.com/p/f90866dcbffc
 *
 * @Author lzs
 * @Date 2023/9/16 15:59
 **/
@RequestMapping("file/upload")
@RestController
public class FileUploadController {

    @Qualifier("mappedByteBufferUploadStrategy")
    @Autowired
    private SliceUploadStrategy mappedByteBufferUploadStrategy;
    @Qualifier("randomAccessUploadStrategy")
    @Autowired
    private SliceUploadStrategy randomAccessUploadStrategy;

    @PostMapping("byRandomAccess")
    public void byRandomAccess(FileUploadRequestDTO param) {
        randomAccessUploadStrategy.sliceUpload(param);
    }

    @PostMapping("byMappedByteBuffer")
    public void byMappedByteBuffer(FileUploadRequestDTO param) {
        mappedByteBufferUploadStrategy.sliceUpload(param);
    }

}
