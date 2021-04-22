package com.bld.applets.domain.DTO;

/**
 * @author tyx
 * @title: UserInfoDTO
 * @projectName applets
 * @description: TODO
 * @date 2021/4/19 11:58
 */
public class UserInfoDTO {

    /** $column.columnComment */
    private Long id;

    /** 名称 */
    private String name;

    /** 电话号码 */
    private String phone;

    /** 会员等级 */
    private Integer level;

    /** 是否拥有充电桩：1是、0否 */
    private Integer owner;

    /** 积分 */
    private Long integral;

    /** 余额 */
    private String balance;

    /** 头像地址 */
    private String photo;

    /** 性别 0女 1男 **/
    private Integer sex;

    private Long monthCharge;

    private Long memberDifference;

}
