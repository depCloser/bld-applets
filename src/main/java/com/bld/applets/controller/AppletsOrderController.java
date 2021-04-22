package com.bld.applets.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bld.applets.domain.AjaxResult;
import com.bld.applets.domain.AppletsPiles;
import com.bld.applets.domain.DTO.ProfitDetailDTO;
import com.bld.applets.domain.DTO.UserProfitDTO;
import com.bld.applets.domain.TableDataInfo;
import com.bld.applets.service.IAppletsPilesService;
import com.bld.applets.service.IAppletsUserService;
import com.bld.applets.utils.CommonUtils;
import com.bld.applets.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bld.applets.domain.AppletsOrder;
import com.bld.applets.service.IAppletsOrderService;

import javax.naming.ldap.Rdn;

/**
 * 订单Controller
 * @author tyx
 * @date 2021-03-03
 */
@Controller
@RequestMapping("/applets/order")
public class AppletsOrderController extends BaseController
{

    @Autowired
    private IAppletsOrderService appletsOrderService;

    @Autowired
    private IAppletsPilesService pilesService;

    @Autowired
    private IAppletsUserService userService;

    /**
     * 查询订单列表
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(AppletsOrder appletsOrder)
    {
        startPage();
        List<AppletsOrder> list = appletsOrderService.selectAppletsOrderList(appletsOrder.setUserId(CommonUtils.getUserId()));
        return getDataTable(list);
    }

    /**
     * 查询订单列表
     */
    @GetMapping("/get")
    @ResponseBody
    public AjaxResult list(Long id)
    {
        AppletsOrder appletsOrder = appletsOrderService.selectAppletsOrderById(id);
        if (appletsOrder != null) {
            return AjaxResult.success(appletsOrder);
        }
        return AjaxResult.error("未查询到订单");
    }

    /**
     * 新增保存订单
     * 这里区分订单类型（充电订单和充值订单）
     * 目前这个接口统一充电订单
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppletsOrder appletsOrder)
    {
        Long pilesId = appletsOrder.getPilesId();
        if (pilesId == null) {
            return AjaxResult.error("充电桩id不能为空");
        }
        appletsOrder.setCode(ServiceUtil.generateOrderCode())
                .setUserId(CommonUtils.getUserId())
                .setUserName(CommonUtils.getUser().getName())
                .setStatus(1)
                .setType(1)
                .setStartTime(new Date()) // 开始充电计时
                .setCreateTime(new Date());
        appletsOrder.setUpdateTime(new Date());
        int i = appletsOrderService.insertAppletsOrder(appletsOrder);
        // 订单创建成功
        if (i > 0) {
            // 更新充电桩为正在充电状态
            pilesService.updateAppletsPiles(new AppletsPiles().setId(pilesId).setChargedState(1l));
            return AjaxResult.success(appletsOrder.getId());
        }
        return toAjax(i);
    }

    /*
     * @Author: tyx
     * @Description: 开始/结束充电
     * @Param: [orderId, type]->[订单号，类型：1开始充电， 0结束充电]
     * @return: com.ruoyi.common.core.domain.AjaxResult
     * @Date: 2021/3/3
     */
    @PostMapping("/recharge")
    @ResponseBody
    public AjaxResult recharge(Long orderId, Integer type)
    {
        AppletsOrder order = appletsOrderService.selectAppletsOrderById(orderId);
        if (order == null) {
            return AjaxResult.error("该订单不存在");
        }
        AppletsPiles piles = pilesService.selectAppletsPilesById(order.getPilesId());
        // 充电桩状态 0维护、1正常
        Long status = piles.getStatus();
        if (0 == status) {
            return AjaxResult.error("充电桩维护中");
        }
        // 充电桩充电状态
        Long chargedState = piles.getChargedState();
        // 开始充电
        if (1 == type) {
            if (chargedState != 0) {
                return AjaxResult.error("该充电桩正在充电");
            }
            order.setStartTime(new Date());
            // 修改对应充电桩充电状态
            pilesService.updateAppletsPiles(piles.setChargedState(1l));
        } else { // 结束充电
            if (chargedState != 1) {
                return AjaxResult.error("充电已结束");
            }
            order.setEndTime(new Date())
                    // 计算充电量
                    .setCharge(ServiceUtil.getCharge(order))
                    // 计算费用
                    .setCost(ServiceUtil.getCost(order))
                    // 计算积分
                    .setIntegral(ServiceUtil.getIntegral(order));
            // 修改对应充电桩充电状态
            pilesService.updateAppletsPiles(piles.setChargedState(0l));
        }
        int i = appletsOrderService.updateAppletsOrder(order);
        if (i > 0) {
            return AjaxResult.success(order);
        }
        return toAjax(i);
    }

    /**
     * 修改保存订单
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsOrder appletsOrder)
    {
        return toAjax(appletsOrderService.updateAppletsOrder(appletsOrder));
    }

    /**
     * 删除订单
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appletsOrderService.deleteAppletsOrderByIds(ids));
    }

    /**
     * 充电量更新
     */
    @GetMapping("/updateCharge")
    @ResponseBody
    public AjaxResult remove(AppletsOrder order)
    {
        AppletsOrder appletsOrder = new AppletsOrder();
        List<AppletsOrder> appletsOrders = appletsOrderService.selectAppletsOrderList(order);
        if (CollectionUtils.isEmpty(appletsOrders)) {
           return AjaxResult.error("未查询到订单信息");
        }
        return AjaxResult.success(ServiceUtil.getCharge(appletsOrders.get(0)));
    }

    // 收益查询，统计用户所属充电桩下，非本人产生的已完成、充电型订单
    @GetMapping("queryProfit")
    @ResponseBody
    public AjaxResult queryProfit () {
        // 查询所有订单
        List<AppletsOrder> appletsOrders = appletsOrderService.queryProfit(CommonUtils.getUserId());
        ArrayList<UserProfitDTO> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(appletsOrders)) {
            // 分组
            Map<Long, List<AppletsOrder>> collect = appletsOrders.stream().collect(Collectors.groupingBy(AppletsOrder::getPilesId));
            collect.forEach((k, v) -> {
                AppletsPiles piles = pilesService.selectAppletsPilesById(k);
                // 求和
                Optional<String> reduce = v.stream().map(AppletsOrder::getCost).reduce((acc, item) -> {
                    return new BigDecimal(acc).add(new BigDecimal(item)).toString();
                });
                result.add(new UserProfitDTO()
                        .setTotalAmount(reduce.isPresent() ? reduce.get() : "0")
                        .setPilesId(k)
                        .setPilesCode(piles.getCode())
                        .setOrders(v));
            });
        }
        return AjaxResult.success(result);
    }

}
