package letscode.com.trial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Testweights {

    /*
        Написать функцию, возвращающую количество комбинаций
        заданного набора весов грузиков для заданного веса.

    */
    // набор весов грузиков
    static int[] allweights = {1000, 500, 200, 100, 50, 20, 10, 5, 2, 1};

    static int numOfComb = 0;

    static boolean isToPrint;

    public static void main(String[] args) {
        int weight = 25;  // заданный вес
        Comb comb = new Comb(weight);
        System.out.println(" start weight = " + weight);
        isToPrint = weight < 100;
        check(0, comb);
        if (isToPrint) {
            combs.stream().forEach(cmb -> {
                System.out.println(cmb.getweightsComb());
            });
        }
        System.out.println(" number of combinations = " + numOfComb);
    }
    public static class Comb {
        int sumweight = 0;
        int[] combin;

        public Comb(int sumweight) {
            this.sumweight = sumweight;
            combin = new int[allweights.length];
        }
        public String getweightsComb() {
            StringBuffer buf = new StringBuffer();
            Arrays.stream(combin).forEach(cm -> {
                buf.append(cm + " ");
            });
            return buf.toString();
        }
        public int getSumweight() {
            return sumweight;
        }

        public void setCombin(int[] combin) {
            this.combin = Arrays.copyOf(combin, combin.length);
        }

        public Comb clone() {
            Comb cm = new Comb(sumweight);
            cm.setCombin(combin);
            return cm;
        }
        public void setInd(int ind, int num) {
            combin[ind - 1] = num;
            sumweight -= allweights[ind - 1] * num;
        }
    }
    public static void check(int ind, Comb comb) {
//    	System.out.println(" ind " + ind);
        if (ind > 0) {
            if (comb.getSumweight() == 0) {
                if (isToPrint)
                    combs.add(comb);
                numOfComb ++;
                return;
            }
        }
        ind ++;
        if (ind == allweights.length) {
            comb.setInd(ind, comb.getSumweight());
            if (isToPrint)
                combs.add(comb);
            numOfComb ++;
            return;
        }
        int weight = allweights[ind - 1];
        int numCl = comb.getSumweight() / weight;
        for (int i = 0; i <= numCl; i ++) {
            Comb cm = comb.clone();
            cm.setInd(ind, i);
            check(ind, cm);
        }
    }
    public static List<Comb> combs = new ArrayList<>();
}
