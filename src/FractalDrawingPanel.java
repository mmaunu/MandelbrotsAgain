import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class FractalDrawingPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -6984773470073180198L;
	private int width, height;
	
	private FractalMandelbrotOrJulia mandy;
	
	private boolean animatingColors = false;
	
	
	public FractalDrawingPanel(int w, int h)
	{
		super();
		width = w;
		height = h;
		mandy = new Julia(width, height, -0.5, 0, (double)width/height*2, 2, 0, 1);
		mandy.calculateIterationScores();
		mandy.draw();
		setBackground(Color.red);
	}
	
	public void paintComponent(Graphics g)
	{
		BufferedImage image = mandy.getImage();
		if( image != null )
		{
			Graphics2D g2d = (Graphics2D)g;	
			g2d.drawImage(image, 0, 0, this);	
		}
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		repaint();
	}
	
	
	public void recenter(int deltaX, int deltaY)
	{
			mandy.recenter(deltaX,deltaY);
			mandy.calculateIterationScores();
			mandy.draw();
			repaint();
	}
	
	//this has the fractal zoom in on the new center (cX, cY) and rescale
	//If |scaleFactor| > 1, this zooms in; if |scaleFactor| < 1, this zooms out
	public void recenterAndZoom(int cX, int cY, double scaleFactor)
	{
			mandy.recenterAndZoom(cX, cY, Math.abs(scaleFactor));
			mandy.calculateIterationScores();
			mandy.draw();
			repaint();
		
	}
	
	public void animateColors() {
		System.out.println("trying to animate");
		if(!animatingColors) {
			animatingColors = true;
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(animatingColors) {
						mandy.getColorChooser().modifyColorRamp();
						mandy.draw();
						repaint();
						
						try {
							Thread.sleep(3);
						}
						catch(InterruptedException e) {
							System.out.println("Really...you couldn't let me sleep?");
						}
					}
				}
			}).start();
		}
	}

	public void stopColorAnimation() {
		animatingColors = false;
	}
	
}
