package group.assignment.a1.Page;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import group.assignment.a1.Adapter.RecycleAdapter;
import group.assignment.a1.Adapter.TargetDetailAdapter;
import group.assignment.a1.MyDatabaseHelper;
import group.assignment.a1.Object.TargetDetailItem;
import group.assignment.a1.R;
import group.assignment.a1.SwipeHelper;

public class TargetDetail extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        retrieveData();
    }

    int target_id;
    ArrayList<TargetDetailItem> targetDetails = new ArrayList();
    RecyclerView rc_targetDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_detail);

        Button btn_back = findViewById(R.id.backToTarget);
        ImageView iv_targetDetail = findViewById(R.id.iv_targetDetail);
        TextView tv_titleTarget = findViewById(R.id.tv_titleTarget);
        TextView tv_titleDescription = findViewById(R.id.tv_descriptionTarget);
        rc_targetDetail = findViewById(R.id.rc_targetDetail);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Intent intent = getIntent();
        target_id = intent.getIntExtra("id", 1);
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this, "Target.db", null, MyDatabaseHelper.dbVersion);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        try {
            Cursor cursor = sqLiteDatabase.query("target", null, "id = ?", new String[]{String.valueOf(target_id)}, null, null, null);
            if (cursor.moveToFirst()) {
                @SuppressLint("Range") int icon = cursor.getInt(cursor.getColumnIndex("icon"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));

                iv_targetDetail.setImageResource(icon);
                tv_titleTarget.setText(name);
                tv_titleDescription.setText(description);
            } else {
                tv_titleDescription.setText("Nothing");
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Unable get the data",Toast.LENGTH_SHORT).show();
        }
        SwipeHelper swipeHelper = new SwipeHelper(this,rc_targetDetail) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new UnderlayButton(
                        "Delete",
                        0,
                        Color.parseColor("#FF3C30"),
                        new UnderlayButton.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                int position = viewHolder.getAdapterPosition();
                                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();

                                int dltId = 0;
                                try{
                                    dltId = targetDetails.get(position).getId();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                sqLiteDatabase.delete("dailyLogin", "id = ?" , new String[]{String.valueOf(dltId)});
                                targetDetails.remove(position);
                                rc_targetDetail.getAdapter().notifyItemRemoved(position);
                            }
                        }
                ));
            }
        };
        retrieveData();
    }

    private void retrieveData() {
        targetDetails.clear();
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "Target.db", null, MyDatabaseHelper.dbVersion);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            Cursor cursor = db.query("dailyLogin", null, "target_id = ?", new String[]{String.valueOf(target_id)}, null, null, "id desc");
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                    @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
                    @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));
                    targetDetails.add(new TargetDetailItem(id, date, description));
                } while (cursor.moveToNext());
            }
            cursor.close();

            rc_targetDetail.setLayoutManager(new LinearLayoutManager(this));
            rc_targetDetail.setAdapter(new TargetDetailAdapter(getApplicationContext(), targetDetails));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Not record found",Toast.LENGTH_SHORT).show();
        }
    }
}