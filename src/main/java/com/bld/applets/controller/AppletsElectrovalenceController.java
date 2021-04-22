package com.bld.applets.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bld.applets.domain.AjaxResult;
import com.bld.applets.domain.AppletsPiles;
import com.bld.applets.domain.DTO.BatchAddPriceDTO;
import com.bld.applets.domain.DTO.ChargingPriceDTO;
import com.bld.applets.domain.DTO.ElectrovalenceDTO;
import com.bld.applets.utils.CommonUtils;
import com.google.gson.Gson;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bld.applets.domain.AppletsElectrovalence;
import com.bld.applets.service.IAppletsElectrovalenceService;

/**
 * 充电桩电价Controller
 * @author tyx
 * @date 2021-03-03
 */
@Controller
@RequestMapping("/applets/electrovalence")
public class AppletsElectrovalenceController extends BaseController
{

    @Autowired
    private IAppletsElectrovalenceService appletsElectrovalenceService;

    /**
     * 查询充电桩电价列表
     */
    @GetMapping("/list")
    @ResponseBody
    public Object list(AppletsElectrovalence appletsElectrovalence)
    {
        List<AppletsElectrovalence> list = appletsElectrovalenceService.selectAppletsElectrovalenceList(appletsElectrovalence.setUserId(CommonUtils.getUserId()));
        // 根据充电桩分组
        Map<AppletsPiles, List<AppletsElectrovalence>> result = list.stream().collect(Collectors.groupingBy(AppletsElectrovalence::getPiles));

        ArrayList<Object> arrayData = new ArrayList<>();

        result.forEach((k, v) -> {
            arrayData.add(new ElectrovalenceDTO()
                    .setPid(k.getId())
                    .setName(k.getName())
                    .setAddr(k.getAddrProvince() + k.getAddrCity() + k.getAddrDistrict() + k.getAddrDetail())
                    .setPriceArray(v.stream().map(e -> {
                        Integer timeFrame = e.getTimeFrame();
                        return new ChargingPriceDTO()
                                .setId(e.getId())
                                .setPrice(e.getPrice())
                                .setStartTime(e.getStartTime())
                                .setEndTime(e.getEndTime())
                                .setInterval(e.getTimeFrame());
                    }).collect(Collectors.toList())));
        });

        return arrayData;
    }

    /**
     * 新增保存充电桩电价
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody BatchAddPriceDTO batchAddPriceDTO)
    {
        List<ChargingPriceDTO> list = batchAddPriceDTO.getList();
        ArrayList<AppletsElectrovalence> arrayList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(e -> {
                AppletsElectrovalence appletsElectrovalence = new AppletsElectrovalence();
                appletsElectrovalence.setUpdateTime(new Date());
                appletsElectrovalence.setCreateTime(new Date());
                appletsElectrovalence.setStartTime(e.getStartTime())
                        .setEndTime(e.getEndTime())
                        .setUserId(CommonUtils.getUserId())
                        .setPrice(e.getPrice())
                        .setTimeFrame(e.getInterval())
                        .setType(batchAddPriceDTO.getType())
                        .setPilesId(batchAddPriceDTO.getPilesId());
                arrayList.add(appletsElectrovalence);
            });
        }
        if (arrayList.size() > 0) {
            return toAjax(appletsElectrovalenceService.batchInsertAppletsElectrovalence(arrayList));
        }
        return AjaxResult.error();
    }

    /**
     * 修改保存充电桩电价
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppletsElectrovalence appletsElectrovalence)
    {
        appletsElectrovalence.setUpdateTime(new Date());
        return toAjax(appletsElectrovalenceService.updateAppletsElectrovalence(appletsElectrovalence));
    }

    /**
     * 删除充电桩电价
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            if (ids.startsWith("all")) {
                String pid = ids.substring(3);
                if (StringUtils.isNotEmpty(pid)) {
                    return toAjax(appletsElectrovalenceService.deleteAllElectrovalenceByPilesId(Long.parseLong(pid)));
                }
            }
            return toAjax(appletsElectrovalenceService.deleteAppletsElectrovalenceByIds(ids));
        }
        return AjaxResult.error();
    }


}
