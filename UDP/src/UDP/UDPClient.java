package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient 
{
	private String host;
	private int port;
	
	public UDPClient(String host, int port)
	{
		this.host = host;
		this.port = port;
	}
	
	/**
	 * Request with an UDP String message
	 * @param message
	 * @return response String
	 */
	public String send(String message)
	{
		DatagramSocket aSocket = null;
		String response = null;
		try{
			aSocket = new DatagramSocket();
			String requestData = message;
			byte [] m = requestData.getBytes();
			InetAddress aHost = InetAddress.getByName(this.host); //change for IP address
			int serverPort = this.port;
			DatagramPacket request = new DatagramPacket(m, requestData.length(), aHost, serverPort);
	        aSocket.send(request);
	        byte [] buffer = new byte[1000];
	        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
	        aSocket.receive(reply);
	        response = new String(reply.getData());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			aSocket.close();
		}
		return response.trim();
	}
}
