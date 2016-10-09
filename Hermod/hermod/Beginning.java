package fr.ymir.hermod;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.UIManager;

import fr.ymir.hermod.network.ScanThread;
import fr.ymir.hermod.gui.MainWindow;
import fr.ymir.hermod.network.HermodServer;

public class Beginning 
{	
	public static void main(String[] args) 
	{
		
		for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) 
        {
        	if ("Nimbus".equals(laf.getName()))
        	{
                try 
                {
                    UIManager.setLookAndFeel(laf.getClassName());
                } 
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
        //System.out.println(UIManager.getLookAndFeel().getName());
		

		// TODO Optimize this code sample
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try 
		{
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("rsc/Roboto-Medium.ttf")));
		} 
		catch (FontFormatException | IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MainWindow mw = new MainWindow();
		
	}

}
