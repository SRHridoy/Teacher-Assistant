package com.elitcoder.teacherassistant.Attendance;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.elitcoder.teacherassistant.Attendance.adapter.StudentAdapter;
import com.elitcoder.teacherassistant.Options.OptionActivity;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class UpdateExcel {
    private static final String TAG = "UpdateExcel";
    //checking and update:
    @RequiresApi(api = Build.VERSION_CODES.R)
    public static void updatingExcel(Context context){
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Attendance of CSE-21.xlsx");

        //Update:
        if (file.exists()) {
            Log.d(TAG, "File Path " + file.getAbsolutePath());

            try {
                FileInputStream fis = new FileInputStream(file);
                Workbook workbook = new HSSFWorkbook(fis);
                Sheet sheet = workbook.getSheetAt(0); // Access the first sheet

                // Determine the last column index
                Row headerRow = sheet.getRow(0);
                int lastColumn = headerRow.getLastCellNum();


                //Checking if Already attendance is taken:
                // Retrieve the header value of the last column
                Cell lastHeaderCell = headerRow.getCell(lastColumn-1);

                //Check:
                Log.d("Date:",ExcelCreation.getCurrentDate());
                Log.d("Date from Excel:",lastHeaderCell.toString());

//                if(ExcelCreation.getCurrentDate().equals(lastHeaderCell.toString())){
//                    Toast.makeText(context, "Attendance is already taken!", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(context, OptionActivity.class);
//                    context.startActivity(intent);
//                }

//                else {
                    // Add new column header
                    Cell newHeaderCell = headerRow.createCell(lastColumn);
                    newHeaderCell.setCellValue(ExcelCreation.getCurrentDate());

                    // Populate the new column with data
                    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                        Row row = sheet.getRow(i);
                        if (row == null) {
                            row = sheet.createRow(i);
                        }
                        Cell newCell = row.createCell(lastColumn);
                        newCell.setCellValue(StudentAdapter.isPresentLists[i]?"Present":"Absent");
                    }

                    fis.close();

                    // Write the updated workbook back to the file
                    FileOutputStream fos = new FileOutputStream(file);
                    workbook.write(fos);
                    fos.close();

                    workbook.close();
                    Toast.makeText(context, "Attendance has been taken successfully.", Toast.LENGTH_LONG).show();

                //}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //Create and Generate Excel:
            ExcelCreation.writeToExcel(context);
        }
    }
}
