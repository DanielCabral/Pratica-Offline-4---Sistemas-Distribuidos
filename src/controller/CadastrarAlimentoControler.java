package controller;

import java.rmi.RemoteException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Alimento;
import server.Cliente;

public class CadastrarAlimentoControler {

	@FXML private TextField nome;
	@FXML private TextField preco;
	@FXML private TextField peso;
	@FXML private Button botaoCadastrarOuAlterar;
	@FXML private Label labelCliente;
	
	Cliente cliente;
	Alimento alimento = null;
	
	public void adicionarCliente() throws NumberFormatException, RemoteException {
		if(validacaoDeEntradas()){
			if(alimento == null) {
				boolean cadastrou = cliente.adicionarProduto(nome.getText(), "Alimento", Double.parseDouble(preco.getText()), Double.parseDouble(peso.getText()), "", "");
				if(cadastrou) {
					Main.caixaDeInformacao("Cadastro Realizado", "Sucesso!", "Alimento cadastrado", 1);
					Stage stage = (Stage) nome.getScene().getWindow();
					stage.close();
				}else {
					Main.caixaDeInformacao("Cadastro Não Realizado", "Falha!", "Alimento não cadastrado, dados incorretos", 2);
				}
			}else {
				boolean alterou = cliente.alterarProduto(alimento.getCodigo(), nome.getText(), "Alimento", Double.parseDouble(preco.getText()), Double.parseDouble(peso.getText()), "", "");
				if(alterou) {
					Main.caixaDeInformacao("Alteração Realizada", "Sucesso!", "Alimento alterado", 1);
					Stage stage = (Stage) nome.getScene().getWindow();
					stage.close();
				}else {
					Main.caixaDeInformacao("Alteração Não Realizada", "Falha!", "Alimento não atualizado, dados incorretos", 2);
				}
			}
		}else {
			Main.caixaDeInformacao("Erro", "Erro!", "Dados invalidos ou incompletos", 3);
		}
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
		
	}
	
	@FXML
	protected void voltar(ActionEvent event) {
		Stage stage = (Stage) nome.getScene().getWindow();
		stage.close();
	}

	private boolean validacaoDeEntradas() {
		if (!nome.getText().isEmpty() && !preco.getText().isEmpty() && !peso.getText().isEmpty()) {
			boolean ehDouble=true;
			try
			{
			  Double.parseDouble(preco.getText());
			}
			catch(NumberFormatException e)
			{
				ehDouble=false;
			}
			return ehDouble;
		} else {
			return false;
		}

	}




}
