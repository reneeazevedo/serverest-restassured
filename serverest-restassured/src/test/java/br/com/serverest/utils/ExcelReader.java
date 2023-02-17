package br.com.serverest.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {


    public static JSONObject readExcel(String filePath, String fileName, String sheetName) throws IOException {

        //Create an object of File class to open xlsx file
        File file = new File(filePath + "/" + fileName);

        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        Workbook guru99Workbook = null;

        //Find the file extension by splitting file name in substring  and getting only extension name

        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        //Check condition if the file is xlsx file

        if (fileExtensionName.equals(".xlsx")) {
            //If it is xlsx file then create object of XSSFWorkbook class
            guru99Workbook = new XSSFWorkbook(inputStream);
        }
        //Check condition if the file is xls file
        else if (fileExtensionName.equals(".xls")) {
            //If it is xls file then create object of HSSFWorkbook class
            guru99Workbook = new HSSFWorkbook(inputStream);

        }

        //Read sheet inside the workbook by its name

        Sheet guru99Sheet = guru99Workbook.getSheet(sheetName);
        Row row = guru99Sheet.getRow(1);

        String email = row.getCell(0).getStringCellValue();
        String password = row.getCell(1).getStringCellValue();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        jsonObject.put("password", password);




        return jsonObject;

    }

    public static void main(String[] args) {
        try {
            readExcel("src/test/resources/", "user.xlsx", "Pagina1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
