package interview.drupe.interviewtasknm.gamecomponents;

public class BoardDrawResponse {
    private boolean wasDrawSuccessfull;
    private String currentDraw;

    public BoardDrawResponse(boolean wasDrawn, String currentDraw) {
        this.wasDrawSuccessfull = wasDrawn;
        this.currentDraw = currentDraw;
    }

    public String getCurrentDraw() {
        return currentDraw;
    }

    public boolean wasDrawSuccessful() {
        return wasDrawSuccessfull;
    }
}
