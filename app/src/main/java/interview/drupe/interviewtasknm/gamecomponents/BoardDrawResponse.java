package interview.drupe.interviewtasknm.gamecomponents;

public class BoardDrawResponse {
    final private boolean wasDrawSuccessfull;
//    final private String currentDraw;
    final private boolean[][] currentBoard;

//    public BoardDrawResponse(boolean wasDrawn, String currentDraw) {
//        this.wasDrawSuccessfull = wasDrawn;
////        this.currentDraw = currentDraw;
//    }

    public BoardDrawResponse(boolean wasDrawn, boolean[][] currentBoard) {
        this.wasDrawSuccessfull = wasDrawn;
        this.currentBoard = currentBoard;
    }

//    public String getCurrentDraw() {
//        return currentDraw;
//    }

        public boolean[][] getCurrentBoard() {
        return currentBoard;
    }

    public boolean wasDrawSuccessful() {
        return wasDrawSuccessfull;
    }
}
