package rechner;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote{
	String calculate(String formulaForSuccess) throws RemoteException;
}
