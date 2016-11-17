package com.jlc.api.myutil;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class MyString {
    public static void main(String[] args) {
        //myCharAt("abc");
        //myGetChars("abcd");
        //System.out.println(myRegionMatches());
        //System.out.println("see you tonight".replace('e','o'));
        //System.out.println("12345".subSequence(1,3).getClass().getName());
        String s2 = String.format("%1$tY/%1$tm/%1$td", new Date());
        System.out.println(s2);
    }

    public static int myLength(char[] chars){
        String str = new String(chars);
        return str.length();
    }
    public static char myCharAt(String str){
         char ch = str.charAt(1);
        System.out.println(ch);
        return ch;
    }
    public static char[] myGetChars(String str){
        char[] chs = new char[10];
        str.getChars(1,3,chs,0);
        for (char ch:chs) {
            System.out.println(ch);
        }
        return chs;
    }
    public static boolean myRegionMatches(){
        String str="aAaa";
        return str.regionMatches(true,0,"aaaaa",0,4);
    }

}
