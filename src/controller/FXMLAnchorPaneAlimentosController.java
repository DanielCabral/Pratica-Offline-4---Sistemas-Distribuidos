package controller;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
import models.Alimento;
import models.Produto;
import server.Cliente;
public class FXMLAnchorPaneAlimentosController {
	@FXML
    private TableView<Alimento> tableViewAlimentos;
    @FXML
    private TableColumn<Alimento, String> tableColumnAlimentoNome;
    @FXML
    private TableColumn<Alimento, String> tableColumnAlimentoPreco;
    @FXML private TableColumn<Alimento, String> tableColumnAlimentoPeso;
    
    private ArrayList<Alimento> listAlimentos;
    private ObservableList<Alimento> observableListAlimentos;
    private Cliente c;
    
    //Selecionados
    Alimento alimentoSelecionado;
	static Stage dialog;
    public void inserir() {
    	dialog = new Stage();
		dialog.initStyle(StageStyle.UTILITY);
		dialog.initModality(Modality.APPLICATION_MODAL);
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FXMLAnchorPaneAlimentosController.class.getResource("/view/CadastroDeCliente.fxml"));
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
    	if (alimentoSelecionado != null) {
			dialog = new Stage();
			dialog.initStyle(StageStyle.UTILITY);
			dialog.initModality(Modality.APPLICATION_MODAL);
			Parent root;
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(FXMLAnchorPaneAlimentosController.class.getResource("/view/CadastroDeCliente.fxml"));
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
    
    public void removerCliente() throws RemoteException {
    	if(alimentoSelecionado!=null) {
			//Cliente.remover(clienteSelecionado.getNome());
			//carregarTableViewCliente();
		}
        
    }
		
	@FXML
    public void initialize() throws RemoteException {
		c =new Cliente();
		System.out.println(c.pesquisarProduto("Eletronico").size());
        carregarTableViewCliente();

        tableViewAlimentos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));

    }

	public Alimento selecionarItemTableViewClientes(Produto newValue){
        //alimentoSelecionado=newValue;
        
        return null;
    }

	public void carregarTableViewCliente() throws RemoteException {
        tableColumnAlimentoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnAlimentoPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tableColumnAlimentoPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));

        listAlimentos = c.listarAlimento();

        observableListAlimentos = FXCollections.observableArrayList(listAlimentos);
        tableViewAlimentos.setItems(observableListAlimentos);
    }
	
	
}

