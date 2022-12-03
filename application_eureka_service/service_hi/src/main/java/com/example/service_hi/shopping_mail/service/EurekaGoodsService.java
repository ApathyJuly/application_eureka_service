package com.example.service_hi.shopping_mail.service;

import com.example.service_hi.shopping_mail.entity.EurekaGoods;

import java.util.List;

/**
 * (EurekaGoods)表服务接口
 *
 * @author makejava
 * @since 2021-02-05 13:49:15
 */
public interface EurekaGoodsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    EurekaGoods queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<EurekaGoods> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param eurekaGoods 实例对象
     * @return 实例对象
     */
    EurekaGoods insert(EurekaGoods eurekaGoods);

    /**
     * 修改数据
     *
     * @param eurekaGoods 实例对象
     * @return 实例对象
     */
    EurekaGoods update(EurekaGoods eurekaGoods);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    List<EurekaGoods> selectGoodMax(int number);
}