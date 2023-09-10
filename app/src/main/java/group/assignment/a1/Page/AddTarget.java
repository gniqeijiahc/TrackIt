package group.assignment.a1.Page;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import group.assignment.a1.MyDatabaseHelper;
import group.assignment.a1.R;


public class AddTarget extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_target);

        MyDatabaseHelper myDb = new MyDatabaseHelper(this, "Target.db", null, MyDatabaseHelper.dbVersion);
        SQLiteDatabase db = myDb.getWritableDatabase();
        Cursor cursor = db.query("target", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int icon = cursor.getInt(cursor.getColumnIndex("icon"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") int day = cursor.getInt(cursor.getColumnIndex("day"));
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            } while (cursor.moveToNext());
        }
        cursor.close();


        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button10 = findViewById(R.id.button10);
        Button button11 = findViewById(R.id.button11);
        Button button12 = findViewById(R.id.button12);
        Button button13 = findViewById(R.id.button13);
        Button button14 = findViewById(R.id.button14);
        Button button15 = findViewById(R.id.button15);
        Button button16 = findViewById(R.id.button16);
        Button button17 = findViewById(R.id.button17);
        Button button18 = findViewById(R.id.button18);
        Button button19 = findViewById(R.id.button19);
        Button button20 = findViewById(R.id.button20);
        Button button21 = findViewById(R.id.button21);
        Button button22 = findViewById(R.id.button22);
        Button button23 = findViewById(R.id.button23);
        Button button24 = findViewById(R.id.button24);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport1;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport2;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport3;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport4;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport5;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport6;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport7;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport8;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport9;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport10;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport11;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport12;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport13;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport14;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport15;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport16;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport17;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport18;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport19;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport20;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport21;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport22;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport23;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        button24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTarget.this, AddTargetDetail.class);
                int icon = R.drawable.sport24;
                String name = "Exercise";
                intent.putExtra("iconRes", icon);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

    }
}