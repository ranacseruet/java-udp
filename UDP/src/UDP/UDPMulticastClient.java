package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * This is a Client library helps to broadcast multicast
 * message over LAN.
 *
 * @author  Eftakhairul Islam <eftakhairul@gmail.ocm>
 * @version 1.0
 * @since   2014-10-24
 */
public class UDPMulticastClient extends UDPClient {

	public UDPMulticastClient(String host, 
			                  int 	 port) throws IOException {
		//Overloading parent constructor
		super(host, port);
		
		InetAddress group = InetAddress.getByName(this.host);
		this.socket 	  = new MulticastSocket(this.port);
		
		((MulticastSocket)this.socket).joinGroup(group);
	}
	
	public void broadcast(String message) throws IOException, InterruptedException {		
		try{
			DatagramPacket dgram = new DatagramPacket(message.getBytes(), 
													  message.length(),
													  InetAddress.getByName(this.host), 
													  this.port);
			this.socket.send(dgram);							
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.socket.close();
		}	
	}

	public void reliableBroadcast(String message) throws IOException, InterruptedException {
		try{
			DatagramPacket dgram = new DatagramPacket(message.getBytes(),
					message.length(),
					InetAddress.getByName(this.host),
					this.port);
			this.socket.send(dgram);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.socket.close();
		}
	}

}
