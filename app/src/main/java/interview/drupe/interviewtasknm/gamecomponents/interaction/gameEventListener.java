package interview.drupe.interviewtasknm.gamecomponents.interaction;

public interface gameEventListener {
    void onDrawResult(boolean[][] currBoard, int width, int height);
    void onGameEnd();
}
