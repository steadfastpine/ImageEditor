package imageeditor;
import java.awt.image.*;

/**
 * Provides an interface to a picture as an array of Pixels
 */
public class PixelImage
{
  private BufferedImage myImage;
  private int width;
  private int height;

  /**
   * Map this PixelImage to a real image
   * @param bi The image
   */
  public PixelImage(BufferedImage bi)
  {
    // initialise instance variables
    this.myImage = bi;
    this.width = bi.getWidth();
    this.height = bi.getHeight();
  }

  
  /**
   * Return the width of the image
   */
  public int getWidth()
  {
    return this.width;
  }

  /**
   * Return the height of the image
   */
  public int getHeight()
  {
    return this.height;
  }

  /**
   * Return the BufferedImage of this PixelImage
   */
  public BufferedImage getImage()
  {
    return this.myImage;
  }

  /**
   * Return the image's pixel data as an array of Pixels.  The
   * first coordinate is the x-coordinate, so the size of the
   * array is [width][height], where width and height are the
   * dimensions of the array
   * @return The array of pixels
   */
  public Pixel[][] getData()
  {
    Raster r = this.myImage.getRaster();
    Pixel[][] data = new Pixel[r.getHeight()][r.getWidth()];
    int[] samples = new int[3];

    for (int row = 0; row < r.getHeight(); row++)
    {
      for (int col = 0; col < r.getWidth(); col++)
      {
        samples = r.getPixel(col, row, samples);
        Pixel newPixel = new Pixel(samples[0], samples[1], samples[2]);
        data[row][col] = newPixel;
      }
    }

    return data;
  }

  /**
   * Set the image's pixel data from an array.  This array matches
   * that returned by getData().  It is an error to pass in an
   * array that does not match the image's dimensions or that
   * has pixels with invalid values (not 0-255)
   * @param data The array to pull from
   */
  public void setData(Pixel[][] data)
  {
    int[] pixelValues = new int[3];     // a temporary array to hold r,g,b values
    WritableRaster wr = this.myImage.getRaster();

    if (data.length != wr.getHeight())
    {
      throw new IllegalArgumentException("Array size does not match");
    }
    else if (data[0].length != wr.getWidth())
    {
      throw new IllegalArgumentException("Array size does not match");
    }

    for (int row = 0; row < wr.getHeight(); row++)
    {
      for (int col = 0; col < wr.getWidth(); col++)
      {
        pixelValues[0] = data[row][col].red;
        pixelValues[1] = data[row][col].green;
        pixelValues[2] = data[row][col].blue;
        wr.setPixel(col, row, pixelValues);
      }
    }
  }

  
  
  
	// add a method to compute a new image given weighted averages
  
  
  

