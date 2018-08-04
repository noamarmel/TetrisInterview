package interview.drupe.interviewtasknm.gamecomponents.gameblocks;

public class Lshape extends Block {
    public Lshape() {
        super(2, 3);
}

    @Override
    protected void setupShapeMatrix() {
        this.shapeMatrix = new boolean[][]{
                {true, false, false},
                {true, false, false},
                {true, true, false}};
    }
}
