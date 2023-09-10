package group.assignment.a1.Page;

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
import java.util.Date;
import java.util.Calendar;

import group.assignment.a1.MyDatabaseHelper;
import group.assignment.a1.R;

public class CountdownCreate extends AppCompatActivity {

    private String selected_date;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcountdown);

        Button buttonCreate = findViewById(R.id.button_create);
        EditText Etitle = findViewById(R.id.NotificationTitle);
        TextView tv_currentDate = findViewById(R.id.tv_currentDate);
        Switch Eswitch1 = findViewById(R.id.switch1);
        CalendarView cv = findViewById(R.id.calendarView);
        Date currentDate = new Date();
        tv_currentDate.setText(sdf.format(currentDate));



        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                selected_date = year + "-" + (month + 1) + "-" +(day + 1);
                String current_date = year + "-" + (month + 1) + "-" +(day);
                tv_currentDate.setText(current_date);
            }
        });

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                String title = Etitle.getText().toString();
                boolean notice = Eswitch1.isChecked();
                Date target_date = null;
                if (selected_date == null) {
                    selected_date = sdf.format(new Date());
                }
                try {
                    target_date = sdf.parse(selected_date);
                } catch (ParseException e) {
                    Toast.makeText(getApplicationContext(),"Error!!!",Toast.LENGTH_SHORT).show();
                }

                Calendar currentCalendar = Calendar.getInstance();
                currentCalendar.setTime(currentDate);
                Calendar targetCalendar = Calendar.getInstance();
                targetCalendar.setTime(target_date);

                long differenceInMillis = targetCalendar.getTimeInMillis() - currentCalendar.getTimeInMillis();
                int differenceInDays = (int) differenceInMillis / (1000 * 60 * 60 * 24);

                if(differenceInDays > 0) {
                    MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(getApplicationContext(), "Target.db", null, MyDatabaseHelper.dbVersion);
                    SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                    contentValues.put("title", title);
                    contentValues.put("description", "");
                    contentValues.put("date", selected_date);
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