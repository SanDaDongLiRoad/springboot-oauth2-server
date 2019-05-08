package com.lizhi.demo.service;

import com.lizhi.demo.domain.Goods;

import java.util.List;

/**
 * @author lenovo
 */
public interface GoodsService {

    /**
     * 查询商品列表
     * @return
     */
    List<Goods> queryGoods();

    /**
     * 根据商品ID查询商品
     * @param id
     * @return
     */
    Goods queryGoodById(Long id);

    /**
     * 保存商品
     * @param goods
     * @return
     */
    Goods saveGoods(Goods goods);

    /**
     * 删除商品
     * @param id
     */
    void deleteGoods(Long id);
}
