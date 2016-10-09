package fr.ymir.ressources.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import fr.ymir.ressources.RexedImages;

public class FakeQuitButton extends JLabel implements MouseListener
{

	protected Icon mIconActive = RexedImages.quit;
	protected Icon mIconFocus = RexedImages.quitOnFocus;
	protected Icon mIconClick = RexedImages.quitOnClick;
	protected JFrame mParentFrame;
	
	public FakeQuitButton ()
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
			//this.mParentFrame.setState(JFrame.DO_NOTHING_ON_CLOSE);
			this.mParentFrame.setVisible(false);
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
