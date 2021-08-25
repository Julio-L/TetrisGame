package Game;
import GamePieces.Constants;
import GamePieces.PieceType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author JulioL
 */
public class Cell extends ImageView {

    private String folder = "Assets/";

    private Tetris board;

    private PieceType type;

    String[] sources = {
        "lightBlue.png",
        "blue.png",
        "orange.png",
        "yellow.png",
        "green.png",
        "purple.png",
        "red.png"
    };

    public Cell(Tetris board) {
        this.board = board;
        this.setFitHeight(board.getPieceHeight());
        this.setFitWidth(board.getPieceWidth());
        this.setOpacity(0);
        type = PieceType.E;
    }

    public void fill(PieceType type) {
        this.type = type;
        if (type != PieceType.E) {
            this.setImage(new Image(folder + sources[type.ordinal()]));
            this.setOpacity(1);
        }else{
            this.setImage(null);
        }
    }

    public void free() {
        this.type = PieceType.E;
        this.setImage(null);
    }

    public void swap(Cell cell) {
        this.fill(cell.type);
        cell.fill(PieceType.E);
    }

}
