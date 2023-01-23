package com.app.incubator.utils;


import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {

       int[]  a = {3,2,-2,7,5,7};
       int max =  0;
       int max2nd = 0;

       for ( int i = 0 ; i < a.length ; i++ ) {

           if (a[i] > max) {

               max2nd = max;

               max = a[i]; // 7

               System.out.println(max2nd);
               System.out.println(max);

               if (max2nd < max ) {;
                   max2nd = max;
               }
           }
           else if(a[i] > max) {

               max2nd = max;
           }
       }

       System.out.println(max2nd);

    }
}
