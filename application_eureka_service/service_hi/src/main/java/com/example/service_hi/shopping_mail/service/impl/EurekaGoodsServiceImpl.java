package com.example.service_hi.shopping_mail.service.impl;

import com.example.service_hi.shopping_mail.dao.EurekaGoodsDao;
import com.example.service_hi.shopping_mail.entity.EurekaGoods;
import com.example.service_hi.shopping_mail.service.EurekaGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (EurekaGoods)表服务实现类
 *
 * @author makejava
 * @since 2021-02-05 13:49:15
 */
@Service("eurekaGoodsService")
public class EurekaGoodsServiceImpl implements EurekaGoodsService {
    @Resource
    private EurekaGoodsDao eurekaGoodsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public EurekaGoods queryById(String id) {
        return this.eurekaGoodsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<EurekaGoods> queryAllByLimit(int offset, int limit) {
        return this.eurekaGoodsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param eurekaGoods 实例对象
     * @return 实例对象
     */
    @Override
    public EurekaGoods insert(EurekaGoods eurekaGoods) {
        this.eurekaGoodsDao.insert(eurekaGoods);
        return eurekaGoods;
    }

    /**
     * 修改数据
     *
     * @param eurekaGoods 实例对象
     * @return 实例对象
     */
    @Override
    public EurekaGoods update(EurekaGoods eurekaGoods) {
        this.eurekaGoodsDao.update(eurekaGoods);
        return this.queryById(eurekaGoods.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.eurekaGoodsDao.deleteById(id) > 0;
    }

    @Override
    public List<EurekaGoods> selectGoodMax(int number) {
        List<EurekaGoods> numbers = this.eurekaGoodsDao.selectGoodMax(number);
        System.out.println(numbers);
        return this.eurekaGoodsDao.selectGoodMax(number);
    }
}