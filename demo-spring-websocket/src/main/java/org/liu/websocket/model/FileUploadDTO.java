package org.liu.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Author lzs
 * @Date 2023/9/16 16:24
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FileUploadDTO {
    private Long mtime;
    private String path;
    private Long size;
    private String fileExt;
    private String fileId;
    private Boolean uploadComplete;
    private Map<Integer, String> chunkMd5Info;

    public boolean isUploadComplete() {

        return true;
    }
}
