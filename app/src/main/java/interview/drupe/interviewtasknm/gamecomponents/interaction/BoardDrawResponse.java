package interview.drupe.interviewtasknm.gamecomponents.interaction;

public class BoardDrawResponse {
    final private boolean wasDrawSuccessfull;
    final private boolean[][] currentBoard;

    public BoardDrawResponse(boolean wasDrawn, boolean[][] currentBoard) {
        this.wasDrawSuccessfull = wasDrawn;
        this.currentBoard = currentBoard;
    }

    public boolean[][] getCurrentBoard() {
        return currentBoard;
    }

    public boolean wasDrawSuccessful() {
        return wasDrawSuccessfull;
    }
}
