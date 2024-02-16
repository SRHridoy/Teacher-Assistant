package com.elitcoder.teacherassistant.Attendance;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;
import android.widget.Toast;

import com.elitcoder.teacherassistant.Attendance.adapter.StudentAdapter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelCreationLater {
    public static void readExcelAndUpdate(Context context){
        File documentsDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"Attendance of CSE-21.xlsx");
        String filepath = documentsDirectory.getAbsolutePath();
        if (filepath == null) {
            Toast.makeText(context, "File path is null", Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            FileInputStream fileInputStream = new FileInputStream(filepath);
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);

            //Getting last header column index :
            Row headerRow = sheet.getRow(sheet.getFirstRowNum());
            int lastCol = headerRow.getLastCellNum();

            //Checking if the current date is already exists in header or not :
            String currentDate = ExcelCreationFristTime.getCurrentDate();
            Cell lastCell = headerRow.getCell(lastCol-1);
            if(lastCell == null){
                lastCell = headerRow.createCell(lastCol-1);
                lastCell.setCellValue(currentDate);
            }else{
                String lastDate = lastCell.getStringCellValue();
                if(!currentDate.equals(lastDate)){
                    headerRow.createCell(lastCol).setCellValue(currentDate);
                }
            }

            //Generating Presence :
            for (int i = 0; i < StudentInfoLists.studentInfoLists.size();i++){
                Row dataRow = sheet.createRow(i+1);
                dataRow.createCell(lastCol).setCellValue(StudentAdapter.isPresentLists[i]?"Present":"Absent");
            }

            // Write changes back to the file
            FileOutputStream fileOut = new FileOutputStream(filepath);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

            Toast.makeText(context, "File updated successfully", Toast.LENGTH_SHORT).show();
            Log.d("Updatepath",filepath);

        }catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(context, "File not found! at "+filepath, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error reading files", Toast.LENGTH_SHORT).show();
        }
    }
}
