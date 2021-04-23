package com.bld.applets.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bld.applets.domain.AjaxResult;
import com.bld.applets.domain.AppletsPower;
import com.bld.applets.domain.AppletsUser;
import com.bld.applets.domain.DTO.QueryPilesDTO;
import com.bld.applets.domain.TableDataInfo;
import com.bld.applets.service.IAppletsPowerService;
import com.bld.applets.service.IAppletsUserService;
import com.bld.applets.utils.BeanUtils;
import com.bld.applets.utils.CommonUtils;
import com.bld.applets.utils.ServiceUtil;
import org.apache.commons.lang3.StringUtils;
import org.gavaghan.geodesy.Ellipsoid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bld.applets.domain.AppletsPiles;
import com.bld.applets.service.IAppletsPilesService;

/**
 * 充电桩Controller
 * @author tyx
 * @date 2021-03-03
 */
@Controller
@RequestMapping("/applets/piles")
public class AppletsPilesController extends BaseController
{

    @Autowired
    private IAppletsPilesService appletsPilesService;

    @Autowired
    private IAppletsPowerService powerService;

    @Autowired
    private IAppletsUserService userService;

    /**
     * @Author: tyx
     * @Description: 查询充电桩列表
     * @Param: [appletsPiles, sourceJ（客户端经度）, sourceW（客户端纬度）, distance（距离 km）]
     * @return: com.bld.applets.domain.TableDataInfo
     * @Date: 2021/4/22
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(QueryPilesDTO queryPilesDTO) {
        startPage();
        AppletsPiles piles = BeanUtils.queryDTO2Piles(queryPilesDTO);
        if (queryPilesDTO.getUserId() == null) {
            piles.setUserId(CommonUtils.getUserId());
        }
        List<AppletsPiles> list = appletsPilesService.selectAppletsPilesList(piles);
        Double distance = queryPilesDTO.getDistance();
        String sourceJ = queryPilesDTO.getSourceJ();
        String sourceW = queryPilesDTO.getSourceW();
        // 距离筛选
        if (distance != null && distance > 0 && StringUtils.isNotEmpty(sourceJ) && StringUtils.isNotEmpty(sourceW)) {
            list = list.stream().filter(e -> {
                double temp = ServiceUtil.getDistance(sourceJ, sourceW, e.getLongitude(), e.getLatitude(), Ellipsoid.WGS84);
                if ((temp / 1000) <= distance) {
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
        }
        return getDataTable(list);
    }

    /**
     * 新增保存充电桩
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsPiles appletsPiles)
    {
        appletsPiles.setCreateTime(new Date()).setUpdateTime(new Date());

        int i = appletsPilesService.insertAppletsPiles(appletsPiles.setUserId(CommonUtils.getUserId()));
        // 成功
        if (i > 0) {
            AppletsUser user = CommonUtils.getUser();
            Integer owner = user.getOwner();
            if (owner != 1) {
                // 更新所有者状态
                userService.updateAppletsUser(user.setOwner(1));
            }
            return AjaxResult.success(appletsPiles.getId());
        }
        return toAjax(i);
    }

    /**
     * 修改保存充电桩
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsPiles appletsPiles)
    {
        // 0可以充电、1正在充电、2重启
        Long chargedState = appletsPiles.getChargedState();
        if (chargedState != null && 2 == chargedState) {
            // 重启充电桩
            // 异步请求
            // 重启完成回调此接口修正状态
        }
        // 充电桩状态 0维护、1正常
        Long status = appletsPiles.getStatus();
        if (status != null && 0 == status) {
            // 维护中的充电桩无法使用
        }
        appletsPiles.setUpdateTime(new Date());
        return toAjax(appletsPilesService.updateAppletsPiles(appletsPiles));
    }

    /**
     * 删除充电桩
     */
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        int i = appletsPilesService.deleteAppletsPilesByIds(ids);
        if (i > 0) {
            // 统计充电桩
            List<AppletsPiles> piles = appletsPilesService.selectAppletsPilesList(new AppletsPiles().setUserId(CommonUtils.getUserId()));
            if (piles.isEmpty()) {
                AppletsUser user = CommonUtils.getUser();
                if (user.getOwner() != 0) {
                    // 更新所有者状态
                    userService.updateAppletsUser(user.setOwner(0));
                }
            }
        }
        return toAjax(appletsPilesService.deleteAppletsPilesByIds(ids));
    }

    @GetMapping("/queryDevice")
    @ResponseBody
    public AjaxResult queryDevice (AppletsPiles appletsPiles) {
        List<AppletsPiles> piles = appletsPilesService.selectAppletsPilesList(appletsPiles);
        if (piles.isEmpty()) {
            return AjaxResult.error("未查询到设备");
        }
        AppletsPiles result = piles.get(0);
        AppletsPower lastRecord = powerService.getLastRecord(result.getId());
        if (lastRecord == null) {
            result.setPower("0");
        } else {
            result.setPower(lastRecord.getPower());
        }
        return AjaxResult.success(result);
    }

}
