package csv;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import utils.Log;
import au.com.bytecode.opencsv.CSVReader;

public class ReadFile {
	public static Object[][] getTestDataFromCSVFile(String filePath,String fileName){
		CSVReader csvReader;
		List<String[]> records = new ArrayList<String[]>();
		try {
//			FileReader file = new FileReader(filePath+"\\"+fileName);
//			csvReader = new CSVReader(file);
			FileInputStream fins = new FileInputStream(filePath+"\\"+fileName);
			InputStreamReader gbReader = new InputStreamReader(fins);
			csvReader = new CSVReader(gbReader);
			records = csvReader.readAll();
			csvReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("Can not read csv file");
		}
		//返回从第二行开始的数据
		Object[][] results = new Object[records.size()-1][];
		for(int i=1;i<records.size();i++){
			results[i-1] = records.get(i);
		}
		return results;
	}
}
