package com.elitcoder.teacherassistant.Attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.elitcoder.teacherassistant.Attendance.adapter.StudentAdapter;
import com.elitcoder.teacherassistant.R;
import com.elitcoder.teacherassistant.databinding.ActivityAttendanceBinding;

public class AttendanceActivity extends AppCompatActivity {
//TODO: Developer Hridoy will finalize this section both UI and Backend...
    ActivityAttendanceBinding attendanceBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attendanceBinding = ActivityAttendanceBinding.inflate(getLayoutInflater());
        View view = attendanceBinding.getRoot();
        setContentView(view);

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