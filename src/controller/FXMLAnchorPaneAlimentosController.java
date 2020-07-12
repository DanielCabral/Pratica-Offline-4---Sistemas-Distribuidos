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
    
    @FXML private TextField campoDePesquisa;
    
    private ArrayList<Alimento> listAlimentos;
    private ObservableList<Alimento> observableListAlimentos;
    private Cliente c;
    
    //Selecionado
    Alimento alimentoSelecionado;
	static Stage dialog;
    public void inserir() {
    	dialog = new Stage();
		dialog.initStyle(StageStyle.UTILITY);
		dialog.initModality(Modality.APPLICATION_MODAL);
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FXMLAnchorPaneAlimentosController.class.getResource("/view/CadastroDeAlimento.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			dialog.setScene(scene);
			CadastrarAlimentoControler controler = loader.getController();
			controler.setCliente(c);
			dialog.showAndWait();
			listAlimentos = c.listarAlimento();
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
    		listAlimentos= c.pesquisarAlimento(campoTexto);
    		carregarTableViewCliente();
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
				loader.setLocation(FXMLAnchorPaneAlimentosController.class.getResource("/view/CadastroDeAlimento.fxml"));
				root = loader.load();
				Scene scene = new Scene(root);
				dialog.setScene(scene);
				CadastrarAlimentoControler controler = loader.getController();
				controler.setCliente(c);
				controler.setAlimento(alimentoSelecionado);
				dialog.showAndWait();
				listAlimentos = c.listarAlimento();
				carregarTableViewCliente();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    public void removerCliente() throws RemoteException {
    	if(alimentoSelecionado!=null) {
    		boolean apagar = Main.caixaDeInformacao("Confirmação", "", "Tem certeza que deseja remover?", 0);
				if(apagar) {
	    		boolean apagou = c.apagarProduto(alimentoSelecionado.getCodigo(), "Alimento");
				if(apagou) {
					Main.caixaDeInformacao("Remoção Realizada", "Removido!", "Alimento Removido", 0);
				}
				listAlimentos = c.listarAlimento();
				carregarTableViewCliente();
			}
		}
        
    }
		
	@FXML
    public void initialize() throws RemoteException {
		//Inicializar classe com stub cliente
		c =new Cliente();
		
        listAlimentos = c.listarAlimento();
        carregarTableViewCliente();

        tableViewAlimentos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewAlimentos(newValue));

    }

	public Alimento selecionarItemTableViewAlimentos(Alimento newValue){
        alimentoSelecionado=newValue;
        
        return null;
    }

	public void carregarTableViewCliente() throws RemoteException {
        tableColumnAlimentoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnAlimentoPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tableColumnAlimentoPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));


        observableListAlimentos = FXCollections.observableArrayList(listAlimentos);
        tableViewAlimentos.setItems(observableListAlimentos);
    }
	
	
}

