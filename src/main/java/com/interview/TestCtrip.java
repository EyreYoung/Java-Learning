package com.interview;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/9/8 7:26 下午
 */
public class TestCtrip {
    public static void main(String[] args) {
        test2();
    }

    public static void test1(){
        Scanner in = new Scanner(System.in);
        String ss = in.nextLine();
        String sentence = in.nextLine();
        String replace = in.nextLine();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ss.length(); i++) {
            char c = ss.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            }
            else {
                map.put(c, 1);
            }
        }
        HashMap<Character, Integer> cur = map;
        int count = 0;
        StringBuilder sb = new StringBuilder(sentence);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (cur.containsKey(c)) {
                if (cur.get(c) == 1) {
                    cur.remove(c);
                }
                else {
                    cur.put(c, cur.get(c) - 1);
                }
                count++;
                if (count == ss.length()) {
                    list.add(i);
                    count = 0;
                    cur = new HashMap<>(map);
                }
            }
            else {
                count = 0;
                cur = new HashMap<>(map);
            }
        }
        for (int i = list.size() - 1; i >= 0 ; i--) {
            sb.replace(list.get(i) - ss.length() + 1, list.get(i) + 1, replace);
        }
        System.out.println(sb);
    }

    /**
     * 1. 背景: 订单系统的业务逻辑处理，通过采用工作流的方式进行处理，基于面向配置编程以利于后续扩展与维护.
     *
     * 2. 要求: 当前需要根据工作流的配置解析和找出所有工作流路径, 可能存在循环依赖路径.
     *
     * 3. 说明: 空格隔开的字符串每一个字母都代表一个节点. 比如a bc e, 表示a的下一个节点为b或者c， 输出为:abe, ace.
     *
     * 4. 举例: 有3个分支，
     *
     *             输入,
     *
     *                  a bc df gh
     *
     *             输出如下， 结果输出根据单个字符的下标index排序:  Comparator.naturalOrder().
     *
     *                  abdg
     *
     *                  abdh
     *
     *                  abfg
     *
     *                  abfh
     *
     *                  acdg
     *
     *                  acdh
     *
     *                  acfg
     *
     *                  acfh
     *   输入描述
     * 输入工作流程配置(可能存在循环依赖路径)
     *
     * 输出描述
     * 找出所有的路径, 正常路径与循环依赖路径,  并且必须标识出循环依赖路径(后缀为--circular dependency),  当前实例题仅为2个分支场景. 3个或3个以上分支请见上面举例
     * 样例输入
     * a bc d eag f
     * 样例输出
     * abdef
     * abdaf--circular dependency
     * abdgf
     * acdef
     * acdaf--circular dependency
     * acdgf
     *
     * 提示
     * 约束: 1个字母代表下一个节点.
     * abae: 由于a最终会流转回a, 证明这个是错误的配置即循环依赖路径， 输出带后缀说明: abae--circular dependency
     *
     * 根据单个字符的下标index排序， 结果的排序:  Comparator.naturalOrder()
     */
    public static void test2(){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] S = s.split(" ");
        ArrayList<String> ret = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < S.length; i++) {
            list = new ArrayList<>(ret);
            ret = new ArrayList<>();
            for (int j = 0; j < S[i].length(); j++) {
                if (i == 0) {
                    ret.add(String.valueOf(S[i].charAt(j)));
                }
                else {
                    for (String value : list) {
                        ret.add(value + S[i].charAt(j));
                    }
                }
            }
        }
        ret.sort(Comparator.naturalOrder());
        for (int i = 0; i < ret.size(); i++) {
            System.out.print(ret.get(i));
            if (check(ret.get(i))) {
                System.out.print("--circular dependency");
            }
            System.out.println();
        }
    }

    public static boolean check(String s) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) return true;
            else set.add(s.charAt(i));
        }
        return false;
    }
}
