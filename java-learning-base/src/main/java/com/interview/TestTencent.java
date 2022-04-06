package com.interview;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/9/6 8:05 下午
 */
public class TestTencent {
    public static void main(String[] args) {
        test3();
    }

    // 链表的公共部分
    public static void test1() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] A = new int[n];
        for(int i = 0; i < n; i++) {
            A[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] B = new int[m];
        for(int i = 0; i < m; i++) {
            B[i] = in.nextInt();
        }

        int x = 0, y = 0;
        while(x < n && y < m) {
            if (A[x] == B[y]) {
                System.out.print(A[x] + " ");
                x++;
                y++;
            } else if (A[x] > B[y]) {
                x++;
            } else {
                y++;
            }
        }
    }

    /**
     *      50 5
     *      2 1 2
     *      5 10 11 12 13 14
     *      2 0 1
     *      2 49 2
     *      4 6 7 8 2
     *      从0开始传播，输出通知到的人
     */
    public static void test2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] A = new int[m][];
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        for(int i = 0; i < m; i++) {
            int num = sc.nextInt();
            int[] X = new int[num];
            A[i] = X;
            for(int j = 0; j < num; j++) {
                X[j] = sc.nextInt();
                if (!map.containsKey(X[j])) {
                    map.put(X[j], new HashSet<>());
                }
                map.get(X[j]).add(i);
            }
        }
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> ret = new HashSet<>();
        for (Integer x:map.get(0)) {
            for (Integer y:A[x]) {
                stack.push(y);
            }
        }
        while (!stack.isEmpty()) {
            Integer temp = stack.pop();
            if (!ret.contains(temp)) {
                ret.add(temp);
                for (Integer x : map.get(temp)) {
                    for (Integer y : A[x]) {
                        stack.push(y);
                    }
                }
            }
        }
        System.out.println(ret.size());

    }

    public static void test3() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i++) {
            String s = sc.next();
            if(!map.containsKey(s)) {
                map.put(s, 1);
            }
            else {
                map.put(s, map.get(s) + 1);
            }
        }
        System.out.println(map);

        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(map.entrySet());
        list.sort(new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        System.out.println(list);

        for(int i = 0;  i < K; i++) {
            System.out.println(list.get(i).getKey() + " " + list.get(i).getValue());
        }

        for(int i = K;  i > 0; i--) {
            System.out.println(list.get(list.size() - i).getKey() + " " + list.get(list.size() - i).getValue());
        }
    }
}
