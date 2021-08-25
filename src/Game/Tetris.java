package Game;

import GamePieces.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 *
 * @author JulioL
 */
public class Tetris extends Settings {

    private Pane display;
    private Scene scene;
    private Rectangle cover;

    private ImageView preview;
    private Rectangle nextBackground;
    private double xNextB;
    private double yNextB;

    private Rectangle bounds;
    private double boardWidth;
    private double boardHeight;
    private double startX;
    private double startY;

    private boolean grid[];
    private Cell gridImgs[];

    private Piece currentPiece;
    private Piece nextPiece;

    private int[] scores;
    
    private Label title;
    private Label[] labels;
    private HBox labelContainer;
    private VBox[] div;

    private Button play;
    private int bWidth = 100;

    public Tetris() {
        grid = new boolean[ROWS * COLS];
        gridImgs = new Cell[ROWS * COLS];
        
        boardWidth = COLS * pieceWidth;
        boardHeight = ROWS * pieceHeight;
        
        bounds = new Rectangle();
        this.setBounds();

        play = new Button("PLAY");
        this.setPlayButton();

        cover = new Rectangle();
        this.setCover();

        nextBackground = new Rectangle();
        preview = new ImageView();
        this.setPreview();

        title = new Label("TETRIS");
        
        scores = new int[3];

        labels = new Label[6];
        div = new VBox[2];
        div[0] = new VBox();
        div[1] = new VBox();
        labelContainer = new HBox();
        this.setLabel();

        display = new Pane();
        this.setDisplay();

        scene = new Scene(display);
        this.setScene();

        setUpBlocks();
    }

    public void setUpGame() {
        if (currentPiece != null) {
            display.getChildren().remove(currentPiece.getImg());
        }

        this.freeBlocks();
        currentPiece = randomPiece();
        currentPiece.addToBoard();
        nextPiece = randomPiece();
        updatePreview();
        for (int i = 0; i < scores.length; i++) {
            scores[i] = 0;
        }
        updateLabels();
        updatePlayButton(false);
        this.drop();
    }

    public void gameover() {
        currentPiece.kill();
        updatePlayButton(true);
    }

    public void updatePlayButton(boolean on) {
        double op = .3;
        if (on) {
            op = 1;
        }
        play.setDisable(!on);
        play.setOpacity(op);
    }

    private void setScene() {
        scene.setOnKeyPressed(e -> {
            KeyCode kc = e.getCode();
            if ((currentPiece != null) && (kc == KeyCode.D || kc == KeyCode.A)) {
                int dir = -1;
                if (e.getCode() == KeyCode.D) {
                    dir = 1;
                }
                currentPiece.moveRightLeft(dir);
            } else if ((currentPiece != null) && (kc == KeyCode.SPACE)) {
                currentPiece.rotate(1);
            } else if ((currentPiece != null) && kc == KeyCode.S) {
                currentPiece.pause();
                int code = currentPiece.dropDown();
                if (code == 1) {
                    //piece has been placed
                    this.createPiece();
                    this.drop();
                } else if (code == 2) {
                    //game over
                    this.gameover();
                }
                currentPiece.resume();
            }
        });
    }

    private void setDisplay() {
        display.setPrefHeight(HEIGHT);
        display.setPrefWidth(WIDTH);

        display.setStyle("-fx-background-color: black");

        if (display != null) {
            display.getChildren().addAll(bounds, cover,
                    nextBackground, preview, labelContainer, play, title);
        }
    }

    private void setPreview() {
        nextBackground.setWidth(pieceWidth * 7);
        nextBackground.setHeight(pieceHeight * 5);
        xNextB = startX + boardWidth + 10;
        nextBackground.setLayoutX(xNextB);
        yNextB = startY;
        nextBackground.setLayoutY(startY);
        nextBackground.setFill(Color.BLACK);
        nextBackground.setStroke(Color.WHITE);
    }

    private void setBounds() {
        bounds.setWidth(boardWidth);
        bounds.setHeight(boardHeight);
        bounds.setFill(Color.BLACK);
        bounds.setStroke(Color.WHITE);
        startX = (30);
        startY = (60);
        bounds.setLayoutX(startX);
        bounds.setLayoutY(startY);
    }

    private void setPlayButton() {
        play.setPrefWidth(bWidth);
        play.setLayoutX(startX + (boardWidth / 2.0) - (bWidth / 2.0));
        play.setLayoutY(startY + boardHeight + 10);
        play.setOnMousePressed(e -> setUpGame());
    }

    private void setCover() {
        cover.setFill(Color.BLACK);
        cover.setWidth(boardWidth);
        cover.setHeight(startY);
        cover.setLayoutX(startX);
    }

    public void setLabel() {

        for (int i = 0; i < labels.length / 2; i++) {
            labels[i] = new Label(String.format("%-10s", names[i]));
            labels[i].setFont(new Font("contrast", 25));
            labels[i].setTextFill(Color.WHITESMOKE);
            div[0].getChildren().add(labels[i]);
            div[0].setSpacing(45);
        }

        for (int i = labels.length / 2; i < labels.length; i++) {
            labels[i] = new Label(String.format("%-10s", "0").replace(" ", "0"));
            labels[i].setFont(new Font("contrast", 25));
            labels[i].setTextFill(Color.WHITESMOKE);
            div[1].getChildren().add(labels[i]);
            div[1].setSpacing(45);
        }

        labelContainer.getChildren().addAll(div[0], div[1]);
        labelContainer.setPrefWidth(this.nextBackground.getWidth());
        double x = this.nextBackground.getLayoutX();
        double y = this.nextBackground.getLayoutY() + this.nextBackground.getHeight() + 10;
        labelContainer.setLayoutX(x);
        labelContainer.setLayoutY(y);
        title.setPrefWidth(118);
        title.setFont(new Font("contrast", 40));
        title.setLayoutX(WIDTH/2.0 - 59);
        title.setTextFill(Color.WHITESMOKE);
    }

