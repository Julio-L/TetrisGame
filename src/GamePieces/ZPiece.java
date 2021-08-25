package GamePieces;

import Game.Tetris;


/**
 *
 * @author JulioL
 */
public class ZPiece extends Piece {

    public ZPiece(Tetris board) {
        this(board, PieceType.Z);
    }

    public ZPiece(Tetris board, PieceType type) {
        super(board, type);
        super.setCurrRow(-Constants.ZRow);
        super.setRowsCols(Constants.ZRow, Constants.ZCol);
        super.setImage(Constants.ZImg);
        super.setPiece(Constants.ZRotations[0]);
        super.setMod(2);
        super.setOffsets(Constants.ZOffset);
        super.setRotations(Constants.ZRotations);
        calculateXYPivot();
    }

    @Override
    public void calculateXYPivot() {
        double pieceHeight = this.getBoard().getPieceHeight();
        double pieceWidth = this.getBoard().getPieceWidth();
        double xPivot = pieceWidth + (pieceWidth/2);
        double yPivot = pieceHeight + (pieceHeight/2);
        super.setXYPivot(xPivot, yPivot);
    }

}
