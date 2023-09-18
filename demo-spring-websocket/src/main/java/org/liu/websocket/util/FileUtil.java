package org.liu.websocket.util;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author lzs
 * @Date 2023/9/16 16:25
 **/
public class FileUtil {
    @SneakyThrows
    public static void close(RandomAccessFile accessConfFile) {
        if (null == accessConfFile) {
            return;
        }
        accessConfFile.close();
    }

    public static String getExtension(String toFileNewName) {
        return null;
    }

    public static String withoutHeadAndTailDiagonal(String path) {
        return null;
    }

    public static void freedMappedByteBuffer(MappedByteBuffer mappedByteBuffer) {
        if (null == mappedByteBuffer) {
            return;
        }
        mappedByteBuffer.clear();
    }

    public static void close(FileChannel fileChannel) {
        if (null == fileChannel) {
            return;
        }
        try {
            fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
