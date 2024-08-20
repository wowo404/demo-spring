package org.liu.mockito.controller;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.liu.mockito.pojo.po.Order;
import org.liu.mockito.service.OrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author lzs
 * @Date 2024/8/19 15:14
 **/
@RequiredArgsConstructor
@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("get")
    public Order get(Long id) {
        return orderService.findById(id);
    }

    @PutMapping
    public void add(Order order) {
        System.out.println(order);
    }

    @PostMapping("save")
    public void save(@RequestBody Order order) {
        System.out.println(order);
    }

    @DeleteMapping
    public void delete(Long id){
        System.out.println("delete success：" + id);
    }

    @PostMapping("upload")
    public void upload(MultipartFile file, Integer from) throws IOException {
        String filename = RandomString.make(12);
        String suffix = file.getName().substring(file.getName().lastIndexOf("."));
        file.transferTo(new File("E:\\downloads\\" + filename + suffix));
        System.out.println("上传成功");
    }

}
