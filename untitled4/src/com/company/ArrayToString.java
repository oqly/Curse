package com.company;

public class ArrayToString {
    int[] array;
    String nameArray;

    private String convert(){
        nameArray += ": ";
        for(int i =  0; i < array.length-1; i++){
            nameArray += array[i] + ".";
        }
        nameArray += array[array.length-1];
        return nameArray;
    }
    public String getNameArray(){
        return nameArray;
    }

    public ArrayToString(String nameArray, int[] array)
    {
        this.nameArray = nameArray;
        this.array = array;
        convert();
    }
}
