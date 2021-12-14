package imageeditor;
/**
 * Filter for Gaussian effect
 * 3x3 matrix
 * 1,2,1,2,4,2,1,2,1 
 */
import java.util.*;
public class GaussianFilter implements Filter
{
	public void filter(PixelImage pi)
	{
		Matrix gaussianMatrix = new Matrix( 1,2,1,2,4,2,1,2,1 );
		PixelImage.matrix3x3v1(pi, gaussianMatrix);
	}
}