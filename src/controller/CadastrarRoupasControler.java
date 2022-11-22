package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Produto;
import models.Roupa;
import server.Cliente;

import java.rmi.RemoteException;

public class CadastrarRoupasControler {

	@FXML private TextField nome;
	@FXML private TextField preco;
	@FXML private TextField tamanho;
	@FXML private Button botaoCadastrarOuAlterar;
	@FXML private Label labelCliente;
	
	Cliente cliente;
	Roupa roupa = null;
	
	public void adicionarCliente() throws NumberFormatException, RemoteException {
		if(validacaoDeEntradas()){
			if(roupa == null) {
				boolean cadastrou = cliente.adicionarProduto(new Roupa(Produto.gerarCodigo(), nome.getText(), "Roupa", Double.parseDouble(preco.getText()), tamanho.getText()));
				if(cadastrou) {
					Main.caixaDeInformacao("Cadastro Realizado", "Sucesso!", "Roupa cadastrada", 1);
					Stage stage = (Stage) nome.getScene().getWindow();
					stage.close();
				}else {
					Main.caixaDeInformacao("Cadastro N�o Realizado", "Falha!", "Roupa n�o cadastrada, dados incorretos", 2);
				}
			}else {
				boolean alterou = cliente.alterarProduto(new Roupa(roupa.getCodigo(), nome.getText(), "Roupa", Double.parseDouble(preco.getText()), tamanho.getText()));
				if(alterou) {
					Main.caixaDeInformacao("Altera��o Realizada", "Sucesso!", "Roupa alterada", 1);
					Stage stage = (Stage) nome.getScene().getWindow();
					stage.close();
				}else {
					Main.caixaDeInformacao("Altera��o N�o Realizada", "Falha!", "Roupa n�o atualizada, dados incorretos", 2);
				}
			}
		}else {
			Main.caixaDeInformacao("Erro", "Erro!", "Dados invalidos ou incompletos", 3);
		}
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setRoupa(Roupa roupa) {
		this.roupa = roupa;
		nome.setText(roupa.getNome());
		preco.setText(roupa.getPreco().toString());
		tamanho.setText(""+roupa.getTamanho());
		labelCliente.setText("Alterar Roupa");
		botaoCadastrarOuAlterar.setText("Alterar");
	}
	
	@FXML
	protected void voltar(ActionEvent event) {
		Stage stage = (Stage) nome.getScene().getWindow();
		stage.close();
	}

	private boolean validacaoDeEntradas() {
		if (!nome.getText().isEmpty() && !preco.getText().isEmpty() && !tamanho.getText().isEmpty()) {
			return true;
		} else {
			return false;
		}

	}




}
