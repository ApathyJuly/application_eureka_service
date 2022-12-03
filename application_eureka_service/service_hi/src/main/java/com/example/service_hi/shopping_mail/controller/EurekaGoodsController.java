package com.example.service_hi.shopping_mail.controller;

import com.example.service_hi.shopping_mail.entity.EurekaGoods;
import com.example.service_hi.shopping_mail.service.EurekaGoodsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (EurekaGoods)表控制层
 *
 * @author Apathy
 * @since 2021-02-05 13:49:15
 */
@RestController
@RequestMapping("/eurekaGoods")
public class EurekaGoodsController {
    /**
     * 服务对象
     */
    @Resource
    private EurekaGoodsService eurekaGoodsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public EurekaGoods selectOne(String id) {
        return this.eurekaGoodsService.queryById(id);
    }

    /**
     * 查询销售排行前number的商品
     * @return
     */
    @GetMapping("selectGoodMax")
    public List<EurekaGoods> selectGoodMax(){
        int number = 3;
        return this.eurekaGoodsService.selectGoodMax(number);
    }
    @GetMapping("getGoodsAllData")
    public Object getGoodsAllData(String id){

        return null;
    }
}