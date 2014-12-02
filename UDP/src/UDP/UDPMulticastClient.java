package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
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
	
	protected InetAddress group;

	public UDPMulticastClient(String host, 
			                  int 	 port) throws IOException {
		//Overloading parent constructor
		super(host, port);		
		
		this.group = InetAddress.getByName(this.host);	
	}
	
	public void broadcast(String message) throws IOException {		
		try{
			this.socket 	  	= new MulticastSocket(this.port);
			((MulticastSocket)this.socket).joinGroup(this.group);
			
			DatagramPacket dgram = new DatagramPacket(message.getBytes(), 
													  message.length(),
													  InetAddress.getByName(this.host), 
													  this.port);
			((MulticastSocket)this.socket).send(dgram);							
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			((MulticastSocket)this.socket).leaveGroup(group);
			((MulticastSocket)this.socket).close();
		}	
	}

	public boolean reliableBroadcast(String message) throws IOException {
		String response = null;
		try{ 
			this.socket 	    	= new MulticastSocket(this.port);
			((MulticastSocket)this.socket).joinGroup(this.group);
			 DatagramPacket request = new DatagramPacket(message.getBytes(), 
					 								   message.length(),
					 								   this.group, 
					 								   this.port);
			 ((MulticastSocket)this.socket).send(request);
			 

			 // get their responses!
			 byte[] buf 				= new byte[10];
			 DatagramPacket reply    	= new DatagramPacket(buf, buf.length);
			 ((MulticastSocket)this.socket).setSoTimeout(2000);
			 ((MulticastSocket)this.socket).receive(reply);
			 
			 response			        = new String(reply.getData());
		     response 					= response.trim();
		}catch(SocketTimeoutException e){
			return false;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			((MulticastSocket)this.socket).leaveGroup(group);
			((MulticastSocket)this.socket).close();
		}
		
		return response.equals("ok")? true:false;
	}

}
