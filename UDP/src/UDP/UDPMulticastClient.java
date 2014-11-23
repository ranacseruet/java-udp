package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPMulticastClient extends UDPClient{

	public UDPMulticastClient(String host, 
			                  int 	 port) {
		super(host, port);		
	}
	
	public void broadcast(String message) throws IOException, InterruptedException {		
		try{
			this.socket 		 = new DatagramSocket();
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
