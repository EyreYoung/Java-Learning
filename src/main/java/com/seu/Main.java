package com.seu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Block;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "Main")
public class Main {

    public static void main(String[] args) {
        System.out.println(foo(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
    }

    public static int foo(int[] gas, int[] cost) {
        int gasSum = 0, costSum = 0;

        int len = gas.length;
        int v = 0;
        int start = 0;
        for (int i = 0; i < len; i++) {
            gasSum += gas[i];
            costSum += cost[i];
            v += gas[i];
            v -= cost[i];
            if (v < 0) {
                start = i + 1;
                v = 0;
            }
        }
        if(gasSum - costSum < 0) return -1;
        return start;
    }

}
