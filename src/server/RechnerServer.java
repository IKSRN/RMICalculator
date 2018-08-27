package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rechner.Calculator;

public class RechnerServer implements Calculator{
	
	protected RechnerServer() throws RemoteException{
		super();
	}

	@Override
	public String calculate(String formulaForSuccess) throws RemoteException {
		return new Calculation().doMaths(formulaForSuccess);
	}
	
	public static void main(String[] args) {
		
		try {
			RechnerServer rechnerServer = new RechnerServer();
			Calculator stub = (Calculator) UnicastRemoteObject.exportObject(rechnerServer, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("Rechner", stub);
			
			System.out.println("Ready! Waiting for rquests...");
			
		}catch(Exception e) {
			System.out.println("Server Exception: "+e.toString());
			e.printStackTrace();
		}
	}

}
