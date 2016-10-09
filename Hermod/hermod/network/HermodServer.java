package fr.ymir.hermod.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.ymir.hermod.Properties;

public class HermodServer implements Runnable
{
	
	protected ServerSocket 	hermodServerSocket 		= null;
	protected boolean 		isStopped 				= false;
	protected Thread 		runningThread			= null;
	
	protected List<String>	gnaaIpList				= null;
	
	public HermodServer ()
	{
		super();
	}
	
	@Override
	public void run() 
	{
		synchronized (this) 
		{
			this.runningThread = Thread.currentThread();
		}
		
		gnaaIpList = Collections.synchronizedList(new ArrayList<String>());
		
		openHermodServerSocket();
		
		while (!isStopped())
		{
			Socket clientSocket = null;
			
			try
			{
				clientSocket = hermodServerSocket.accept();
			}
			catch (IOException e)
			{
				if (isStopped())
				{
					System.out.println("Server is stopped");
					return;
				}
				throw new RuntimeException("Can't open client socket", e);
			}
			
			gnaaIpList.add(clientSocket.getInetAddress().getHostAddress());
			System.out.println("Add a client into the list: "+clientSocket.getInetAddress().getHostAddress());
			new Thread (new WorkerRunnable(clientSocket)).start();
		}		
	}

	public void openHermodServerSocket()
	{
		try 
		{
			hermodServerSocket = new ServerSocket(Properties.HERMOD_SERVER_PORT);
		} 
		catch (IOException e) 
		{
			throw new RuntimeException("Can't open port "+ Properties.HERMOD_SERVER_PORT, e);
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
			hermodServerSocket.close();
		}
		catch (IOException e)
		{
			throw new RuntimeException("Error closing server socket", e);
		}
	}
	
	public List<String> getGnaaIpList ()
	{
		return gnaaIpList;
	}
	
}
