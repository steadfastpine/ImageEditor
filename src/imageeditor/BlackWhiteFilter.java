package imageeditor;
/**
 * Filter for Black & White effect
 * Sets each rgb value to be the average of all three
 */
public class BlackWhiteFilter implements Filter
{
	public void filter(PixelImage pi)
	{
		Pixel[][] data = pi.getData();
    
		for (int row = 0; row < pi.getHeight(); row++)
		{
			for (int col = 0; col < pi.getWidth(); col++)
			{      	  
				data[row][col].red  = ( data[row][col].red + data[row][col].green + data[row][col].blue ) / 3;
				data[row][col].green  = ( data[row][col].red + data[row][col].green + data[row][col].blue ) / 3;
				data[row][col].blue  = ( data[row][col].red + data[row][col].green + data[row][col].blue ) / 3;
			}
		}
    
		for (int row = 0; row < pi.getHeight(); row++)
		{
			for (int col = 0; col < pi.getWidth(); col++)
			{  
				data[row][col].red  = ( data[row][col].red + data[row][col].green + data[row][col].blue ) / 3;
				data[row][col].green  = ( data[row][col].red + data[row][col].green + data[row][col].blue ) / 3;
				data[row][col].blue  = ( data[row][col].red + data[row][col].green + data[row][col].blue ) / 3;
			}
		}
    
		pi.setData(data);
	}
}
