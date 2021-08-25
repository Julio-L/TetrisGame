package GamePieces;


import Game.Tetris;
import GamePieces.Piece;


/**
 *
 * @author JulioL
 */
public class LPiece extends Piece {

    public LPiece(Tetris board) {
        this(board, PieceType.L);
    }

    public LPiece(Tetris board, PieceType type) {
        super(board, type);
        super.setCurrRow(-Constants.LRow);
        super.setRowsCols(Constants.LRow, Constants.LCol);
        super.setImage(Constants.LImg);
        super.setPiece(Constants.LRotations[0]);
        super.setRotations(Constants.LRotations);
        super.setOffsets(Constants.LOffset);
        calculateXYPivot();
    }

    @Override
    public void calculateXYPivot() {
        double pieceHeight = this.getBoard().getPieceHeight();
        double pieceWidth = this.getBoard().getPieceWidth();
        double xPivot = (pieceWidth / 2);
        double yPivot = pieceHeight + (pieceHeight / 2);
        super.setXYPivot(xPivot, yPivot);
    }

}
