package GamePieces;


import Game.Tetris;
import GamePieces.Piece;


/**
 *
 * @author JulioL
 */
public class JPiece extends Piece {

    public JPiece(Tetris board) {
        this(board, PieceType.J);
    }

    public JPiece(Tetris board, PieceType type) {
        super(board, type);
        super.setCurrRow(-Constants.JRow);
        super.setRowsCols(Constants.JRow, Constants.JCol);
        super.setImage(Constants.JImg);
        super.setPiece(Constants.JRotations[0]);
        super.setOffsets(Constants.JOffset);
        super.setRotations(Constants.JRotations);
        calculateXYPivot();
    }

    @Override
    public void calculateXYPivot() {
        double pieceHeight = this.getBoard().getPieceHeight();
        double pieceWidth = this.getBoard().getPieceWidth();
        double xPivot = pieceWidth + (pieceWidth / 2);
        double yPivot = pieceHeight + (pieceHeight / 2);
        super.setXYPivot(xPivot, yPivot);
    }

}
