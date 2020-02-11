package data.EdgarDL;

public enum Quarter {
	Q1,Q2,Q3,Q4;
	
	public static Quarter determine(int month){
		switch (month){
			case 1:
			case 2:
			case 3:
				return Q1;
			case 4:
			case 5:
			case 6:
				return Q2;
			case 7:
			case 8:
			case 9:
				return Q3;
			case 10:
			case 11:
			case 12:
				return Q4;
			default : 
				return null;
		}			
	}
	
	public static Quarter getNext (Quarter input){
		switch (input){
			case Q1: return Q2;
			case Q2: return Q3;
			case Q3: return Q4;
			case Q4: return Q1;
			default: return null;
		}
	}

	public static Quarter getPrev(Quarter input){
		switch (input){
			case Q1: return Q4;
			case Q2: return Q1;
			case Q3: return Q2;
			case Q4: return Q3;
		default: return null;
		}
	}
	
}
