package fr.ymir.ressources.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JLabel;

import fr.ymir.ressources.RexedImages;

public class QuitButton extends JLabel implements MouseListener
{

	protected Icon mIconActive = RexedImages.quit;
	protected Icon mIconFocus = RexedImages.quitOnFocus;
	protected Icon mIconClick = RexedImages.quitOnClick;
	
	public QuitButton ()
	{
		this.setIcon(mIconActive);
		this.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		this.setIcon(mIconClick);
		System.exit(0);
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
