package interview.drupe.interviewtasknm.gamecomponents.gameblocks;

public class StraightLine extends Block {

    public StraightLine() {
        super(1, 4);
    }

    @Override
    protected void setupShapeMatrix() {
        this.shapeMatrix = new boolean[][]{
                {true, false, false, false},
                {true, false, false, false},
                {true, false, false, false},
                {true, false, false, false}};
    }
}
