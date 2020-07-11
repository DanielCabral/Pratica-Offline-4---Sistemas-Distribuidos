package server;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.LojaDistribuida;

public class Cliente {
	private static String nomeServidor = "127.0.0.1";
	private static int porta = 12345;
	private static final String NOMEOBJDIST = "MinhaLoja";
	public static void main(String args[]) {
		try {
			System.out.println("Conectando no servidor "+ nomeServidor);
			
			// Obtendo refer^encia do servi¸co de registro
			Registry registro = LocateRegistry.getRegistry(nomeServidor, porta);
			
			// Procurando pelo objeto distribu´ıdo registrado previamente com o NOMEOBJDIST
			LojaDistribuida stub = (LojaDistribuida) registro.lookup(NOMEOBJDIST);
			
			// Invocando m´etodos do objeto distribu´ıdo			
					
			System.out.println("Cadastrou: " + stub.adicionarProduto("Furadeira Eletrica Skill", "Eletronico", 23.5, 0, "Skill", ""));
			
			System.out.println("Fim da execução do cliente!"); 
		} catch (RemoteException | NotBoundException ex) {
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
