package com.yishi.mall.web.config;

import com.alibaba.fastjson.JSON;
import com.yishi.mall.response.Result;
import com.yishi.mall.user.service.UserService;
import com.yishi.mall.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "systemFilter", urlPatterns = {"/**"})
@Slf4j
public class SystemFilter implements Filter {
    @Resource
    private UserService userService;
    @Value("${web.user.timeout}")
    private long timeOut;

    @Override
    public void init(FilterConfig config) throws ServletException {
        log.info("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
//        if (uri.startsWith("/admin")) {//排除的url
//            chain.doFilter(request, response);
//            return;
//        }
        LocalDateTime now = LocalDateTime.now();
        String token = request.getHeader("token");
        Map<String, Object> trace = new HashMap<>();
        trace.put("time", now);
        trace.put("uri", uri);
        trace.put("token", token);
        trace.put("params", request.getParameterMap());
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        trace.put("ip", ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip);
        log.info("system request:{}", JSON.toJSONString(trace));
        if (!StringUtils.isBlank(token)) {
            // TODO: 2020/5/15  根据token获取用户信息
            UserInfo user=new UserInfo();
            if (user != null) {
                if (user.getState() == 9) {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json; charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.print(JSON.toJSONString(Result.fail("user fail")));
                    out.flush();
                    out.close();
                    return;
                }
                request.setAttribute("user", user);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }

}
