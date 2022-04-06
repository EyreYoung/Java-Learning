package com.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/8/27 7:01 下午
 */
public class TestJD {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        int i = 1;
        int j = 0;
        int[] down = new int[7];
        while(c > 0) {
            i *= 3;
            c -= i;
            j++;
        }
        int howmuch = j;
        int no = c+i-1;
        List<Integer> list = new ArrayList<>();
        if (no == 0) list.add(0);
        while(no!=0){
            int remainder=no%3;
            no=no/3;
            list.add(0, remainder);
        }
        int p = list.size();
        for (int k = 0; k < howmuch-p; k++) {
            list.add(0, 0);
        }

        System.out.println(list);
        int ret = 0;
        for(int k = 0; k < howmuch;k++){
            int now;
            int fake = list.get(k);
            if(fake == 0) now = 2;
            else if(fake == 1) now = 3;
            else if(fake == 2) now = 5;
            else now = 1;
            ret+=now*Math.pow(10, howmuch-k-1);
        }
        System.out.println(ret);
    }
}

class A {

}

class B extends A {

}
