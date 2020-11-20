package letscode.com.trial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestWeights {

    /*
        Написать функцию, возвращающую количество комбинаций
        заданного набора весов грузиков для заданного веса.
       Пример : вес 50
       start weight = 50
        number of combinations = 451
        два из них
       1.  1 гиря 50
       2. 50 гирь по 1

    */
    // набор весов грузиков
    static int[] allWeights = {1000, 500, 200, 100, 50, 20, 10, 5, 2, 1};

    static int numOfComb = 0;

    static boolean isToPrint;

    public static void main(String[] args) {
        int weight = 50;  // заданный вес
        Comb comb = new Comb(weight);
        System.out.println(" start weight = " + weight);
        isToPrint = weight < 50;
        check(0, comb);
        if (isToPrint) {
            combs.stream().forEach(cmb -> {
                System.out.println(cmb.getWeightsComb());
            });
        }
        System.out.println(" number of combinations = " + numOfComb);
    }

    public static class Comb {
        int sumWeight = 0;
        int[] combin;

        public int getSumWeight() {
            return sumWeight;
        }

        public Comb(int sumWeight) {
            this.sumWeight = sumWeight;
            combin = new int[allWeights.length];
        }

        public String getWeightsComb() {
            StringBuffer buf = new StringBuffer();
            Arrays.stream(combin).forEach(cm -> {
                buf.append(cm + " ");
            });
            return buf.toString();
        }

        public void setCombin(int[] combin) {
            this.combin = Arrays.copyOf(combin, combin.length);
        }

        public Comb clone() {
            Comb cm = new Comb(sumWeight);
            cm.setCombin(combin);
            return cm;
        }

        public void setInd(int ind, int num) {
            combin[ind - 1] = num;
            sumWeight -= allWeights[ind - 1] * num;
        }
    }

    public static void check(int ind, Comb comb) {
//    	System.out.println(" ind " + ind);
        if (ind > 0) {
            if (comb.getSumWeight() == 0) {
                if (isToPrint)
                    combs.add(comb);
                numOfComb++;
                return;
            }
        }

        if (++ind > allWeights.length) {
            return;
        }
        int weight = allWeights[ind - 1];
        int numCl = comb.getSumWeight() / weight;
        for (int i = 0; i <= numCl; i++) {
            Comb cm = i > 0 ? comb.clone() : comb;
            cm.setInd(ind, i);
            check(ind, cm);
        }
    }

    public static List<Comb> combs = new ArrayList<>();
}
