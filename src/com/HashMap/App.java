package com.HashMap;

public class App {
    public static void main(String[] args) {
        Map<String,Object> map=new HashMap<>();
        map.put("123","321");
        map.put("Monkey","caocaocao");
        System.out.println(map.get("123"));
    }
}
