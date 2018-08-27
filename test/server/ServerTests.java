package server;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.junit.jupiter.api.Test;

import client.GUIConstants;
import rechner.Calculator;

class ServerTests {

//	private static RechnerServer rs;
//	
//	@BeforeAll
//	static void setUp() throws RemoteException {
//		rs = new RechnerServer();
//	}

	@Test
	void testMain() throws RemoteException, NotBoundException {
		String input = "3+3-3X3:3+(-3)-(-3)X(-3):(-3)";
		String expected = "3.0";
		
		Registry registry = LocateRegistry.getRegistry(GUIConstants.SERVER_IP1,1099);
		Calculator stub = (Calculator) registry.lookup("Rechner");
		
		String actual = stub.calculate(input);
		
		assertEquals(expected, actual);
	}
}
