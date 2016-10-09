package fr.ymir.gnaa;

public class Gnaa
{
	public static void main(String[] args) 
	{
		GnaaServerThread gnaaServer = new GnaaServerThread();
		DiscoveringServer discoverServer = new DiscoveringServer();
		
		new Thread(gnaaServer).start();
		new Thread(discoverServer).start();
	}

}
