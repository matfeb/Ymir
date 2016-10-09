package fr.ymir.hermod;

/**
 * @author Mathieu Febvay 
 * @since 04-24-2014
 * 
 * @version 1.0
 * 
 * Network protocol between Hermod and Gnaa 
 * HERMOD VERSION
 * ****************************************
 * - first send:
 * 	- 3 first digits for command 
 *	- rest for data's length
 * - second send:
 * 	- data
 */

public final class Commands 
{
	public static final int 	SEND_FILE 			= 100;
	public static final int 	SET_TIME 			= 110;
	public static final int 	SET_NAME 			= 120;
	
	public static final int 	RECEIVE_FILE 		= 200;
	public static final int 	GET_LIST 			= 210;
	public static final int 	GET_NAME 			= 220;
	
	public static final int 	BROADCASTING_OK	 	= 300;
	public static final int		STOP				= 400;
	public static final int 	GET_STATE 			= 500;
	public static final int 	REBOOT				= 600;
	
	public static void receiveFile()
	{
		
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


