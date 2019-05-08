package com.lizhi.demo.service.impl;

import com.lizhi.demo.domain.Goods;
import com.lizhi.demo.repository.GoodsRepository;
import com.lizhi.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lenovo
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public List<Goods> queryGoods() {
        return goodsRepository.findAll();
    }

    @Override
    public Goods queryGoodById(Long id) {
        return goodsRepository.findOne(id);
    }

    @Override
    public Goods saveGoods(Goods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public void deleteGoods(Long id) {
        goodsRepository.delete(id);
    }
}
