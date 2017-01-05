import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;


public class FractalViewer extends JFrame implements MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = -6458272044010583213L;

	private FractalDrawingPanel drawingPanel;
	
	private int lastDragX, lastDragY;
	private boolean isDragging;
	
	private String title; 
	
	public FractalViewer()
	{
		super("Fractal Viewer 0.723");
		title = "Fractal Viewer 0.723";
		
		drawingPanel = new FractalDrawingPanel(1700, 1200);
		
		drawingPanel.addMouseMotionListener(this);
		drawingPanel.addMouseListener(this);
		
		setSize(1800,1300);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(drawingPanel, BorderLayout.CENTER);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	
	public static void main(String[] args)
	{
		FractalViewer fv = new FractalViewer();
	}



	
	public void mouseReleased(MouseEvent me)
	{
		isDragging = false;		
	}
	
	public void mouseDragged(MouseEvent me)
	{	
		if(!isDragging)
		{
			isDragging = true;
			lastDragX = me.getX();
			lastDragY = me.getY();
		}
		
		//send the delta's for the change in drag
		drawingPanel.recenter((me.getX() - lastDragX), (me.getY() - lastDragY));
		
		lastDragX = me.getX();
		lastDragY = me.getY();
	}
	
	public void mouseClicked(MouseEvent me)
	{
	//	if(!isDragging)
		{ 
			int whichButton = me.getButton();
			if(whichButton == MouseEvent.BUTTON1)
			{
				if(me.getModifiersEx() == MouseEvent.CTRL_DOWN_MASK) {
					drawingPanel.animateColors();
				}
				else 
				{
					drawingPanel.recenterAndZoom(me.getX(), me.getY(), 2);
				}
			}
			else if(whichButton == MouseEvent.BUTTON2)
			{
				//uh...do nothing for now
			}
			else if(whichButton == MouseEvent.BUTTON3)
			{
				if(me.getModifiersEx() == MouseEvent.CTRL_DOWN_MASK) {
					drawingPanel.stopColorAnimation();
				}
				else {
					drawingPanel.recenterAndZoom(me.getX(), me.getY(), 0.5);
				}
				
			}
		}
	}


	public void mouseMoved(MouseEvent me)
	{
		this.setTitle(title + " (" + me.getX() + ", " + me.getY() + ")");
	}



	
	
	
	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub	
	}
	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub	
	}
	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
	}
}
