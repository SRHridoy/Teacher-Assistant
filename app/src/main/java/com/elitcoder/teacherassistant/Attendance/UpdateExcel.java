package com.elitcoder.teacherassistant.Attendance;

import static com.elitcoder.teacherassistant.Attendance.adapter.StudentAdapter.isPresentLists;

import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class UpdateExcel {
    private static final String TAG = "UpdateExcel";

    @RequiresApi(api = Build.VERSION_CODES.R)
    public static void updatingExcel(AttendanceActivity context) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Attendance of CSE-21.xlsx");

        if (file.exists()) {
            Log.d(TAG, "File Path " + file.getAbsolutePath());

            try {
                FileInputStream fis = new FileInputStream(file);
                Workbook workbook = new HSSFWorkbook(fis);
                Sheet sheet = workbook.getSheetAt(0); // Access the first sheet

                // Determine the last column index
                Row headerRow = sheet.getRow(0);
                int lastColumn = headerRow.getLastCellNum();

                // Checking if attendance is already taken (Optional, if you want to avoid duplicates)
                Cell lastHeaderCell = headerRow.getCell(lastColumn - 1);
                Log.d("Date:", ExcelCreation.getCurrentDate());
                Log.d("Date from Excel:", lastHeaderCell.toString());

                // Uncomment this block to avoid taking attendance again on the same day
                /*
                if (ExcelCreation.getCurrentDate().equals(lastHeaderCell.toString())) {
                    Toast.makeText(context, "Attendance is already taken for today!", Toast.LENGTH_SHORT).show();
                    fis.close();
                    workbook.close();
                    return;
                }
                */

                // Add new column header
                Cell newHeaderCell = headerRow.createCell(lastColumn);
                newHeaderCell.setCellValue(ExcelCreation.getCurrentDate());

                // Ensure the array is not smaller than the number of rows
                if (isPresentLists.length < sheet.getLastRowNum()) {
                    throw new ArrayIndexOutOfBoundsException("The isPresentLists array is smaller than the number of rows in the Excel sheet.");
                }

                // Populate the new column with data
                for (int i = 0; i < sheet.getLastRowNum(); i++) { // Start from 0 to include all students
                    Row row = sheet.getRow(i + 1);
                    if (row == null) {
                        row = sheet.createRow(i + 1);
                    }

                    Cell newCell = row.createCell(lastColumn);
                    newCell.setCellValue(isPresentLists[i] ? "    P" : "    A");
                }

                fis.close();

                // Write the updated workbook back to the file
                FileOutputStream fos = new FileOutputStream(file);
                workbook.write(fos);
                fos.close();

                workbook.close();

                // Reset the array for next usage
                Arrays.fill(isPresentLists, false);
                Toast.makeText(context, "Attendance has been taken successfully.", Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
                Log.e(TAG, "ArrayIndexOutOfBoundsException: " + e.getMessage());
                Toast.makeText(context, "Failed to take attendance due to data mismatch.", Toast.LENGTH_LONG).show();
            }
        } else {
            // Create and generate Excel if the file doesn't exist
            ExcelCreation.writeToExcel(context);
        }
    }
}
