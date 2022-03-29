package org.dng;

import java.util.Scanner;

/**
 Задание 7
 Заполните n-мерный квадратный массив возрастающими
 числами – змейкой. Выведите результат на экран с соблюдением ширины столбцов
 Пример:
 1 2 3 4
 8 7 6 5
 9 10 11 12
 16 15 14 13
 */
public class dz53_7 {
    public static int inputIntValue(String name) {
        int value = 0;
        boolean done = false;
        try (Scanner sc = new Scanner(System.in)) {
            while (!done) {
                System.out.println();
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

    public static void main(String[] args) {
        int dimension = inputIntValue("dimension of array");
        int[][] arr = new int[dimension][dimension];
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[0].length; x++) {
                if((y+1) % 2 != 0){
                    arr[y][x] = (x+1)+y*dimension;
                }
                else{
                    arr[y][dimension-1-x] = (x+1)+y*dimension;
                }
            }
        }

        System.out.println();
//        int[][] arr = {{1,3,2,5,4},{0,9,8,4,3,5,6}};
        System.out.println("the original array is:");
        for (int[] y : arr) {
            System.out.print("{");
            for (int x : y) {
                System.out.printf("%3d  ", x);
            }
            System.out.println(" }");
        }
//        System.out.println();
    }
}
