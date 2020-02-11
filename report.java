package data.EdgarDL;

import java.time.*;

public class report {
	
	int reportDate=0;
	LocalDate repDate= LocalDate.of(1900,1,1);
	String CIK, year, accNo;
	
	
	report (String raw){
		parseString(raw);
	}
	report (int date, String code){
		reportDate=date;
		repDate=repDate.plusDays(reportDate-2);
		parseCode(code);
	}
	
	void parseString (String input){
		int commaIndex =0;
		
		commaIndex = input.indexOf(",");
		parseCode(input.substring(commaIndex+1,input.length()));
		reportDate= Integer.parseInt(input.substring(0,commaIndex));
		repDate=repDate.plusDays(reportDate-2);
	}
	
	void parseCode(String input){
		CIK = new String(input.substring(0,10));
		year= new String(input.substring(11,13));
		accNo = new String(input.substring(14,20));
	}
	
	Quarter repQrt (){
		
		return Quarter.determine(repDate.getMonthValue());
	}
	
}