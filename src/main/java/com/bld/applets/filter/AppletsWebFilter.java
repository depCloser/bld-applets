package com.bld.applets.filter;

import com.bld.applets.domain.AjaxResult;
import com.bld.applets.domain.AppletsUser;
import com.bld.applets.service.IAppletsUserService;
import com.bld.applets.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author tyx
 * @title: webFilter
 * @projectName applets
 * @description: TODO
 * @date 2021/3/3 16:16
 */
@WebFilter(urlPatterns = "/*")
public class AppletsWebFilter implements Filter {

    @Autowired
    private IAppletsUserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 设置字符集、请求头
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
//        String markType = request.getHeader("markType");
        String servletPath = request.getServletPath();
//        // mac校验
//        // post请求参数不能为空
//        String method = request.getMethod();
//        if ("POST".equals(method)) {
//            // 除mac外没有其余参数
//            if (request.getParameterMap().size() < 1) {
//                AjaxResult error = AjaxResult.parametersMissing();
//                PrintWriter writer = response.getWriter();
//                writer.print(error);
//                writer.flush();
//                writer.close();
//                return;
//            }
//        }
        // 需要放行的请求
        if ("/applets/user/signIn".equals(servletPath) || "/applets/user/signUp".equals(servletPath)
            || "/applets/wx/callBack".equals(servletPath)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 获取session缓存
        AppletsUser user = CommonUtils.getUser();
        if (user == null) {
            returnErrorMsg(response, "未登录！");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private void returnErrorMsg (HttpServletResponse response, String message) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(AjaxResult.error(message));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}
