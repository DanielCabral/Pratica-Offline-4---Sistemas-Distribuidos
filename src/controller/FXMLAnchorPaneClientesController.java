package controller;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Produto;
import server.Cliente;
public class FXMLAnchorPaneClientesController {
	@FXML
    private TableView<Produto> tableViewClientes;
    @FXML
    private TableColumn<Produto, String> tableColumnProdutoNome;
    @FXML
    private TableColumn<Produto, String> tableColumnClienteEndereco;


    @FXML private TableColumn<Produto, String> tableColumnClienteCPF;
    private List<Produto> listClientes;
    private ObservableList<Produto> observableListProdutos;
    
    //Selecionados
    Cliente clienteSelecionado;
	static Stage dialog;
    public void inserir() {
    	dialog = new Stage();
		dialog.initStyle(StageStyle.UTILITY);
		dialog.initModality(Modality.APPLICATION_MODAL);
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FXMLAnchorPaneClientesController.class.getResource("/view/CadastroDeCliente.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			dialog.setScene(scene);
			CadastrarClienteControler controler = loader.getController();
			//controler.setCliente(null);
			dialog.showAndWait();
			carregarTableViewCliente();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    public void alterarCliente() {
    	if (clienteSelecionado != null) {
			dialog = new Stage();
			dialog.initStyle(StageStyle.UTILITY);
			dialog.initModality(Modality.APPLICATION_MODAL);
			Parent root;
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(FXMLAnchorPaneClientesController.class.getResource("/view/CadastroDeCliente.fxml"));
				root = loader.load();
				Scene scene = new Scene(root);
				dialog.setScene(scene);
				CadastrarClienteControler controler = loader.getController();
				//controler.setCliente(clienteSelecionado);
				dialog.showAndWait();
				carregarTableViewCliente();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    public void removerCliente() {
    	if(clienteSelecionado!=null) {
			//Cliente.remover(clienteSelecionado.getNome());
			carregarTableViewCliente();
		}
        
    }
		
	@FXML
    public void initialize() {
        //carregarTableViewCliente();

        //tableViewClientes.getSelectionModel().selectedItemProperty().addListener(
          //      (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));

    }

	public Cliente selecionarItemTableViewClientes(Cliente cliente){
        clienteSelecionado=cliente;
        
        return cliente;
        

    }

	public void carregarTableViewCliente() {
        //tableColumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        //tableColumnClienteCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        //tableColumnClienteEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));

        //listClientes = Cliente.listar();

        //observableListClientes = FXCollections.observableArrayList(listClientes);
        //tableViewClientes.setItems(observableListClientes);
    }
	
	
}

