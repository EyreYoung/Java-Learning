package com.seu.prototype;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Company")
public class Company implements Cloneable {
    private String name;
    private String address;

    public Company(){
        log.debug("Company 构造函数");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public Company clone(){
        Company company = null;
        try {
            company = (Company) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return company;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
