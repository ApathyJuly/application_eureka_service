package com.example.service_hi.shopping_mail.dao;

import com.example.service_hi.shopping_mail.entity.EurekaGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (EurekaGoods)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-05 13:38:35
 */
@Mapper
@Repository(value="EurekaGoodsDao")
public interface EurekaGoodsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    EurekaGoods queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<EurekaGoods> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param eurekaGoods 实例对象
     * @return 对象列表
     */
    List<EurekaGoods> queryAll(EurekaGoods eurekaGoods);

    /**
     * 新增数据
     *
     * @param eurekaGoods 实例对象
     * @return 影响行数
     */
    int insert(EurekaGoods eurekaGoods);

    /**
     * 修改数据
     *
     * @param eurekaGoods 实例对象
     * @return 影响行数
     */
    int update(EurekaGoods eurekaGoods);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    @Select("SELECT * FROM eureka_goods  ORDER BY goods_sales_volume DESC LIMIT 0,#{number};")
    List<EurekaGoods> selectGoodMax(int number);
}