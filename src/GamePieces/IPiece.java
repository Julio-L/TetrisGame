package GamePieces;


import Game.Tetris;
import GamePieces.Piece;


/**
 *
 * @author JulioL
 */
public class IPiece extends Piece {

    public IPiece(Tetris board) {
        this(board, PieceType.I);
    }

    public IPiece(Tetris board, PieceType type) {
        super(board, type);
        super.setCurrRow(-Constants.IRow);
        super.setRowsCols(Constants.IRow, Constants.ICol);
        super.setImage(Constants.IImg);
        super.setPiece(Constants.IRotations[0]);
        super.setRotations(Constants.IRotations);
        super.setOffsets(Constants.IOffset);
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
