package com.bld.applets.controller;

import com.bld.applets.domain.AjaxResult;
import com.bld.applets.domain.AjaxResult.Type;
import com.bld.applets.domain.PageDomain;
import com.bld.applets.domain.TableDataInfo;
import com.bld.applets.utils.CommonUtils;
import com.bld.applets.utils.Convert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * web层通用数据处理
 * @author ruoyi
 */
public class BaseController
{

    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        HttpServletRequest request = CommonUtils.getRequest();
        PageHelper.startPage(Convert.toInt(request.getParameter("pageNum"), 1),
                Convert.toInt(request.getParameter("pageSize"), 10),
                Convert.toStr(request.getParameter("orderBy"), "desc"));
    }

    /**
     * 设置请求分页数据
     */
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     * 
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    /**
     * 响应返回结果
     * 
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result)
    {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public AjaxResult success()
    {
        return AjaxResult.success();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error()
    {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message)
    {
        return AjaxResult.success(message);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message)
    {
        return AjaxResult.error(message);
    }

    /**
     * 返回错误码消息
     */
    public AjaxResult error(Type type, String message)
    {
        return new AjaxResult(type, message);
    }

}
