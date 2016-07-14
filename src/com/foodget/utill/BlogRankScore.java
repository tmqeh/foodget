package com.foodget.utill;

public class BlogRankScore {
	public static final int IMAGESCORE=2;
	public static final int COMMENTSCORE=1;

	public static int bodyLengthScore(int bodyLenth){
		int bodyLenthScore=0;
		
		if(bodyLenth>=2000){
			bodyLenthScore=10;
		}else if(bodyLenth>=1500){
			bodyLenthScore=7;
		}else if(bodyLenth>=1000){
			bodyLenthScore=5;
		}else if(bodyLenth>=500){
			bodyLenthScore=3;
		}else{
			bodyLenthScore=1;
		}
		return bodyLenthScore;
	}

}
