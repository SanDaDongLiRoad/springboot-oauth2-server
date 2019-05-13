package com.lizhi.demo.controller;

import com.lizhi.demo.annotation.Classification;
import com.lizhi.demo.annotation.MethodClassification;
import com.lizhi.demo.domain.Goods;
import com.lizhi.demo.dto.CustomUserDetails;
import com.lizhi.demo.service.GoodsService;
import com.lizhi.demo.utils.Result;
import com.lizhi.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lenovo
 */
@Classification
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询商品列表
     * @return
     */
    @GetMapping("queryGoods")
    @MethodClassification(BUSTYPE = MethodClassification.TYPE.DX)
    public Result queryGoods(){
        List<Goods> goodsList = goodsService.queryGoods();
        return ResultUtil.success(goodsList);
    }

    /**
     * 根据商品ID查询商品
     * @param id
     * @return
     */
    @GetMapping("queryGoodById")
    public Result queryGoodById(@RequestParam Long id){
        Goods goods = goodsService.queryGoodById(id);
        return ResultUtil.success(goods);
    }

    /**
     * 保存商品
     * @param goods
     * @return
     */
    @PostMapping("saveGoods")
    public Result saveGoods(@RequestBody Goods goods){
        Goods saveGoods = goodsService.saveGoods(goods);
        return ResultUtil.success(saveGoods);
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @GetMapping("deleteGoods")
    public Result deleteGoods(@RequestParam Long id){
        goodsService.deleteGoods(id);
        return ResultUtil.success();
    }

}
