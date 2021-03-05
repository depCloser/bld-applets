package com.bld.applets.filter;

import com.bld.applets.domain.AppletsUser;
import com.bld.applets.service.IAppletsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author tyx
 * @title: webFilter
 * @projectName applets
 * @description: TODO
 * @date 2021/3/3 16:16
 */
@javax.servlet.annotation.WebFilter
public class AppletsWebFilter implements Filter {

    @Autowired
    private IAppletsUserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String code = request.getParameter("code");
        AppletsUser appletsUser = new AppletsUser().setCode(code);
        List<AppletsUser> appletsUsers = userService.selectAppletsUserList(appletsUser);

        if (CollectionUtils.isEmpty(appletsUsers)) {
            userService.insertAppletsUser(appletsUser);
        } else {
            appletsUser = appletsUsers.get(0);
        }

        request.getSession().setAttribute("user", appletsUser);
        filterChain.doFilter(request, response);
    }

}
