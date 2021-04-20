package com.trinath.dsalgo.self;

public class CaeserCypherEncrypter {
    public static String caesarCypherEncryptor(String str, int key) {
        char[] chars = new char[str.length()];
        key = key%26;
        for(int i=0; i<str.length(); i++){
            int a = 'a';
            int z = 'z';
            int code = str.charAt(i)+key;
            if(code>z){
                code  = a + code%a;
            }
            chars[i] = (char) code;
        }
        return new String(chars);
    }
}
