package group.assignment.a1.Page;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import group.assignment.a1.R;

public class Profile extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    GoogleSignInAccount gAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        gAuth = GoogleSignIn.getLastSignedInAccount(this);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
        TextView tv_username = findViewById(R.id.tv_username);
        ImageView iv_profile = findViewById(R.id.iv_profile);
        Button btn_signIn = findViewById(R.id.bt_click);
        Button btCheckIn = findViewById(R.id.bt_check_in);
        Button btProgress = findViewById(R.id.bt_progress);
        Button btCalendar = findViewById(R.id.bt_calendar);
        Button btProfile = findViewById(R.id.bt_profile);
        Button btn_logout = findViewById(R.id.btn_logout);

        if(user != null){
            tv_username.setText(user.getEmail());
            tv_username.setVisibility(View.VISIBLE);
            btn_logout.setVisibility(View.VISIBLE);
            btn_signIn.setVisibility(View.INVISIBLE);
        }
        if(gAuth != null){
            tv_username.setText(gAuth.getDisplayName());
            tv_username.setVisibility(View.VISIBLE);
            iv_profile.setImageURI(gAuth.getPhotoUrl());
            btn_logout.setVisibility(View.VISIBLE);
            btn_signIn.setVisibility(View.INVISIBLE);
        }


        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user != null) {
                    auth.getInstance().signOut();
                    Toast.makeText(getApplicationContext(),"Sign Out Successful",Toast.LENGTH_SHORT).show();
                }
                if(gAuth != null){
                    gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(),"Sign Out Successful",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                btn_logout.setVisibility(View.INVISIBLE);
                btn_signIn.setVisibility(View.VISIBLE);
                tv_username.setText("Unknown");
            }
        });

        btCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个 Intent 来启动目标 Activity
                Intent intent = new Intent(getApplicationContext(), Checkin.class);
                startActivity(intent);
                finish();
            }
        });

        btProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Countdown.class);
                startActivity(intent);
                finish();
            }
        });
    }
}