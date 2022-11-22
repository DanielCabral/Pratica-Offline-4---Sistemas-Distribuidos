package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Eletronico;
import models.Produto;
import server.Cliente;

import java.rmi.RemoteException;

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
				boolean cadastrou = cliente.adicionarProduto(new Eletronico(Produto.gerarCodigo(), nome.getText(), "Eletronico", Double.parseDouble(preco.getText()), marca.getText()));
				if(cadastrou) {
					Main.caixaDeInformacao("Cadastro Realizado", "Sucesso!", "Eletronico cadastrado", 1);
					Stage stage = (Stage) nome.getScene().getWindow();
					stage.close();
				}else {
					Main.caixaDeInformacao("Cadastro N�o Realizado", "Falha!", "Eletronico n�o cadastrado, dados incorretos", 2);
				}
			}else {
				boolean alterou = cliente.alterarProduto(new Eletronico(eletronico.getCodigo(), nome.getText(), "Eletronico", Double.parseDouble(preco.getText()), marca.getText()));
				if(alterou) {
					Main.caixaDeInformacao("Altera��o Realizada", "Sucesso!", "Eletronico alterado", 1);
					Stage stage = (Stage) nome.getScene().getWindow();
					stage.close();
				}else {
					Main.caixaDeInformacao("Altera��o N�o Realizada", "Falha!", "Eletronico n�o atualizado, dados incorretos", 2);
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
		nome.setText(eletronico.getNome());
		preco.setText(eletronico.getPreco().toString());
		marca.setText(""+eletronico.getMarca());
		labelCliente.setText("Alterar Eletronico");
		botaoCadastrarOuAlterar.setText("Alterar");
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