  	// 3x3 v1 weighted average method - isolated colors
  	// each color for a given pixel is averaged with weights with other of the same color
	public static void matrix3x3v1(PixelImage pi, Matrix matrix)
	{
		Pixel[][] data = pi.getData();
	    Pixel[][] dataFiltered = pi.getData();
	    
	    Pixel topLeftPixel, topRightPixel, topCenterPixel;
	    Pixel middleLeftPixel, middleRightPixel, middleCenterPixel;
	    Pixel bottomLeftPixel, bottomRightPixel, bottomCenterPixel;   
	
		int topLeftWeight, topRightWeight, topCenterWeight;
	    int middleLeftWeight, middleRightWeight, middleCenterWeight;
	    int bottomLeftWeight, bottomRightWeight, bottomCenterWeight;   
	    
	    int topLeftFilter, topRightFilter, topCenterFilter;
	    int middleLeftFilter, middleRightFilter, middleCenterFilter;
	    int bottomLeftFilter, bottomRightFilter, bottomCenterFilter;  
	    
	    int weightedAverage;
	    
		for (int row = 1; row < pi.getHeight() - 1; row++)
		{
			for (int col = 1; col < pi.getWidth() - 1; col++)
			{
	    	  
				topLeftPixel=data[row-1][col-1];
	    		topRightPixel=data[row-1][col+1];
	    		topCenterPixel=data[row-1][col];
	    		middleLeftPixel=data[row][col-1];
	    		middleRightPixel=data[row][col+1];
	    		middleCenterPixel=data[row][col];
	    		bottomLeftPixel=data[row+1][col-1];
	    		bottomRightPixel=data[row+1][col+1];
	    		bottomCenterPixel=data[row+1][col]; 
	    	  
	    		topLeftWeight=matrix.topLeftWeight;
	    		topRightWeight=matrix.topRightWeight;
	    		topCenterWeight=matrix.topCenterWeight;
	    		middleLeftWeight=matrix.middleLeftWeight;
	    		middleRightWeight=matrix.middleRightWeight;
	    		middleCenterWeight=matrix.middleCenterWeight;
	    		bottomLeftWeight=matrix.bottomLeftWeight;
	    		bottomRightWeight=matrix.bottomRightWeight;
	    		bottomCenterWeight=matrix.bottomCenterWeight;
	    		
	    		topLeftFilter=topLeftPixel.red*topLeftWeight;
	    		topRightFilter=topRightPixel.red*topRightWeight;
	    		topCenterFilter=topCenterPixel.red*topCenterWeight;
	    		middleLeftFilter=middleLeftPixel.red*middleLeftWeight;
	    		middleRightFilter=middleRightPixel.red*middleRightWeight;
	    		middleCenterFilter=middleCenterPixel.red*middleCenterWeight;
	    		bottomLeftFilter=bottomLeftPixel.red*bottomLeftWeight;
	    		bottomRightFilter=bottomRightPixel.red*bottomRightWeight;
	    		bottomCenterFilter= bottomCenterPixel.red*bottomCenterWeight;
	    		
	    		weightedAverage=((topLeftFilter+topRightFilter+topCenterFilter+middleLeftFilter+middleRightFilter+middleCenterFilter+bottomLeftFilter+bottomRightFilter+bottomCenterFilter) / 16);
	    		
	    		if (weightedAverage<0)
	    		{
	    			weightedAverage=0;
	    		}
	    		if (weightedAverage>255)
	    		{
	    			weightedAverage=255;
	    		}   		
	    		
	    		dataFiltered[row][col].red = weightedAverage;
	    		
	    		
	    		
	    		topLeftFilter=topLeftPixel.green*topLeftWeight;
	    		topRightFilter=topRightPixel.green*topRightWeight;
	    		topCenterFilter=topCenterPixel.green*topCenterWeight;
	    		middleLeftFilter=middleLeftPixel.green*middleLeftWeight;
	    		middleRightFilter=middleRightPixel.green*middleRightWeight;
	    		middleCenterFilter=middleCenterPixel.green*middleCenterWeight;
	    		bottomLeftFilter=bottomLeftPixel.green*bottomLeftWeight;
	    		bottomRightFilter=bottomRightPixel.green*bottomRightWeight;
	    		bottomCenterFilter= bottomCenterPixel.green*bottomCenterWeight;
	    	
	    		weightedAverage=((topLeftFilter+topRightFilter+topCenterFilter+middleLeftFilter+middleRightFilter+middleCenterFilter+bottomLeftFilter+bottomRightFilter+bottomCenterFilter) / 16);
	    		
	    		if (weightedAverage<0)
	    		{
	    			weightedAverage=0;
	    		}
	    		if (weightedAverage>255)
	    		{
	    			weightedAverage=255;
	    		}   		
	    		
	    		dataFiltered[row][col].green =weightedAverage;
	    		
	    		topLeftFilter=topLeftPixel.blue*topLeftWeight;
	    		topRightFilter=topRightPixel.blue*topRightWeight;
	    		topCenterFilter=topCenterPixel.blue*topCenterWeight;
	    		middleLeftFilter=middleLeftPixel.blue*middleLeftWeight;
	    		middleRightFilter=middleRightPixel.blue*middleRightWeight;
	    		middleCenterFilter=middleCenterPixel.blue*middleCenterWeight;
	    		bottomLeftFilter=bottomLeftPixel.blue*bottomLeftWeight;
	    		bottomRightFilter=bottomRightPixel.blue*bottomRightWeight;
	    		bottomCenterFilter= bottomCenterPixel.blue*bottomCenterWeight;
	
	    		weightedAverage=((topLeftFilter+topRightFilter+topCenterFilter+middleLeftFilter+middleRightFilter+middleCenterFilter+bottomLeftFilter+bottomRightFilter+bottomCenterFilter) / 16);
	    		
	    		if (weightedAverage<0)
	    		{
	    			weightedAverage=0;
	    		}
	    		if (weightedAverage>255)
	    		{
	    			weightedAverage=255;
	    		}   		
	    		
	    		dataFiltered[row][col].blue =weightedAverage;
			}
		}

		pi.setData(dataFiltered);
	} 
  
	
  	// 3x3 v2 weighted average method - converted colors
  	// rgb is converted to decimal before weight matrix application
	public static void matrix3x3v2(PixelImage pi, Matrix matrix)
	{
	    Pixel[][] data = pi.getData();
	    Pixel[][] dataFiltered = pi.getData();
	    
	    Pixel topLeftPixel, topRightPixel, topCenterPixel;
	    Pixel middleLeftPixel, middleRightPixel, middleCenterPixel;
	    Pixel bottomLeftPixel, bottomRightPixel, bottomCenterPixel;   

		int topLeftWeight, topRightWeight, topCenterWeight;
		int middleLeftWeight, middleRightWeight, middleCenterWeight;
	    int bottomLeftWeight, bottomRightWeight, bottomCenterWeight;   
	    
	    int topLeftFilter, topRightFilter, topCenterFilter;
	    int middleLeftFilter, middleRightFilter, middleCenterFilter;
	    int bottomLeftFilter, bottomRightFilter, bottomCenterFilter;  
	    
	    int weightedAverage, weightedAverageDec;
	    int red, green, blue;
	    

		NumberConverter ncv = new NumberConverter();

	    
		for (int row = 1; row < pi.getHeight() - 1; row++)
		{
			for (int col = 1; col < pi.getWidth() - 1; col++)
			{
	   	  
	    		topLeftPixel=data[row-1][col-1];
	    		topRightPixel=data[row-1][col+1];
	    		topCenterPixel=data[row-1][col];
	    		middleLeftPixel=data[row][col-1];
	    		middleRightPixel=data[row][col+1];
	    		middleCenterPixel=data[row][col];
	    		bottomLeftPixel=data[row+1][col-1];
	    		bottomRightPixel=data[row+1][col+1];
	    		bottomCenterPixel=data[row+1][col]; 
	    	  
	    		topLeftWeight=matrix.topLeftWeight;
	    		topRightWeight=matrix.topRightWeight;
	    		topCenterWeight=matrix.topCenterWeight;
	    		middleLeftWeight=matrix.middleLeftWeight;
	    		middleRightWeight=matrix.middleRightWeight;
	    		middleCenterWeight=matrix.middleCenterWeight;
	    		bottomLeftWeight=matrix.bottomLeftWeight;
	    		bottomRightWeight=matrix.bottomRightWeight;
	    		bottomCenterWeight=matrix.bottomCenterWeight;
	    		
	    		topLeftFilter=ncv.rgbToDec(topLeftPixel.red,topLeftPixel.green,topLeftPixel.blue)*topLeftWeight;
	    		topRightFilter=ncv.rgbToDec(topRightPixel.red,topRightPixel.green,topRightPixel.blue)*topRightWeight;
	    		topCenterFilter=ncv.rgbToDec(topCenterPixel.red,topCenterPixel.green,topCenterPixel.blue)*topCenterWeight;
	    		middleLeftFilter=ncv.rgbToDec(middleLeftPixel.red,middleLeftPixel.green,middleLeftPixel.blue)*middleLeftWeight;
	    		middleRightFilter=ncv.rgbToDec(middleRightPixel.red,middleRightPixel.green,middleRightPixel.blue)*middleRightWeight;
	    		middleCenterFilter=ncv.rgbToDec(middleCenterPixel.red,middleCenterPixel.green,middleCenterPixel.blue)*middleCenterWeight;
	    		bottomLeftFilter=ncv.rgbToDec(bottomLeftPixel.red,bottomLeftPixel.green,bottomLeftPixel.green)*bottomLeftWeight;
	    		bottomRightFilter=ncv.rgbToDec(bottomRightPixel.red,bottomRightPixel.green,bottomRightPixel.blue)*bottomRightWeight;
	    		bottomCenterFilter=ncv.rgbToDec(bottomCenterPixel.red,bottomCenterPixel.green,bottomCenterPixel.blue)*bottomCenterWeight;
	    		
	    		weightedAverageDec=((topLeftFilter+topRightFilter+topCenterFilter+middleLeftFilter+middleRightFilter+middleCenterFilter+bottomLeftFilter+bottomRightFilter+bottomCenterFilter) / 16);
	    		
	    		ncv.decToRgb(weightedAverageDec);
	    		
	    		red=ncv.red;
	    		
	    		if (red<0)
	    		{
	    			red=0;
	    		}
	    		if (red>255)
	    		{
	    			red=255;
	    		}   		
	    		
	    		dataFiltered[row][col].red = red;
	    		
	    		green=ncv.green;
	    		
	    		if (green<0)
	    		{
	    			green=0;
	    		}
	    		if (green>255)
	    		{
	    			green=255;
	    		}   		
	    		
	    		dataFiltered[row][col].green = green;
	    		
	    		blue=ncv.blue;
	    		
	    		if (blue<0)
	    		{
	    			blue=0;
	    		}
	    		if (blue>255)
	    		{
	    			blue=255;
	    		}   		
	    		
	    		dataFiltered[row][col].blue = blue;
			}
	    }

		pi.setData(dataFiltered);
	}
  
	
	
	
  	// 5x5 v1 weighted average method - isolated colors
  	// each color for a given pixel is averaged with weights with other of the same color
	public static void matrix5x5v1(PixelImage pi, Matrix5x5 matrix)
	{
		Pixel[][] data = pi.getData();
	    Pixel[][] dataFiltered = pi.getData();
	    
	    Pixel pixel00,pixel01,pixel02,pixel03,pixel04,pixel10,pixel11,pixel12,pixel13,pixel14,pixel20,pixel21,pixel22,pixel23,pixel24,pixel30,pixel31,pixel32,pixel33,pixel34,pixel40,pixel41,pixel42,pixel43,pixel44;
	    int pixel00Weight,pixel01Weight,pixel02Weight,pixel03Weight,pixel04Weight,pixel10Weight,pixel11Weight,pixel12Weight,pixel13Weight,pixel14Weight,pixel20Weight,pixel21Weight,pixel22Weight,pixel23Weight,pixel24Weight,pixel30Weight,pixel31Weight,pixel32Weight,pixel33Weight,pixel34Weight,pixel40Weight,pixel41Weight,pixel42Weight,pixel43Weight,pixel44Weight;
	
	    int pixel00Filter,pixel01Filter,pixel02Filter,pixel03Filter,pixel04Filter,pixel10Filter,pixel11Filter,pixel12Filter,pixel13Filter,pixel14Filter,pixel20Filter,pixel21Filter,pixel22Filter,pixel23Filter,pixel24Filter,pixel30Filter,pixel31Filter,pixel32Filter,pixel33Filter,pixel34Filter,pixel40Filter,pixel41Filter,pixel42Filter,pixel43Filter,pixel44Filter;
	
	    
	    int weightedAverage;
	    
		for (int row = 2; row < pi.getHeight() - 2; row++)
		{
			for (int col = 2; col < pi.getWidth() - 2; col++)
			{
				pixel00=data[row-2][col-2];
				pixel01=data[row-2][col-1];
				pixel02=data[row-2][col];
				pixel03=data[row-2][col+1];
				pixel04=data[row-2][col+2];
				pixel10=data[row-1][col-2];
				pixel11=data[row-1][col-1];
				pixel12=data[row-1][col];
				pixel13=data[row-1][col+1];
				pixel14=data[row-1][col+2];
				pixel20=data[row][col-2];
				pixel21=data[row][col-1];
				pixel22=data[row][col];
				pixel23=data[row][col+1];
				pixel24=data[row][col+2];
				pixel30=data[row+1][col-2];
				pixel31=data[row+1][col-1];
				pixel32=data[row+1][col];
				pixel33=data[row+1][col+1];
				pixel34=data[row+1][col+2];
				pixel40=data[row+2][col-2];
				pixel41=data[row+2][col-1];
				pixel42=data[row+2][col];
				pixel43=data[row+2][col+1];
				pixel44=data[row+2][col+2];
	    	  
				pixel00Filter=matrix.pixel00*pixel00.red;
				pixel01Filter=matrix.pixel01*pixel01.red;
				pixel02Filter=matrix.pixel02*pixel02.red;
				pixel03Filter=matrix.pixel03*pixel03.red;
				pixel04Filter=matrix.pixel04*pixel04.red;
				pixel10Filter=matrix.pixel10*pixel10.red;
				pixel11Filter=matrix.pixel11*pixel11.red;
				pixel12Filter=matrix.pixel12*pixel12.red;
				pixel13Filter=matrix.pixel13*pixel13.red;
				pixel14Filter=matrix.pixel14*pixel14.red;
				pixel20Filter=matrix.pixel20*pixel20.red;
				pixel21Filter=matrix.pixel21*pixel21.red;
				pixel22Filter=matrix.pixel22*pixel22.red;
				pixel23Filter=matrix.pixel23*pixel23.red;
				pixel24Filter=matrix.pixel24*pixel24.red;
				pixel30Filter=matrix.pixel30*pixel30.red;
				pixel31Filter=matrix.pixel31*pixel31.red;
				pixel32Filter=matrix.pixel32*pixel32.red;
				pixel33Filter=matrix.pixel33*pixel33.red;
				pixel34Filter=matrix.pixel34*pixel34.red;
				pixel40Filter=matrix.pixel40*pixel40.red;
				pixel41Filter=matrix.pixel41*pixel41.red;
				pixel42Filter=matrix.pixel42*pixel42.red;
				pixel43Filter=matrix.pixel43*pixel43.red;
				pixel44Filter=matrix.pixel44*pixel44.red;
	
	    		weightedAverage=((pixel00Filter+pixel01Filter+pixel02Filter+pixel03Filter+pixel04Filter+pixel10Filter+pixel11Filter+pixel12Filter+pixel13Filter+pixel14Filter+pixel20Filter+pixel21Filter+pixel22Filter+pixel23Filter+pixel24Filter+pixel30Filter+pixel31Filter+pixel32Filter+pixel33Filter+pixel34Filter+pixel40Filter+pixel41Filter+pixel42Filter+pixel43Filter+pixel44Filter) / matrix.weightTotal);
	    		
	    		if (weightedAverage<0)
	    		{
	    			weightedAverage=0;
	    		}
	    		if (weightedAverage>255)
	    		{
	    			weightedAverage=255;
	    		}   		
	    		
	    		dataFiltered[row][col].red = weightedAverage;
	    		
	    		
				pixel00Filter=matrix.pixel00*pixel00.green;
				pixel01Filter=matrix.pixel01*pixel01.green;
				pixel02Filter=matrix.pixel02*pixel02.green;
				pixel03Filter=matrix.pixel03*pixel03.green;
				pixel04Filter=matrix.pixel04*pixel04.green;
				pixel10Filter=matrix.pixel10*pixel10.green;
				pixel11Filter=matrix.pixel11*pixel11.green;
				pixel12Filter=matrix.pixel12*pixel12.green;
				pixel13Filter=matrix.pixel13*pixel13.green;
				pixel14Filter=matrix.pixel14*pixel14.green;
				pixel20Filter=matrix.pixel20*pixel20.green;
				pixel21Filter=matrix.pixel21*pixel21.green;
				pixel22Filter=matrix.pixel22*pixel22.green;
				pixel23Filter=matrix.pixel23*pixel23.green;
				pixel24Filter=matrix.pixel24*pixel24.green;
				pixel30Filter=matrix.pixel30*pixel30.green;
				pixel31Filter=matrix.pixel31*pixel31.green;
				pixel32Filter=matrix.pixel32*pixel32.green;
				pixel33Filter=matrix.pixel33*pixel33.green;
				pixel34Filter=matrix.pixel34*pixel34.green;
				pixel40Filter=matrix.pixel40*pixel40.green;
				pixel41Filter=matrix.pixel41*pixel41.green;
				pixel42Filter=matrix.pixel42*pixel42.green;
				pixel43Filter=matrix.pixel43*pixel43.green;
				pixel44Filter=matrix.pixel44*pixel44.green;
	
	    		weightedAverage=((pixel00Filter+pixel01Filter+pixel02Filter+pixel03Filter+pixel04Filter+pixel10Filter+pixel11Filter+pixel12Filter+pixel13Filter+pixel14Filter+pixel20Filter+pixel21Filter+pixel22Filter+pixel23Filter+pixel24Filter+pixel30Filter+pixel31Filter+pixel32Filter+pixel33Filter+pixel34Filter+pixel40Filter+pixel41Filter+pixel42Filter+pixel43Filter+pixel44Filter) / matrix.weightTotal);
	    		
	    		if (weightedAverage<0)
	    		{
	    			weightedAverage=0;
	    		}
	    		if (weightedAverage>255)
	    		{
	    			weightedAverage=255;
	    		}   		
	    		
	    		dataFiltered[row][col].green = weightedAverage;
	    		
	    
	    		
				pixel00Filter=matrix.pixel00*pixel00.blue;
				pixel01Filter=matrix.pixel01*pixel01.blue;
				pixel02Filter=matrix.pixel02*pixel02.blue;
				pixel03Filter=matrix.pixel03*pixel03.blue;
				pixel04Filter=matrix.pixel04*pixel04.blue;
				pixel10Filter=matrix.pixel10*pixel10.blue;
				pixel11Filter=matrix.pixel11*pixel11.blue;
				pixel12Filter=matrix.pixel12*pixel12.blue;
				pixel13Filter=matrix.pixel13*pixel13.blue;
				pixel14Filter=matrix.pixel14*pixel14.blue;
				pixel20Filter=matrix.pixel20*pixel20.blue;
				pixel21Filter=matrix.pixel21*pixel21.blue;
				pixel22Filter=matrix.pixel22*pixel22.blue;
				pixel23Filter=matrix.pixel23*pixel23.blue;
				pixel24Filter=matrix.pixel24*pixel24.blue;
				pixel30Filter=matrix.pixel30*pixel30.blue;
				pixel31Filter=matrix.pixel31*pixel31.blue;
				pixel32Filter=matrix.pixel32*pixel32.blue;
				pixel33Filter=matrix.pixel33*pixel33.blue;
				pixel34Filter=matrix.pixel34*pixel34.blue;
				pixel40Filter=matrix.pixel40*pixel40.blue;
				pixel41Filter=matrix.pixel41*pixel41.blue;
				pixel42Filter=matrix.pixel42*pixel42.blue;
				pixel43Filter=matrix.pixel43*pixel43.blue;
				pixel44Filter=matrix.pixel44*pixel44.blue;
	
	    		weightedAverage=((pixel00Filter+pixel01Filter+pixel02Filter+pixel03Filter+pixel04Filter+pixel10Filter+pixel11Filter+pixel12Filter+pixel13Filter+pixel14Filter+pixel20Filter+pixel21Filter+pixel22Filter+pixel23Filter+pixel24Filter+pixel30Filter+pixel31Filter+pixel32Filter+pixel33Filter+pixel34Filter+pixel40Filter+pixel41Filter+pixel42Filter+pixel43Filter+pixel44Filter) / matrix.weightTotal);
	    		
	    		if (weightedAverage<0)
	    		{
	    			weightedAverage=0;
	    		}
	    		if (weightedAverage>255)
	    		{
	    			weightedAverage=255;
	    		}   		
	    		
	    		dataFiltered[row][col].blue = weightedAverage;
			}
		}
	
		pi.setData(dataFiltered);
	} 
}
