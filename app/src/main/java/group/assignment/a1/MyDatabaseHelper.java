package group.assignment.a1;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.jetbrains.annotations.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static int dbVersion = 15;

    public static final String CREATE_TARGET = "create table target " +
            "(id integer primary key autoincrement, icon int, name text, description text, day int, checkindate text, status int)";
            //status 0 hasn't check in, 1 check in, 1000 complete

    public static final String CREATE_DailyLoginDetail = "create table dailyLogin(id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, description TEXT, target_id INTEGER)";
    public static final String CREATE_CountDown = "create table CountDown(" +
            "id integer primary key autoincrement," +
            "title text," +
            "date text," +
            "description text," +
            "notification int," +
            "day int" +
            ")";


    private Context mContext;
;
    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TARGET);
        sqLiteDatabase.execSQL(CREATE_DailyLoginDetail);
        sqLiteDatabase.execSQL(CREATE_CountDown);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS target");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS dailyLogin");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CountDown");
        onCreate(sqLiteDatabase);
    }
}
