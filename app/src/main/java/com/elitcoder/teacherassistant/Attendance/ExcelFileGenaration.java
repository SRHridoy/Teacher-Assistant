package com.elitcoder.teacherassistant.Attendance;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class ExcelFileGenaration {
    public static File file;

    public static void generateExcelFile(Context context, HSSFWorkbook workbook){
        try{
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"Attendance of CSE-21.xlsx");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            Log.d("InPath",file.getAbsolutePath());
            Toast.makeText(context,"Attendance saved to "+file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(context, "Successfully Recorded!", Toast.LENGTH_LONG).show();
        }


    }
}
