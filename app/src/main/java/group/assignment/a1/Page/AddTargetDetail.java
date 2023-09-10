package group.assignment.a1.Page;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

import group.assignment.a1.MyDatabaseHelper;
import group.assignment.a1.R;

public class AddTargetDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_target_detail);

        MyDatabaseHelper myDb = new MyDatabaseHelper(this,"Target.db", null, MyDatabaseHelper.dbVersion);
        SQLiteDatabase db = myDb.getWritableDatabase();

        Intent intent = getIntent();
        int icon = intent.getIntExtra("iconRes", 0);
        String name = intent.getStringExtra("name");
        ImageView imv = findViewById(R.id.targetIcon);
        imv.setImageResource(icon);
        EditText nameTarget = findViewById(R.id.targetName);
        EditText et_addTargetDescription = findViewById(R.id.et_addTargetDescription);
        Button buttonCreateTarget = findViewById(R.id.buttonAddTarget);

        buttonCreateTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();

                if(TextUtils.isEmpty(nameTarget.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please enter the title",Toast.LENGTH_SHORT).show();
                    return;
                }
                cv.put("icon", icon);
                cv.put("name", nameTarget.getText().toString());
                cv.put("description",et_addTargetDescription.getText().toString());
                cv.put("day", 0);
                cv.put("status", 0);
                cv.put("checkindate","10-12-2020");
                db.insert("target", null, cv);
                db.close();
                myDb.close();
                finish();
            }
        });
    }
}