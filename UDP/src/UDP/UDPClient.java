package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class UDPClient {
	protected String host;
	protected int    port;
	protected DatagramSocket  socket;
	
	public UDPClient(String host, int port)	{
		this.host   = host;
		this.port   = port;
	}
	
	/**
	 * Request with an UDP String message
	 * @param message
	 * @return response String
	 */
	public String send(String message) {

		String response = null;
		try{
			this.socket 			= new DatagramSocket();
			String requestData 		= message;
			byte [] m 				= requestData.getBytes();
			InetAddress aHost 		= InetAddress.getByName(this.host); //change for IP address
			int serverPort 			= this.port;
			DatagramPacket request 	= new DatagramPacket(m, requestData.length(), aHost, serverPort);
			this.socket.send(request);
	        byte [] buffer = new byte[1000];
	        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
	        socket.setSoTimeout(2000);
	        this.socket.receive(reply);

	        //System.out.println("recieved response");
	        response 				= new String(reply.getData());
	        response 				= response.trim();
		}
		catch(SocketTimeoutException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			this.socket.close();
		}

		return response;
	}
	
	public void sendOnly(String message) {
		try{
			this.socket 			= new DatagramSocket();
			String requestData 		= message;
			byte [] m 				= requestData.getBytes();
			InetAddress aHost 		= InetAddress.getByName(this.host); //change for IP address
			int serverPort 			= this.port;
			DatagramPacket request 	= new DatagramPacket(m, requestData.length(), aHost, serverPort);
			this.socket.send(request);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.socket.close();
		}
	}
}
