package com.seu.prototype;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "简单原型模式测试")
public class BusinessCard implements Cloneable {
    // clone()方法并不是Cloneable接口中的，而是Object类中定义的，这里实现Cloneable方法只为了标示这个类可拷贝
    private String name;
    private String company;

    public BusinessCard() {
        log.debug("BusinessCard 构造函数");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public BusinessCard clone(){
        BusinessCard businessCard = null;
        try {
            businessCard = (BusinessCard) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return businessCard;
    }

    @Override
    public String toString() {
        return "BusinessCard{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                '}';
    }

    public static void main(String[] args) {
        BusinessCard businessCard = new BusinessCard();
        businessCard.setName("yyd");
        businessCard.setCompany("SEU");
        BusinessCard businessCard1 = businessCard.clone();
        businessCard1.setName("lc");
        BusinessCard businessCard2 = businessCard.clone();
        businessCard2.setName("Huaian");
        log.debug(businessCard.toString());
        log.debug(businessCard1.toString());
        log.debug(businessCard2.toString());
    }
}
