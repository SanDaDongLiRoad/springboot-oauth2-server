package com.lizhi.demo.enums;

import lombok.Getter;

/**
 * @author lenovo
 */

@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数不正确"),
    ERROR(-1,"未知错误"),
    GOOD_NOT_EXIST(10, "商品不存在"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDER_STATUS_ERROR(14, "订单状态不正确"),
    ORDER_UPDATE_FAIL(15, "订单更新失败"),
    ORDER_DETAIL_EMPTY(16, "订单详情为空"),
    WECHAT_MP_ERROR(20, "微信公众账号方面错误"),
    ORDER_CANCEL_SUCCESS(22, "订单取消成功"),
    ORDER_FINISH_SUCCESS(23, "订单完结成功"),
    GOOD_STATUS_ERROR(24, "商品状态不正确");

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
