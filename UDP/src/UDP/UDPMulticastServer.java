package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * This is a Server library helps to receive multicast
 * message over LAN.
 *
 * @author  Eftakhairul Islam <eftakhairul@gmail.ocm>
 * @version 1.0
 * @since   2014-10-24
 */
public class UDPMulticastServer extends UDPServer {	

	public UDPMulticastServer(String host,
							  int 	 port, 
							  int 	 DGRAM_LENGTH) throws IOException {
		super();

		this.host 			= host;
		this.port 			= port;
		this.socket 		= new MulticastSocket(this.port);

		this.DGRAM_LENGTH 	= DGRAM_LENGTH;
		this.buffer 		= new byte[this.DGRAM_LENGTH];
		this.request 		= new DatagramPacket(buffer, buffer.length);
		
		// must bind receive side
		((MulticastSocket)this.socket).joinGroup(InetAddress.getByName(this.host)); 
	}
	
	public UDPMulticastServer(String host,
			  				  int 	 port) throws IOException {
		this(host, port, 6400);		 
	}
	
	public String recieve() {
		String data = null;
		try {			
			((MulticastSocket)this.socket).receive(this.request);
			Arrays.fill(this.buffer, (byte) 0);
			data = new String(this.request.getData());
		}
		catch(Exception err) {
			err.printStackTrace();
		}
		
		return data.trim();
	}	
	
	public boolean isNull() {
		return (this.socket == null)? true:false;
	}
	
	public void close() {
		if (!isNull()) {
			this.socket.close();
		}		
	}
}
