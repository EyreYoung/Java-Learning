package com.ke.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class singletonMap {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name2", "lianjia");
        Map<String, String> singletonMap = Collections.singletonMap("name", "ke");
        singletonMap.putAll(map);
        System.out.println("singletonMap = " + singletonMap);
    }
}
