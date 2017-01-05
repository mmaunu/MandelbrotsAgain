import java.awt.image.BufferedImage;


public abstract class FractalMandelbrotOrJulia {

	public abstract void calculateIterationScores();
	public abstract BufferedImage draw();
	public abstract FractalColorChooser getColorChooser();
	public abstract BufferedImage getImage();
	public abstract int[][] getIterationNumbers();
	public abstract void recenter(int a, int b);
	public abstract void recenterAndZoom(int a, int b, double scale);
}
