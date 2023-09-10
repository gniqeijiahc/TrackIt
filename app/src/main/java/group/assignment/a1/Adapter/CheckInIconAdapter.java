package group.assignment.a1.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import group.assignment.a1.MyDatabaseHelper;
import group.assignment.a1.Object.TargetItem;
import group.assignment.a1.Page.Checkin;
import group.assignment.a1.Page.MainActivity;
import group.assignment.a1.R;

public class CheckInIconAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TargetItem> targetItemArrayList;


    public CheckInIconAdapter(Context context, ArrayList<TargetItem> targetItemArrayList) {
        this.context = context;
        this.targetItemArrayList = targetItemArrayList;
    }

    @Override
    public int getCount() {
        return targetItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
//            imageView.setLayoutParams(new GridView.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//            ));
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }
        TargetItem targetItem = targetItemArrayList.get(position);

        if(targetItem.getStatus() == 0)
            imageView.setImageResource(targetItem.getIconResource());
        else if(targetItem.getStatus() == 1)
            imageView.setImageResource(R.drawable.shadowtick3);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint({"NewApi", "LocalSuppress"}) LocalDate currentDate = LocalDate.now();
                ContentValues values = new ContentValues();
                MyDatabaseHelper myDb = new MyDatabaseHelper(context, "Target.db", null, MyDatabaseHelper.dbVersion);
                SQLiteDatabase db = myDb.getWritableDatabase();
                if(targetItem.getStatus() == 0){
                    values.put("status", 1);
                    values.put("day", targetItem.getDay() + 1);
                    int rowsUpdated = db.update("target", values, "id = " + targetItem.getTargetId(), null);
                    targetItem.setStatus(1);
                    targetItem.setDay(targetItem.getDay() + 1);
                    imageView.setImageResource(R.drawable.shadowtick3);

                    //DialogBox
                    ConstraintLayout constraintDialogCheckIn = v.findViewById(R.id.constraint_dialogCheckIn);
                    View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.dialog_check_in,constraintDialogCheckIn);
                    Button btn_submitCheckIn = view.findViewById(R.id.btn_submitCheckIn);
                    ImageView dialog_image = view.findViewById(R.id.dialog_image);
                    TextView tv_checkInTitle = view.findViewById(R.id.tv_checkInTitle);
                    EditText et_dialogCheckIn = view.findViewById(R.id.et_dialogCheckIn);

                    //Set content of dialog box
                    dialog_image.setImageResource(targetItem.getIconResource());
                    tv_checkInTitle.setText(targetItem.getName());

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setView(view);
                    builder.setCancelable(true);
                    final AlertDialog alertDialog = builder.create();

                    btn_submitCheckIn.findViewById(R.id.btn_submitCheckIn).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                            String dayMessage = et_dialogCheckIn.getText().toString();
                            ContentValues cv_target = new ContentValues();
                            cv_target.put("checkindate", String.valueOf(currentDate));
                            db.update("target",cv_target,"id = ?",new String[]{String.valueOf(targetItem.getTargetId())});
                            boolean empty = true;
                            ContentValues dailyContent = new ContentValues();
                            dailyContent.put("description", dayMessage);
                            dailyContent.put("target_id", targetItem.getTargetId());
                            dailyContent.put("date", String.valueOf(currentDate));
                            Cursor cursor = db.query("dailyLogin",null,"target_id = ?",new String[]{String.valueOf(targetItem.getTargetId())},null,null,null);
                            if(cursor.moveToFirst()) {
                                do {
                                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
                                String current_Date = String.valueOf(currentDate);
                                if(current_Date.equals(date)){
                                    Toast.makeText(context.getApplicationContext(), "Update",Toast.LENGTH_SHORT).show();
                                    db.update("dailyLogin",dailyContent,"target_id = ?",new String[]{String.valueOf(targetItem.getTargetId())});
                                    empty = false;
                                }
                                } while (cursor.moveToNext());
                            }
                            cursor.close();

                            if(empty) {
                                Toast.makeText(context.getApplicationContext(), "Insert",Toast.LENGTH_SHORT).show();
                                db.insert("dailyLogin", null, dailyContent);
                            }
                        }

                    });
                    if(alertDialog.getWindow() != null){
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    }
                    alertDialog.show();
                }
                else if(targetItem.getStatus() == 1){
                    values.put("status", 0);
                    values.put("day", targetItem.getDay() - 1);
                    int rowsUpdated = db.update("target", values, "id = " + targetItem.getTargetId(), null);
                    targetItem.setStatus(0);
                    targetItem.setDay(targetItem.getDay() - 1);
                    imageView.setImageResource(targetItem.getIconResource());
                }

            }
        });
        return imageView;
    }


}