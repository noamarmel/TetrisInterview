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
        int len = shapeMatrix.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                boolean temp = shapeMatrix[i][j];
                shapeMatrix[i][j] = shapeMatrix[len - 1 - j][len - 1 - i];
                shapeMatrix[len - 1 - j][len - 1 - i] = temp;
            }
        }

        for(int i = 0; i < len/2; i++){
            for(int j = 0;j < len; j++){
                boolean temp = shapeMatrix[i][j];
                shapeMatrix[i][j] = shapeMatrix[len-1 -i][j];
                shapeMatrix[len -1 -i][j] = temp;
            }
        }
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
