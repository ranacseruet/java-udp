package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer 
{
	private String host;
	private int port;
	private DatagramSocket socket;
	private DatagramPacket request;
	
	public UDPServer(String host, int port) throws SocketException
	{
		this.host = host;
		this.port = port;
		this.socket = new DatagramSocket(this.port);
	}
	
	public String recieveRequest()
	{
		String data = null;
		try {
			byte [] buffer = new byte[10000];
			request = new DatagramPacket(buffer, buffer.length);
			this.socket.receive(request);
			data = new String(request.getData());
		}
		catch(Exception err) {
			err.printStackTrace();
		}
		return data;
	}
	
	public void sendResponse(String response)
	{
		DatagramPacket reply = new DatagramPacket(response.getBytes(), response.length(), request.getAddress(), request.getPort());
		try {
			this.socket.send(reply);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			this.socket.close();
		}
	}
}
