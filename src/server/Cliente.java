package server;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Alimento;
import models.LojaDistribuida;
import models.Produto;

public class Cliente {
	private static String nomeServidor = "127.0.0.1";
	private static int porta = 12345;
	private static final String NOMEOBJDIST = "MinhaLoja";
	Registry registro;
	LojaDistribuida stub;
	public Cliente() {
		try {
			// Obtendo refer^encia do servi¸co de registro
			registro = LocateRegistry.getRegistry(nomeServidor, porta);
					
			// Procurando pelo objeto distribu´ıdo registrado previamente com o NOMEOBJDIST
			stub = (LojaDistribuida) registro.lookup(NOMEOBJDIST);
		} catch (RemoteException | NotBoundException ex) {
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public ArrayList<Produto> pesquisarProduto(String tipo) throws RemoteException {
		return stub.listarProdutos(tipo);
	}

	public ArrayList<Alimento> listarAlimento() throws RemoteException {
		return stub.listarAlimentos();
	}
	
	public static void main(String args[]) {
		try {
			System.out.println("Conectando no servidor "+ nomeServidor);
			
			// Obtendo refer^encia do servi¸co de registro
			Registry registro = LocateRegistry.getRegistry(nomeServidor, porta);
			
			// Procurando pelo objeto distribu´ıdo registrado previamente com o NOMEOBJDIST
			LojaDistribuida stub = (LojaDistribuida) registro.lookup(NOMEOBJDIST);
			
			// Invocando m´etodos do objeto distribu´ıdo			
					
			System.out.println("Cadastrou: " + stub.adicionarProduto("Arroz", "Alimento", 20.5, 5, "", ""));
			
			System.out.println("Fim da execução do cliente!"); 
		} catch (RemoteException | NotBoundException ex) {
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
