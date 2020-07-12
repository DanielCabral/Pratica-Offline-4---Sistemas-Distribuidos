package controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
	private static Stage palco;
	
	public static Stage getPalco() {
		return palco;
	}
	public static void setPalco(Stage palco) {
		Main.palco = palco;
	}
	public void start (Stage palco) {
		setPalco(palco);
		telaInicial();
	}
	
	public static void telaInicial() {
		try {
			Login l=new Login();
			
			if(l.getLogou()) {
				Parent root = FXMLLoader.load(Main.class.getResource("/view/FXMLVBoxMain.fxml"));
				
				Scene scene = new Scene(root);
				Image img=new javafx.scene.image.Image("file:icone.png");
				palco.getIcons().add(img);
				palco.setTitle("Distribute Store");
				palco.setScene(scene);
				palco.show();
			}else {
				Main.caixaDeInformacao("Login não realizado", "Falha!", "Login falhou, dados incorretos", 2);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean caixaDeInformacao(String titulo,String cabecalho,String conteudo,int tipo) {
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
        dialogoInfo.setContentText(conteudo);
        return !dialogoInfo.showAndWait().get().getButtonData().toString().equals("CANCEL_CLOSE");
        
	}
	public static void main(String[] args) {
		launch(args);
	}
}
