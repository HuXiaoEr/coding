package JzOffer;

// OVER
public class N3_二维数组中的查找 {
	
	// better
    public boolean Find(int target, int [][] array) {
    	if(array == null || array.length == 0 || array[0].length == 0) return false;
    	int r = 0;
    	int c = array[0].length-1;
    	while(r < array.length && c >= 0){
    		if(target > array[r][c]) r++;
    		else if(target < array[r][c]) c--;
    		else return true;
    	}
    	return false;
    }
	
	
	
	// 什么时候写的？？
	//careful about j < 0
	public boolean Find2(int target, int [][] array) {
		if(array == null || array.length == 0) return false;
		int row = array.length;
		int col = array[0].length;
		int i = 0, j = col - 1;
		while(i < row && j >= 0)
		{
			while(j >= 0 && target < array[i][j]) j--;  //donnot forget j >= 0
            if(j < 0) return false;  //donnot forget
			if(target == array[i][j]) return true;
			i++;
		}
		return false;
    }
}
