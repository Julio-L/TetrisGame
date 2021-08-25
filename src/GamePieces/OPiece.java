package GamePieces;


import Game.Tetris;
import GamePieces.Piece;


/**
 *
 * @author JulioL
 */
public class OPiece extends Piece {

    public OPiece(Tetris board) {
        this(board, PieceType.O);
    }

    public OPiece(Tetris board, PieceType type) {
        super(board, type);
        super.setCurrRow( -Constants.ORow);
        super.setRowsCols(Constants.ORow, Constants.OCol);
        super.setImage(Constants.OImg);
        super.setPiece(Constants.ORotations[0]);
        super.setOffsets (Constants.OOffset);
        super.setRotations(Constants.ORotations);
        super.setXYPivot(0, 0);
    }

    @Override
    public void calculateXYPivot() {
        return;
    }

}
