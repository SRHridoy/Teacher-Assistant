package com.elitcoder.teacherassistant.HstuCSEDept;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebViewClient;

import com.elitcoder.teacherassistant.R;
import com.elitcoder.teacherassistant.databinding.ActivityCseDeptBinding;

public class CSEDeptActivity extends AppCompatActivity {
    ActivityCseDeptBinding cseDeptBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cseDeptBinding = ActivityCseDeptBinding.inflate(getLayoutInflater());
        View view = cseDeptBinding.getRoot();
        setContentView(view);

        cseDeptBinding.webCse.getSettings().setJavaScriptEnabled(true);
        cseDeptBinding.webCse.setWebViewClient(new WebViewClient());
        cseDeptBinding.webCse.loadUrl("https://hstu.ac.bd/cse/dept_cse");
    }
}