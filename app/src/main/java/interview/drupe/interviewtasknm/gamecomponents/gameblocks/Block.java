package interview.drupe.interviewtasknm.gamecomponents.gameblocks;

import android.util.Pair;

import java.util.HashMap;
import java.util.HashSet;

public abstract class Block {
    boolean[][] shapeMatrix;
    int size;

    public Block(int numCols, int numRows) {
        this.size = numCols > numRows ? numCols : numRows;

        setupShapeMatrix();
    }

    public int getSize() {
        return size;
    }

    public boolean[][] getRawShapeMatrix() {
        return shapeMatrix;
    }

    public void rotate90DegClockwise() {

    }

    public HashSet<Pair<Integer, Integer>> getAsHashSet() {
        HashSet<Pair<Integer, Integer>> set = new HashSet<>();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (shapeMatrix[r][c] == true) {
                    set.add(new Pair<Integer, Integer>(c, r));
                }
            }
        }

        return set;
    }

    protected abstract void setupShapeMatrix();
}
