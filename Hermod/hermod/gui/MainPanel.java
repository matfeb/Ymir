package fr.ymir.hermod.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.ymir.hermod.network.HermodServer;
import fr.ymir.hermod.network.ScanThread;
import fr.ymir.ressources.RexedColors;
import fr.ymir.ressources.buttons.MainPanelMenuButton;

public class MainPanel extends JPanel 
{
	protected MainPanelMenuButton 	buttonPages 				= new MainPanelMenuButton("PAGES");
	protected MainPanelMenuButton 	buttonScreens 				= new MainPanelMenuButton("SCREENS");
	protected MainPanelMenuButton 	buttonSettings 				= new MainPanelMenuButton("SETTINGS");
	protected MainPanelMenuButton 	buttonTest 					= new MainPanelMenuButton("TEST");
	
	protected HermodServer 			hermodServer				= null;
	protected ScanThread 			scanningThread				= null;
	
	protected CardLayout 			cl 							= new CardLayout();
	protected JPanel 				content 					= new JPanel();
	protected JPanel 				buttonPanel 				= new JPanel();
	
	
	public MainPanel()
	{		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		
		
		/*
		buttonPages.setAlignmentX(CENTER_ALIGNMENT);
		buttonPages.setHorizontalAlignment(SwingConstants.LEFT);

		buttonScreens.setAlignmentX(CENTER_ALIGNMENT);
		buttonScreens.setHorizontalAlignment(SwingConstants.LEFT);
		
		buttonSettings.setAlignmentX(CENTER_ALIGNMENT);
		buttonSettings.setHorizontalAlignment(SwingConstants.LEFT);*/
		
		buttonPanel.add(buttonPages);
		buttonPanel.add(buttonScreens);
		buttonPanel.add(buttonSettings);
		buttonPanel.add(buttonTest);
		
		buttonPanel.setBackground(RexedColors.flatWebAppProMenu);

		buttonPages.addMouseListener(new MouseListener() 
		{
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
			@Override
			public void mousePressed(MouseEvent arg0) {}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				cl.show(content, "PAGES");
			}
		});
		
		buttonScreens.addMouseListener(new MouseListener() 
		{
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
			@Override
			public void mousePressed(MouseEvent arg0) {}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				cl.show(content, "SCREENS");
			}
		});
				
		buttonSettings.addMouseListener(new MouseListener() 
		{
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
			@Override
			public void mousePressed(MouseEvent arg0) {}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				cl.show(content, "SETTINGS");
			}
		});
		
		buttonTest.addMouseListener(new MouseListener() 
		{
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
			@Override
			public void mousePressed(MouseEvent arg0) {}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				hermodServer = new HermodServer();
				scanningThread = new ScanThread();
				
				new Thread(hermodServer).start();
				new Thread(scanningThread).start();
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hermodServer.stop();
				scanningThread.stop();
				
				System.out.println(hermodServer.getGnaaIpList().size());
				
			}
		});
		
		JPanel screens = new JPanel();
		screens.setBackground(Color.BLUE);
		JPanel pages = new JPanel();
		pages.setBackground(Color.red);
		JPanel settings = new JPanel();
		settings.setBackground(Color.green);
		
		content.setLayout(cl);
		
		
		content.add(pages, "PAGES");
		content.add(screens, "SCREENS");
		content.add(settings, "SETTINGS");
	
		
		this.setLayout(new BorderLayout());
		
		this.add(buttonPanel, BorderLayout.WEST);
		this.add(content, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
}
