package com.lizhi.demo.utils;

import com.lizhi.demo.enums.ResultEnum;

/**
 * @author lenovo
 */
public class ResultUtil {

    public static Result success(Object data){

        Result result = new Result(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),data);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code, String msg){
        Result result = new Result(code,msg,null);
        return result;
    }
}
