package imageeditor;

public class NumberConverter {
	
	

    int red, green, blue;
	
    public int hexToDec(String hex){  
	    int decimal=Integer.parseInt(hex,16);  
	    return decimal;  
    } 
    
    public String decToHex(int decimal){  
        		String hex = Integer.toHexString(decimal);
        		return hex;
    }   
    
    

    
    
	public int decToBin(int numberDec) {
		
		int binary[] = new int[40];
	     int index = 0;
	     while(numberDec > 0){
	       binary[index++] = numberDec%2;
	       numberDec = numberDec/2;
	     }
	 	String binaryOut = "";
	   for(int i = index-1;i >= 0;i--){
	       //System.out.print(binary[i]);
	       binaryOut=binaryOut+binary[i];
	    }
	   
	    int binaryOutInt=Integer.parseInt(binaryOut);  
	    //System.out.println(binaryOutInt);
	    return binaryOutInt;
	}
	
	public int binToDec(int numberBin) {
		int decimal=0,p=0;
        
        while(numberBin!=0)
        {
            decimal+=((numberBin%10)*Math.pow(2,p));
            numberBin=numberBin/10;
            p++;
        }
        
       // System.out.println(decimal);
	    return decimal;
	}
	
	
	public int rgbToDec(int red, int green, int blue) {

		String redConv, greenConv, blueConv, rgbBin, rgbHex;
		int rgbDec;
		redConv=this.decToHex(red);
		greenConv=this.decToHex(green);		
		blueConv=this.decToHex(blue);	
		
		rgbHex=String.valueOf(redConv) + String.valueOf(greenConv) + String.valueOf(blueConv);
		rgbDec=this.hexToDec(rgbHex);
		
		
		return rgbDec;
		//rgbBin=Integer.parseInt(redConv+greenConv+blueConv);
		
	}	
	
	public void decToRgb(int rgbDec) {

		String redConv, greenConv, blueConv, rgbBin, rgbHex;
		
		
		//rgbHex=this.decToHex(rgbDec);
		
		this.red = rgbDec / 65536;
		this.green = (rgbDec - this.red * 65536) / 256;
		this.blue = rgbDec - this.red * 65536 - this.green * 256;
		

		
		
		//this.red=;
		
	}	
	
	
	public static void main(String[] args) {
		NumberConverter obj = new NumberConverter();

	     System.out.println("\n\nRgb to dec r:255 g:255 b:255: ");
	     System.out.println( obj.rgbToDec(255,255,255));
	     
	     System.out.println("\n\nDec to rgb 16777215: ");
	     obj.decToRgb(16777215);
	     System.out.println( obj.red);	    
	     System.out.println( obj.green);	
	     System.out.println( obj.blue);
	     
	     System.out.println("\n\nRgb to dec r:100 g:100 b:100: ");
	     System.out.println( obj.rgbToDec(100,100,100));
	     
	     System.out.println("\n\nDec to rgb 6579300: ");
	     obj.decToRgb(6579300);
	     System.out.println( obj.red);	    
	     System.out.println( obj.green);	
	     System.out.println( obj.blue);
	     	     
	    
	     System.out.println("\n\nDecimal to hex 124: ");
	     System.out.println( obj.decToHex(124));

	     System.out.println("\n\nHex to decimal 7c: ");
	     System.out.println( obj.hexToDec("7c"));
	     
	     System.out.println("\n\nHex to decimal A: ");
	     System.out.println( obj.hexToDec("A"));
		
	     System.out.println("\n\nBinary representation of 124: ");
	     System.out.println(obj.decToBin(124));
	     System.out.println("\nBinary representation of 45: ");
	     System.out.println( obj.decToBin(45));
	     System.out.println("\nBinary representation of 999: ");
	     System.out.println( obj.decToBin(999));
	     
	     
	     System.out.println("\n\nDecimal representation of 1111100: ");
	     System.out.println( obj.binToDec(1111100));
	     System.out.println("\nDecimal representation of 101101: ");
	     System.out.println( obj.binToDec(101101));
	     System.out.println("\nDecimal representation of 1111100111: ");
	     System.out.println(obj.binToDec(1111100111));

	}

}
