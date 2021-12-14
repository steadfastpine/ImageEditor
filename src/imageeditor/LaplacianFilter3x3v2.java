package imageeditor;
/**
 * Filter for Laplacian effect v2
 * 3x3 matrix
 * 0,-1,0,-1,4,-1,0,-1,0 
 */
import java.util.*;
public class LaplacianFilter3x3v2 implements Filter
{
	public void filter(PixelImage pi)
	{
		Matrix laplacianMatrix = new Matrix(0,-1,0,-1,4,-1,0,-1,0);
		PixelImage.matrix3x3v1(pi, laplacianMatrix);
	}
}