//package com.gokul.satsum.util;
//
//import java.util.Arrays;
//
//public class Test {
//
//    // input [11,14,22,45,66]
//    // output [11,22,66]
//    public static void main(String[] args) {
//
//        int[] arr = {11, 14, 22, 45, 66};
//
//        for (int i = 0; i < arr.length; i++) {
//            int div = arr[i] / 10;
//            int reminder = arr[i] % 10;
//
//            if (div == reminder) {
//                System.out.println(arr[i]);
//            }
//        }
//
//        //Write in Java 8
//        Arrays.asList(11, 14, 22, 45, 66).stream().filter(a->{
//            int div =a/10;
//            int reminder = a % 10;
//            return div==reminder;
//        }).forEach(System::Println);
//
//    }
//}
