package com.example.service_hi.runtime_lock.java_object;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 栈实现模拟弹出栈元素时内存溢出
 */
public class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack();
        Object obj = new Integer(100);
        for (int i = 0; i < 20; i++) {
            stack.push(obj);
        }
//        stack.push(obj);
        Object arr = stack.pop();
        System.out.println(arr);
        System.out.println("");
    }
}
class Stack{
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPAITY = 16;
    public Stack(){
        elements = new Object[DEFAULT_INITIAL_CAPAITY];
    }
    public void push(Object obj){
        ensureCapacity();
        elements[size++] = obj;
        System.out.println(elements.length);
        System.out.println(size);
    }
    public Object pop(){
        if(size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }
    private void ensureCapacity(){
        if(elements.length ==size)
            elements = Arrays.copyOf(elements,2*size+1);
    }
}