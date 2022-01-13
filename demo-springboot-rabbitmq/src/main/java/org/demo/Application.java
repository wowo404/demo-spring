package org.demo;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application
{
    public static void main( String[] args )
    {
//        SpringApplication.run(Application.class, args);

        DocsConfig config = new DocsConfig();
        config.setProjectPath("D:\\work\\workspace-idea\\demo-spring\\demo-springboot-rabbitmq"); // 项目根目录
        config.setProjectName("demo-springboot-rabbitmq"); // 项目名称
        config.setApiVersion("V1.0");       // 声明该API的版本
        config.setDocsPath("D:\\home\\docs"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
    }
}
