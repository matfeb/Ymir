package fr.ymir.hermod.gui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.ymir.ressources.RexedColors;
import fr.ymir.ressources.buttons.*;

public class TitleBar extends JPanel
{	
	private Icon mLogoIcon = new ImageIcon ("rsc/label_1901.png");
	private Dimension mMinimalDimension;
	
	private JLabel mLabel;
		
	public TitleBar ()
	{
		this.mLabel = new JLabel (mLogoIcon);
		this.mMinimalDimension = new Dimension(0,48);
		
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));
		this.setBackground(RexedColors.flatWebAppProTitle);
		
		this.add(mLabel);
		// Create a virtual box which takes all available space
		this.add(Box.createHorizontalGlue());
		this.add(new QuitButton());
		
		this.setPreferredSize(mMinimalDimension);
	}
}