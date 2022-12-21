package com.mockito;

import java.util.LinkedList;
import java.util.List;

import org.mockito.Mockito;

public class mock {

    public static void main(String[] args) {
        LinkedList mockedList = Mockito.mock(LinkedList.class);
//        System.out.println(mockedList.getClass().getName());
//        mockedList.add("one");
//        mockedList.add("two");
//        mockedList.clear();
//
//        verify(mockedList).add("one");
//        verify(mockedList).clear();

        Mockito.when(mockedList.get(0)).thenReturn("First");
        Mockito.when(mockedList.get(1)).thenThrow(new Exception("Crap!"));

        System.out.println(mockedList.get(0));
//        System.out.println(mockedList.get(1));

    }

}
