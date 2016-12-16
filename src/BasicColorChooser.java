
public class BasicColorChooser extends FractalColorChooser
{
	private double red = 2.0, green = 5.0, blue = 7.0;
	
	public int getColor( int iter )
	{
		return (int)(red*iter)%256 * 65536 +
				(int)(Math.abs(256 - green*iter))%256 * 256 +
				(int)(blue*iter)%256;
	}
	
	public void modifyColorRamp() {
		red += .05;
		red %= 31;
		green += .05;
		green %= 43;
		blue += .05;
		blue %= 47;
	}
}
