package com.interview;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/9/11 7:10 下午
 */
public class TestNetease {
    public static void main(String[] args) {
        test1();
//        System.out.println(compress("\"abbbbbbAAAdcdddd\""));

    }

    private static void test1() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<Integer> B = new ArrayList<>();
        int[] A = new int[n];
        String Ast = in.next();
        String Bst = in.next();
        for (int i = 0; i < Bst.length(); i++) {
            B.add(Character.getNumericValue(Bst.charAt(i)));
        }
        for (int i = 0; i < Ast.length(); i++) {
            A[i] = Character.getNumericValue(Ast.charAt(i));
        }
        B.sort(Collections.reverseOrder());
        for (int j = 0; j < n; j++) {
            if (A[j] > B.get(j)) {
                System.out.println(-1);
                return;
            } else if (A[j] < B.get(j)) {
                break;
            }
        }
        boolean flag = false;
        int ret = 0;
        for (int j = 0; j < n; j++) {
            if (flag) {
                ret = ret * 10 + B.remove(B.size() - 1);
                continue;
            }
            int x = findX(B, A[j]);
            if (x > A[j]) {
                flag = true;
            }
            ret = ret * 10 + x;
        }
        System.out.println(ret);
    }

    private static int findX(List<Integer> B, int x) {
        int size = B.size();
        for (int i = 0; i < size; i++) {
            if (B.get(i) == x) {
                size--;
                return B.remove(i);
            }
            else if (B.get(i) < x && size > 1) {
                size--;
                return B.remove(i - 1);
            }
        }
        return B.remove(B.size() - 1);
    }

    public static String compress (String raw_str) {
        // write code here
        int start = 0;
        int count = 1;
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 1; i < raw_str.length(); i++) {
            char c = raw_str.charAt(i);
            char lastC = raw_str.charAt(i - 1);
            if(c == lastC) {
                count++;
                if(i == raw_str.length() - 1 && count >= 4 && count <= 55) {
                    Map<String, Object> temp = new HashMap<>();
                    temp.put("位置", start);
                    temp.put("个数", count);
                    temp.put("字符", lastC);
                    list.add(temp);
                }
                 else if(count == 55) {
                    Map<String, Object> temp = new HashMap<>();
                    temp.put("位置", start);
                    temp.put("个数", count);
                    temp.put("字符", c);
                    list.add(temp);
                    i++;
                    start = i;
                    count = 1;
                }
            }
            else {
                if (count >= 4) {
                    Map<String, Object> temp = new HashMap<>();
                    temp.put("位置", start);
                    temp.put("个数", count);
                    temp.put("字符", lastC);
                    list.add(temp);
                }
                start = i;
                count = 1;
            }
        }
        StringBuilder sb = new StringBuilder(raw_str);
        for (int i = list.size() - 1; i >= 0 ; i--) {
            System.out.println(list.get(i));
            Integer st = (Integer) list.get(i).get("位置");
            Integer num = (Integer) list.get(i).get("个数");
            char replace = (Character) list.get(i).get("字符");
            char n = num <= 29 ? (char) ((int) 'a' + num - 4) : (char) ((int) 'A' + num - 30);
            sb.replace(st, st + num, "0" + n + replace);
        }
        return String.valueOf(sb);
    }
}
