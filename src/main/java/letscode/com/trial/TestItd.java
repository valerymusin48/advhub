package letscode.com.trial;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TestItd {
/*
 Дана таблица переводов денег между клиентами банка.

Требуется:

1. Найти все циклические переводы денег с количеством вовлечённых клиентов более 3.

2. Распечатать ID транзакций, отправителя и получателя в таких циклах

3. Посчитать суммы всех входящих и исходящих средств для каждого из клиентов,
 вовлечённых в такие циклические переводы денег и распечатать их

	 ID перевода       ID клиента 1           ID клиента 2                  Сумма
	    1                      1                      6                      16365
    	2                      6                      2                      1902
    	3                      6                      3                      5940
    	4                      6                      7                      5304
    	5                      6                      5                      6544
    	6                      6                      8                      2068
    	7                      5                     10                      19624
    	8                      9                      5                      17450
    	9                      4                      9                      769
        10                    10                      9                      20636
        11                     9                     11                      2870
    	12                    10                      9                      16876
    	13                    13                      6                      20881
    	14                    10                     13                      1878
    	15                    13                     15                      1073
    	16                    15                     13                      13285
    	17                    13                     16                      18122
    	18                    16                     17                      16795
    	19                    13                     14                      3907
    	20                    11                     12                      4522
    	21                    12                     14                      22027
    	22                    14                     12                      23494
    	23                    14                     20                      11969
    	24                    20                     18                      12296
    	25                    20                     19                      19792
    	26                    12                     15                      7597
    	27                    10                     13                      6933
    	28                    11                     12                      23693
    	29                    11                     12                      18072
 		30                     8                     17                      22419


 */

    private static int numCl = 20;
    private static int numPer = 30;

    private static int rootFound = 1;
    private static int loopFound = -1;
    private static int notFound = 0;

    static int[][] perevods = new int[][]{
            {1, 1, 6, 16365},
            {2, 6, 2, 1902},
            {3, 6, 3, 5940},
            {4, 6, 7, 5304},
            {5, 6, 5, 6544},
            {6, 6, 8, 2068},
            {7, 5, 10, 19624},
            {8, 9, 5, 17450},
            {9, 4, 9, 769},
            {10, 10, 9, 20636},
            {11, 9, 11, 2870},
            {12, 10, 9, 16876},
            {13, 13, 6, 20881},
            {14, 10, 13, 1878},
            {15, 13, 15, 1073},
            {16, 15, 13, 13285},
            {17, 13, 16, 18122},
            {18, 16, 17, 16795},
            {19, 13, 14, 3907},
            {20, 11, 12, 4522},
            {21, 12, 14, 22027},
            {22, 14, 12, 23494},
            {23, 14, 20, 11969},
            {24, 20, 18, 12296},
            {25, 20, 19, 19792},
            {26, 12, 15, 7597},
            {27, 10, 13, 6933},
            {28, 11, 12, 23693},
            {29, 11, 12, 18072},
            {30, 8, 17, 22419}
    };
    static List<Node> clients = new ArrayList<>();
    static Set<Integer> usedPers = new HashSet<>();

    static Node getToNode(int per) {
        int clTo = perevods[per - 1][2];
        Node clientTo = clients.get(clTo - 1);
        return clientTo;
    }

    static int getToNodeNum(int per) {
        int clTo = perevods[per - 1][2];
        return clTo;
    }

    static int getFrNodeNum(int per) {
        int clTo = perevods[per - 1][1];
        return clTo;
    }

    static Node getFrNode(int per) {
        int clNum = perevods[per - 1][1];
        Node clientFr = clients.get(clNum - 1);
        return clientFr;
    }

    static int getSum(int per) {
        int sum = perevods[per - 1][3];
        return sum;
    }

    // class Route - маршрут
    private static class Route {
        public Node getRoot() {
            return root;
        }

        public void setRoot(Node root) {
            this.root = root;
        }

        public int getNumOfSteps() {
            return numOfSteps;
        }

        public void setNumOfSteps(int numOfSteps) {
            this.numOfSteps = numOfSteps;
        }

        public List<Integer> getPers() {
            return pers;
        }

        public void setPers(List<Integer> pers) {
            this.pers = pers;
        }

        public Set<Integer> getNodes() {
            return nodes;
        }

        public void setNodes(Set<Integer> nodes) {
            this.nodes = nodes;
        }

        Node root;
        int numOfSteps;


        public int getRootNum() {
            return rootNum;
        }

        public void setRootNum(int rootNum) {
            this.rootNum = rootNum;
        }

        int rootNum;
        List<Integer> pers = new ArrayList<>();
        Set<Integer> nodes = new HashSet<>();

        public String getPath() {
            StringBuffer result = new StringBuffer("");
            pers.forEach(per -> {
                result.append("per(" + per + ") " + getFrNodeNum(per) + "->" + getToNodeNum(per) + "  ");
            });
            return result.toString();
        }

        public String getSums() {
            StringBuffer result = new StringBuffer("");
            nodes.forEach(n -> {
                Node node = clients.get(n - 1);
                result.append(node.getAddSum() + "->" + node.nodeId + "->" + node.getSubSum() + "  ");
            });
            return result.toString();
        }

        public Route(int rootNum) {
            this.rootNum = rootNum;
            root = clients.get(rootNum - 1);
            nodes.add(rootNum);
        }

        public void addPer(int per) {
            pers.add(per);
            numOfSteps++;
        }

        public void addNode(int nodeNum) {
            nodes.add(nodeNum);
        }

        public Route clone() {
            Route cl = new Route(rootNum);
            pers.forEach(per -> cl.addPer(per));
            nodes.forEach(node -> cl.addNode(node));
            return cl;
        }

        public int checkNode(int nodeNum) {
            if (nodeNum == rootNum) {
                return rootFound;
            }
            if (nodes.contains(nodeNum)) {
                return loopFound;
            }
            return notFound;
        }

        public boolean isEqual(Route route) {
            if (route.getNumOfSteps() != numOfSteps) {
                return false;
            }
            return pers.stream().filter(per -> !route.pers.contains(per)).count() == 0;
        }
    } // class Route - маршрут

    // class Node - клиент
    private static class Node {
        public int getNodeId() {
            return nodeId;
        }

        public void setNodeId(int nodeId) {
            this.nodeId = nodeId;
        }

        public int getAddSum() {
            return addSum;
        }

        public void setAddSum(int addSum) {
            this.addSum = addSum;
        }

        public int getSubSum() {
            return subSum;
        }

        public void setSubSum(int subSum) {
            this.subSum = subSum;
        }

        public List<Integer> getPerevs() {
            return perevs;
        }

        public void setPerevs(List<Integer> perevs) {
            this.perevs = perevs;
        }

        public List<Node> getNodes() {
            return nodes;
        }

        public void setNodes(List<Node> nodes) {
            this.nodes = nodes;
        }

        public Set<Integer> getIntoPers() {
            return intoPers;
        }

        public void setIntoPers(Set<Integer> intoPers) {
            this.intoPers = intoPers;
        }

        public int getNumIntoPers() {
            return numIntoPers;
        }

        public void setNumIntoPers(int numIntoPers) {
            this.numIntoPers = numIntoPers;
        }

        public int getNumFromPers() {
            return numFromPers;
        }

        public void setNumFromPers(int numFromPers) {
            this.numFromPers = numFromPers;
        }

        int nodeId;
        int addSum;
        int subSum;
        List<Integer> perevs = new ArrayList();
        List<Node> nodes = new ArrayList<>();
        Set<Integer> intoPers = new HashSet<>();
        int numIntoPers = 0;
        int numFromPers = 0;

        public Node(int nodeId) {
            this.nodeId = nodeId;
        }

        public void sumAdd(int sum) {
            addSum += sum;
        }

        public void sumSub(int sum) {
            subSum += sum;
        }

        public void addIntoPer(int per) {
            intoPers.add(per);
            numIntoPers++;
        }

        public void addPer(int per) {
            perevs.add(per);
            Node clientTo = getToNode(per);
            int sum = getSum(per);
            clientTo.sumAdd(sum);
            sumSub(sum);
            nodes.add(clientTo);
            numFromPers++;
        }

        int numOfCicles;

        public void incrCicles() {
            numOfCicles++;
        }

        public boolean isToStartCicle() {
            return numIntoPers > 0 && numFromPers > numOfCicles;
        }
    } // class Node - клиент

    static void setPerevods() {
        for (int i = 1; i <= numPer; i++) {
            Node clientFr = getFrNode(i);
            clientFr.addPer(i);
            Node clientTo = getToNode(i);
            clientTo.addIntoPer(i);
//            System.out.println(" per " + clientFr.nodeId + " " + clientFr.numFromPers);
//            System.out.println(" per " + clientTo.nodeId + " " + clientTo.numIntoPers);
        }
    }

    static void setNodes() {
        for (int i = 1; i <= numCl; i++) {
            Node node = new Node(i);
            clients.add(node);
        }
    }

    static Node getNode(int client) {
        return clients.get(client - 1);
    }

    static List<Route> cicles = new ArrayList<>();

    static void addCicle(Route route) {
        if (cicles.stream().filter(rt -> route.isEqual(rt)).count() > 0)
            return;
        cicles.add(route);
        route.getNodes().forEach(nodeId -> {
            getNode(nodeId).incrCicles();
        });
    }

    // найти все маршруты стартующие из Root (клиента
    static void findRouts(int per, Route route) {
        Node next = route.getRoot();
        if (per > 0) {
            int nextNode = getToNodeNum(per);
            route.addPer(per);
            usedPers.add(per);
            //           System.out.println(" rout " + route.getPath());
            if (route.checkNode(nextNode) == rootFound) {
                //               System.out.println(" cicle found " + route.getPath());
                addCicle(route);
                return;
            }
            if (route.checkNode(nextNode) == loopFound) {
//                System.out.println(" loop found " + route.getPath());
                return;
            }
            next = getToNode(per);
            route.addNode(nextNode);
        }
        next.getPerevs().stream().filter(it -> !isUsed(it)).forEach(it -> {
            Route clone = route.clone();
            findRouts(it, clone);
        });
        return;
    }  // найти все маршруты стартующие из Root (клиента

    static boolean isUsed(int per) {
        return usedPers.contains(per);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        setNodes();
        setPerevods();
        clients.stream().filter(node -> node.isToStartCicle()).forEach(node -> {
            usedPers.clear();
            Route route = new Route(node.getNodeId());
//            System.out.println(" start route node = " + node.getNodeId());
            findRouts(0, route);
        });

        cicles.stream().filter(route -> route.getNumOfSteps() > 3).forEach(route -> {
            System.out.println(" cicle " + route.getPath());
            System.out.println(" cicle sums " + route.getSums());
        });

        System.out.println(" finished ");
    }

}