package imageeditor;
/**
 * Matrix class for 5x5 weighed averages
 */
public class Matrix5x5{
	
	 int pixel00,pixel01,pixel02,pixel03,pixel04,pixel10,pixel11,pixel12,pixel13,pixel14,pixel20,pixel21,pixel22,pixel23,pixel24,pixel30,pixel31,pixel32,pixel33,pixel34,pixel40,pixel41,pixel42,pixel43,pixel44;
	 int weightTotal;
	 
	public Matrix5x5(int pixel00In, int pixel01In, int pixel02In, int pixel03In, int pixel04In, int pixel10In, int pixel11In, int pixel12In, int pixel13In, int pixel14In, int pixel20In, int pixel21In, int pixel22In, int pixel23In, int pixel24In, int pixel30In, int pixel31In, int pixel32In, int pixel33In, int pixel34In, int pixel40In, int pixel41In, int pixel42In, int pixel43In, int pixel44In) {
		pixel00=pixel00In;
		pixel01=pixel01In;
		pixel02=pixel02In;
		pixel03=pixel03In;
		pixel04=pixel04In;
		pixel10=pixel10In;
		pixel11=pixel11In;
		pixel12=pixel12In;
		pixel13=pixel13In;
		pixel14=pixel14In;
		pixel20=pixel20In;
		pixel21=pixel21In;
		pixel22=pixel22In;
		pixel23=pixel23In;
		pixel24=pixel24In;
		pixel30=pixel30In;
		pixel31=pixel31In;
		pixel32=pixel32In;
		pixel33=pixel33In;
		pixel34=pixel34In;
		pixel40=pixel40In;
		pixel41=pixel41In;
		pixel42=pixel42In;
		pixel43=pixel43In;
		pixel44=pixel44In;
		
		weightTotal=pixel00+pixel01+pixel02+pixel03+pixel04+pixel10+pixel11+pixel12+pixel13+pixel14+pixel20+pixel21+pixel22+pixel23+pixel24+pixel30+pixel31+pixel32+pixel33+pixel34+pixel40+pixel41+pixel42+pixel43+pixel44;
	}
}
