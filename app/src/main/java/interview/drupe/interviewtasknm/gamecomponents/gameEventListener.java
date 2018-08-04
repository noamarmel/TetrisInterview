package interview.drupe.interviewtasknm.gamecomponents;

public interface gameEventListener {
    void onDrawResult(boolean[][] currBoard, int width, int height);
    void onGameEnd();
}
