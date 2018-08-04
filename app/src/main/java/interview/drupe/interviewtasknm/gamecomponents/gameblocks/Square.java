package interview.drupe.interviewtasknm.gamecomponents.gameblocks;

public class Square extends Block {

    public Square() {
        super(2, 2);
    }

    @Override
    protected void setupShapeMatrix() {
        this.shapeMatrix = new boolean[][]{
                {true, true},
                {true, true}};
    }
}
