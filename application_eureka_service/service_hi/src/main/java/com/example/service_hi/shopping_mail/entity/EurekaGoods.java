package com.example.service_hi.shopping_mail.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (EurekaGoods)实体类
 *
 * @author makejava
 * @since 2021-02-05 13:39:12
 */
public class EurekaGoods implements Serializable {
    private static final long serialVersionUID = -64350031545081728L;
    /**
     * id
     */
    private String id;
    /**
     * 商品图片路径
     */
    private String goodsImg;
    /**
     * 商品名
     */
    private String goodsName;
    /**
     * 商品详情
     */
    private String goodsDetial;
    /**
     * 商品价格
     */
    private Double goodsPrice;
    /**
     * 销量
     */
    private Long goodsSalesVolume;
    /**
     * 商品创建时间
     */
    private Date goodCreateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDetial() {
        return goodsDetial;
    }

    public void setGoodsDetial(String goodsDetial) {
        this.goodsDetial = goodsDetial;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Long getGoodsSalesVolume() {
        return goodsSalesVolume;
    }

    public void setGoodsSalesVolume(Long goodsSalesVolume) {
        this.goodsSalesVolume = goodsSalesVolume;
    }

    public Date getGoodCreateTime() {
        return goodCreateTime;
    }

    public void setGoodCreateTime(Date goodCreateTime) {
        this.goodCreateTime = goodCreateTime;
    }

}