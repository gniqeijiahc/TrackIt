package group.assignment.a1.Page;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import group.assignment.a1.Adapter.CountDownAdapter;
import group.assignment.a1.Object.TargetDate;
import group.assignment.a1.MyDatabaseHelper;
import group.assignment.a1.R;
import group.assignment.a1.SwipeHelper;

public class Countdown extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        updateRemainingDays();
        retrieveData();
    }


    private TextView tv_firstCountDownTitle, tv_firstCountDownDay;
    private RecyclerView rc_calendar;
    MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "Target.db", null, MyDatabaseHelper.dbVersion);
    ArrayList<TargetDate> countdown = new ArrayList<TargetDate>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        rc_calendar = findViewById(R.id.rc_calendar);
        tv_firstCountDownTitle = findViewById(R.id.tv_title);
        tv_firstCountDownDay = findViewById(R.id.tv_firstCountDown);
        Button btCheckIn = findViewById(R.id.bt_check_in);
        Button btProgress = findViewById(R.id.bt_progress);
        Button btCalendar = findViewById(R.id.bt_calendar);
        Button btProfile = findViewById(R.id.bt_profile);
        ImageButton btAdd = findViewById(R.id.bt_add);

        btCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个 Intent 来启动目标 Activity
                Intent intent = new Intent(Countdown.this, Checkin.class);
                startActivity(intent);
                finish();
            }
        });

        btProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Countdown.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
                finish();
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Countdown.this, AddCountdown.class);
                startActivity(intent);
            }
        });

        SwipeHelper swipeHelper = new SwipeHelper(this,rc_calendar) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new UnderlayButton(
                        "Delete",
                        0,
                        Color.parseColor("#FF3C30"),
                        new UnderlayButton.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                int id = countdown.get(pos).getTargetId();
                                db.delete("CountDown", "id = " + id, new String[]{});
                                db.close();
                                retrieveData();
                                if(countdown.size() == 0){
                                    tv_firstCountDownTitle.setText("Title");
                                    tv_firstCountDownDay.setText("days left");
                                }
                            }
                        }
                ));
            }
        };
    }

    public void retrieveData() {
        countdown.clear();
        int i = 1;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Countdown", null, null, null, null, null, "day ASC ");
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
                @SuppressLint("Range") int day = cursor.getInt(cursor.getColumnIndex("day"));
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                if (i == 1) {
                    tv_firstCountDownTitle.setText(title);
                    tv_firstCountDownDay.setText(String.valueOf(day));
                    i++;
                }
                countdown.add(new TargetDate(title, date, day, id));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        rc_calendar.setLayoutManager(new LinearLayoutManager(this));
        rc_calendar.setAdapter(new CountDownAdapter(getApplicationContext(), countdown));
    }

    @SuppressLint("NewApi")
    private void updateRemainingDays() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        @SuppressLint({"NewApi", "LocalSuppress"}) LocalDate currentDate = LocalDate.now();
        LocalDate targetDate = null;

        //Sqlite get the data from database and renew the data
        Cursor cursor = db.query("Countdown", null, null, null, null, null, "day ASC ");
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
                targetDate = LocalDate.parse(date); // 从您的选择日期中获取目标日期
                long differenceInDays = ChronoUnit.DAYS.between(currentDate,targetDate);
                if(differenceInDays == 0){
                    db.delete("CountDown","id = ?", new String[]{String.valueOf(id)});
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }
}



