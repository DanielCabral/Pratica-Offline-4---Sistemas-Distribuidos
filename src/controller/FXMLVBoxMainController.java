package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class FXMLVBoxMainController {
	@FXML
    private AnchorPane anchorPane;
	private int telaAtual;
	
	public void abrirHome() throws IOException {
		if (telaAtual != 0) {
			//AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/FXMLVBoxMain.fxml"));
	        anchorPane.getChildren().clear();
		} else {
			System.out.println("Evitando recarregar pagina a toa");
			
		}
		telaAtual = 0;
	}
	
	public void AbrirAlimentos() throws IOException {
		if (telaAtual != 1) {
			AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneAlimentos.fxml"));
	        anchorPane.getChildren().setAll(a);
		} else {
			System.out.println("Evitando recarregar pagina a toa");
			
		}
		telaAtual = 1;
        
	}
	
	public void AbrirEletronicos() throws IOException {
		if (telaAtual != 2) {
			AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneEletronicos.fxml"));
	        anchorPane.getChildren().setAll(a);
		} else {
			System.out.println("Evitando recarregar pagina a toa");
			
		}
		telaAtual = 2;
	}
	public void AbrirRoupas() throws IOException {
		if (telaAtual != 3) {
			AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneRoupas.fxml"));
	        anchorPane.getChildren().setAll(a);
		} else {
			System.out.println("Evitando recarregar pagina a toa");
			
		}
		telaAtual = 3;
        
	}
	
	public void abrirClientes() throws IOException {
		
	}
	
	public void help(){
		Desktop desktop = Desktop.getDesktop();
        try {
        	desktop.open(new File("src/files/leia-me.txt"));
        } catch (IOException ex) {
        	System.out.println(ex);
        }
	}
	@FXML
    public void initialize()  {
		System.out.println("Abrindo programa cliente");
		try {
			abrirHome();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
	
}
