package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import model.Cliente;

public class CadastrarClienteControler {

	@FXML private TextField nome;
	@FXML private TextField endereco;
	@FXML private TextField cpf;
	@FXML private Button botaoCadastrarOuAlterar;
	@FXML private Label labelCliente;
	

	public void adicionarCliente() {
		
	}

	@FXML
	protected void voltar(ActionEvent event) {
		Stage stage = (Stage) nome.getScene().getWindow();
		stage.close();
	}

	private boolean validacaoDeEntradas() {
		if (!nome.getText().isEmpty() && !endereco.getText().isEmpty() && !cpf.getText().isEmpty()) {
			return true;
		} else {
			return false;
		}

	}




}
