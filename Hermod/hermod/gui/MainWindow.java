package fr.ymir.hermod.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.ymir.ressources.ComponentResizer;
import fr.ymir.hermod.gui.TitleBar;
import fr.ymir.ressources.ComponentMover;
import fr.ymir.ressources.RexedColors;
import fr.ymir.ressources.RexedImages;

public class MainWindow extends JFrame 
{
	public Dimension actualPrimaryScreenDimension = Toolkit.getDefaultToolkit().getScreenSize();
	public Dimension minimalProgramDimension = new Dimension (1000, 750);
	private int heightProgramLocation = (int)(this.actualPrimaryScreenDimension.getHeight()/2 - this.minimalProgramDimension.getHeight()/2);
	private int widthProgramLocation = (int)(this.actualPrimaryScreenDimension.getWidth()/2 - this.minimalProgramDimension.getWidth()/2);
	 
	private JPanel mTitleBar = new TitleBar ();
	private JPanel mMainPanel = new MainPanel ();
	
	private ComponentMover cm;
	private ComponentResizer cr;
	private int mInsetsValues =-10000;
	private Insets cmInsets = new Insets(mInsetsValues, mInsetsValues, mInsetsValues, mInsetsValues);
			
	JComponent comp = (JComponent)getContentPane();
	
	public MainWindow ()
	{
		// Paddings of MainWindow
		comp.setBorder(new EmptyBorder (1,1,1,1));

		this.setIconImage(RexedImages.logo.getImage());
		this.setUndecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.getContentPane().setBackground(RexedColors.flatIron);
		
		this.setLayout (new BorderLayout());
		
		this.getContentPane().add(this.mTitleBar, BorderLayout.NORTH);
		this.getContentPane().add(this.mMainPanel, BorderLayout.CENTER);
		
		this.setMinimumSize(this.minimalProgramDimension);
		this.setMaximumSize(actualPrimaryScreenDimension);
		
		this.setLocation(this.widthProgramLocation, this.heightProgramLocation);
		
		this.cm = new ComponentMover(this, mTitleBar);
		this.cm.setEdgeInsets(cmInsets);
		
		this.cr = new ComponentResizer();
		cr.registerComponent(this);
		cr.setMinimumSize(minimalProgramDimension);
		cr.setMaximumSize(actualPrimaryScreenDimension);
		
		this.pack();
	
		this.setVisible(true);
	}
}
