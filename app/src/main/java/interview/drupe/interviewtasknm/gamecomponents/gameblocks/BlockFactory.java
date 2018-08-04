package interview.drupe.interviewtasknm.gamecomponents.gameblocks;

import java.util.Random;

public class BlockFactory {
    public enum BlockType {
        SQUARE, L, LINE;

        public static BlockType getRandom()
        {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }

    public static Block getBlock(BlockType type)
    {
        Block retBlock = null;

        switch (type){
            case L:
                retBlock = new Lshape();
                break;
            case LINE:
                retBlock = new StraightLine();
                break;
            case SQUARE:
                retBlock =  new Square();
                break;
        }

        return retBlock;
    }

    public static Block getRandomBlock()
    {
        return getBlock(BlockType.getRandom());
    }
}
