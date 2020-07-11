package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class Main extends Application {
	private static Stage palco;
	
	public static Stage getPalco() {
		return palco;
	}
	public static void setPalco(Stage palco) {
		Main.palco = palco;
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("/view/FXMLVBoxMain.fxml"));			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.resizableProperty().setValue(Boolean.FALSE);
			primaryStage.setTitle("Cliente");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void caixaDeInformacao(String titulo,String cabecalho,String conteudo,int tipo) {
		Alert dialogoInfo;
		if(tipo==0) {
			dialogoInfo= new Alert(Alert.AlertType.CONFIRMATION);
		}else if(tipo==1){
			dialogoInfo= new Alert(Alert.AlertType.INFORMATION);
		}else if(tipo==2){
			dialogoInfo= new Alert(Alert.AlertType.WARNING);	
		}else {
			dialogoInfo= new Alert(Alert.AlertType.ERROR);
		}
        dialogoInfo.setTitle(titulo);
        //dialogoInfo.setHeaderText("Esse e o cabe�alho...");
        dialogoInfo.setContentText(conteudo);
        dialogoInfo.showAndWait();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
