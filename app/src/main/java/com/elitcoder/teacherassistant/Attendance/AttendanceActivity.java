package com.elitcoder.teacherassistant.Attendance;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.Manifest;
import android.widget.Button;
import android.widget.Toast;

import com.elitcoder.teacherassistant.Attendance.adapter.StudentAdapter;
import com.elitcoder.teacherassistant.Options.OptionActivity;
import com.elitcoder.teacherassistant.R;
import com.elitcoder.teacherassistant.databinding.ActivityAttendanceBinding;

public class AttendanceActivity extends AppCompatActivity {
    //TODO: Developer Hridoy will finalize this section both UI and Backend...
    ActivityAttendanceBinding attendanceBinding;
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attendanceBinding = ActivityAttendanceBinding.inflate(getLayoutInflater());
        View view = attendanceBinding.getRoot();
        setContentView(view);

        //Requesting Permission for Excel :
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.MANAGE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        //RecyclerFunctionality call:
        recyclerFunctionality();

        //Setting up finish button :
        finishAttendance();

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
                            //Toast.makeText(AttendanceActivity.this, "ExcelFirst is called!", Toast.LENGTH_LONG).show();
                           // ExcelCreation.writeToExcel(AttendanceActivity.this);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            UpdateExcel.updatingExcel(AttendanceActivity.this);
                        }
                        dialog.dismiss();
                            //Go to optionActivity after taking attendance...
                            Intent opIntent = new Intent(AttendanceActivity.this, OptionActivity.class);
                            startActivity(opIntent);
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

//    public boolean checkStoragePermissions(){
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
//            //Android is 11 (R) or above
//            return Environment.isExternalStorageManager();
//        }else {
//            //Below android 11
//            int write = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//            int read = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
//
//            return read == PackageManager.PERMISSION_GRANTED && write == PackageManager.PERMISSION_GRANTED;
//        }
//    }
}

