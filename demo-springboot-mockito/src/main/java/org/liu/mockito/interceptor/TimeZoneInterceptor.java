package org.liu.mockito.interceptor;

import org.liu.mockito.support.TimezoneContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class TimeZoneInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String timezoneId = request.getHeader("X-Timezone-Id");
        if (timezoneId != null) {
            try {
                TimezoneContext.setZoneId(ZoneId.of(timezoneId));
            } catch (Exception e) {
                // 处理无效时区 ID
                TimezoneContext.setZoneId(ZoneOffset.UTC); // 设置安全默认值
            }
        } else {
            TimezoneContext.setZoneId(ZoneOffset.UTC); // 默认值
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TimezoneContext.clear();
    }
}
