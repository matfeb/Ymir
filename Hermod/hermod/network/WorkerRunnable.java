package fr.ymir.hermod.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import fr.ymir.hermod.Commands;

public class WorkerRunnable implements Runnable 
{
	protected Socket clientSocket = null;
	protected byte[]	buff = new byte[16];

	public WorkerRunnable (Socket pClientSocket)
	{
		clientSocket = pClientSocket;
	}
	
	@Override
	public void run() 
	{
		try
		{
			OutputStream os = clientSocket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			dos.writeInt(Commands.BROADCASTING_OK);
			System.out.println("OK order was sent to Gnaa");
			dos.flush();
				
			dos.close();
			os.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(clientSocket != null)
					clientSocket.close();
			} 
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
