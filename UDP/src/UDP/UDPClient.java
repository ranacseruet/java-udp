package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	protected String 			host;
	protected int    			port;
	protected DatagramSocket  	socket;
	
	public UDPClient(String host, int port) {
		this.host   = host;
		this.port   = port;
		this.socket = null;
	}
	
	/**
	 * Request with an UDP String message
	 * @param message
	 * @return response String
	 */
	public String send(String message) {
		
		String response = null;
		try {
			this.socket 			= new DatagramSocket();
			String requestData 		= message;
			byte [] m 				= requestData.getBytes();
			InetAddress aHost 		= InetAddress.getByName(this.host); //change for IP address
			int serverPort 			= this.port;
			DatagramPacket request = new DatagramPacket(m, requestData.length(), aHost, serverPort);
	        this.socket.send(request);

	        byte [] buffer 			= new byte[6400];
	        DatagramPacket reply 	= new DatagramPacket(buffer, buffer.length);
	        this.socket.receive(reply);

	        response 				= new String(reply.getData());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.socket.close();
		}

		return response.trim();
	}
}
