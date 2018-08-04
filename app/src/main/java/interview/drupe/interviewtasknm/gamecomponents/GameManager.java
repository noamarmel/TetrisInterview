package interview.drupe.interviewtasknm.gamecomponents;

import java.util.Timer;
import java.util.TimerTask;

import interview.drupe.interviewtasknm.gamecomponents.gameblocks.Block;
import interview.drupe.interviewtasknm.gamecomponents.gameblocks.BlockFactory;

public class GameManager implements userInteractionListener {
    private static int DEFAULT_BOARD_WIDTH = 10;
    private static int DEFAULT_BOARD_HEIGHT = 15;
    private static int DEFAULT_BLOCK_INSERTION_POSITION_X = 5;
    private static int DEFAULT_BLOCK_INSERTION_POSITION_Y = 0;

    private Object boardLoopSyncObj = new Object();
    private Board gameBoard;
    private GameBlockHolder activeBlock;
    private boolean isGameBoardFull = false;
    private Timer timer;

    private gameEventListener gameEventListener;

    public GameManager(gameEventListener eventListener) {
        this.gameEventListener = eventListener;
        timer = new Timer();
        gameBoard = new Board(DEFAULT_BOARD_WIDTH, DEFAULT_BOARD_HEIGHT);

//        gameEventListener.onDrawResult(gameBoard.currentDraw);
    }

    public void runGameLoop() {
        if (!isGameBoardFull) {
            timer.schedule(getNextStepTask(), 1000);
        }
    }

    private TimerTask getNextStepTask() {
        TimerTask gameLoopTask = new TimerTask() {
            @Override
            public void run() {
                synchronized (boardLoopSyncObj) {
                    if (!isGameBoardFull) {
                        //schedule next step
                        runGameLoop();

                        if (activeBlock == null) {
                            activeBlock = createNewActiveBlock();
                        }

                        BoardDrawResponse response = gameBoard.draw(activeBlock.block, activeBlock.xPosition, activeBlock.yPosition + 1);
                        if (response.wasDrawSuccessful()) {
                            activeBlock.yPosition += 1;
//                            gameEventListener.onDrawResult(response.getCurrentDraw());
                            gameEventListener.onDrawResult(response.getCurrentBoard(), gameBoard.getTableWidth(), gameBoard.getTableHeight());
                        } else {
                            if (activeBlock.yPosition == DEFAULT_BLOCK_INSERTION_POSITION_Y) {
                                isGameBoardFull = true;
                                gameEventListener.onGameEnd();
                            }
                            else
                            {
                                gameBoard.save(activeBlock.block, activeBlock.xPosition, activeBlock.yPosition);
                                activeBlock = null;
//                                gameEventListener.onDrawResult(null);//gameBoard.currentDraw);
                                gameEventListener.onDrawResult(response.getCurrentBoard(), gameBoard.getTableWidth(), gameBoard.getTableHeight());
                            }
                        }
                    }
                }
            }
        };

        return gameLoopTask;
    }

    private GameBlockHolder createNewActiveBlock() {
        return new GameBlockHolder(
                BlockFactory.getRandomBlock(),
                DEFAULT_BLOCK_INSERTION_POSITION_X,
                DEFAULT_BLOCK_INSERTION_POSITION_Y - 1);
    }

    @Override
    public void onMoveRight() {
        BoardDrawResponse actionResponse = gameBoard.draw(activeBlock.block, activeBlock.xPosition + 1, activeBlock.yPosition );
        if (actionResponse.wasDrawSuccessful()) {
            activeBlock.xPosition += 1;
//            gameEventListener.onDrawResult(actionResponse.getCurrentDraw());
            gameEventListener.onDrawResult(actionResponse.getCurrentBoard(), gameBoard.getTableWidth(), gameBoard.getTableHeight());
        }
    }

    @Override
    public void onMoveLeft() {
        BoardDrawResponse actionResponse = gameBoard.draw(activeBlock.block, activeBlock.xPosition - 1, activeBlock.yPosition );
        if (actionResponse.wasDrawSuccessful()) {
            activeBlock.xPosition -= 1;
//            gameEventListener.onDrawResult(actionResponse.getCurrentDraw());
            gameEventListener.onDrawResult(actionResponse.getCurrentBoard(), gameBoard.getTableWidth(), gameBoard.getTableHeight());
        }

    }

    @Override
    public void onRotate() {
        activeBlock.block.rotate90DegClockwise();
        BoardDrawResponse actionResponse = gameBoard.draw(activeBlock.block, activeBlock.xPosition, activeBlock.yPosition );
        if (actionResponse.wasDrawSuccessful()) {
//            gameEventListener.onDrawResult(actionResponse.getCurrentDraw());
            gameEventListener.onDrawResult(actionResponse.getCurrentBoard(), gameBoard.getTableWidth(), gameBoard.getTableHeight());
        }
    }

    private class GameBlockHolder {
        Block block;
        int xPosition;
        int yPosition;

        public GameBlockHolder(Block baseBlock, int xPosition, int yPosition) {
            this.block = baseBlock;
            this.xPosition = xPosition;
            this.yPosition = yPosition;
        }
    }
}
