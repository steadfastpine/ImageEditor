package imageeditor;
import java.util.*;
public class FibonacciSequence5x5Center implements Filter
{
	  public void filter(PixelImage pi)
	  {
		  Matrix5x5 gaussianMatrix = new Matrix5x5( 2,3,5,3,2,3,5,8,5,3,5,8,13,8,5,3,5,8,5,3,2,3,5,3,2 );
		  PixelImage.matrix5x5v1(pi, gaussianMatrix);
	  }
}