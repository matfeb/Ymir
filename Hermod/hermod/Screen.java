package fr.ymir.hermod;

public class Screen
{
	protected String name = null;
	protected String[] listFiles = null;

	
	public Screen ()
	{
		
	}
	
	/*
	 * 	SETTERS
	 */
	
	public void setName (String pName)
	{
		name = pName;
	}
	
	public void setListFiles (String[] pListFiles)
	{
		listFiles = pListFiles;
	}
	
	/*
	 * 	GETTERS
	 */
	
	public String getName ()
	{
		return name;
	}
	
	public String[] getListFiles ()
	{
		return listFiles;
	}

}
