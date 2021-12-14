package imageeditor;
/**
 * Matrix class for 3x3 weighed averages
 */
public class Matrix{
	
	int topLeftWeight;
	int topRightWeight;
	int topCenterWeight;
	int middleLeftWeight;
	int middleRightWeight;
	int middleCenterWeight;
	int bottomLeftWeight;
	int bottomRightWeight;
	int bottomCenterWeight; 
	
	public Matrix(int topLeftWeightIn, int topRightWeightIn, int topCenterWeightIn, int middleLeftWeightIn, int middleRightWeightIn, int middleCenterWeightIn, int bottomLeftWeightIn, int bottomRightWeightIn, int bottomCenterWeightIn) {
		topLeftWeight=topLeftWeightIn;
		topRightWeight=topRightWeightIn;
		topCenterWeight=topCenterWeightIn;
		middleLeftWeight=middleLeftWeightIn;
		middleRightWeight=middleRightWeightIn;
		middleCenterWeight=middleCenterWeightIn;
		bottomLeftWeight=bottomLeftWeightIn;
		bottomRightWeight=bottomRightWeightIn;
		bottomCenterWeight=bottomCenterWeightIn;
	}
}
