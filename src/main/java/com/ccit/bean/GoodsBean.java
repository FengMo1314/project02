package com.ccit.bean;

import java.util.Date;

public class GoodsBean {
    private int goods_id;
    private String goods_name;
    private String goods_intro;
    private int goods_category_id;
    private String goods_cover_img;
    private String goods_carousel;
    private String goods_detail_content;
    private int original_price;
    private int selling_price;
    private int stock_num;
    private String tag;
    private int goods_sell_status;
    private int create_user;
    private Date create_time;
    private int update_user;
    private Date update_time;

    public GoodsBean(int goods_id, String goods_name, String goods_intro, int goods_category_id, String goods_cover_img, String goods_carousel, String goods_detail_content, int original_price, int selling_price, int stock_num, String tag, int goods_sell_status, int create_user, Date create_time, int update_user, Date update_time) {
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.goods_intro = goods_intro;
        this.goods_category_id = goods_category_id;
        this.goods_cover_img = goods_cover_img;
        this.goods_carousel = goods_carousel;
        this.goods_detail_content = goods_detail_content;
        this.original_price = original_price;
        this.selling_price = selling_price;
        this.stock_num = stock_num;
        this.tag = tag;
        this.goods_sell_status = goods_sell_status;
        this.create_user = create_user;
        this.create_time = create_time;
        this.update_user = update_user;
        this.update_time = update_time;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_intro() {
        return goods_intro;
    }

    public void setGoods_intro(String goods_intro) {
        this.goods_intro = goods_intro;
    }

    public int getGoods_category_id() {
        return goods_category_id;
    }

    public void setGoods_category_id(int goods_category_id) {
        this.goods_category_id = goods_category_id;
    }

    public String getGoods_cover_img() {
        return goods_cover_img;
    }

    public void setGoods_cover_img(String goods_cover_img) {
        this.goods_cover_img = goods_cover_img;
    }

    public String getGoods_carousel() {
        return goods_carousel;
    }

    public void setGoods_carousel(String goods_carousel) {
        this.goods_carousel = goods_carousel;
    }

    public String getGoods_detail_content() {
        return goods_detail_content;
    }

    public void setGoods_detail_content(String goods_detail_content) {
        this.goods_detail_content = goods_detail_content;
    }

    public int getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(int original_price) {
        this.original_price = original_price;
    }

    public int getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(int selling_price) {
        this.selling_price = selling_price;
    }

    public int getStock_num() {
        return stock_num;
    }

    public void setStock_num(int stock_num) {
        this.stock_num = stock_num;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getGoods_sell_status() {
        return goods_sell_status;
    }

    public void setGoods_sell_status(int goods_sell_status) {
        this.goods_sell_status = goods_sell_status;
    }

    public int getCreate_user() {
        return create_user;
    }

    public void setCreate_user(int create_user) {
        this.create_user = create_user;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(int update_user) {
        this.update_user = update_user;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "goods_id=" + goods_id +
                ", goods_name='" + goods_name + '\'' +
                ", goods_intro='" + goods_intro + '\'' +
                ", goods_category_id=" + goods_category_id +
                ", goods_cover_img='" + goods_cover_img + '\'' +
                ", goods_carousel='" + goods_carousel + '\'' +
                ", goods_detail_content='" + goods_detail_content + '\'' +
                ", original_price=" + original_price +
                ", selling_price=" + selling_price +
                ", stock_num=" + stock_num +
                ", tag='" + tag + '\'' +
                ", goods_sell_status=" + goods_sell_status +
                ", create_user=" + create_user +
                ", create_time=" + create_time +
                ", update_user=" + update_user +
                ", update_time=" + update_time +
                '}';
    }
}
