package org.dng;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


class DiaryService{
    static void add(String topic, int mark){
        if(StudentDiaryImp.twm.containsKey(topic)){
            int[] arr = new int[StudentDiaryImp.twm.get(topic).length+1];
            System.arraycopy(Arrays.stream(StudentDiaryImp.twm.get(topic)).mapToInt(Integer::intValue).toArray(),0, arr,0, arr.length-1);
            arr[arr.length-1] = mark;
            StudentDiaryImp.twm.replace(topic, IntStream.of(arr).boxed().toArray(Integer[]::new));
        }
        else{
            Integer[] arr = new Integer[1];
            arr[0] = mark;
            StudentDiaryImp.twm.put(topic, arr);
        }
    }
    static void remove(String topic){
        if(StudentDiaryImp.twm.containsKey(topic)){
            StudentDiaryImp.twm.remove(topic);
        }
    }

    static void edit(String topic){
        if(StudentDiaryImp.twm.containsKey(topic)){
            System.out.println("Current marks for topic " + topic + " are");
            Arrays.stream(StudentDiaryImp.twm.get(topic)).forEach(v -> System.out.print(""+v+", "));
            System.out.println("Enter number of mark for edit");
            //editing marks

        }
    }


    static void printMarks(String topic){
        if(StudentDiaryImp.twm.containsKey(topic)){
            Arrays.stream(StudentDiaryImp.twm.get(topic)).forEach(v -> System.out.print(""+v+", "));
        }
    }
    static void printMarks(){
        for (var entry : StudentDiaryImp.twm.entrySet()) {
            //System.out.println(entry.getKey() + "/" + entry.getValue());
            System.out.println("Topic " + entry.getKey() + " marks " +
                Arrays.stream(entry.getValue())
                        .collect(Collectors.toList())
                        .toString() );
        }

    }
}


public class StudentDiaryImp {
    public static HashMap<String, Integer[]> twm = new HashMap<>();


    public static void main(String[] args) {
        Pattern topicPattern = Pattern.compile("^[a-zA-Zа-яА-Я]+-?[a-zA-Zа-яА-Я]*");
        Pattern markPattern = Pattern.compile("\\s\\d+");
        try (Scanner sc = new Scanner(System.in)){
            boolean stop = false;
            int choice = -1;
            String line;
            while (!stop){
                System.out.println("Enter your choice: 1 - add topic and mark, 2 - remove topic, 4 - print marks by topic, 5 - print all topics with marks, 0 - exit");
                if(sc.hasNextInt()){
                    choice = sc.nextInt();
                    sc.nextLine();
                }
                switch (choice){
                    case 1 ->{
                        System.out.println("Enter topic and mark. Example: mathematics 5");
                        try{
                            if(sc.hasNextLine()) {
                                String topic = null;
                                int mark = 0;
                                line = sc.nextLine();
                                Matcher topicMatcher = topicPattern.matcher(line);
                                if (topicMatcher.find()) {
                                    topic = topicMatcher.group();
                                    System.out.println("topic = " + topic);
                                } else {
                                    throw new Exception("wrong input - can`t read topic...");
                                }

                                Matcher markMatcher = markPattern.matcher(line);
                                if (markMatcher.find() ) {
                                    mark = Integer.valueOf(markMatcher.group().trim()).intValue();
                                    if((mark<2) || (mark > 5)){
                                        throw new Exception("wrong input - mark must be in interval of 2-5...");
                                    }
                                    System.out.println("mark = " + mark);
                                } else {
                                    throw new Exception("wrong input - mark must be in interval of 2-5...");
                                }

                                DiaryService.add(topic, mark);

                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                    }
                    case 2 ->{
                        System.out.println("Enter topic for removal. Example: MarksizmLenenizm");
                        try{
                            if(sc.hasNextLine()) {
                                String topic = null;
                                line = sc.nextLine();
                                Matcher topicMatcher = topicPattern.matcher(line);
                                if (topicMatcher.find()) {
                                    topic = topicMatcher.group();
                                    System.out.println("topic = " + topic);
                                } else {
                                    throw new Exception("wrong input - can`t reed topic...");
                                }
                                DiaryService.remove(topic);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    case 3 ->{
                        System.out.println("Enter topic for topic. Example: MarksizmLenenizm");
                        try{
                            if(sc.hasNextLine()) {
                                String topic = null;
                                line = sc.nextLine();
                                Matcher topicMatcher = topicPattern.matcher(line);
                                if (topicMatcher.find()) {
                                    topic = topicMatcher.group();
                                    System.out.println("topic = " + topic);
                                } else {
                                    throw new Exception("wrong input - can`t reed topic...");
                                }
                                DiaryService.edit(topic);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    case 4 ->{
                        System.out.println("Enter topic for print marks");
                        try{
                            if(sc.hasNextLine()) {
                                String topic = null;
                                line = sc.nextLine();
                                Matcher topicMatcher = topicPattern.matcher(line);
                                if (topicMatcher.find()) {
                                    topic = topicMatcher.group();
                                    System.out.println("topic = " + topic);
                                } else {
                                    throw new Exception("wrong input - can`t reed topic...");
                                }
                                DiaryService.printMarks(topic);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                    }
                    case 5 -> {
                        DiaryService.printMarks();
                    }
                    case 0 ->{
                        stop = true;
                        System.out.println("Our great program is ended ;) !");
                    }
                    default -> {
                        System.out.println("In our roulette you can only three option for choice: 1, 2 or 0 ;)");
                    }
                }

            }
        }



    }
}
