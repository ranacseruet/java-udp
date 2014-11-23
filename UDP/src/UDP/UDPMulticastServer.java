package UDP;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPMulticastServer extends UDPServer {
	
	public MulticastSocket socket;	

	public UDPMulticastServer(String host, int port) throws IOException {
		super(host, port);	
		
		this.socket = new MulticastSocket(this.port);
		
		// must bind receive side
		socket.joinGroup(InetAddress.getByName(this.host)); 
	}
}
