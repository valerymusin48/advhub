package letscode.com.entities;

import java.util.Arrays;
import java.util.List;

public class Parallep {

    private int length;
    private int width;
    private int hight;


    public Parallep(int length, int width, int hight) {
        this.length = length;
        this.width = width;
        this.hight = hight;
    }
    public int getValue() {
        return length * width * hight;
    }
    public List<int[]> getTurns() {
        int[][] turns =  {
            {
                length, width, hight
            },
            {
                length, hight, width
            },
            {
                width, length, hight
            },
            {
                hight, width, length
            }

        };
        return Arrays.asList(turns);
    }
    public boolean fitsIn(Parallep parallep) {
        return parallep.getTurns().stream().filter(t -> lessEq(t)).count() > 0;
    }
    private boolean lessEq(int[] turn) {
        if (turn[0] <= length &&
            turn[1] <= width &&
            turn[2] <= hight)  {
            return true;
        }
        return false;
    }
}
