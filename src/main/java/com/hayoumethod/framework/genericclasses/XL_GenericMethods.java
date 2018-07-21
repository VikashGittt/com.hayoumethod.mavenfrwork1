package com.hayoumethod.framework.genericclasses;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XL_GenericMethods {
   public static String TestDataPath1;
  
   public static String TDSheetName1;
   public static String TDSheetName2;
   public static String TDSheetName3;
   public static HashMap<String, Object> testData_HM;
   public static String[] ArrColumnNames={"Module Name", "SubModule Name","TestCase Name", "Field Name", "Expected Result", "Actual Result", "Status", "Snapshot Link" };
   
   public static String ResultFileName="";
   public static String ResultSheet="ResultSheet";

	public static void main(String[] args) throws IOException, InvalidFormatException {
	
		//String[] arrs=new String[6];
		//arrs[0]="Nitin";
		//new ArrayList<String>();
		String[] ResultArray={"TC001", "Create And Validate Lead", "Marketing", "Leads", "SaveBtn", "Save", "Save", "Passed", "Snapshot"};
		xl_WriteResultToExcel(ResultArray);
		
		xl_WriteSimple();
		HashMap<String, String> hm=new HashMap<String, String>();
		hm.put("Name", "Nitin");
		hm.put("Address", "Gurgaon");
		String x=hm.get("Name");
		XL_GenericMethods a=new XL_GenericMethods();
		System.out.println(a);
		
	//	TestDataPath1="FSH_WorkBook.xlsx";
		//TDSheetName1="Requirements_FSH";
		xl_GetFullData();
		
		
	}

	
	
	public static void xl_WriteSimple() throws IOException{
		Workbook wbookObj=xl_CreateWorkbook("Reports/Result.xlsx");
		Sheet sheetObj=wbookObj.createSheet(ResultSheet);
		
		for(int i=0; i<=10; i++){
			Row rowObj=sheetObj.createRow(i);
			for(int j=0; j<=5; j++){
				Cell cellObj=rowObj.createCell(j);
				cellObj.setCellValue("Selenium");
			}
		}
		
		FileOutputStream fos=new FileOutputStream("Reports/Result.xlsx");
		wbookObj.write(fos);
	}
	
public static void xl_WriteResultToExcel(String[] ArrResultStep) throws IOException, InvalidFormatException{
	File resFileObj=new File(ResultFileName);
		    if(	resFileObj.exists()==false){
		      xl_CreateResultSheet();
		    }
		    xl_UpdateResults(ArrResultStep);
	}
	
	public static void xl_UpdateResults(String[] ArrResultStep) throws InvalidFormatException, IOException{
		FileInputStream fis=new FileInputStream(ResultFileName);
	    Workbook wBookObj=WorkbookFactory.create(fis);
	    Sheet sheetObj=wBookObj.getSheet(ResultSheet);
	    int rowlimit=sheetObj.getLastRowNum();
	    int reqrow=rowlimit+1;
	    Row rowObj=sheetObj.createRow(reqrow);
	    for(int i=0; i<=ArrResultStep.length-1;i++){
	    	Cell cellObj=rowObj.createCell(i);
	    	String ValArr=ArrResultStep[i];
	    	String status=ArrResultStep[ArrResultStep.length-2];
	    	if(status.equalsIgnoreCase("Passed")==false){
	    		CellStyle failstyle=xl_SetCellStyling(wBookObj, IndexedColors.RED.getIndex());
	    		if(i==ArrResultStep.length-1){
	    			/////HYPERLINK("C:\","snapshot")
	    			String ImagePath=ArrResultStep[ArrResultStep.length-1];
	    			System.out.println("Method xl_UpdateResults- Imagepath variable val = "+ImagePath);
	    			cellObj.setCellFormula("HYPERLINK("+'"'+ImagePath+'"'+", "+'"'+"Click here for snapshot"+'"'+")");
	    		}else{
	    			cellObj.setCellValue(ValArr);
	    		}
	    		
	    		cellObj.setCellStyle(failstyle);
	    	}else{
	    		CellStyle passstyle=xl_SetCellStyling(wBookObj, IndexedColors.GREEN.getIndex());
	    		cellObj.setCellValue(ValArr);
	    		cellObj.setCellStyle(passstyle);
	    	}
	    }
	    FileOutputStream FOS=new FileOutputStream(ResultFileName);
	    wBookObj.write(FOS);
	}
	
	private static CellStyle xl_SetCellStyling(Workbook wbookObj, short colorNum){
		CellStyle styleObj=wbookObj.createCellStyle();
		styleObj.setFillForegroundColor(colorNum);
		//short fillingPattern=CellStyle.SOLID_FOREGROUND;
		FillPatternType fillingPattern= FillPatternType.SOLID_FOREGROUND;
		styleObj.setFillPattern(fillingPattern);
		styleObj.setVerticalAlignment(VerticalAlignment.TOP);
		styleObj.setAlignment(HorizontalAlignment.GENERAL);
		styleObj.setWrapText(true);
		styleObj.setBorderBottom(BorderStyle.THICK);
		styleObj.setBorderRight(BorderStyle.THICK);
		return styleObj;
	}
	private static Font xl_SetFontStyling(Workbook wbookObj, int fontHieght){
		Font fontObj=wbookObj.createFont();
		fontObj.setFontHeightInPoints((short) fontHieght);
		fontObj.setItalic(true);
	//	fontObj.setBoldweight(Font.BOLDWEIGHT_BOLD);  Deprecated
		fontObj.setBold(true);
		fontObj.setColor(IndexedColors.WHITE.getIndex());
		fontObj.setFontName("Arial");
		return fontObj;
	}
	
	public static void xl_CreateResultSheet() throws IOException{
		Workbook wbookObj=xl_CreateWorkbook("Reports/Result.xlsx");
		Sheet sheetObj=wbookObj.createSheet(ResultSheet);
		//////////////////////////////////////////////////////
		short ColumnColorNo=IndexedColors.DARK_TEAL.getIndex();
		CellStyle styleObj=xl_SetCellStyling(wbookObj, ColumnColorNo);
	    Font fontObj=xl_SetFontStyling(wbookObj, 12);
	    styleObj.setFont(fontObj);
		/////////////////////////////////////////////////
		int arrItemCnt=ArrColumnNames.length;
			Row rowObj=sheetObj.createRow(0);
			for(int j=0; j<=arrItemCnt-1; j++){
				Cell cellObj=rowObj.createCell(j);
				//sheetObj.autoSizeColumn(100);
				sheetObj.setColumnWidth(j, 5500);
				String ColumnName=ArrColumnNames[j];
				cellObj.setCellValue(ColumnName);
				cellObj.setCellStyle(styleObj);
			}
		 String TS=fn_GetTimeStamp();
		 ResultFileName="Reports/Result"+TS+".xlsx";
		FileOutputStream fos=new FileOutputStream(ResultFileName);
		wbookObj.write(fos);
	}
	
	
	
	public static String fn_GetTimeStamp(){
		DateFormat DF=DateFormat.getDateTimeInstance();
		String DateVal=DF.format(new Date());
		DateVal=DateVal.replaceAll("/", "-");
		DateVal=DateVal.replaceAll(",", "-");
		DateVal=DateVal.replaceAll(":", "_");
		return DateVal;
	}
	
	
	public static void xl_ReadSimple() throws IOException{
		Workbook wbookObj=xl_GetWorkbook("FSH_WorkBook.xlsx");

		Sheet sheetObj=wbookObj.getSheetAt(0);
		Row rowObj=sheetObj.getRow(0);
		
		Cell cellObj=rowObj.getCell(1);
		String val=cellObj.getStringCellValue();
		System.out.println(val);
	}
	
	public static void xl_GetFullData() throws IOException{
		Workbook wbookObj=xl_GetWorkbook(TestDataPath1);
		Sheet sheetobj=wbookObj.getSheet(TDSheetName1);
		int rowCount=sheetobj.getLastRowNum();
		int columnCount =sheetobj.getRow(0).getLastCellNum();
		 MissingCellPolicy mcp=Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;	
	    // MissingCellPolicy mcp=Row.CREATE_NULL_AS_BLANK;
	     
	    String [][] ArrPageData=new String[rowCount][columnCount];
		for(int i=0; i<=rowCount-1; i++){
			Row rowObj=sheetobj.getRow(i);
			if(rowObj!=null){
				 int cellcount=rowObj.getLastCellNum();
				   for(int j=0; j<cellcount; j++){
							   Cell cellObj=rowObj.getCell(j, mcp);
							   String cellValue=null;
							  // int cellType=cellObj.getCellType();
							   CellType cellType=cellObj.getCellTypeEnum();
							   // if(cellType==Cell.CELL_TYPE_STRING){
							   if(cellType==CellType.STRING){
							    cellValue=cellObj.getStringCellValue();
								   System.out.println(cellValue);
							   //}else if(cellType==Cell.CELL_TYPE_NUMERIC){
							   }else if(cellType==CellType.NUMERIC){
								    cellValue=cellObj.toString();
								   System.out.println(cellValue);
							   }
							  ArrPageData[i][j]=cellValue;
				   }
			}
		}
	}
	public static void xl_GetTestData1(String rownum) throws IOException{
		 try {
			   int rowNum = Integer.parseInt(rownum); 
			   // You can use this method to convert String to int, But if input 
			   //is not an int  value then this will throws NumberFormatException. 
			     
				System.out.println("Go to Test Data folder");
			    Workbook wbookObj=xl_GetWorkbook(TestDataPath1);//("TestData/login/Login.xlsx");
				System.out.println(" Go to Test Data sheet ");
			    Sheet sheetobj=wbookObj.getSheet(TDSheetName1);//("Login_Page");
				 
			    //	int columnCount =sheetobj.getRow(0).getLastCellNum();
			    MissingCellPolicy mcp=Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;	     
			   // String [] ArrRowData=new String[columnCount];
				System.out.println("Identify the test data row");
			    Row rowObj=sheetobj.getRow(rowNum);
				System.out.println("Get the data from row");
			    HashMap<String, Object> hmObject= new HashMap<String, Object>();
				 System.out.println("Data taken in Hashmap");
			    if(rowObj!=null){
					 int columnCount=rowObj.getLastCellNum();
					 System.out.println("celcount"+columnCount);
					 for(int j=0; j<columnCount; j++){
							   Cell cellObj=rowObj.getCell(j, mcp);
							   Object cellValue=null;
							  // int cellType=cellObj.getCellType();
							     CellType cellType=cellObj.getCellTypeEnum();
							   //if(cellType==Cell.CELL_TYPE_STRING){
							     if(cellType==CellType.STRING){
								    cellValue=cellObj.getStringCellValue();
								    
								   System.out.println(cellValue);
								   System.out.println("String"+j);
								   
							  // }else if(cellType==Cell.CELL_TYPE_NUMERIC){

								   }else if(cellType==CellType.NUMERIC){
									//cellValue=cellObj.toString();
								   cellValue=cellObj.getNumericCellValue();
								    
								   System.out.println(cellValue);
								   System.out.println("Numeric"+j);
							   }
							   String ColumnName=sheetobj.getRow(0).getCell(j, mcp).getStringCellValue();
							   hmObject.put(ColumnName, cellValue);
						   }
		            testData_HM=hmObject;
				}
		}
			catch(NumberFormatException e) {
				   System.out.println("input is not an int value"); 
				   // Here catch NumberFormatException
				   // So input is not a int.
				 } 

	}
	public static void xl_GetTestData1(String TestDataPath1, String TDSheetName1,  String rownum) throws IOException{
		 try {
			 	int rowNum = Integer.parseInt(rownum); 
			   // You can use this method to convert String to int, But if input 
			   //is not an int  value then this will throws NumberFormatException. 
			    System.out.println("Valid input");
			    System.out.println(" Nav to Test datafolder");
			    Workbook wbookObj=xl_GetWorkbook(TestDataPath1);//("TestData/login/Login.xlsx");
			    System.out.println(" Nav to Test datasheet");
			    Sheet sheetobj=wbookObj.getSheet(TDSheetName1);//("Login_Page");
				
			    //	int columnCount =sheetobj.getRow(0).getLastCellNum();
			    MissingCellPolicy mcp=Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;	     
			   // String [] ArrRowData=new String[columnCount];
			 
			    Row rowObj=sheetobj.getRow(rowNum);
				System.out.println("Nav to Test data row");
			    HashMap<String, Object> hmObject= new HashMap<String, Object>();
				System.out.println("Get data");
			    if(rowObj!=null){
					 int columnCount=rowObj.getLastCellNum();
					 System.out.println("celcount"+columnCount);
					 for(int j=0; j<columnCount; j++){
							   Cell cellObj=rowObj.getCell(j, mcp);
							   Object cellValue=null;
							  // int cellType=cellObj.getCellType();
							     CellType cellType=cellObj.getCellTypeEnum();
							   //if(cellType==Cell.CELL_TYPE_STRING){
							     if(cellType==CellType.STRING){
								    cellValue=cellObj.getStringCellValue();
								    
								   System.out.println(cellValue);
								   System.out.println("String"+j);
								   
							  // }else if(cellType==Cell.CELL_TYPE_NUMERIC){

								   }else if(cellType==CellType.NUMERIC){
									//cellValue=cellObj.toString();
								   cellValue=cellObj.getNumericCellValue();
								    
								   System.out.println(cellValue);
								   System.out.println("Numeric"+j);
							   }
							   String ColumnName=sheetobj.getRow(0).getCell(j, mcp).getStringCellValue();
							   hmObject.put(ColumnName, cellValue);
						   }
		            testData_HM=hmObject;
				}
		}
			catch(NumberFormatException e) {
				   System.out.println("input is not an int value"); 
				   // Here catch NumberFormatException
				   // So input is not a int.
				 } 

	}
	
	public static Workbook xl_GetWorkbook(String filePath) throws IOException{
		 InputStream fis= new FileInputStream(filePath);
		String[] patharr=filePath.split("\\.");
		String ext=patharr[1];
		 Workbook wbookObj=null;
		if(ext.equalsIgnoreCase("xls")){
			   wbookObj= new HSSFWorkbook(fis);
		}else if(ext.equalsIgnoreCase("xlsx")){
			wbookObj=new XSSFWorkbook(fis);
		}
		return wbookObj;
		
	}
	
	public static Workbook xl_CreateWorkbook(String filePath) throws IOException{
		// OutputStream fos= new FileOutputStream(filePath);
		String[] patharr=filePath.split("\\.");
		String ext=patharr[1];
		 Workbook wbookObj=null;
		if(ext.equalsIgnoreCase("xls")){
			   wbookObj= new HSSFWorkbook();
		}else if(ext.equalsIgnoreCase("xlsx")){
			wbookObj=new XSSFWorkbook();
		}
		return wbookObj;
		
	}

	
	
}
