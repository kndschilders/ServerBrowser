package queryServer;

import java.rmi.RemoteException;

public class Server implements IServer {
	public String address;
	public int port;
	
	public Server(String address, int port) {
		this.address = address;
		this.port = port;
	}

	@Override
	public String getAddress() throws RemoteException {
		return address;
	}

	@Override
	public int getPort() throws RemoteException {
		return port;
	}

	@Override
	public String getFullAddress() throws RemoteException {
		return this.address + ":" + this.port;
	}
}
