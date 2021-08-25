package GamePieces;

import Game.Tetris;


/**
 *
 * @author JulioL
 */
public class TPiece extends Piece {

    public TPiece(Tetris board) {
        this(board, PieceType.T);
    }

    public TPiece(Tetris board, PieceType type) {
        super(board, type);
        super.setCurrRow(-Constants.TRow);
        super.setRowsCols(Constants.TRow, Constants.TCol);
        super.setImage(Constants.TImg);
        super.setPiece(Constants.TRotations[0]);
        super.setOffsets(Constants.TOffset);
        super.setRotations(Constants.TRotations);
        calculateXYPivot();
    }

    @Override
    public void calculateXYPivot() {
        double pieceHeight = this.getBoard().getPieceHeight();
        double pieceWidth = this.getBoard().getPieceWidth();
        double xPivot = pieceWidth + (pieceWidth / 2);
        double yPivot = (pieceHeight / 2);
        super.setXYPivot(xPivot, yPivot);
    }

}
