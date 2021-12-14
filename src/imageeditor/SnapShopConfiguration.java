package imageeditor;

// Write your short report here (-2 if there is no report)

/**
 * A class to configure the SnapShop application
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SnapShopConfiguration
{
  /**
   * Method to configure the SnapShop.  Call methods like addFilter
   * and setDefaultFilename here.
   * @param theShop A pointer to the application
   */
  public static void configure(SnapShop theShop)
  {
	theShop.setDefaultFilename("/home/nu/programs/java/ImageEditor/billg.jpg");  
	//theShop.setDefaultFilename("c:/hw5/billg.jpg");	  
	theShop.addFilter(new FlipHorizontalFilter(), "Flip Horizontal");
    // add your other filters below

    theShop.addFilter(new FlipVerticalFilter(), "Flip Vertical");
    theShop.addFilter(new NegativeFilter(), "Negative");    
    theShop.addFilter(new GaussianFilter(), "Gaussian 3x3");
    theShop.addFilter(new GaussianFilter5x5(), "Gaussian 5x5");    
    theShop.addFilter(new LaplacianFilter(), "Laplacian 3x3 v1");   
    theShop.addFilter(new LaplacianFilter3x3v2(), "Laplacian 3x3 v2"); 
    theShop.addFilter(new HeatMapFilter(), "Laplacian 3x3 v3");     
    theShop.addFilter(new UnsharpFilter(), "Unsharp Masking 3x3");   
    theShop.addFilter(new EdgyFilter(), "Edgy 3x3");
    theShop.addFilter(new BlackWhiteFilter(), "Black & White");   
    theShop.addFilter(new FibonacciSequence5x5Linear(), "Phi 5x5 Linear");    
    theShop.addFilter(new FibonacciSequence5x5Center(), "Phi 5x5 Center");
  }
}
