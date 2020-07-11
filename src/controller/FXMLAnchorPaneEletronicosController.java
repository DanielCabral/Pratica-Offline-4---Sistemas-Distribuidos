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
import models.Eletronico;
import server.Cliente;
public class FXMLAnchorPaneEletronicosController {
	@FXML
    private TableView<Eletronico> tableViewEletronicos;
    @FXML
    private TableColumn<Eletronico, String> tableColumnEletronicoNome;
    @FXML
    private TableColumn<Eletronico, String> tableColumnEletronicoPreco;
    @FXML private TableColumn<Eletronico, String> tableColumnEletronicoMarca;
    
    @FXML private TextField campoDePesquisa;
    
    private ArrayList<Eletronico> listEletronicos;
    private ObservableList<Eletronico> observableListEletronicos;
    private Cliente c;
    
    //Selecionado
    Eletronico eletronicoSelecionado;
	static Stage dialog;
    public void inserir() {
    	dialog = new Stage();
		dialog.initStyle(StageStyle.UTILITY);
		dialog.initModality(Modality.APPLICATION_MODAL);
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FXMLAnchorPaneEletronicosController.class.getResource("/view/CadastroDeEletronico.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			dialog.setScene(scene);
			CadastrarEletronicoControler controler = loader.getController();
			controler.setCliente(c);
			dialog.showAndWait();
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
    		listEletronicos= c.pesquisarEletronico(campoTexto);
    		carregarTableViewCliente();
    	}
    		
    }
    
    public void alterarCliente() {
    	if (eletronicoSelecionado != null) {
			dialog = new Stage();
			dialog.initStyle(StageStyle.UTILITY);
			dialog.initModality(Modality.APPLICATION_MODAL);
			Parent root;
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(FXMLAnchorPaneEletronicosController.class.getResource("/view/CadastroDeEletronico.fxml"));
				root = loader.load();
				Scene scene = new Scene(root);
				dialog.setScene(scene);
				CadastrarEletronicoControler controler = loader.getController();
				controler.setEletronico(eletronicoSelecionado);
				dialog.showAndWait();
				carregarTableViewCliente();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    public void removerCliente() throws RemoteException {
    	if(eletronicoSelecionado != null) {
    		boolean apagar = Main.caixaDeInformacao("Confirmação", "", "Tem certeza que deseja remover?", 0);
				if(apagar) {
	    		boolean apagou = c.apagarProduto(eletronicoSelecionado.getCodigo(), "Eletronico");
				if(apagou) {
					Main.caixaDeInformacao("Remoção Realizada", "Removido!", "Alimento Removido", 0);
				}
				carregarTableViewCliente();
			}
		}
    }
		
	@FXML
    public void initialize() throws RemoteException {
		//Inicializar classe com stub cliente
		c =new Cliente();
		
        carregarTableViewCliente();

        tableViewEletronicos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewEletronicos(newValue));

    }

	public Alimento selecionarItemTableViewEletronicos(Eletronico newValue){
        eletronicoSelecionado = newValue;
        
        return null;
    }

	public void carregarTableViewCliente() throws RemoteException {
        tableColumnEletronicoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnEletronicoPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tableColumnEletronicoMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));

        listEletronicos = c.listarEletronico();
        System.out.println(listEletronicos.size());
        observableListEletronicos = FXCollections.observableArrayList(listEletronicos);
        tableViewEletronicos.setItems(observableListEletronicos);
    }
	
	
}

