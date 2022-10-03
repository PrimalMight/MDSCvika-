package cz.vutbr.bmds.cv01;

import java.util.ArrayList;

public class MyClass implements ISum{
    private static int count = 0;

    private ArrayList<Integer> list = new ArrayList<Integer>();

    // CONSTRUCTORS
    public MyClass(){
        count += 1;
    }
    public MyClass(int...numbers){
        this();

        for (int i: numbers) {
            addInteger(i);
        }
    }

    // CLASS METHODS
    public void addInteger(int number) throws IllegalArgumentException{
        if(number > 0){
            list.add(number);
        }
        else{
            throw new IllegalArgumentException("Cant insert negative numbers");
        }
    }

    public boolean integerExists(int number){
        return list.contains(number);
    }

    @Override
    public int sum(){
        int sum = 0;
        for (int i: list){
            sum += 1;
        }
        return sum;
    }

    @Override
    public String toString(){
        return "Array of length: " + list.size() + ", with sum: " + sum();
    }

    public void print(){
        System.out.println("Array (" + list.size() + "): ");
        for (int i: list) {
            System.out.println(i + " ");
        }
    }


    // STATIC METHODS
    public static int getCount(){
        return count;
    }

    public static MyClass createUnited(MyClass first, MyClass second){
        MyClass newClass = new MyClass();

        newClass.list.addAll(first.list);
        newClass.list.addAll(second.list);

        return newClass;
    }


}
