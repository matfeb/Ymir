package fr.ymir.ressources.buttons;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Painter;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import fr.ymir.ressources.RexedColors;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.GridLayout;
import java.util.Map;

/**
 * ThemeDemo
 *
 * @author Created by Jasper Potts (May 7, 2008)
 * @version 1.0
 */
public class MainPanelMenuButton extends JButton 
{
	UIDefaults buttonDefaults = new UIDefaults();
	Dimension minDim = new Dimension (256, 64);
	
	public MainPanelMenuButton (String pLabel)
    {
	 	super(pLabel);

        this.setFont(new Font("Roboto Medium", Font.BOLD, 20)); 
  
        buttonDefaults.put("Button.contentMargins", new Insets (10, 80, 10, 10));
        this.setPreferredSize(minDim);
        this.setMaximumSize(minDim);
        this.setMinimumSize(minDim);
        
        buttonDefaults.put("Button.backgroundPainter", new Painter<JComponent>() {
            public void paint(Graphics2D g, JComponent c, int w, int h) 
            {
            	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            	g.setColor(RexedColors.flatWebAppProMenu);
            	g.fillRect(0, 0, w, h);
            	//c.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, RexedColors.flatWebAppProMenuBorder));
                c.setForeground(new Color(0x6e6c6b));     

            }

        });
        
        buttonDefaults.put("Button[MouseOver].backgroundPainter", new Painter<JComponent>() {
            public void paint(Graphics2D g, JComponent c, int w, int h) 
            {
            	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            	g.setColor(RexedColors.flatLightBlue);
            	g.fillRect(0, 0, w, h);
                c.setForeground(RexedColors.flatLightWhite);
                
            }

        });
        
       buttonDefaults.put("Button[Pressed].backgroundPainter", new Painter<JComponent>() {
            public void paint(Graphics2D g, JComponent c, int w, int h) 
            {
            	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            	g.setColor(RexedColors.flatDarkBlue);
            	g.fillRect(0, 0, w, h);
                c.setForeground(RexedColors.flatLightWhite);
                
            }
            

        });
      
        this.putClientProperty("Nimbus.Overrides",buttonDefaults);
        this.putClientProperty("Nimbus.Overrides.InheritDefaults",false);

    
    }
}