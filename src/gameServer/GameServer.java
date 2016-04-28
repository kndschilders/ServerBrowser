package gameServer;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import queryServer.IServer;
import queryServer.IServerList;

public class GameServer {
	private static final int PORT = 420;
	private static final String IP = "localhost";
	static final String BINDINGNAME = "serverList";
	
	private Registry registry;
	private IServerList serverList;
	
	private static final String MYIP = "192.168.1.1";
	private static final int MYPORT = 1337;
	Random random = new Random();
	
	public GameServer() throws IOException {
		//Try to get the registry
		try {
			registry = LocateRegistry.getRegistry(IP, PORT);
			System.out.println("Registry lookup succesfull");
		} catch (RemoteException re) {
			System.out.println("GameServer: RemoteException: " + re.getMessage());
            registry = null;
		}
		
		if(registry != null) {
			try {
				serverList = (IServerList) registry.lookup(BINDINGNAME);
			} catch (RemoteException re) {
				System.out.println("GameServer: RemoteException: " + re.getMessage());
                serverList = null;
			} catch (NotBoundException nbe) {
				System.out.println("GameServer: NotBoundException: " + nbe.getMessage());
				serverList = null;
			}
		}
		
		if(serverList != null) {
			try {
				serverList.addServer(MYIP, random.nextInt(2000));
			} catch (RemoteException ex) {
				Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		while (true) {
			System.out.println("test");
			for (IServer server : serverList.getServers()) {
				System.out.println(server.getFullAddress());
			}
			System.out.println("Press enter to refresh list");
			System.in.read();
		}
	}
	
	public static void main(String[] args) throws IOException {
		GameServer server = new GameServer();
	}
}
