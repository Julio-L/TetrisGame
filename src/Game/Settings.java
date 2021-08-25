package Game;


/**
 *
 * @author JulioL
 */
public class Settings {

    protected int WIDTH = 750;
    protected int HEIGHT = 900;
    

    protected int ROWS = 20;
    protected int COLS = 10;

    protected int pieceWidth = 40;
    protected int pieceHeight = 40;
    
    protected int delay = 0;
    protected int speed = 300;

    protected String[] names = {"SCORE: ", "LINE: ", "LEVEL: "};
    protected int[] points = {40, 100, 300, 400};

    protected int incLvl = 20;

    public int getWIDTH() {
        return WIDTH;
    }

    public int getROWS() {
        return ROWS;
    }


    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getCOLS() {
        return COLS;
    }

}
