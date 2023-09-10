package group.assignment.a1.Page;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Calendar;

import group.assignment.a1.MyDatabaseHelper;
import group.assignment.a1.R;

public class AddCountdown extends AppCompatActivity {

    private String selected_date;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @SuppressLint("NewApi")
    LocalDate currentDate = LocalDate.now(), targetDate = LocalDate.now();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcountdown);

        Button buttonCreate = findViewById(R.id.button_create);
        EditText Etitle = findViewById(R.id.NotificationTitle);
        TextView tv_currentDate = findViewById(R.id.tv_currentDate);
        Switch Eswitch1 = findViewById(R.id.switch1);
        CalendarView cv = findViewById(R.id.calendarView);
        tv_currentDate.setText(String.valueOf(currentDate));



        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @SuppressLint("NewApi")
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                targetDate = LocalDate.of(year,month + 1,day);
                selected_date = year + "-" + (month + 1) + "-" +(day);
                tv_currentDate.setText(selected_date);
            }
        });


        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                String title = Etitle.getText().toString();
                boolean notice = Eswitch1.isChecked();
                if(targetDate == null){
                    targetDate = LocalDate.now();
                }
                @SuppressLint({"NewApi", "LocalSuppress"}) long differenceInDays = ChronoUnit.DAYS.between(currentDate,targetDate);

                if(differenceInDays > 0) {
                    MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(getApplicationContext(), "Target.db", null, MyDatabaseHelper.dbVersion);
                    SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                    contentValues.put("title", title);
                    contentValues.put("description", "");
                    contentValues.put("date", String.valueOf(targetDate));
                    contentValues.put("notification", notice);
                    contentValues.put("day", differenceInDays);
                    sqLiteDatabase.insert("CountDown", null, contentValues);
                    sqLiteDatabase.close();
                }
                finish();
            }
        });

    }



}