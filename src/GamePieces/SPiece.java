package GamePieces;


import Game.Tetris;
import GamePieces.Piece;


/**
 *
 * @author JulioL
 */
public class SPiece extends Piece {

    public SPiece(Tetris board) {
        this(board, PieceType.S);
    }

    public SPiece(Tetris board, PieceType type) {
        super(board, type);
        super.setCurrRow(-Constants.SRow);
        super.setRowsCols(Constants.SRow, Constants.SCol);
        super.setImage(Constants.SImg);
        super.setPiece(Constants.SRotations[0]);
        super.setOffsets(Constants.SOffset);
        super.setMod(2);
        super.setRotations(Constants.SRotations);
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
