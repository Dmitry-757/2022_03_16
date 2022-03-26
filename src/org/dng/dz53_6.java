package org.dng;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Задание 6
 * Создать квадратный массив размерности n заполненный
 * случайными числами, вывести массив на экран в виде таблицы, найти наименьший и наибольший элемент массива
 * и вывести их на экран (если найдено несколько одинаковых
 * элементов – вывести индексы строки и столбца, где есть повторения). Вывести на экран время выполнения поиска, в
 * миллисекундах. Размерность массива должна задаваться с
 * клавиатуры.
 */


public class dz53_6 {
    public static int inputIntValue(String name) {
        int value = 0;
        boolean done = false;
        try (Scanner sc = new Scanner(System.in)) {
            while (!done) {
                System.out.println("Please, enter the " + name);
                if (sc.hasNextInt()) {
                    value = sc.nextInt();
                    if (value <= 0) {
                        System.out.println("Wrong input. Try again ))");
                    } else {
                        done = true;
                    }
                } else {
                    System.out.println("Wrong input. Try again ))");
                    sc.nextLine();
                }
            }
        }
        return value;
    }

    public static int getMax(int[][] arr) {
        int val = arr[0][0];
        for (int[] y : arr) {
            for (int x : y) {
                if (val < x) {
                    val = x;
                }
            }
        }
        return val;
    }

    public static int getMaxStrm(int[][] arr) {
        //*** let`s reduce dimension of arr
        int[] arr2 = new int[arr.length * arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, arr2, i * arr[i].length, arr[i].length);
        }
//        for (int x:arr2) {
//            System.out.printf("%3d,", x);
//        }
//        System.out.println();
        return IntStream.of(arr2).distinct().boxed().mapToInt(Integer::intValue).max().getAsInt();
    }


    public static int getMin(int[][] arr) {
        int val = arr[0][0];
        for (int[] y : arr) {
            for (int x : y) {
                if (val > x) {
                    val = x;
                }
            }
        }
        return val;
    }

    public static int getMinStrm(int[][] arr) {
        //*** let`s reduce dimension of arr - expand 2-dimension array to one line
        int[] arr2 = new int[arr.length * arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, arr2, i * arr[i].length, arr[i].length);
        }
        return IntStream.of(arr2).distinct().boxed().mapToInt(Integer::intValue).min().getAsInt();
    }


    public static void GetDuplicatedValIdx(int[][] arr) {
        //*** let`s reduce dimension of arr - expand 2-dimension array to one line
        int[] arr2 = new int[arr.length * arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, arr2, i * arr[i].length, arr[i].length);
        }
        //sort the arr for compare current element with previous
        Arrays.sort(arr2);

        int index1, index2;
        int[][] reduplicationArr = new int[0][3];//duplicatedValue/number of reduplications

        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[y].length; x++) {
                index1 = Arrays.binarySearch(arr2, 0, arr2.length + 0, arr[y][x]); //search element
                index2 = Arrays.binarySearch(arr2, 0, index1 - 0, arr[y][x]);//search before
                if (index2 < 0) {
                    index2 = Arrays.binarySearch(arr2, index1 + 1, arr2.length + 0, arr[y][x]); //search after
                }
                if (index2 >= 0) {
                    //***** add 1 element  for reduplicationArr2
                    int[][] tmpArr = new int[reduplicationArr.length + 1][3];
                    for (int j = 0; j < reduplicationArr.length; j++) {
                        System.arraycopy(reduplicationArr[j], 0, tmpArr[j], 0, reduplicationArr[j].length);
                    }
                    reduplicationArr = tmpArr;
                    tmpArr = new int[0][0]; //release some memory

                    reduplicationArr[reduplicationArr.length-1][0] = arr[y][x];
                    reduplicationArr[reduplicationArr.length-1][1] = y;
                    reduplicationArr[reduplicationArr.length-1][2] = x;
                }
            }
        }
        for (int i = 0; i < reduplicationArr.length; i++) {
            System.out.println(Arrays.toString(reduplicationArr[i]));
        }
    }


    public static void main(String[] args) {
//        int dimension = inputIntValue("dimension of array");
//        int[][] arr = new int[dimension][dimension];
//        for (int y = 0; y < arr.length; y++) {
//            for (int x = 0; x < arr[0].length; x++) {
//                arr[y][x] = (int) (Math.random() * 100);
//            }
//        }


//        for (int[] y : arr) {
//            for (int x : y) {
//                System.out.printf("%3d", x);
//            }
//            System.out.println();
//        }

//        System.out.println("min value of array calculated by the fori-meth is  " + getMin(arr));
//        System.out.println("min value of array calculated by the Stream-meth is " + getMinStrm(arr));
//
//        System.out.println("max value of array calculated by the fori-meth is  " + getMax(arr));
//        System.out.println("max value of array calculated by the Stream-meth is " + getMaxStrm(arr));

        int[][] arr2 = {{1,2,3},{4,2,1}};
        for (int[] y : arr2) {
            for (int x : y) {
                System.out.printf("%3d", x);
            }
            System.out.println();
        }

        GetDuplicatedValIdx(arr2);

    }
}