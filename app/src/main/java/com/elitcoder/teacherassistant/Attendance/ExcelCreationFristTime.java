package com.elitcoder.teacherassistant.Attendance;

import android.annotation.SuppressLint;
import android.content.Context;

import com.elitcoder.teacherassistant.Attendance.adapter.StudentAdapter;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelCreationFristTime {
    public static void writeToExcel(Context context){
        //Creating Workbook and sheet in Excel :
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        //Creating Header :
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Student ID");
        headerRow.createCell(1).setCellValue("Student Name");
        headerRow.createCell(2).setCellValue(getCurrentDate());
        //Generating values :
        for (int i = 0; i < StudentInfoLists.studentInfoLists.size();i++){
            Row dataRow = sheet.createRow(i+1);
            dataRow.createCell(0).setCellValue(StudentInfoLists.studentInfoLists.get(i).getStdId());
            dataRow.createCell(1).setCellValue(StudentInfoLists.studentInfoLists.get(i).getStdName());
            dataRow.createCell(2).setCellValue(StudentAdapter.isPresentLists[i]?"Present":"Absent");
        }

        //Saveing data to file :
        ExcelFileGenaration.generateExcelFile(context,workbook);
    }
    public static String getCurrentDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE,dd-MM-yyyy");
        String currentDate = simpleDateFormat.format(new Date());
        return currentDate;
    }
}
