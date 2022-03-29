package org.dng;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ОБЯЗАТЕЛЬНЫЙ ФУНКЦИОНАЛ
 * • получить средний балл (дробный в том числе)
 * • вывести все оценки
 * • вывести максимальную оценку и название предмета
 * • вывести минимальную оценку и название соответствующего предмета
 */

class DiaryService {

    static void add(String topic, int mark) {
        if (StudentDiaryImp.diary.containsKey(topic)) {
            int[] arr = new int[StudentDiaryImp.diary.get(topic).length + 1];
            System.arraycopy(Arrays.stream(StudentDiaryImp.diary.get(topic)).mapToInt(Integer::intValue).toArray(), 0, arr, 0, arr.length - 1);
            arr[arr.length - 1] = mark;
            StudentDiaryImp.diary.replace(topic, IntStream.of(arr).boxed().toArray(Integer[]::new));
        } else {
            Integer[] arr = new Integer[1];
            arr[0] = mark;
            StudentDiaryImp.diary.put(topic, arr);
        }
    }

    static void remove(String topic) {
        if (StudentDiaryImp.diary.containsKey(topic)) {
            StudentDiaryImp.diary.remove(topic);
        }
    }

    static void edit(String topic, int idx, int mark) {
        if ((idx >= 1) || (StudentDiaryImp.diary.get(topic).length <= idx)) {
            StudentDiaryImp.diary.get(topic)[idx - 1] = mark;
        } else {
            System.out.println("Wrong number of mark!");
        }
    }


    static void printMarks(String topic) {
        if (StudentDiaryImp.diary.containsKey(topic)) {
            Arrays.stream(StudentDiaryImp.diary.get(topic)).forEach(v -> System.out.print("" + v + ", "));
            System.out.println();
        }
    }

    static void printMarks() {
        for (var entry : StudentDiaryImp.diary.entrySet()) {
            System.out.println("Topic " + entry.getKey() + " marks " +
                    Arrays.stream(entry.getValue())
                            .collect(Collectors.toList()));
        }

    }
}


public class StudentDiaryImp {
    public static HashMap<String, Integer[]> diary = new HashMap<>();//key = topic, value = array of marks


    public static void main(String[] args) {
        Pattern topicPattern = Pattern.compile("^[a-zA-Zа-яА-Я]+-?[a-zA-Zа-яА-Я]*");
        Pattern markPattern = Pattern.compile("\\s\\d+");
        try (Scanner sc = new Scanner(System.in)) {
            boolean stop = false;
            int choice = -1;
            String line;
            while (!stop) {
                System.out.println();
                System.out.println("Enter your choice: 1 - add topic and mark, 2 - remove topic, 3 - edit marks for topic, 4 - print marks by topic, 5 - print all topics with marks");
                System.out.println("6 - show max mark and topic, 7 - show min mark and topic, 8 - show average mark, 0 - exit");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine();
                }
                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter topic and mark. Example: mathematics 5");
                        try {
                            if (sc.hasNextLine()) {
                                String topic;
                                int mark;
                                line = sc.nextLine();
                                Matcher topicMatcher = topicPattern.matcher(line);
                                if (topicMatcher.find()) {
                                    topic = topicMatcher.group();
                                    System.out.println("topic = " + topic);
                                } else {
                                    throw new Exception("wrong input - can`t read topic...");
                                }

                                Matcher markMatcher = markPattern.matcher(line);
                                if (markMatcher.find()) {
                                    mark = Integer.parseInt(markMatcher.group().trim());
                                    if ((mark < 2) || (mark > 5)) {
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
                    case 2 -> {
                        System.out.println("Enter topic for removal. Example: MarksizmLenenizm");
                        try {
                            if (sc.hasNextLine()) {
                                String topic;
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
                            continue;
                        }
                    }

                    case 3 -> {
                        System.out.println("Enter topic for edit marks. Example: MarksizmLenenizm");
                        try {
                            if (sc.hasNextLine()) {
                                String topic;
                                line = sc.nextLine();
                                Matcher topicMatcher = topicPattern.matcher(line);
                                if (topicMatcher.find()) {
                                    topic = topicMatcher.group();
                                    System.out.println("topic = " + topic);
                                } else {
                                    throw new Exception("wrong input - can`t reed topic...");
                                }

                                if (StudentDiaryImp.diary.containsKey(topic)) {
                                    System.out.println("Current marks for topic " + topic + " are");
                                    DiaryService.printMarks(topic);

                                    System.out.println("Enter number of mark for edit");
                                    if (sc.hasNextInt()) {
                                        int idx = sc.nextInt();
                                        sc.nextLine();
                                        if ((idx <= 0) || (StudentDiaryImp.diary.get(topic).length < idx)) {
                                            throw new Exception("wrong input - illegal number of mark...");
                                        }

                                        System.out.println("current mark is " + StudentDiaryImp.diary.get(topic)[idx - 1]);
                                        System.out.println("Enter new mark");
                                        if (sc.hasNextInt()) {
                                            int mark = sc.nextInt();
                                            sc.nextLine();
                                            if ((mark < 2) || (mark > 5)) {
                                                throw new Exception("wrong input - mark must be in interval of 2-5...");
                                            }
                                            //editing mark
                                            DiaryService.edit(topic, idx, mark);
                                            DiaryService.printMarks(topic);
                                        } else {
                                            System.out.println("wrong input!");
                                        }
                                    } else {
                                        throw new Exception("wrong input!");
                                    }
                                } else {
                                    System.out.println("this topic isn't found!");
                                    throw new Exception("wrong input!");
                                }


                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                    }

                    case 4 -> {
                        System.out.println("Enter topic for print marks");
                        try {
                            if (sc.hasNextLine()) {
                                String topic;
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
                            continue;
                        }

                    }
                    case 5 -> {
                        DiaryService.printMarks();
                    }
                    case 0 -> {
                        stop = true;
                        System.out.println("Our great program is ended ;) !");
                    }
                    case -1 -> {
                    }
                    default -> {
                        System.out.println("In our roulette you can only three option for choice: 1, 2, 3, 4, 5 or 0 ;)");
                    }
                }
                choice = -1;

            }
        }


    }
}
