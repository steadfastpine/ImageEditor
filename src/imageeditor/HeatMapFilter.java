package imageeditor;
/**
 * Filter for Laplacian effect v3 - heap map effect
 * 3x3 matrix
 * -1,-1,-1,-1,8,-1,-1,-1,-1 
 */
import java.util.*;
public class HeatMapFilter implements Filter
{
	public void filter(PixelImage pi)
	{
		Matrix heatMapMatrix = new Matrix(-1,-1,-1,-1,8,-1,-1,-1,-1);
		PixelImage.matrix3x3v2(pi, heatMapMatrix);
	}
}