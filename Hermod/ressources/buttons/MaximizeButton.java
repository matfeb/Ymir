package fr.ymir.ressources.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import fr.ymir.ressources.RexedImages;

public class MaximizeButton extends JLabel implements MouseListener
{

	protected Icon mIconActive = RexedImages.maximize;
	protected Icon mIconFocus = RexedImages.maximizeOnFocus;
	protected Icon mIconClick = RexedImages.maximizeOnClick;
	protected JFrame mParentFrame; 
	
	public MaximizeButton ()
	{
		this.setIcon(mIconActive);
		this.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		this.setIcon(mIconClick);
		this.mParentFrame = (JFrame)this.getTopLevelAncestor(); 
		if (mParentFrame != null)
			// We maximized the JFrame and we put it on top of the taskbar
			this.mParentFrame.setExtendedState((mParentFrame.getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH ? JFrame.NORMAL : JFrame.MAXIMIZED_BOTH);
		else
			System.out.println("Parent null");
	}
	
	@Override
	public void mouseEntered(MouseEvent e) 
	{
		this.setIcon(mIconFocus);	
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		this.setIcon(mIconActive);
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

}
