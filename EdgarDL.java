package data.EdgarDL;

import java.util.*;
import java.io.*;
import org.apache.commons.net.ftp.*;

public class EdgarDL {

	String fileName;
	Vector<report> accessions= new Vector<report>(20);
	Vector<StringBuilder> urlZ= new Vector<StringBuilder>(20,5);
	
	
	void read_file(String path){
		String temp="";
		File accessionstxt = new  File(path);
		try{
			BufferedReader buffRead = new BufferedReader(new FileReader(accessionstxt));
			
			do{
				temp= buffRead.readLine();
				if (temp==null){
					break;
				}
					accessions.add(new report(temp));						
			}while (temp!=null);
			buffRead.close();
			
		}catch(Exception e){
			System.out.println(e);	
		}			
	}
	
	void urlMaker(){
		for (int i=0;i<accessions.size();i++){
			System.out.println(accessions.get(i).repDate+"|"+accessions.get(i).reportDate);
			urlZ.add(new StringBuilder("/edgar/data/"));	//ftp://ftp.sec.gov
			urlZ.get(i).append("37996/"+accessions.get(i).year+"/"+accessions.get(i).CIK+accessions.get(i).year+accessions.get(i).accNo);
		}
	}
	
	void downloader(){
		 FTPClient ftpClient = new FTPClient();
		 try{
			 ftpClient.connect("ftp.sec.gov");
	         ftpClient.login("anonymous","leocui9387@gmail.com");
	         ftpClient.enterLocalPassiveMode();
	         ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	         
	         File temp = new File("F:/Stock/Ford/10q/ford"+Quarter.getPrev(accessions.get(1).repQrt())+accessions.get(1).year+".xlsx");
	         OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(temp));
	         boolean success=ftpClient.retrieveFile(urlZ.get(1)+"/Financial_Report.xlsx", outputStream1);
	         outputStream1.close();
	         System.out.println(success);
	         System.out.println(urlZ.get(1)+"/Financial_Report.xlsx");
		 }catch(Exception e){
			 System.out.println(e);
		 }
	}
		
	public static void main (String[] args){
		EdgarDL dler = new EdgarDL(); 
		dler.fileName="F:/Stock/Ford/FordAccession10Q.csv";
		dler.read_file(dler.fileName);
		dler.urlMaker();
		dler.downloader();
		
	}
}