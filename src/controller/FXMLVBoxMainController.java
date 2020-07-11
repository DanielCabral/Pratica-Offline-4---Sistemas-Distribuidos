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
	
	public void abrirEquipamentos() throws IOException {
		if (telaAtual != 1) {
			AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneEquipamentos.fxml"));
	        anchorPane.getChildren().setAll(a);
		} else {
			System.out.println("Evitando recarregar pagina a toa");
			
		}
		telaAtual = 1;
        
	}
	
	public void abrirHome() throws IOException {
		if (telaAtual != 2) {
			AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneHome.fxml"));
	        anchorPane.getChildren().setAll(a);
		} else {
			System.out.println("Evitando recarregar pagina a toa");
			
		}
		telaAtual = 2;
	}
	public void abrirLocais() throws IOException {
		if (telaAtual != 3) {
			AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneLocais.fxml"));
	        anchorPane.getChildren().setAll(a);
		} else {
			System.out.println("Evitando recarregar pagina a toa");
			
		}
		telaAtual = 3;
        
	}
	public void abrirResponsaveis() throws IOException {
		if (telaAtual != 4) {
			AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneResponsaveis.fxml"));
	        anchorPane.getChildren().setAll(a);
		} else {
			System.out.println("Evitando recarregar pagina a toa");
			
		}
		telaAtual = 4;
        
	}
	public void abrirClientes() throws IOException {
		if (telaAtual != 5) {
			AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneClientes.fxml"));
	        anchorPane.getChildren().setAll(a);
		} else {
			System.out.println("Evitando recarregar pagina a toa");
			
		}
		telaAtual = 5;
        
	}
	public void abrirVendas() throws IOException {
		if (telaAtual != 6) {
			AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneVendas.fxml"));
	        anchorPane.getChildren().setAll(a);
		} else {
			System.out.println("Evitando recarregar pagina a toa");
			
		}
		telaAtual = 6;
        
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
		System.out.println("Abrindo programa");
		try {
			abrirHome();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
	
}
