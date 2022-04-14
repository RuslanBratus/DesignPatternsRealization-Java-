package com.epam.rd.autocode.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

class Iterators implements Iterable<String>{

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array){

        ArrayList<Integer> myArrayList = new ArrayList<Integer>();
        for(int i : array)
        {
            for (int j = 0; j < 2; j++) {
                myArrayList.add(i);
            }
        }
        Iterator<Integer> iter = myArrayList.iterator();
        return iter;
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        ArrayList<Integer> myArrayList = new ArrayList<Integer>();
        for(int i : array)
        {
            for (int j = 0; j < 3; j++) {
                myArrayList.add(i);
            }
        }
        Iterator<Integer> iter = myArrayList.iterator();
        return iter;
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {

        ArrayList<Integer> myArrayList = new ArrayList<Integer>();
        for(int i : array)
        {
            for (int j = 0; j < 5; j++) {
                myArrayList.add(i);
            }
        }
        Iterator<Integer> iter = myArrayList.iterator();
        return iter;
        }

    public static Iterable<String> table(String[] columns, int[] rows){
        ArrayList<String> myArrayList = new ArrayList<String>();

        for (String i : columns)
        {
            for (int j : rows)
            {
                myArrayList.add(i+j);
            }
        }


        Iterable<String> iter = new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                Iterator<String> iter = myArrayList.iterator();
                return iter;
            }
        };
        return iter;
    }


    @Override
    public Iterator iterator() {
        return null;
    }
}
