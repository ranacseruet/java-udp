package UDP;

import java.io.IOException;
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
							  int 	 port) throws IOException {
		super();

		this.host 	= host;
		this.port 	= port;
		this.socket = new MulticastSocket(this.port);

		this.DGRAM_LENGTH 	= 6400;
		byte [] buffer 		= new byte[this.DGRAM_LENGTH];
		this.request 		= new DatagramPacket(buffer, buffer.length);
		
		// must bind receive side
		socket.joinGroup(InetAddress.getByName(this.host)); 
	}
}
