package gameServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GameServer {
	private static final int PORT = 420;
	private static final String IP = "localhost";
	private Registry registry;
	
	public GameServer() {
		//Try to get the registry
		try {
			registry = LocateRegistry.getRegistry(IP, PORT);
			System.out.println("Registry lookup succesfull");
		} catch (RemoteException re) {
			System.out.println("GameServer: RemoteException: " + re.getMessage());
            registry = null;
		}
	}
	
	public static void main(String[] args) {
		GameServer server = new GameServer();
	}
}
