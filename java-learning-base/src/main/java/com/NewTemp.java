package com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/7/20
 */
public class NewTemp {

    public static void main(String[] args) {
        System.out.println(
                new NewTemp()
                        .longestWord(
                                new String[]{"apple","iOS","dog","nana","man",
                                        "good","goodman","mangood","goodgood","nananana","appleiOS"}));
    }

    public String longestWord (String[] words) {
        if (words.length == 0) return "";
        String ret = "";
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("")) continue;
            StringBuilder toBeCompare = new StringBuilder(words[i]);
            for (int j = 0; j < words.length; j++) {
                if (i == j || words[j].equals("")) continue;
                while (toBeCompare.indexOf(words[j]) > -1) {
                    int len = toBeCompare.indexOf(words[j]);
                    toBeCompare.replace(len, len + words[j].length(), "");
                }
                if ("".contentEquals(toBeCompare)) {
                    if (words[i].length() > ret.length()
                            ||
                            (words[i].length() == ret.length() && words[i].compareTo(ret) < 0)) {
                        ret = words[i];
                        break;
                    }
                }
            }
        }
        return ret;
    }

}
