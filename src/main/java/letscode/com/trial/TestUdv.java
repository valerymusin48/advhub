package letscode.com.trial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
Написать функцию принимающюю на вход String[]
Исходный массив менять нельзя

Требуется найти группы строк содержащие одинаковый набор символов и вывести на консоль в виде:
набор символов(в любом порядке) = индексы строк в исходном массиве через запятую(в любом порядке)
Если у строки нет парной с ней строки с одинаковым набором символов - то печать её не надо.

 */

public class TestUdv {
    private static Object vals;

    public static void main(String[] args) {

        String[] in = {"qwe","wqe","qwee","a","a", "eqw"};
        purseIt(in, 3);  //  num - число одинаковых  символов

    }
    static String sortIt(String str) {
        char charArray[] = str.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
    static String dispIt(List<Integer> list) {
        StringBuffer buf = new StringBuffer(" ");
        list.stream().forEach(i -> {
            buf.append(i).append(",");
        });
        return buf.deleteCharAt(buf.length() - 1).toString();
    }
    static void purseIt(String[] vals, int num) {
        ConcurrentMap<String, List<Integer>> map = new ConcurrentHashMap<>();
        int ind = 0;
        for (String str : vals) {
            String sorted = sortIt(str);
            List list = map.get(sorted);
            if (list == null) {
               list = new ArrayList();
            }
            list.add(ind ++);
            map.put(sorted, list);
        }
        map.keySet().stream().filter(res -> map.get(res).size() >= num)
                .forEach(res -> {
                    System.out.println(res + dispIt(map.get(res)));
                }

        );
    }
}
