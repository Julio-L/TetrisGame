package GamePieces;


/**
 *
 * @author JulioL
 */
public class Constants {

    static String assetsFolder = "Assets/";
    
    static boolean IRotations[][] = {{true, true, true, true, false, false}};
    static int IOffset[][] = {{2, -1}, {-1, 1}, {1, -2}, {-2, 2}};
    static int IRow = 1;
    static int ICol = 4;
    static String IImg = assetsFolder + "I.png";

    static boolean JRotations[][] = {
        {false, true, false, true, true, true},
        {true, false, false, true, true, true},
        {true, true, true, false, true, false},
        {true, true, true, false, false, true}
    };
    static int JOffset[][] = {{-1, 0}, {0, 0}, {0, 1}, {1, -1}};
    static int JRow = 3;
    static int JCol = 2;
    static String JImg = assetsFolder + "J.png";

    static boolean LRotations[][] = {
        {true, false, true, false, true, true},
        {true, true, true, true, false, false},
        {true, true, false, true, false, true},
        {false, false, true, true, true, true}
    };
    static int LOffset[][] = {{1, 1}, {1, -1}, {-1, 0}, {0, 0}};
    static int LRow = 3;
    static int LCol = 2;
    static String LImg = assetsFolder + "L.png";

    static boolean ORotations[][] = {{true, true, true, true, false, false}};
    static int OOffset[][] = {{0, 0}, {0, 1}, {1, -1}, {-1, 0}};
    static int ORow = 2;
    static int OCol = 2;
    static String OImg = assetsFolder + "O.png";
   

    static boolean SRotations[][] = {
        {false, true, true, true, true, false},
        {true, false, true, true, false, true}
    };
    static int SRow = 2;
    static int SCol = 3;
    static String SImg = assetsFolder + "S.png";
    static int SOffset[][] = {{0, 0}, {0, 1}, {1, -1}, {-1, 0}};

    static boolean TRotations[][] = {
        {true, true, true, false, true, false},
        {false, true, true, true, false, true},
        {false, true, false, true, true, true},
        {true, false, true, true, true, false}
    };
    static int TRow = 2;
    static int TCol = 3;
    static String TImg = assetsFolder + "T.png";
    static int TOffset[][] = {{1, -1}, {-1, 0}, {0, 0 }, {0, 1}};

    static boolean ZRotations[][] = {
        {true, true, false, false, true, true},
        {false, true, true, true, true, false}
    };
    static int ZRow = 2;
    static int ZCol = 3;
    static String ZImg = assetsFolder + "Z.png";
    static int ZOffset[][] = {{0, 0}, {0, 1}, {1, -1}, {-1, 0}};
}
