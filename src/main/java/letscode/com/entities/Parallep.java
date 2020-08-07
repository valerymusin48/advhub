package letscode.com.entities;

import java.util.Arrays;
import java.util.List;

public class Parallep {

    private int length;
    private int width;
    private int height;


    public Parallep(int length, int width, int height ) {
        this.length = length;
        this.width = width;
        this.height = height;
    }
    public int getValue() {
        return length * width * height ;
    }
    public List<int[]> getTurns() {
        int[][] turns =  {
            {
                length, width, height 
            },
            {
                length, height, width
            },
            {
                width, length, height 
            },
            {
                height, width, length
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
            turn[2] <= height )  {
            return true;
        }
        return false;
    }
}
