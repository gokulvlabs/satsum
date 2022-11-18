package com.satsum.util;

import java.util.HashMap;

public class Solution {

    public int solution(String[] A) {

        A= new String[]{"abc", "yyy", "def", "csv"};
        StringBuilder s = new StringBuilder();
        HashMap<Character, Integer> repMap ;
        boolean addFlag = false;
        for(String str : A){
            repMap = new HashMap<Character, Integer>();
            for(Character a: str.toCharArray()) {
                if (repMap.containsKey(a)) {
                    addFlag = false;
                    break;
                }
                repMap.put(a, 1);
                addFlag = true;
            }
            if(addFlag) {
                s.append(str);
            }
            repMap.clear();
        }
        System.out.println("this is a debug message: "+s);
        // write your code in Java SE 8
       // String s = String.join("",A);

        if (s.length()==0) return 0;

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max=0;
        for (int i=0, j=0; i<s.length(); ++i){
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-j+1);
        }
        return max;

    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int num = solution.solution(args);
        System.out.println("num"+num);
    }
    }


// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");


