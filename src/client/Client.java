package client;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rechner.Calculator;

public class Client {

	private static Registry registry;
	private static Calculator calculator;

	public static void main(String[] args) {
		try {
			createConnection();
			new GUI();
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	public static String sendRequest(String formulaForSuccess) {
		try {
			System.out.println(formulaForSuccess);
			String response = calculator.calculate(formulaForSuccess);
			return response;
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
			return GUIConstants.ERROR_TEXT;
		}
	}

	static void createConnection() throws AccessException, RemoteException, NotBoundException {
		registry = LocateRegistry.getRegistry(GUIConstants.SERVER_IP1, 1099);

		calculator = (Calculator) registry.lookup("Rechner");
	}
}
