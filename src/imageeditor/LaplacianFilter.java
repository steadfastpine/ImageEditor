package imageeditor;
/**
 * Filter for Laplacian effect v1
 * 3x3 matrix
 * -1,-1,-1,-1,8,-1,-1,-1,-1
 */
import java.util.*;
public class LaplacianFilter implements Filter
{
	public void filter(PixelImage pi)
	{
		Matrix laplacianMatrix = new Matrix(-1,-1,-1,-1,8,-1,-1,-1,-1);
		PixelImage.matrix3x3v1(pi, laplacianMatrix);
	}
}