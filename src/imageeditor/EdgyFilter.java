package imageeditor;
/**
 * Filter for edgy effect
 * 3x3 matrix
 * -1,-1,-1,-1,9,-1,-1,-1,-1 
 */
import java.util.*;
public class EdgyFilter implements Filter
{
	public void filter(PixelImage pi)
	{
		Matrix edgyMatrix = new Matrix( -1,-1,-1,-1,9,-1,-1,-1,-1 );
		PixelImage.matrix3x3v1(pi, edgyMatrix);
	}
}
