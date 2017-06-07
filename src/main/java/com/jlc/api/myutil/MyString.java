package com.jlc.api.myutil;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class MyString {
    public static void main(String[] args) {
        myCharAt("abc");
        myGetChars("abcd");
        System.out.println(myRegionMatches());
        System.out.println("see you tonight".replace('e','o'));
//        System.out.println("12345".subSequence(1,3).getClass().getName());
//        String s2 = String.format("%1$tY/%1$tm/%1$td", new Date());
//        System.out.println(s2);
//        instanceStr();
//        myCodePoint();
//        myCopyValueOf();
//        myValueOf();
        System.out.println(111111);

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
        str.getChars(0,3,chs,0);
        for(char ch:chs){
            System.out.println(ch);
        }
        return chs;
    }
    public static boolean myRegionMatches(){
        String str="aAaa";
        return str.regionMatches(true,0,"aaaaa",0,4);
    }

    public static void instanceStr(){
        String str1 = null;
        String str2 = new String();
        String str3 = new String("aaaa");
        char[] chs = {'d','d','a'};
        String str4 = new String(chs);
        byte[] by = {65,66,67};
        String str5 = new String(by);
        System.out.println(str5);
    }

    public static void myCodePoint(){
        String str = "ABlucy";
        System.out.println(str.codePointAt(0));
        System.out.println(str.codePointBefore(2));
        System.out.println(str.codePointCount(1,3));
    }

    public static void myCopyValueOf(){
        char[] chs = {'a','g','c','h'};
        System.out.println(String.copyValueOf(chs));
    }

    public static void myValueOf(){
        System.out.println(String.valueOf(true));
    }

}
