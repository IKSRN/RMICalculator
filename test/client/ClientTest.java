package client;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ClientTest {

//	private static Client c;
//
//	@BeforeAll
//	static void setUp() {
//		c = new  Client();
//	}
	
	@BeforeAll
	static void setUp() {
		new Client();
		try {
			Client.createConnection();
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testSendRequestReturn3ForInput() {
		String input = "3+3-3X3:3+(-3)-(-3)X(-3):(-3)";
		String expected = "3.0";
		String actual = Client.sendRequest(input);
		
		assertEquals(expected, actual);
	}
}
