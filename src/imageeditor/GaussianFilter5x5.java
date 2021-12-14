package imageeditor;
import java.util.*;
public class GaussianFilter5x5 implements Filter
{
	  public void filter(PixelImage pi)
	  {
		  Matrix5x5 gaussianMatrix = new Matrix5x5( 2,4,5,4,2,4,9,12,9,4,5,12,15,12,5,4,9,12,9,4,2,4,5,4,2 );
		  PixelImage.matrix5x5v1(pi, gaussianMatrix);
	  }
}