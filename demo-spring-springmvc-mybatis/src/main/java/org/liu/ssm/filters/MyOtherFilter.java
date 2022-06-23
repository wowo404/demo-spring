package org.liu.ssm.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import java.io.IOException;
import java.util.Collections;

/**
 * @Author lzs
 * @Date 2022/6/23 14:22
 **/
@Component
@Slf4j
public class MyOtherFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterConfig filterConfig = getFilterConfig();
        //filterConfig是Nullable的，这里是idea误报了
        if (null != filterConfig) {
            String params = String.join(",", Collections.list(filterConfig.getInitParameterNames()));
            log.info("当前过滤器名字:{}, 参数:{}", filterConfig.getFilterName(), params);
        }
        log.info("before execute next filter");
        chain.doFilter(request, response);
        log.info("after execute next filter");
    }
}
