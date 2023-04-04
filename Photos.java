 package MyPackage;


public class Ex2 {
	

	public static int [][] rgb2gray (int[][][] im) {
		int [][] gray = new int [im[0].length][im[0][0].length] ;
		for (int i = 0; i < im[0].length; i++) {
			for (int j = 0; j < im[0][0].length; j++) { 
				gray[i][j] = ((int)(((im[0][i][j]) * 0.3 + (im[1][i][j]) * 0.59 + (im[2][i][j])* 0.11))) * 255 ;  	 		
			}
		} 
		return gray ;
	}
	
	public static int[][][] rotate90(int[][][] im){
		int [][][] spin = new int [im.length][im[0][0].length][im[0].length] ; 
		int widthOldPhoto = im[0][0].length ; 
		int heightOldPhoto = im[0].length ; 
		for (int i = 0; i < widthOldPhoto ; i++) {
			for (int j = 0; j < heightOldPhoto ; j++) {
				spin[0][i][heightOldPhoto -1  - j] = im[0][j][i] ; 
				spin[1][i][heightOldPhoto -1  - j] = im[1][j][i] ;
				spin[2][i][heightOldPhoto -1  - j] = im[2][j][i] ;
			}
		}
		return spin ; 
	}
	
	
				// help function

	public static int SmoothForPiexl(int [][][] SmoothImg ,int I , int J ,  int n , int c ) {
		int i = 0 , j = 0 ; 
		int sum = 0 ; 
		int count = 0 ;  
		for (i = I - n ; i <= I + n  ; i++) {
			for (j = J - n  ; j <= J + n  ; j++){
				if(i >= 0 && i <= SmoothImg[0].length - 1  && j >= 0 && j <= SmoothImg[0][0].length - 1 ) {
					sum = sum + SmoothImg[c][i][j] ;
					count = count + 1 ;
					}
				}
			}
		sum = (int)(sum/count) ; 
		return sum  ;
		
	}
	public static int [][][] Smooth(int [][][] img,int n) {   
		int height = img[0].length ; 
		int width = img[0][0].length ;
		int colors = img.length ; 
		for (int I = 0 ; I < height ;  I++) {
			for (int J = 0 ; J < width ; J++) {
				for (int c = 0; c < colors; c++) {
					img[c][I][J] =  SmoothForPiexl(img , I , J , n , c ) ; 
				}				
			}
		}
		return img ; 
	}
	
	
	 public static int[][] scaleup(double factor_h, double factor_w, int[][] im) {
		 int newWidth = (int)((im[0].length * factor_w) +1) ;
		 int newHeight = (int)((im.length * factor_h) +1) ; 
		 int [][] stretch = new int [newHeight][newWidth] ; 
		 for (int i = 0; i < im.length -1; i++) {
			 for (int j = 0; j <im[0].length -1 ; j++) {
				 for (int fw = 0; fw < factor_w; fw++) {
					 for (int fh = 0; fh < factor_h; fh++) {
					 stretch [(int)((i* factor_h +fh)+1 )][(int)((j*factor_w + fw)+1)] = im[i][j] ;
					 }
		 		}
			}
		 }	 
		 return  stretch ; 
	 }
	
	 
	 
	
	public static void main(String[] args) {
		
		int [][][] image = MyImageIO.readImageFromFile("C:/Users/Student/Pictures/Saved Pictures/onn.jpg") ;
		int [][] ex = new int [image[0].length][image[0][0].length] ; 
		 ex = rgb2gray (image) ; 
				
			MyImageIO.writeImageToFile(("C:/Users/Student/Pictures/Saved Pictures/four2" ) ,  ex	) ;
				
		
		
		int [][][] image2 = MyImageIO.readImageFromFile("C:/Users/Student/Pictures/Saved Pictures/onn.jpg") ;
		int [][][] rotate2 = new int [image2.length][image2[0][0].length][image2[0].length] ; 
		rotate2 = rotate90 (image2) ; 
		MyImageIO.writeImageToFile("C:/Users/Student/Pictures/Saved Pictures/two2" , rotate2);
		
		
		int [][][] image3 = MyImageIO.readImageFromFile("C:/Users/Student/Pictures/Saved Pictures/onn.jpg") ;
		image3 = Smooth(image3 , 4 ) ; 
		MyImageIO.writeImageToFile("C:/Users/Student/Pictures/Saved Pictures/two3", image3);
		
		
		
		
		int [][] scall = new int [ex.length][ex[0].length] ; 
		scall = scaleup(0.5 , 1.3 , ex ) ; 
		
		MyImageIO.writeImageToFile(("C:/Users/Student/Pictures/Saved Pictures/five22" ) ,  scall	) ;
		
		  
		
		
		
		
		
	}

}
