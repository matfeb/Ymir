package fr.ymir.gnaa;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author Mathieu Febvay 
 * @since 04-24-2014
 * 
 * @version 1.0
 * 
 * Network protocol between Hermod and Gnaa 
 * GNAA VERSION
 * ****************************************
 * 
 * SENDING AND RECEIVING FILES PROTOCOL
 * =====================================
 * - first send:
 * 	- 3 first digits for command 
 * - second send:	
 *	- data's length
 * - third send:
 * 	- data
 * ======================================
 * 
 * 
 */

public class Commands 
{	
	public static final int 	RECEIVE_FILE 		= 100;
	public static final int 	SET_TIME 			= 110;
	public static final int 	SET_NAME 			= 120;
	
	public static final int 	SEND_FILE 			= 200;
	public static final int 	GET_LIST 			= 210;
	public static final int 	GET_NAME 			= 220;
	
	public static final int 	BROADCASTING_OK	 	= 300;
	public static final int		STOP				= 400;
	public static final int 	REBOOT				= 600;
	
	public static void receiveFile(DataInputStream pDIs)
	{
		try 
		{			
			System.out.println("Receiving file...");
			int length = 0;
			length = pDIs.readInt();
			System.out.println("Size of:" +length);
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
	
	public static void setTime()
	{
		
	}
	
	public static void sendFile()
	{
		
	}
	
	public static void sendList()
	{
		
	}
		
	public static void startSlideShow()
	{
		
	}
	
	public static void stopSlideShow()
	{
		
	}
	
	public static void rebootThePi()
	{
		
	}
	
}


