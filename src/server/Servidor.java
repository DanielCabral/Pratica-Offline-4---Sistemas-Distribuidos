package server;


import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Loja;
import models.LojaDistribuida;
import models.Produto;

public class Servidor {
	// Constantes que indicam onde est´a sendo executado o servi¸co de registro,
	// qual porta e qual o nome do objeto distribu´ıdo
	private static String nomeServidor = "127.0.0.1";
	private static int porta = 12344;
	private static final String NOMEOBJDIST = "MinhaLoja";
	public static void main(String args[]){
		try {
			// Criando
			Loja c = new Loja();
			
			// Definindo o hostname do servidor
			System.setProperty("java.rmi.server.hostname", nomeServidor);
			
			LojaDistribuida stub = (LojaDistribuida)
			UnicastRemoteObject.exportObject(c, 0);
			
			// Criando serviço de registro
			Registry registro = LocateRegistry.createRegistry(porta);
			
			// Registrando objeto distribu´ıdo
			registro.bind(NOMEOBJDIST, stub);
			System.out.println("Servidor pronto!\n");
			System.out.println("Pressione CTRL + C para encerrar...");
		} catch (RemoteException | AlreadyBoundException ex) {
		Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}