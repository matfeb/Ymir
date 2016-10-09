package fr.ymir.hermod.network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import fr.ymir.hermod.Properties;

public class ScanThread implements Runnable
{
	protected byte[]			sendBuf 				= null;
	protected Thread			runningThread 			= null;

	protected DatagramSocket 	broadcastSenderSocket 	= null;
	protected DatagramPacket 	sendPacket 				= null;
	
	public ScanThread()
	{	
			try 
			{
				sendBuf = Properties.AUTH_ASW.getBytes(Properties.ENCODING_CHARSET);
				sendPacket = new DatagramPacket(sendBuf, sendBuf.length, InetAddress.getByName(Properties.BROADCASTING_GROUP_ADDRESS), Properties.BROADCASTING_PORT);
			} 
			catch (UnsupportedEncodingException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (UnknownHostException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
	}
	
	@Override
	public void run()
	{
		synchronized (this)
		{
			runningThread = Thread.currentThread();
		}
		
		openBroadcastingSenderSocket();
		
		try 
		{	
			broadcastSenderSocket.send(sendPacket);	
			System.out.println("Packet was sent");
		} 
		catch (IOException e) 
		{	
			System.out.println("Sending packet via socket (port "+ Properties.BROADCASTING_PORT +") failure: " +e.getMessage());	
		} 	
		finally
		{
			stop();
		}
	}	
	
	public void openBroadcastingSenderSocket ()
	{
		try 
		{
			broadcastSenderSocket = new DatagramSocket(Properties.BROADCASTING_PORT);
		} 
		catch (SocketException e) 
		{
			System.out.println("Opening socket on port "+ Properties.BROADCASTING_PORT +" failure: " +e.getMessage());
		}
	}
	
	public void stop ()
	{
		if (broadcastSenderSocket!=null)
			broadcastSenderSocket.close();
	}
}
