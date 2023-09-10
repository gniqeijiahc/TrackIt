package group.assignment.a1.Page;

import static java.time.LocalDate.parse;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.DataFormatException;

import group.assignment.a1.Adapter.CheckInIconAdapter;
import group.assignment.a1.MyDatabaseHelper;
import group.assignment.a1.Object.TargetItem;
import group.assignment.a1.R;

public class Checkin extends AppCompatActivity {
    ArrayList<TargetItem> targetItemArrayList = new ArrayList<>();
    GridView gridView;

    @Override
    protected void onResume() {
        super.onResume();
        refreshStatus();
        retrieveDate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);

        Button btCheckIn = findViewById(R.id.bt_check_in);
        Button btProgress = findViewById(R.id.bt_progress);
        Button btCalendar = findViewById(R.id.bt_calendar);
        Button btProfile = findViewById(R.id.bt_profile);
        gridView = findViewById(R.id.gridView);



        btProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Checkin.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Checkin.this, Countdown.class);
                startActivity(intent);
                finish();
            }
        });

        btProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Checkin.this, Profile.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void retrieveDate(){
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "Target.db", null, MyDatabaseHelper.dbVersion);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query("target", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int icon = cursor.getInt(cursor.getColumnIndex("icon"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") int day = cursor.getInt(cursor.getColumnIndex("day"));
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") int status = cursor.getInt(cursor.getColumnIndex("status"));
                if(status < 100)
                    targetItemArrayList.add(new TargetItem(name, icon, day, id, status));
            } while (cursor.moveToNext());
        }

        CheckInIconAdapter checkInIconAdapter = new CheckInIconAdapter(this, targetItemArrayList);
        gridView.setAdapter(checkInIconAdapter);

        cursor.close();
    }


    @SuppressLint("NewApi")
    void refreshStatus(){
        @SuppressLint({"NewApi", "LocalSuppress"}) LocalDate currentDate = LocalDate.now();
        LocalDate targetDate = null;
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "Target.db", null, MyDatabaseHelper.dbVersion);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("target",null,null,null,null,null,null);
        if(cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                try{
                @SuppressLint("Range") String checkindate = cursor.getString(cursor.getColumnIndex("checkindate"));
                targetDate = LocalDate.parse(checkindate);
                long differenceInDays = ChronoUnit.DAYS.between(targetDate,currentDate);
                if(differenceInDays > 0) {
                    ContentValues cv = new ContentValues();
                    cv.put("status", 0);
                    db.update("target", cv, "id = ?", new String[]{String.valueOf(id)});
                }
                }catch(Exception e){
                }
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();;
    }
}