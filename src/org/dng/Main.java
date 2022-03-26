package org.dng;


import java.util.Arrays;

/**
 Преобразование int [] в Integer [] и наоборот:

 int[] arr1 = {1, 2, 3};

 Integer[] arr2 = Arrays.stream(arr1).boxed().toArray(Integer[]::new);
 or
 Integer[] arr2 = IntStream.of( arr1 ).boxed().toArray( Integer[]::new );

 int[] arr3 = Arrays.stream(arr2).mapToInt(Integer::intValue).toArray();

 int[] aint = {1,2,3,4,5,6,7,8,9,10};
 Integer[] aInt = new Integer[aint.length];
 Arrays.setAll(aInt, i -> aint[i]); ????

 int[] arr1 = {1,1,2,2,3};

 Set<Integer> mySet = Arrays.stream(arr1).boxed().collect(Collectors.toSet());//создали и заполнили set

 Integer[] arr2 = mySet.toArray(new Integer[mySet.size()]); //преобразовали set в Integer[]

 IntStream boxed()
 возвращает поток, состоящий из элементов этого потока, каждый из которых упакован в целое число
 */
public class Main {

    public static void main(String[] args) {
	//int[] arr = {5,9,4,3,5,4,8,4,1,2};
	int[] arr = {1, 2, 3, 4, 4, 4, 6, 5, 5, 8, 9};
        Arrays.sort(arr);
        int id1, id2, id3;
        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.binarySearch(arr,0,arr.length,0));
//        System.out.println(Arrays.binarySearch(arr,0,arr.length,3));
//        System.out.println(Arrays.binarySearch(arr,0,arr.length,4));
//        System.out.println(Arrays.binarySearch(arr,0,arr.length,10));
//        System.out.println(Arrays.binarySearch(arr,0,4,4));
        //System.out.println(Arrays.binarySearch(arr,4,5,4));

        id1 = Arrays.binarySearch(arr,0, 10,4);
        System.out.println(id1);

        id2 = Arrays.binarySearch(arr,0, id1,4);
        System.out.println(id2);

        //id1 = 4;
        id3 = Arrays.binarySearch(arr,id1+1, 10,4);
        System.out.println(id3);


    }
}
