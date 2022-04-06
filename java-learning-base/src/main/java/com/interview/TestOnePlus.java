package com.interview;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/9/24 9:27 上午
 */
public class TestOnePlus {
    String str = new String("onePlus");
    char[] crs = {'3', 'T'};
    public void fun(String str, char[] crs){
        str = "never";
        crs[0]='6';
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test2() {
        double lucheng = -100;
        double height = 100;
        for(int i = 0; i < 10; i++){
            lucheng+=height*2;
            height/=2;
        }
        System.out.println("路程为" + lucheng);
        System.out.println("高度为" + height);
        System.out.println(height*Math.pow(2, 10));
    }

    public static void test1(){
        for(int i = 101; i <= 200; i++) {
            if(isPrime(i)) System.out.println(i);
        }
    }

    public static boolean isPrime(int n){
        if(n < 2) return false;
        if(n==2) return true;
        if(n%2==0) return false;
        for(int i = 3; i * i <= n; i+=2) {
            if(n % i == 0) return false;
        }
        return true;
    }
}