    public void updatePreview() {
        if (nextPiece != null) {
            preview.setImage(new Image(nextPiece.getSource()));
            preview.setFitHeight(nextPiece.actualHeight());
            preview.setFitWidth(nextPiece.actualWidth());

            double pWidth = nextPiece.actualWidth();
            double pHeight = nextPiece.actualHeight();

            double x = xNextB + (nextBackground.getWidth() / 2.0) - (pWidth / 2.0);
            double y = yNextB + (nextBackground.getHeight() / 2.0) - (pHeight / 2.0);

            preview.setLayoutX(x);
            preview.setLayoutY(y);
        }
    }

    private void setUpBlocks() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int index = i * (COLS) + j;
                gridImgs[index] = new Cell(this);
                gridImgs[index].setLayoutX(j * pieceWidth + startX);
                gridImgs[index].setLayoutY(i * pieceHeight + startY);
                display.getChildren().add(gridImgs[index]);
            }
        }
    }

    public void freeBlocks() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int index = i * (COLS) + j;
                gridImgs[index].free();
                grid[index] = false;
            }
        }
    }

    public void removePiece(Piece piece) {
        display.getChildren().remove(piece.getImg());
    }

    public void createPiece() {
        removePiece(currentPiece);
        currentPiece = null;

        currentPiece = nextPiece;
        nextPiece = randomPiece();
        updatePreview();

        currentPiece.addToBoard();
    }

    public Piece randomPiece() {
        int rand = (int) (Math.random() * 100);
        rand = rand % 7;
        Piece p = null;
        switch (rand) {
            case 0:
                p = new IPiece(this);
                break;
            case 1:
                p = new JPiece(this);
                break;
            case 2:
                p = new LPiece(this);
                break;
            case 3:
                p = new OPiece(this);
                break;
            case 4:
                p = new SPiece(this);
                break;
            case 5:
                p = new TPiece(this);
                break;
            case 6:
                p = new ZPiece(this);
                break;
        }
        return p;
    }

    public boolean canDrop(Piece piece, int row, int col, boolean drop) {
        
        if (drop && row < 0) {
            return false;
        }
        boolean p[] = piece.getPiece();
        int pieceCol = piece.getCols();
        int pieceRow = piece.getRows();
        if (!drop) {
            if (piece.getCurrRow() + pieceRow <= 0  && (col >= 0 && col  + pieceCol <= COLS)) {
                return true;
            }
            if (col <= -1 || col  + pieceCol > COLS) {
                return false;
            }
            if (row + pieceRow > ROWS) {
                return false;
            }
        }
        for (int i = row, pR = 0; i < row + pieceRow; i++, pR++) {
            for (int j = col, pC = 0; j < col + pieceCol; j++, pC++) {
                int gIndex = (i * COLS) + j;
                int pIndex = pR * pieceCol + pC;
                if (!drop) {
                    //check
                    if (i >= 0 && j >= 0 && grid[gIndex] && p[pIndex]) {
                        return false;
                    }
                } else {
                    //drop
                    if (!grid[gIndex] && p[pIndex]) {
                        grid[gIndex] = true;
                        gridImgs[gIndex].fill(piece.getType());
                    }
                }
            }
        }
        if (drop) {
            piece.kill();
        }
        return true;
    }

    private void dropRow(int r) {
        for (int row = r; row >= 0; row--) {
            for (int col = 0; col < COLS; col++) {
                int index = row * COLS + col;
                if (row == 0) {
                    grid[index] = false;
                    gridImgs[index].free();
                } else {
                    int topIndex = (row - 1) * COLS + col;
                    Cell top = gridImgs[topIndex];
                    gridImgs[index].swap(top);
                    grid[index] = grid[topIndex];
                }
            }
        }
    }

    public int clearRow(int row) {
        int rowsCleared = 0;
        for (int r = row; r >= currentPiece.getCurrRow(); r--) {
            boolean clear = true;
            while (true) {
                for (int c = 0; c < COLS; c++) {
                    int index = r * COLS + c;
                    if (!grid[index]) {
                        clear = false;
                        break;
                    }
                }
                if (clear) {
                    System.out.println("CLEAR ROW");
                    dropRow(r);
                    rowsCleared += 1;
                } else {
                    break;
                }
            }
        }
        scores[1] += rowsCleared;
        return rowsCleared;
    }

    public void awardPoints(int numCleared) {
        if (numCleared == 0) {
            return;
        }

        int multi = points[numCleared - 1];
        scores[0] += (numCleared * multi) + (scores[2] * multi);
    }

    public void updateLevel() {
        if (scores[2] == 0) {
            return;
        }

        if (scores[2] % incLvl == 0) {
            scores[2]++;
        }
    }

    public void updateLabels() {
        this.updateLevel();
        for (int i = 0, j = 3; i < scores.length; i++, j++) {
            labels[j].setText(String.format("%10d", scores[i]).replace(" ", "0"));
        }
    }

    public void updateCover() {
        cover.toFront();
        title.toFront();
    }

    public Scene getScene() {
        return scene;
    }

    public void drop() {
        currentPiece.start(delay, speed);
    }

    public double getBoardWidth() {
        return boardWidth;
    }

    public double getBoardHeight() {
        return boardHeight;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public Pane getDisplay() {
        return display;
    }

    public double getPieceWidth() {
        return pieceWidth;
    }

    public double getPieceHeight() {
        return pieceHeight;
    }
    
    

}
