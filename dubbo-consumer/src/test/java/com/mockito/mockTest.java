package com.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class mockTest {

    @Test
    public void superLucky() {
        LinkedList mockedList = Mockito.mock(LinkedList.class);
//        System.out.println(mockedList.getClass().getName());
        mockedList.add("one");
        mockedList.add("two");
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