package com.elitcoder.teacherassistant.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.elitcoder.teacherassistant.Options.OptionActivity;
import com.elitcoder.teacherassistant.R;

import java.util.concurrent.Executor;

public class LoginActivity extends AppCompatActivity {
//TODO:Developer Tomal, you have to finish this . You will work on both login ui and backend logic...
    BiometricPrompt biometricPrompt;
    androidx.biometric.BiometricPrompt.PromptInfo promptInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //TODO: Devloper Hridoy Just trying to connect all the acitivities for Demo...
        //TODO:Making Fingerprint enabled login...
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BiometricManager biometricManager = BiometricManager.from(LoginActivity.this);
                switch (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG| BiometricManager.Authenticators.DEVICE_CREDENTIAL)){
                    case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                        Toast.makeText(LoginActivity.this, "No fingerprint sensor found!", Toast.LENGTH_SHORT).show();
                        break;

                    case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                        Toast.makeText(LoginActivity.this, "Doesn't work fingerprint sensor!", Toast.LENGTH_SHORT).show();
                        break;
                    case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                        Toast.makeText(LoginActivity.this, "No fingerprint assigned!", Toast.LENGTH_SHORT).show();
                        break;
                }

                Executor executor = ContextCompat.getMainExecutor(LoginActivity.this);

                biometricPrompt = new BiometricPrompt(LoginActivity.this, executor, new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                    }

                    @Override
                    public void onAuthenticationSucceeded(@NonNull androidx.biometric.BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        //Success Custom layout inflate :
                        LayoutInflater inflater = getLayoutInflater();
                        View successLayout = inflater.inflate(R.layout.success_toast,findViewById(R.id.successToastContainer));

                        //Create toast with this layout :
                        Toast toast = new Toast(LoginActivity.this);
                        TextView toastText = successLayout.findViewById(R.id.toastContent);
                        toastText.setText("Login Successful!");
                        toast.setView(successLayout);
                        //toast.setText("Login Successful!");
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                        //Go to Option Activity:
                        Intent iHome = new Intent(LoginActivity.this,OptionActivity.class);
                        startActivity(iHome);
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                    }
                });

                promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Secure login")
                        .setDescription("Use fingerprint to login!")
                        .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG|BiometricManager.Authenticators.DEVICE_CREDENTIAL)
                        .build();

                biometricPrompt.authenticate(promptInfo);
            }
        });
    }
}