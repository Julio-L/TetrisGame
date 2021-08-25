package GamePieces;
import Game.Tetris;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author JulioL
 */
public abstract class Piece {

    //access to main board
    private Tetris board;

    private boolean piece[];
    private int rows;
    private int cols;

    private int[][] offsets;
    private boolean[][] rotations;

    private int rotate;
    private int mod;

    private int offset;
    private int offsetMod;
    private double xPivot;
    private double yPivot;

    //position in the board
    private int currRow;
    private int currCol;

    private Timeline timeL;
    private KeyFrame kf;

    private String source;
    private ImageView img;
    private PieceType type;

    private Rotate ro = new Rotate();

    //index - i * (len(col)) + j
    public Piece(Tetris board, PieceType type) {
        this.board = board;
        this.type = type;
        piece = new boolean[6];
        rotate = 0;
        currCol = board.getCOLS() / 2;
        mod = 4;
        offsetMod = 4;
    }

    public abstract void calculateXYPivot();

    public void rotate(int dir) {
        this.pause();
        int bRotate = rotate;
        int bOffsetMod = offset;

        offset = (offset + dir) % offsetMod;

        rotate = (rotate + dir) % mod;

        int r = 0;
        int c = 0;
        r = currRow + offsets[offset][0];
        c = currCol + offsets[offset][1];

        if (type != PieceType.I && type != PieceType.O) {
            this.setPiece(rotations[rotate]);
        }

        swapRowsCols();
        boolean drop = board.canDrop(this, r, c, false);

        if (drop && type != type.O) {
            img.getTransforms().add(ro);
            ro.setPivotX(xPivot); //Pivot X Top-Left corner
            ro.setPivotY(yPivot); //Pivot Y
            ro.setAngle(90); //Angle degrees
            this.setCurrRowCol(r, c);
        } else {
            if (type != PieceType.I && type != PieceType.O) {
                this.setPiece(rotations[bRotate]);
            }
            offset = bOffsetMod;
            rotate = bRotate;
            swapRowsCols();
        }
        this.resume();
    }

    public void place(int row, int col) {
        img.setLayoutX(board.getStartX() + currCol * board.getPieceWidth());
        img.setLayoutY(board.getStartY() + currRow * board.getPieceHeight());
    }

    public void setCurrRowCol(int currRow, int currCol) {
        this.currCol = currCol;
        this.currRow = currRow;
    }

    public void setXYPivot(double x, double y) {
        xPivot = x;
        yPivot = y;
    }

    public void setImage(String source) {
        this.source = source;
        img = new ImageView(source);
        img.setFitWidth(board.getPieceWidth() * cols);
        img.setFitHeight(board.getPieceHeight() * rows);
    }

    public void addToBoard() {
        img.setLayoutX(board.getStartX() + currCol * board.getPieceWidth());
        img.setLayoutY(board.getStartY() + currRow * board.getPieceHeight());
        board.getDisplay().getChildren().add(img);
        board.updateCover();
    }

    public int dropDown() {
        int code = 0;
        boolean canDrop = board.canDrop(this, currRow + 1, currCol, false);
        if (canDrop) {
            incCurrRow(1);
            img.setLayoutY(img.getLayoutY() + board.getPieceHeight());
        } else {
            boolean succ = board.canDrop(this, currRow, currCol, true);
            if (succ) {
                int numCleared = board.clearRow(currRow + rows - 1);
                board.awardPoints(numCleared);
                board.updateLabels();
                //placed
                code = 1;
            } else {
                // cannot place (game over)
                code = 2;
            }
        }
        return code;
    }

    public int moveRightLeft(int dir) {
        this.pause();
        boolean canMove = board.canDrop(this, currRow, currCol + dir, false);
        if (canMove) {
            this.incCurrCol(dir);
            img.setLayoutX(img.getLayoutX() + board.getPieceWidth() * dir);
        }
        this.resume();
        return -1;
    }

    public void start(int delay, int speed) {
        timeL = new Timeline(new KeyFrame(Duration.millis(speed),
                (e) -> {
                    int code = dropDown();
                    if (code == 1) {
                        //piece has been placed
                        board.createPiece();
                        board.drop();
                    } else if(code == 2){
                        //game over
                        board.gameover();
                    }
                }
        ));
        timeL.setDelay(Duration.millis(delay));
        timeL.setCycleCount(Timeline.INDEFINITE);
        timeL.play();
    }

    public void kill() {
        timeL.stop();
    }

    public void incCurrCol(int dir) {
        currCol += dir;
    }

    public void incCurrRow(int dir) {
        currRow += dir;
    }

    public void updatePiece(boolean p[]) {
        for (int i = 0; i < p.length; i++) {
            piece[i] = p[i];
        }
    }

    public void setRowsCols(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public void swapRowsCols() {
        int temp = rows;
        rows = cols;
        cols = temp;
    }

    public String getSource() {
        return source;
    }

    public double actualWidth() {
        return board.getPieceWidth() * cols;
    }

    public double actualHeight() {
        return board.getPieceHeight() * rows;
    }

    public boolean[][] getRotations() {
        return rotations;
    }

    public double getxPivot() {
        return xPivot;
    }

    public double getyPivot() {
        return yPivot;
    }

    public void setRotations(boolean[][] rotations) {
        this.rotations = rotations;
    }

    public void setCurrRow(int currRow) {
        this.currRow = currRow;
    }

    public void pause() {
        timeL.pause();
    }

    public void resume() {
        timeL.play();
    }

    public Tetris getBoard() {
        return board;
    }

    public int getMod() {
        return mod;
    }

    public int getCurrRow() {
        return currRow;
    }

    public int getCurrCol() {
        return currCol;
    }

    public Timeline getTimeL() {
        return timeL;
    }

    public KeyFrame getKf() {
        return kf;
    }

    public ImageView getImg() {
        return img;
    }

    public void setMod(int mod) {
        this.mod = mod;
    }

    public void incRotate() {
        rotate = (rotate + 1) % mod;
    }

    public boolean[] getPiece() {
        return piece;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getRotate() {
        return rotate;
    }

    public void setPiece(boolean[] piece) {
        this.piece = piece;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public PieceType getType() {
        return type;
    }

    public int[][] getOffsets() {
        return offsets;
    }


    public void setCurrCol(int currCol) {
        this.currCol = currCol;
    }

    public void setOffsets(int[][] offsets) {
        this.offsets = offsets;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

}
