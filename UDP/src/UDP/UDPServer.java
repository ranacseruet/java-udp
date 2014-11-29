package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
	protected String 			host;
	protected int 				port;
	protected DatagramSocket 	socket;
	protected DatagramPacket 	request;
	protected int 				DGRAM_LENGTH;
		
	public UDPServer(String host, 
					 int 	port,
					 int DGRAM_LENGTH) throws SocketException {

		this.host 			= host;
		this.port 			= port;
		this.DGRAM_LENGTH 	= DGRAM_LENGTH;

		this.socket 		= new DatagramSocket(this.port);
		byte [] buffer 		= new byte[this.DGRAM_LENGTH];
		this.request 		= new DatagramPacket(buffer, buffer.length);
	}
	
	public UDPServer(String host, 
					 int 	port) throws SocketException {
		this(host, port, 6400);
	}

	public UDPServer() {
		this.DGRAM_LENGTH 	= DGRAM_LENGTH;
		byte [] buffer 		= new byte[this.DGRAM_LENGTH];
		this.request 		= new DatagramPacket(buffer, buffer.length);
	}
	
	public String recieveRequest() 	{
		String data = null;
		try {			
			this.socket.receive(request);
			data = new String(request.getData());
		}
		catch(Exception err) {
			err.printStackTrace();
		}
		
		return data.trim();
	}
	
	public byte[] recieveByteRequest()
	{
		try {			
			this.socket.receive(request);
		}
		catch(Exception err) {
			err.printStackTrace();
		}
		
		return request.getData();
	}
	
	public void sendResponse(String response) 	{
		DatagramPacket reply = new DatagramPacket(response.getBytes(), response.length(), request.getAddress(), request.getPort());
		try {
			this.socket.send(reply);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	public boolean isNull() {
		return (this.socket == null)? true:false;
	}
	
	public void close() {
		this.socket.close();
	}
}
