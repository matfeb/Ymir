package fr.ymir.hermod.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClient implements Runnable
{
	
	protected InetAddress broadcastGroup = null;
	protected byte[] tempAddress = new byte[4], receiveBuf = null, sendBuf = null;
	
	protected MulticastSocket socket = null;
	protected DatagramPacket receivePacket = null, sendPacket = null;
	
	private final String RECEIVE_STR = "HELLO_GNAA";
	private final String SEND_STR = "HELLO_HERMOD";
	private final int PORT = 1406;
	private final String GROUP_ADDRESS = "228.0.0.0";
	
	public MulticastClient ()
	{
		try 
		{
			this.socket = new MulticastSocket(PORT);	
			this.socket.setBroadcast(true);										
			this.broadcastGroup = InetAddress.getByName(GROUP_ADDRESS); 
		} 
			catch (IOException e)
			{
				
				e.getMessage();
				System.out.println(broadcastGroup.getHostAddress());
			}	
	}

	@Override
	public void run() 
	{
		try 
		{
			this.receiveBuf = RECEIVE_STR.getBytes("UTF8");						// Initialize the byte[] of the String "HELLO_GNAA"
			this.sendBuf = SEND_STR.getBytes("UTF8");							// Initialize the byte[] of the String "HELLO_HERMOD"
			this.socket.joinGroup(broadcastGroup);									// Join the broadcasting group
			this.receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);	// Initialize the packet which is going to receive data
					
			
			while (true)
			{
				System.out.println("\nGNAA@"+InetAddress.getLocalHost().getHostAddress()+" waiting for a packet");
				socket.receive(receivePacket);
				String strReceive = new String(receivePacket.getData());
				System.out.print("GNAA@"+InetAddress.getLocalHost().getHostAddress()+
						"  receive a new packet from "+
						receivePacket.getAddress().getHostAddress()+"\n"+"This packet contains: "+strReceive);
				if(strReceive.contentEquals(RECEIVE_STR))
				{
					System.out.println(" YES ;)");
					// Initialize the packet which is going to send data at the IPv4 Address sender on Port
					this.sendPacket = new DatagramPacket(sendBuf, sendBuf.length, receivePacket.getAddress(), receivePacket.getPort());	
					socket.send(sendPacket);
					System.out.println("Packet was sent to HERMOD@"+receivePacket.getAddress()+" on port n°"+receivePacket.getPort());
				}
				else
					System.out.println(" NO :(");
			}
		} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			finally
			{
				try 
				{
					this.socket.leaveGroup(broadcastGroup);
					this.socket.close();
				} 
					catch (IOException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}		
	}
	
}
