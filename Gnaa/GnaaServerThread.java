package fr.ymir.gnaa;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import fr.ymir.gnaa.Properties;


public class GnaaServerThread implements Runnable 
{
	protected ServerSocket		gnaaServerSocket	= null;
	
	protected int 				valueOfInputStream 	= 0;
	
	protected Thread			runningThread		= null;
	
	protected boolean 			isStopped 			= false;
	
	public GnaaServerThread()
	{	
		super();
	}

	@Override
	public void run()
	{
		synchronized (this)
		{
			runningThread = Thread.currentThread();
		}
		
		System.out.println("GnaaServer opening...");
		openGnaaServerSocket();
		
		while (!isStopped())
		{
			Socket clientSocket = null;
			
			try
			{
				System.out.println("Waiting for client...");
				clientSocket = gnaaServerSocket.accept();
			}
			catch (IOException e)
			{
				if (isStopped())
				{
					System.out.println("GnaaServer is stopped");
					return;
				}
				throw new RuntimeException("Can't open client socket", e);
			}
			
			System.out.println("Client connected");
			processClientRequest(clientSocket);
		}	
			
		
	}
	
	private synchronized boolean isStopped()
	{
		return isStopped;
	}
	
	public synchronized void stop ()
	{	
		isStopped = true;
		try
		{
			if (gnaaServerSocket!=null)
				gnaaServerSocket.close();
		}
		catch (IOException e)
		{
			throw new RuntimeException("Error closing server socket", e);
		}
	}
	
	public void openGnaaServerSocket()
	{
		try 
		{
			gnaaServerSocket = new ServerSocket(Properties.GNAA_SERVER_PORT);
		} 
		catch (IOException e) 
		{
			throw new RuntimeException("Can't open port "+ Properties.GNAA_SERVER_PORT, e);
		}	
	}

	private void processClientRequest(Socket pClientSocket)
	{
		try
		{
			InputStream is = pClientSocket.getInputStream();
			//OutputStream os = pClientSocket.getOutputStream();
			DataInputStream dis = new DataInputStream(is);
			
			while((valueOfInputStream = dis.readInt()) != -1)
			{
				switch (valueOfInputStream)
				{
					case Commands.RECEIVE_FILE:
					{
						Commands.receiveFile(dis);				
						break;
					}
					case Commands.SEND_FILE:
					{
						System.out.println("Sending file...");
						break;
					}
					case Commands.GET_LIST:
					{
						System.out.println("Sending list of files...");
						break;
					}
					case Commands.SET_TIME:
					{
						Commands.setTime();
						break;
					}
					case Commands.SET_NAME:
					{
						
						break;
					}
					case Commands.GET_NAME:
					{
						
						break;
					}
					
					case Commands.REBOOT:
					{
						Commands.rebootThePi();
						break;
					}
					default:
					{
						System.out.println("COMMAND NOT FOUND");
						break;
					}
				}
			}
			
			if (valueOfInputStream == -1)
			{
				System.out.println("InputStream closed");
			}
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			
		}
		
	}
}
