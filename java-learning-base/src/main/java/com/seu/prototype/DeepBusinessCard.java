package com.seu.prototype;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "深拷贝原型模式测试")
public class DeepBusinessCard implements Cloneable {
    private String name;
    private Company company = new Company();

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String name, String address) {
        this.company.setName(name);
        this.company.setAddress(address);
    }

    @Override
    protected DeepBusinessCard clone() {
        DeepBusinessCard deepBusinessCard = null;
        try {
            deepBusinessCard = (DeepBusinessCard) super.clone();
            deepBusinessCard.company = this.company.clone();
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return deepBusinessCard;
    }

    @Override
    public String toString() {
        return "DeepBussinessCard{" +
                "name='" + name + '\'' +
                ", company=" + company +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        DeepBusinessCard businessCard=new DeepBusinessCard();
        businessCard.setName("钱三");
        businessCard.setCompany("阿里","北京望京");

        DeepBusinessCard cloneCard1=businessCard.clone();
        cloneCard1.setName("赵四");
        cloneCard1.setCompany("百度","北京西二旗");

        DeepBusinessCard cloneCard2=businessCard.clone();
        cloneCard2.setName("孙五");
        cloneCard2.setCompany("腾讯","北京中关村");

        log.debug(businessCard.toString());
        log.debug(cloneCard1.toString());
        log.debug(cloneCard2.toString());

    }
}
