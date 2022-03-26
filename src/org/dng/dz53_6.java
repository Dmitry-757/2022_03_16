package org.dng;

import java.util.*;
import java.util.stream.Collectors;
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

class MyStorage { //this is the storage for the number of duplications and their indices for each element of the array
    private int numDuplication;
    private LinkedList<Integer> index_y = new LinkedList<>();
    private LinkedList<Integer> index_x = new LinkedList<>();

    MyStorage(int numDuplication, int index_y, int index_x) {
        this.numDuplication = numDuplication;
        this.index_y.add(index_y);
        this.index_x.add(index_x);
    }

    public int getNumDuplication() {
        return numDuplication;
    }

    public void setNumDuplication(int numDuplication) {
        this.numDuplication = numDuplication;
    }

    public LinkedList<Integer> getIndex_y() {
        return index_y;
    }
    public LinkedList<Integer> getIndex_x() {
        return index_x;
    }

    public void incNumDuplication(int y, int x) {
        this.numDuplication = ++this.numDuplication;
        this.index_y.add(y);
        this.index_x.add(x);
    }
}

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
//        int[] arr2 = new int[arr.length * arr[0].length];
//        for (int i = 0; i < arr.length; i++) {
//            System.arraycopy(arr[i], 0, arr2, i * arr[i].length, arr[i].length);
//        }
        //*** let`s reduce dimension of arr - expand 2-dimension array to one line
        int lenghtOfNewArr = 0;
        for(int[] y:arr){
            for(int x:y){
                lenghtOfNewArr++;
            }
        }
        int[] arr2 = new int[lenghtOfNewArr];
        int countOfCopyedEl = 0;
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, arr2, countOfCopyedEl+0, arr[i].length);
            countOfCopyedEl += arr[i].length;
        }
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
        int lenghtOfNewArr = 0;
        for(int[] y:arr){
            for(int x:y){
                lenghtOfNewArr++;
            }
        }
        int[] arr2 = new int[lenghtOfNewArr];
        int countOfCopyedEl = 0;
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, arr2, countOfCopyedEl+0, arr[i].length);
            countOfCopyedEl += arr[i].length;
        }
        return IntStream.of(arr2).distinct().boxed().mapToInt(Integer::intValue).min().getAsInt();
    }


    public static int[][] GetDuplicatedValIdx(int[][] arr) {
        //*** let`s reduce dimension of arr - expand 2-dimension array to one line
        int lenghtOfNewArr = 0;
        for(int[] y:arr){
            for(int x:y){
                lenghtOfNewArr++;
            }
        }
        int[] arr2 = new int[lenghtOfNewArr];
        int countOfCopyedEl = 0;
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, arr2, countOfCopyedEl+0, arr[i].length);
            countOfCopyedEl += arr[i].length;
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
                    tmpArr = null; //release some memory

                    reduplicationArr[reduplicationArr.length-1][0] = arr[y][x];
                    reduplicationArr[reduplicationArr.length-1][1] = y+1;
                    reduplicationArr[reduplicationArr.length-1][2] = x+1;
                }
            }
        }
//        System.out.println("duplicated values and its indexes are:");
//        for (int i = 0; i < reduplicationArr.length; i++) {
//            System.out.println(Arrays.toString(reduplicationArr[i]));
//        }
        return reduplicationArr;
    }

    public static void GetDuplicatedValIdxStrm(int[][] arr) {

        Map<Integer, MyStorage> myMap = new HashMap<>();//the arrays value will be as a key and the number of reduplication will be as a Value of Map
        int value;
        MyStorage myStorage;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                value = arr[i][j];
                if (myMap.containsKey(value)) {
                    myStorage = myMap.get(value);
                    myStorage.incNumDuplication(i, j);

                } else {
                    myMap.put(value, new MyStorage(1, i, j));
                }
            }
        }

        myMap = myMap.entrySet().stream().filter(e -> e.getValue().getNumDuplication() != 1).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        System.out.println("The number of duplicated values is " + myMap.size());

        for (var entry : myMap.entrySet()) {
            System.out.println("Value " + entry.getKey() + " occurs " + entry.getValue().getNumDuplication() + " time(s)");

            for (int i = 0; i < entry.getValue().getIndex_y().size(); i++) {
                System.out.print("line = " + (entry.getValue().getIndex_y().get(i)+1)+" ");
                System.out.print("column = " + (entry.getValue().getIndex_x().get(i)+1));
                System.out.println();
            }
            System.out.println();

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

        int[][] arr = {{1,3,2,5,4},{0,9,8,4,3,5,6}};
        System.out.println("the original array is:");
        for (int[] y : arr) {
            System.out.print("{");
            for (int x : y) {
                System.out.printf("%3d", x);
            }
            System.out.println(" }");
        }


        System.out.println("min value of array calculated by the fori-meth is  " + getMin(arr));
        System.out.println("min value of array calculated by the Stream-meth is " + getMinStrm(arr));

        System.out.println("max value of array calculated by the fori-meth is  " + getMax(arr));
        System.out.println("max value of array calculated by the Stream-meth is " + getMaxStrm(arr));



        int [][] reduplicationArr = GetDuplicatedValIdx(arr);
        if(reduplicationArr.length > 0) {
            System.out.println("duplicated values and its indexes are [duplicated_value, line, column]:");
            for (int i = 0; i < reduplicationArr.length; i++) {
                System.out.println(Arrays.toString(reduplicationArr[i]));
            }
        }
        else{
            System.out.println("The duplicated values are not found.");
        }

        System.out.println();
        GetDuplicatedValIdxStrm(arr);

    }
}

