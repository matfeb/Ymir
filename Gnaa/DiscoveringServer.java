package fr.ymir.gnaa;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class DiscoveringServer implements Runnable
{
	
	protected byte[] 			receiveBuf 					= null;
	protected MulticastSocket	broadcastListenerSocket		= null;
	protected DatagramPacket	receivePacket 				= null;
	protected String			strReceive					= null;
	
	protected Socket 			rpiSocket 					= null;
		
	private InetAddress 		serverAddress 				= null;
	private boolean 			isStopped					= false;
	
	protected int 				valueOfInputStream 			= 0;
	
	protected Thread			runningThread				= null;
	
	
	public DiscoveringServer ()
	{
		try 
		{
			receiveBuf = new byte[Properties.AUTH_ASW.getBytes(Properties.ENCODING_CHARSET).length];
			
		} 
		catch (UnsupportedEncodingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	public void run()
	{	
		synchronized (this)
		{
			runningThread = Thread.currentThread();
		}
		
		openBroadcastingListenerSocket();
		
		while(!isStopped())
		{			
			receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);
			
			try 
			{
				System.out.println("Waiting for broadcasting signal from Hermod...");
				broadcastListenerSocket.receive(receivePacket);
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			strReceive = new String(receivePacket.getData()).trim();
			
			if(strReceive.contentEquals(Properties.AUTH_ASW))
			{
				serverAddress = receivePacket.getAddress();
				openRpiSocket();
				
				try 
				{
					InputStream is = rpiSocket.getInputStream();
					DataInputStream dis = new DataInputStream(is);
					
					while((valueOfInputStream = dis.readInt()) != -1)
					{
						if(valueOfInputStream == Commands.BROADCASTING_OK)
						{
							System.out.println("Hermod Server received me, i've closed my socket, let's do it again");
						}
					}
					
					if (valueOfInputStream == -1)
					{
						System.out.println("InputStream closed");
					}
				}
				catch (IOException e) 
				{
					System.out.println("Socket crashed");
					e.getMessage();
				}
				finally
				{
					serverAddress = null;
					receivePacket = null;
					try
					{
						if(rpiSocket!=null && !rpiSocket.isClosed())
							rpiSocket.close();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}	
			}
			else
				strReceive = null;
		} 
	}	
		
	public void openBroadcastingListenerSocket ()
	{
		try
		{
			broadcastListenerSocket = new MulticastSocket(Properties.BROADCASTING_PORT);
			broadcastListenerSocket.joinGroup(InetAddress.getByName(Properties.BROADCASTING_GROUP_ADDRESS));	
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openRpiSocket()
	{
		try 
		{
			rpiSocket = new Socket (serverAddress, Properties.HERMOD_SERVER_PORT);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeRpiSocket()
	{	
		try 
		{
			if (rpiSocket != null)
				rpiSocket.close();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private synchronized boolean isStopped ()
	{
		return isStopped;
	}
	
	public void stop ()
	{
		isStopped = true;
		try 
		{
			if(broadcastListenerSocket != null)
			{
				broadcastListenerSocket.leaveGroup(InetAddress.getByName(Properties.BROADCASTING_GROUP_ADDRESS));
				broadcastListenerSocket.close();
			}
			if (rpiSocket != null)
				rpiSocket.close();
		} 
		catch (UnknownHostException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
