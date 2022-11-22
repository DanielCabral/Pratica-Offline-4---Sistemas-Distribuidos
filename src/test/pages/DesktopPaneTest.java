package test.pages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DesktopPaneTest extends Application {
    @Override
    public void start(Stage stage) {
        try {
            System.out.println(
                    "Ok"
            );
        Parent root = FXMLLoader.load(controller.Main.class.getResource("/view/FXMLVBoxMain.fxml"));
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }catch (Exception e) {
        e.printStackTrace();
    }
    }


}
