package fr.ymir.gnaa;

public class Screen
{
	protected String name = null;
	protected String description = null;
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
	
	public void setDescription (String pDescription)
	{
		description = pDescription;
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
	
	public String getDescription ()
	{
		return description;
	}
	
	public String[] getListFiles ()
	{
		return listFiles;
	}
}
