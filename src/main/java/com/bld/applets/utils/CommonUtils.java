package com.bld.applets.utils;

import com.bld.applets.domain.AppletsUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author tyx
 * @title: commonUtil
 * @projectName energy
 * @description: 通用工具集合
 * @date 2021/2/24
 */
public class CommonUtils {

    public static ServletRequestAttributes getServletRequestAttributes () {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static HttpServletRequest getRequest () {
        return getServletRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse () {
        return getServletRequestAttributes().getResponse();
    }

    /*
     * @Author: tyx
     * @Description: 从session获取登录用户信息  
     * @Param: []
     * @return: com.bld.applets.domain.AppletsUser
     * @Date: 2021/3/3
     */
    public static AppletsUser getUser () {
        HttpSession session = getRequest().getSession();
        Object user = session.getAttribute("user");
        if (user != null) {
            return (AppletsUser) user;
        }
        return null;
    }
    
    /*
     * @Author: tyx
     * @Description: 获取用户id
     * @Param: []
     * @return: java.lang.Long
     * @Date: 2021/3/3
     */
    public static Long getUserId () {
        AppletsUser user = getUser();
        if (user == null) {
            return 0l;
        } else {
            return user.getId();
        }
    }

    public static String[] toStrArray (String str) {
        return str.split(",");
    }

}
