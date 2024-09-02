package com.elitcoder.teacherassistant.Attendance;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.elitcoder.teacherassistant.Attendance.adapter.StudentAdapter;
import com.elitcoder.teacherassistant.Options.OptionActivity;
import com.elitcoder.teacherassistant.R;
import com.elitcoder.teacherassistant.databinding.ActivityAttendanceBinding;
// Your existing imports...

public class AttendanceActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_STORAGE_PERMISSIONS = 100;
    private static final int REQUEST_CODE_MANAGE_STORAGE = 101;

    ActivityAttendanceBinding attendanceBinding;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attendanceBinding = ActivityAttendanceBinding.inflate(getLayoutInflater());
        View view = attendanceBinding.getRoot();
        setContentView(view);

        // Requesting permission for Excel operations
        checkAndRequestPermissions();

        // Recycler Functionality call
        recyclerFunctionality();

        // Setting up finish button
        finishAttendance();
    }

    // Method to check and request permissions
    private void checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                // Direct the user to the settings page to enable MANAGE_EXTERNAL_STORAGE
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_CODE_MANAGE_STORAGE);
            }
        } else {
            // For Android 6.0 to Android 10
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_STORAGE_PERMISSIONS);
            }
        }
    }

    // Handle the result of the permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_STORAGE_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage permissions granted!", Toast.LENGTH_SHORT).show();
                // Proceed with your Excel file operations
            } else {
                Toast.makeText(this, "Storage permissions denied. Cannot proceed!", Toast.LENGTH_LONG).show();
            }
        }
    }

    // Handle the result when returning from the settings page
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_MANAGE_STORAGE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    Toast.makeText(this, "Manage External Storage permission granted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Manage External Storage permission denied. Cannot proceed!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    // Finish button
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
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            UpdateExcel.updatingExcel(AttendanceActivity.this);
                        }
                        dialog.dismiss();
                        // Go to optionActivity after taking attendance...
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
                // Show the dialog
                dialog.show();
            }
        });
    }

    // Setting up RecyclerFunctionality
    private void recyclerFunctionality() {
        attendanceBinding.recyclerAttendance.setLayoutManager(new LinearLayoutManager(this));

        // Adding info by calling
        StudentInfoLists.studentInfo();

        StudentAdapter studentAdapter = new StudentAdapter(this, StudentInfoLists.studentInfoLists);
        attendanceBinding.recyclerAttendance.setAdapter(studentAdapter);
    }
}
