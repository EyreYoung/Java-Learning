package com.seu;

public class BlankReplace {
    /**
     * @param str     要转换的字符数组
     * @param usedLength 字符数组中已经使用的长度
     * @return 转换后使用的字符长度，-1表示处理异常
     */
    public static int replaceBlank(char[] str, int usedLength) {
        // 判断输入是否合法
        if (str == null || str.length < usedLength) {
            return -1;
        }

        //计算空格个数
        int whiteCount = 0;
        for (int i = 0; i < usedLength; i++) {
            if (str[i] == ' ') {
                whiteCount++;
            }
        }

        // 计算转换后的字符长度是多少
        int targetLength = usedLength + whiteCount * 2;
        int tmp = targetLength;

        // 如果转换后的长度大于数组的最大长度，直接返回失败
        if (targetLength > str.length) {
            return -1;
        }

        if (whiteCount == 0) {
            return usedLength;
        }

        usedLength--;
        targetLength--;

        // 字符中有空格，一直处理到所有的空格处理完
        while (usedLength >= 0 && usedLength < targetLength) {
            // 如是当前字符是空格，进行替换
            if (str[usedLength] == ' ') {
                str[targetLength--] = '0';
                str[targetLength--] = '2';
                str[targetLength--] = '%';
            } else { // 否则移动字符
                str[targetLength--] = str[usedLength];
            }
            usedLength--;
        }

        return tmp;
    }

    public static void main(String[] args) {
        char[] str = new char[50];
        String string = "Welcome to China";
        int usedLength = 0;
        for(usedLength=0; usedLength<string.length(); usedLength++){
        	str[usedLength] = string.charAt(usedLength);
        }
        int length = replaceBlank(str,usedLength);
        if(length>-1){
            System.out.println(new String(str, 0, length));
        } else {
            System.out.println("Error Input");
        }
    }
}