package com.lizhi.demo.repository;

import com.lizhi.demo.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lenovo
 */
public interface GoodsRepository extends JpaRepository<Goods,Long>{
}
