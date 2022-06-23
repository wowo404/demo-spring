package liu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lzs
 * @Date 2022/6/15 17:03
 **/
//@CrossOrigin //webConfig中已添加全局配置
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "get hello return ok";
    }

    @PostMapping("hello")
    public String hello2() {
        return "post hello return success";
    }

}
