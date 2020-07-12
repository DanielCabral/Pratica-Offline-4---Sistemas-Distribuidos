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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Alimento;
import models.Roupa;
import server.Cliente;
public class FXMLAnchorPaneRoupasController {
	@FXML
    private TableView<Roupa> tableViewRoupas;
    @FXML
    private TableColumn<Roupa, String> tableColumnRoupaNome;
    @FXML
    private TableColumn<Roupa, String> tableColumnRoupaPreco;
    @FXML private TableColumn<Roupa, String> tableColumnRoupaTamanho;
    
    @FXML private TextField campoDePesquisa;
    
    private ArrayList<Roupa> listRoupas;
    private ObservableList<Roupa> observableListRoupas;
    private Cliente c;
    
    //Selecionado
    Roupa roupaSelecionada;
	static Stage dialog;
    public void inserir() {
    	dialog = new Stage();
		dialog.initStyle(StageStyle.UTILITY);
		dialog.initModality(Modality.APPLICATION_MODAL);
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FXMLAnchorPaneRoupasController.class.getResource("/view/CadastroDeRoupas.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			dialog.setScene(scene);
			CadastrarRoupasControler controler = loader.getController();
			controler.setCliente(c);
			dialog.showAndWait();
			listRoupas = c.listarRoupa();
			carregarTableViewCliente();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @FXML
    private void buscar() throws RemoteException {
    	if(!campoDePesquisa.getText().equals("")) {
    		System.out.println("Pesquisando");
    		String campoTexto = ""+campoDePesquisa.getText();
    		listRoupas= c.pesquisarRoupa(campoTexto);
    		carregarTableViewCliente();
    	}
    		
    }
    
    public void alterarCliente() {
    	if (roupaSelecionada != null) {
			dialog = new Stage();
			dialog.initStyle(StageStyle.UTILITY);
			dialog.initModality(Modality.APPLICATION_MODAL);
			Parent root;
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(FXMLAnchorPaneRoupasController.class.getResource("/view/CadastroDeRoupas.fxml"));
				root = loader.load();
				Scene scene = new Scene(root);
				dialog.setScene(scene);
				CadastrarRoupasControler controler = loader.getController();
				controler.setCliente(c);
				controler.setRoupa(roupaSelecionada);
				dialog.showAndWait();
				listRoupas = c.listarRoupa();
				carregarTableViewCliente();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    public void removerCliente() throws RemoteException {
    	if(roupaSelecionada != null) {
    		boolean apagar = Main.caixaDeInformacao("Confirma��o", "", "Tem certeza que deseja remover?", 0);
				if(apagar) {
	    		boolean apagou = c.apagarProduto(roupaSelecionada.getCodigo(), "Roupa");
				if(apagou) {
					Main.caixaDeInformacao("Remo��o Realizada", "Removido!", "Roupa Removido", 0);
				}
				listRoupas = c.listarRoupa();
				carregarTableViewCliente();
			}
		}
    }
		
	@FXML
    public void initialize() throws RemoteException {
		//Inicializar classe com stub cliente
		c =new Cliente();
		
		
        listRoupas = c.listarRoupa();
        carregarTableViewCliente();

        tableViewRoupas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewRoupas(newValue));

    }

	public Alimento selecionarItemTableViewRoupas(Roupa newValue){
        roupaSelecionada = newValue;
        
        return null;
    }

	public void carregarTableViewCliente() throws RemoteException {
        tableColumnRoupaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnRoupaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tableColumnRoupaTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));

        observableListRoupas = FXCollections.observableArrayList(listRoupas);
        tableViewRoupas.setItems(observableListRoupas);
    }
	
	
}

