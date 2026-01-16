package org.liu.mockito.controller;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.liu.mockito.pojo.po.Order;
import org.liu.mockito.service.OrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.TimeZone;

/**
 * @author lzs
 * @Date 2024/8/19 15:14
 **/
@RequiredArgsConstructor
@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    /**
     * 当controller里的方法使用公共响应数据结构时，如果当前请求返回了null，spring不会为此请求设置响应的content-type，
     * 这会导致mock方法失败，因为我们通常会为mock方法设置期望响应的content-type
     */
    @GetMapping("get")
    public Order get(Long id) {
        Order order = orderService.findById(id);
        System.out.println(order);
        return order;
    }

    @PutMapping
    public void add(Order order) {
        System.out.println(order);
    }

    @PostMapping("save")
    public void save(@RequestBody Order order) {
        System.out.println(order.getInstant().toEpochMilli());
        System.out.println(order.getZonedDateTime().getZone());
        System.out.println(TimeZone.getDefault());
        orderService.save(order);
    }

    @DeleteMapping
    public void delete(Long id) {
        orderService.delete(id);
    }

    @PostMapping("upload")
    public void upload(MultipartFile file, Integer from) throws IOException {
        String filename = RandomString.make(12);
        String suffix = file.getName().substring(file.getName().lastIndexOf("."));
        file.transferTo(new File("E:\\downloads\\" + filename + suffix));
        System.out.println("上传成功");
    }

}
