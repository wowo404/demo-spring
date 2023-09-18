package org.liu.websocket.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author lzs
 * @Date 2023/9/16 16:24
 **/
@Data
public class FileUploadRequestDTO {
    private Integer chunk;
    private Long chunks;
    private Long chunkSize;
    private String md5;
    private String path;
    private MultipartFile file;
}
