package org.tedu.mavenPro2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utils.Log;



public class ReadFile {
    
    /**
     * @param filePath
     * @param fileName
     * @param sheetName
     * @return
     * @throws Exception 
     */
    public static Object[][] getTestDataFromExcel(
    		 String filePath,//文件路径
    		 String fileName,//文件名称
    		 String sheetName//表单名称
    		 ){
    try{
    	 //读取文件流
    	File file = new File(filePath+"\\"+fileName);
    	FileInputStream inputstream = new FileInputStream(file);
    	//获取文件后缀名
    	String fileExtName = fileName.substring(fileName.indexOf("."));
    	//判断后缀，生成Workbook对象
    	Workbook workbook = null;
    	if(fileExtName.equals(".xlsx")){
    		workbook = new XSSFWorkbook(inputstream);
    	}else if(fileExtName.equals(".xls")){
    		workbook = new HSSFWorkbook(inputstream);
    	}
    	
    	//获得表单
    	Sheet sheet = workbook.getSheet(sheetName);
    	//获得行数（不包含第一行）和列数
    	int rowCount = sheet.getLastRowNum()
    			-sheet.getFirstRowNum();
    	int colCount = sheet.getRow(0).getLastCellNum();
    	//遍历所有单元格
    	List<Object[]> records = new ArrayList<Object[]>();
    	for(int i=1;i<rowCount+1;i++){
    		Row row = sheet.getRow(i);
    		String fields[] = new String[colCount];
    		for(int j=0;j<colCount;j++){
    			Cell cell = row.getCell(j);
    			//当前行没有编号为j的列存在，就赋值空串
    			if(j>=row.getLastCellNum()||cell==null){
    				fields[j] = "";
    			}else{
    				cell.setCellType(Cell.CELL_TYPE_STRING);
    				fields[j] = cell.getStringCellValue();
    			}
    		}
    		records.add(fields);
    	}
    	//转换数据格式
    	Object[][] results = new Object[records.size()][];
    	for(int i=0;i<records.size();i++){
    		results[i] = records.get(i);
    	}
    	return results;
    }catch(IOException e){
    	e.printStackTrace();
    	Log.error(e.getMessage());
    	return null;
    }
    }
}
