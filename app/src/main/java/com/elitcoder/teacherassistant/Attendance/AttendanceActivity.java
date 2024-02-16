package com.elitcoder.teacherassistant.Attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.Manifest;
import android.widget.Button;
import android.widget.Toast;

import com.elitcoder.teacherassistant.Attendance.adapter.StudentAdapter;
import com.elitcoder.teacherassistant.R;
import com.elitcoder.teacherassistant.databinding.ActivityAttendanceBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AttendanceActivity extends AppCompatActivity {
//TODO: Developer Hridoy will finalize this section both UI and Backend...
    ActivityAttendanceBinding attendanceBinding;
    Boolean check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attendanceBinding = ActivityAttendanceBinding.inflate(getLayoutInflater());
        View view = attendanceBinding.getRoot();
        setContentView(view);

        //Requesting Permission for Excel :
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        //RecyclerFunctionality call:
        recyclerFunctionality();

        //Setting up finish button :
        finishAttendance();

        //SharedPreference to handle excel :
        SharedPreferences sharedPreferences = getSharedPreferences("ExcelHandling", MODE_PRIVATE);
        check = sharedPreferences.getBoolean("flag",true);
    }

    //Finish button :
    private void finishAttendance() {
        attendanceBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(AttendanceActivity.this);
                dialog.setContentView(R.layout.confirm_attendance_layout);

                Button btnYes = dialog.findViewById(R.id.btnYes);
                Button btnNo = dialog.findViewById(R.id.btnNo);

                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(check){
                            if(ActivityCompat.checkSelfPermission(AttendanceActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                                ExcelCreationFristTime.writeToExcel(AttendanceActivity.this);
                            }else{
                                ActivityCompat.requestPermissions(AttendanceActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PackageManager.PERMISSION_GRANTED);
                            }
                            dialog.dismiss();
                            //Marking as false :
                            SharedPreferences sharedPreferences = getSharedPreferences("ExcelHandling",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("flag",false);
                            editor.apply();
                        }
                        else{
                            ExcelCreationLater.readExcelAndUpdate(AttendanceActivity.this);
                            Toast.makeText(AttendanceActivity.this, "Failed to save!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                //must:
                dialog.show();
            }
        });
    }


    //Setting up RecyclerFunctionality:
    private void recyclerFunctionality() {
        attendanceBinding.recyclerAttendance.setLayoutManager(new LinearLayoutManager(this));

        //Adding info by calling :
        StudentInfoLists.studentInfo();

        StudentAdapter studentAdapter = new StudentAdapter(this,StudentInfoLists.studentInfoLists);
        attendanceBinding.recyclerAttendance.setAdapter(studentAdapter);
    }
}