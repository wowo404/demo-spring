package org.liu.demo.mongodb;

import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.pojo.DestDTO;
import org.liu.demo.mongodb.pojo.SourceDTO;
import org.springframework.beans.BeanUtils;

public class DeepCopyTest {

    @Test
    public void test(){
        SourceDTO sourceDTO = new SourceDTO();
        sourceDTO.setEv_id(1L);
        sourceDTO.setEv_name("a");

        DestDTO destDTO = new DestDTO();

        BeanUtils.copyProperties(sourceDTO, destDTO);
        System.out.println(destDTO.getEv_id());
        System.out.println(destDTO.getEv_name());
    }

}
