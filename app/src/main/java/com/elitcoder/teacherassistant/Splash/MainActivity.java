package com.elitcoder.teacherassistant.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.elitcoder.teacherassistant.Login.LoginActivity;
import com.elitcoder.teacherassistant.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        File excelFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"Attendance of CSE-21.xls");
//        if (!excelFile.exists()) {
//            //Creating Workbook and sheet in Excel :
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            HSSFSheet sheet = workbook.createSheet("CSE_21");
//            //Creating Header :
//            Row headerRow = sheet.createRow(0);
//            headerRow.createCell(0).setCellValue("Student ID");
//            headerRow.createCell(1).setCellValue("Student Name");
//            //Generating values :
//            for (int i = 0; i < StudentInfoLists.studentInfoLists.size(); i++){
//                Row dataRow = sheet.createRow(i+1);
//                dataRow.createCell(0).setCellValue(StudentInfoLists.studentInfoLists.get(i).getStdId());
//                dataRow.createCell(1).setCellValue(StudentInfoLists.studentInfoLists.get(i).getStdName());
//            }
//            // If it doesn't exist, create it
//            ExcelFileGenaration.generateExcelFile(this,workbook);
//        }

        //For Splash Screen
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent  myIntent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(myIntent);
            }
        },1500);
    }
}