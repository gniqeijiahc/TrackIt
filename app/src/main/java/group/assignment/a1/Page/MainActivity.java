package group.assignment.a1.Page;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import group.assignment.a1.Adapter.RecycleAdapter;
import group.assignment.a1.MyDatabaseHelper;
import group.assignment.a1.Object.TargetItem;
import group.assignment.a1.R;
import group.assignment.a1.SwipeHelper;

public class MainActivity extends AppCompatActivity {

    ArrayList<TargetItem> items = new ArrayList<>();
    RecyclerView recyclerViewInProgress;
    RecyclerView recyclerViewComplete;
    Animation scaleUp,scaleDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 找到按钮视图
        Button btCheckIn = findViewById(R.id.bt_check_in);
        Button btProgress = findViewById(R.id.bt_progress);
        Button btCalendar = findViewById(R.id.bt_calendar);
        Button btProfile = findViewById(R.id.bt_profile);
        Button btComplete = findViewById(R.id.bt_complete);
        Button btInProgress = findViewById(R.id.bt_inprogress);
        ImageButton btAddTarget = findViewById(R.id.bt_add);
        recyclerViewInProgress = findViewById(R.id.recycle_view_in_progress);
        recyclerViewComplete = findViewById(R.id.recycle_view_complete);
        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        refreshData(0);

        btComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btComplete.setBackgroundColor(Color.parseColor("#4A5441"));
                btInProgress.setBackgroundColor(Color.TRANSPARENT);
                refreshData(1000);
                recyclerViewInProgress.setVisibility(View.INVISIBLE);
                recyclerViewComplete.setVisibility(View.VISIBLE);
            }
        });

        btInProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btInProgress.setBackgroundColor(Color.parseColor("#4A5441"));
                btComplete.setBackgroundColor(Color.TRANSPARENT);
                refreshData(0);
                recyclerViewComplete.setVisibility(View.INVISIBLE);
                recyclerViewInProgress.setVisibility(View.VISIBLE);
            }
        });

//        btCheckIn.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//              public boolean onTouch(View v, MotionEvent event) {
//
//                if(event.getAction()==MotionEvent.ACTION_UP){
//                    btCheckIn.startAnimation(scaleUp);
//                }else if (event.getAction()==MotionEvent.ACTION_DOWN)
//                    btCheckIn.startAnimation(scaleDown);
//
//                return true;
//            }
//        });

        btCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个 Intent 来启动目标 Activity
                Intent intent = new Intent(MainActivity.this, Checkin.class);
                startActivity(intent);
                finish();
            }
        });

        btCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Countdown.class);
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

        btAddTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTarget.class);
                startActivity(intent);
            }
        });

        SwipeHelper swipeHelper = new SwipeHelper(this, recyclerViewInProgress) {
            MyDatabaseHelper dbHelper = new MyDatabaseHelper(MainActivity.this, "Target.db", null, MyDatabaseHelper.dbVersion);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) { underlayButtons.add(new UnderlayButton(
                    "Delete",
                    0,
                    Color.parseColor("#FF3C30"),
                    new UnderlayButton.UnderlayButtonClickListener() {
                        @Override
                        public void onClick(int pos) {
                            int position = viewHolder.getAdapterPosition();
                            Toast.makeText(getApplicationContext(), items.get(position).getName() + " deleted", Toast.LENGTH_SHORT).show();

                            int dltId = 0;
                            try{
                                dltId = items.get(position).getTargetId();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            db.delete("target", "id = " + items.get(position).getTargetId(), null);
                            items.remove(position);
                            recyclerViewInProgress.getAdapter().notifyItemRemoved(position);
                        }
                    }
            ));

                underlayButtons.add(new UnderlayButton(
                        "Complete",
                        0,
                        Color.parseColor("#FF9502"),
                        new UnderlayButton.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                Toast.makeText(getApplicationContext(), items.get(pos).getName() + " completed", Toast.LENGTH_SHORT).show();
                                int dltId = 0;
                                try{
                                    dltId = items.get(pos).getTargetId();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                ContentValues comCV = new ContentValues();
                                comCV.put("status", 1000);
                                db.update("target", comCV, "id = " + items.get(pos).getTargetId(), null);
                                refreshData(0);
                                // TODO: OnComplete
                            }
                        }
                ));
            }
        };
        SwipeHelper swipeHelper_complete = new SwipeHelper(this, recyclerViewComplete) {
            MyDatabaseHelper dbHelper = new MyDatabaseHelper(MainActivity.this, "Target.db", null, MyDatabaseHelper.dbVersion);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) { underlayButtons.add(new UnderlayButton(
                    "Delete",
                    0,
                    Color.parseColor("#FF3C30"),
                    new UnderlayButton.UnderlayButtonClickListener() {
                        @Override
                        public void onClick(int pos) {
                            int position = viewHolder.getAdapterPosition();
                            Toast.makeText(getApplicationContext(), items.get(position).getName() + " deleted", Toast.LENGTH_SHORT).show();

                            int dltId = 0;
                            try{
                                dltId = items.get(position).getTargetId();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            db.delete("target", "id = " + items.get(position).getTargetId(), null);
                            items.remove(position);
                            recyclerViewInProgress.getAdapter().notifyItemRemoved(position);
                        }
                    }
            ));

                underlayButtons.add(new UnderlayButton(
                        "Activate",
                        0,
                        Color.parseColor("#FF9502"),
                        new UnderlayButton.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                Toast.makeText(getApplicationContext(), items.get(pos).getName() + "Activate", Toast.LENGTH_SHORT).show();
                                int dltId = 0;
                                try{
                                    dltId = items.get(pos).getTargetId();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                ContentValues comCV = new ContentValues();
                                comCV.put("status", 0);
                                db.update("target", comCV, "id = " + items.get(pos).getTargetId(), null);
                                refreshData(1000);
                                // TODO: OnComplete
                            }
                        }
                ));
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshData(0);
    }

    private void refreshData(int targetStatus) {
        items.clear();
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "Target.db", null, MyDatabaseHelper.dbVersion);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query("target", null, "status = ? OR status = ?", new String[]{String.valueOf(targetStatus), String.valueOf(targetStatus + 1)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int icon = cursor.getInt(cursor.getColumnIndex("icon"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") int day = cursor.getInt(cursor.getColumnIndex("day"));
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") int status = cursor.getInt(cursor.getColumnIndex("status"));
                items.add(new TargetItem(name, icon, day, id, status));
            } while (cursor.moveToNext());
        }

        if(targetStatus == 0) {
            recyclerViewInProgress.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewInProgress.setAdapter(new RecycleAdapter(getApplicationContext(), items));
        }else {
            recyclerViewComplete.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewComplete.setAdapter(new RecycleAdapter(getApplicationContext(), items));
        }
        cursor.close();
    }

}