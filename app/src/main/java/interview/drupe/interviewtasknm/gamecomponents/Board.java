package interview.drupe.interviewtasknm.gamecomponents;

import android.util.Pair;

import java.util.HashSet;

import interview.drupe.interviewtasknm.gamecomponents.gameblocks.Block;
import interview.drupe.interviewtasknm.gamecomponents.interaction.BoardDrawResponse;

public class Board {
    private int tableHeight;
    private int tableWidth;

    private boolean[][] gameTable;
    private Object drawSyncObj = new Object();

    public Board(int tableWidth, int tableHeight) {
        this.tableHeight = tableHeight;
        this.tableWidth = tableWidth;

        this.gameTable = new boolean[tableHeight][tableWidth];
    }

    public int getTableHeight() {
        return tableHeight;
    }

    public int getTableWidth() {
        return tableWidth;
    }

    public BoardDrawResponse draw(Block block, int startX, int startY) {
        synchronized (drawSyncObj) {
            boolean drawCheckResult = checkSpaceForNewBlock(block, startX, startY);
            String resultStr = "";
            boolean[][] drawnMatrix = new boolean[tableHeight][tableWidth];

            if (drawCheckResult) {
                for (int r = 0; r < tableHeight; r++) {
                    for (int c = 0; c < tableWidth; c++) {
                        if (startX <= c &&
                                startY <= r &&
                                (r - startY < block.getSize()) &&
                                (c - startX < block.getSize())) {
                            drawnMatrix[r][c] = block.getRawShapeMatrix()[r - startY][c - startX];
                        } else {
                            drawnMatrix[r][c] = gameTable[r][c];
                        }
                    }
                }
            }

            return new BoardDrawResponse(drawCheckResult, drawnMatrix);
        }
    }

    public void save(Block block, int xPos, int yPos) {
        synchronized (drawSyncObj) {
            HashSet<Pair<Integer, Integer>> populatedIndexes = block.getAsHashSet();
            for (Pair<Integer, Integer> indexes : populatedIndexes) {
                int boardColIndex = indexes.first + xPos;
                int boardRowIndex = indexes.second + yPos;

                gameTable[boardRowIndex][boardColIndex] = true;
            }
        }
    }

    private boolean checkSpaceForNewBlock(Block src, int startX, int startY) {
        boolean hasSpace = true;
        HashSet<Pair<Integer, Integer>> populatedIndexes = src.getAsHashSet();
        for (Pair<Integer, Integer> indexes : populatedIndexes) {
            int boardRowIndex = indexes.second + startY;
            int boardColIndex = indexes.first + startX;

            hasSpace &= startX >= 0;
            hasSpace &= startY >= 0;
            hasSpace &= boardRowIndex < tableHeight;
            hasSpace &= boardColIndex < tableWidth;
            hasSpace = hasSpace && gameTable[boardRowIndex][boardColIndex] == false;
        }

        return hasSpace;
    }
}
