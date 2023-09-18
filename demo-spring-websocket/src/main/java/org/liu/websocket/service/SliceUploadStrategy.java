package org.liu.websocket.service;

import org.liu.websocket.model.FileUploadDTO;
import org.liu.websocket.model.FileUploadRequestDTO;

/**
 * @Author lzs
 * @Date 2023/9/16 16:22
 **/
public interface SliceUploadStrategy {
    FileUploadDTO sliceUpload(FileUploadRequestDTO param);
}