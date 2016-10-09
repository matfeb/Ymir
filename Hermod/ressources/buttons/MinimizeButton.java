package fr.ymir.ressources.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import fr.ymir.ressources.RexedImages;

public class MinimizeButton extends JLabel implements MouseListener
{
	protected Icon mIconActive = RexedImages.minimize;
	protected Icon mIconFocus = RexedImages.minimizeOnFocus;
	protected Icon mIconClick = RexedImages.minimizeOnClick;
	protected JFrame mParentFrame; 
	
	public MinimizeButton ()
	{
		this.setIcon(mIconActive);		
		this.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		this.setIcon(mIconClick);
		// We get the parent JFrame
		this.mParentFrame = (JFrame)this.getTopLevelAncestor(); 
		if (mParentFrame != null)
			// We iconified the JFrame
			this.mParentFrame.setState(JFrame.ICONIFIED);
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
