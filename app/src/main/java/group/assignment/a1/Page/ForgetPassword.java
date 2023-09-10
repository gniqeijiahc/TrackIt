package group.assignment.a1.Page;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import group.assignment.a1.R;

public class ForgetPassword extends AppCompatActivity {

    EditText et_resetPasswordEmail;
    Button btn_resetPassword, btn_backToLogin;
    ProgressBar pb_forgetPassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        et_resetPasswordEmail = findViewById(R.id.et_resetPasswordEmail);
        btn_resetPassword = findViewById(R.id.btn_resetPassword);
        btn_backToLogin = findViewById(R.id.btn_backToLogin);
        pb_forgetPassword = findViewById(R.id.pb_forgetPassword);

        mAuth = FirebaseAuth.getInstance();

        btn_resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_resetPasswordEmail.getText().toString();
                if(!TextUtils.isEmpty(email)){
                    resetPassword(email);
                }else{
                    Toast.makeText(getApplicationContext(),"Please enter the email",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void resetPassword(String email){
        pb_forgetPassword.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(email)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(),"Check your email",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                        pb_forgetPassword.setVisibility(View.INVISIBLE);
                    }
                });
    }


}

