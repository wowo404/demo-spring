package org.liu.mockito.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

@SpringBootTest
public class OrderControllerTest {

    private MockMvc mockMvc;

    //疑问：此处的参数WebApplicationContext是如何注入的
    @BeforeEach
    public void init(WebApplicationContext context) {
//1.此方式不成功，指定controller如何注入依赖
        //this.mockMvc = MockMvcBuilders.standaloneSetup(new GraphicalIndexV2Controller()).build();
        //2.基于spring容器配置所有controller类
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .defaultRequest(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .apply(SharedHttpSessionConfigurer.sharedHttpSession())
                .alwaysExpect(MockMvcResultMatchers.status().isOk())
                .alwaysExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
// alwaysExpect表示期望得到什么响应，如果此处设置期望响应是UTF-8，但实际确是iso-8859-1
// 为什么不是utf-8？配置了server.servlet.encoding.charset=utf-8依然无效，why？
//                .alwaysExpect(MockMvcResultMatchers.content().encoding("UTF-8"))
                .build();
    }

    @Test
    public void get() throws Exception {
        //传参方式1：urlTemplate，2.param函数，3.queryParam函数
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/order/get?id={id}", "1234")
                .accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andReturn();
        //注意：getContentAsString方法的参数一定要有，不然输出中文就乱码
        System.out.println(mvcResult.getResponse().getContentAsString(Charset.defaultCharset()));
    }

    @Test
    public void add() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put("/order/add")
                .param("id", "12001")
                .param("code", "abcd1")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString(Charset.defaultCharset()));
    }

    @Test
    public void save() {
    }

    @Test
    public void upload() {
    }
}
