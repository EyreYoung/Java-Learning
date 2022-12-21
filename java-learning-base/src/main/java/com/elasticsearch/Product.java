package com.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String id;

    private String name;

    private int price;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static void main(String[] args) {
        String s = new Product("1", "yyd", 1).toString();
        System.out.println(s);
    }

}
