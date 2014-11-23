package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer 
{
	private String host;
	private int port;
	private DatagramSocket socket;
	
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
			DatagramPacket request = new DatagramPacket(buffer, buffer.length);
			this.socket.receive(request);
			data = new String(request.getData());
		}
		catch(Exception err) {
			err.printStackTrace();
		}
		finally{
			this.socket.close();
		}
		return data;
	}
}
