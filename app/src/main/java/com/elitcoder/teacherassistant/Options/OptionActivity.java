package com.elitcoder.teacherassistant.Options;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.elitcoder.teacherassistant.Attendance.AttendanceActivity;
import com.elitcoder.teacherassistant.Cgpa.CgpaCalcActivity;
import com.elitcoder.teacherassistant.CourseMaterial.CourseMaterialActivity;
import com.elitcoder.teacherassistant.ImageToText.ImageToTextActivity;
import com.elitcoder.teacherassistant.databinding.ActivityOptionBinding;

public class OptionActivity extends AppCompatActivity {
    ActivityOptionBinding optionBinding;
//TODO: Devloper Hridoy will connect all activities using optionActivity...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        optionBinding = ActivityOptionBinding.inflate(getLayoutInflater());
        setContentView(optionBinding.getRoot());

        //Connecting Attendance Activity:
        connectAttendance();
        //Connecting Image to Text Activity:
        connectImageToText();
        //Connecting Cgpa calc:
        connectCgpaCalc();
        //Connecting Course Materials:
        connectCourseMaterials();

    }

    //Course Material Connection :
    private void connectCourseMaterials() {
        optionBinding.btnCourseMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent courseMaterialIntent = new Intent(OptionActivity.this, CourseMaterialActivity.class);
                startActivity(courseMaterialIntent);
            }
        });
    }

    //CGPA calc Connection :
    private void connectCgpaCalc() {
        optionBinding.btnCGPACalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cgpaCalcIntent = new Intent(OptionActivity.this, CgpaCalcActivity.class);
                startActivity(cgpaCalcIntent);
            }
        });
    }

    //ImgToTxt Connection:
    private void connectImageToText() {
        optionBinding.btnImgToTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imgToTxtIntent = new Intent(OptionActivity.this, ImageToTextActivity.class);
                startActivity(imgToTxtIntent);
            }
        });
    }

    //Attendance Connection:
    private void connectAttendance() {
        optionBinding.btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent attendanceIntent = new Intent(OptionActivity.this, AttendanceActivity.class);
                startActivity(attendanceIntent);
            }
        });
    }
}