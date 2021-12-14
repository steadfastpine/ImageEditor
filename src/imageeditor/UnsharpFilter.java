package imageeditor;
/**
 * Filter for unsharpen effect
 * 3x3 matrix
 * -1,-2,-1,-2,28,-2,-1,-2,-1 
 */
import java.util.*;
public class UnsharpFilter implements Filter
{
	public void filter(PixelImage pi)
	{
		Matrix unsharpMatrix = new Matrix(-1,-2,-1,-2,28,-2,-1,-2,-1);
		PixelImage.matrix3x3v1(pi, unsharpMatrix);
	}
}