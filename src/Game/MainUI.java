package Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author JulioL
 */
public class MainUI extends Application {
    static Tetris tetris = new Tetris();
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(tetris.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
