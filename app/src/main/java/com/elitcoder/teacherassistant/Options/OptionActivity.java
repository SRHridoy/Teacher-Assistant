package com.elitcoder.teacherassistant.Options;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.elitcoder.teacherassistant.Attendance.AttendanceActivity;
import com.elitcoder.teacherassistant.ShortNotes.ShortNotesActivity;
import com.elitcoder.teacherassistant.HstuCSEDept.CSEDeptActivity;
import com.elitcoder.teacherassistant.AboutUs.AboutUSActivity;
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
        //Connecting About Us Activity:
        connectAboutUs();
        //Connecting ShortNotes Activity:
        connectShortNotesActivity();
        //Connecting CSE DEPT WEB:
        connectCSEDeptWebActivity();

    }

    //CSE Dept. Web Connection :
    private void connectCSEDeptWebActivity() {
        optionBinding.btnCSEDeptWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCSEDeptWebActivity = new Intent(OptionActivity.this, CSEDeptActivity.class);
                startActivity(goToCSEDeptWebActivity);
            }
        });
    }

    //Short-notes Connection :
    private void connectShortNotesActivity() {
        optionBinding.btnShortNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToShortNotesActivity = new Intent(OptionActivity.this, ShortNotesActivity.class);
                startActivity(goToShortNotesActivity);
            }
        });
    }

    //About Us Connection:
    private void connectAboutUs() {
        optionBinding.btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAboutUs = new Intent(OptionActivity.this, AboutUSActivity.class);
                startActivity(goToAboutUs);
            }
        });
    }

    //Attendance Connection:
    private void connectAttendance() {
        optionBinding.btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAttendanceActivity = new Intent(OptionActivity.this, AttendanceActivity.class);
                startActivity(goToAttendanceActivity);
            }
        });
    }
}