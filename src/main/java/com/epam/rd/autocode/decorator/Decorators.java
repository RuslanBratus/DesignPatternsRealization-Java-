package com.epam.rd.autocode.decorator;

import java.util.ArrayList;
import java.util.List;

public class Decorators {
    public static List<String> evenIndexElementsSubList(List<String> sourceList) {
        List<String> myList = new ArrayList<String>();
        int i = 2;
        for (String temp : sourceList)
        {
            if (i % 2 == 0)
            {
                myList.add(temp);
            }
            i++;

        }
        return myList;
        //throw new UnsupportedOperationException();
    }


}
