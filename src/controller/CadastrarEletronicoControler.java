package controller;

import java.rmi.RemoteException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Eletronico;
import server.Cliente;

public class CadastrarEletronicoControler {

	@FXML private TextField nome;
	@FXML private TextField preco;
	@FXML private TextField marca;
	@FXML private Button botaoCadastrarOuAlterar;
	@FXML private Label labelCliente;
	
	Cliente cliente;
	Eletronico eletronico = null;
	
	public void adicionarCliente() throws NumberFormatException, RemoteException {
		if(validacaoDeEntradas()){
			if(eletronico == null) {
				boolean cadastrou = cliente.adicionarProduto(nome.getText(), "Eletronico", Double.parseDouble(preco.getText()), 0, marca.getText(), "");
				if(cadastrou) {
					Main.caixaDeInformacao("Cadastro Realizado", "Sucesso!", "Eletronico cadastrado", 1);
					Stage stage = (Stage) nome.getScene().getWindow();
					stage.close();
				}else {
					Main.caixaDeInformacao("Cadastro Não Realizado", "Falha!", "Eletronico não cadastrado, dados incorretos", 2);
				}
			}else {
				boolean alterou = cliente.alterarProduto(eletronico.getCodigo(), nome.getText(), "Eletronico", Double.parseDouble(preco.getText()), 0, marca.getText(), "");
				if(alterou) {
					Main.caixaDeInformacao("Alteração Realizada", "Sucesso!", "Eletronico alterado", 1);
					Stage stage = (Stage) nome.getScene().getWindow();
					stage.close();
				}else {
					Main.caixaDeInformacao("Alteração Não Realizada", "Falha!", "Eletronico não atualizado, dados incorretos", 2);
				}
			}
		}else {
			Main.caixaDeInformacao("Erro", "Erro!", "Dados invalidos ou incompletos", 3);
		}
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setEletronico(Eletronico eletronico) {
		this.eletronico = eletronico;
		
	}
	
	@FXML
	protected void voltar(ActionEvent event) {
		Stage stage = (Stage) nome.getScene().getWindow();
		stage.close();
	}

	private boolean validacaoDeEntradas() {
		if (!nome.getText().isEmpty() && !preco.getText().isEmpty() && !marca.getText().isEmpty()) {
			return true;
		} else {
			return false;
		}

	}




}
