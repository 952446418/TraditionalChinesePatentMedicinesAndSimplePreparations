package com.example.myapplication.database;//package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.SlideActivity;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class MedicineDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_Medicine = "create table medicine("
        +"id integer primary key autoincrement,"
        +"MedicineName text,"
        +"Combination text,"
        +"Function text,"
        +"Treatment text,"
        +"taboo text)";

    private Context mContext;

    public MedicineDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_Medicine);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//public class MedicineDatabaseHelper extends SQLiteOpenHelper {
//    private static MedicineDatabaseHelper mInstance = null;
//    private Context mContext;
//
//    /**
//     * 数据库名称
//     **/
//    public static final String DATABASE_NAME = "medicine.db";
//
//    /**
//     * 数据库版本号
//     **/
//    private static final int DATABASE_VERSION = 1;
//
//    /**
//     * 数据库SQL语句 添加一个表
//     **/
//    private static final String NAME_TABLE_CREATE = "create table medicine("
//            +"id integer primary key autoincrement,"
//            +"MedicineName text,"
//            +"Combination text,"
//            +"Function text,"
//            +"Treatment text)";
////    public MedicineDatabaseHelper(Context context) {
////        super(context, DATABASE_NAME, null, DATABASE_VERSION);
////        super(context, name, factory, version);
////    }
//    public MedicineDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//        mContext = context;
//    }
////    /**
////     * 单例模式
////     **/
////    static synchronized MedicineDatabaseHelper getInstance(Context context) {
////        if (mInstance == null) {
////            mInstance = new MedicineDatabaseHelper(context);
////        }
////        return mInstance;
////    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        /**向数据中添加表**/
//        db.execSQL(NAME_TABLE_CREATE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        /**可以拿到当前数据库的版本信息 与之前数据库的版本信息   用来更新数据库**/
//    }
//
//
////    /**
////     * 删除数据库
////     *
////     * @param context
////     * @return
////     */
////    public boolean deleteDatabase(Context context) {
////        return context.deleteDatabase(DATABASE_NAME);
////    }
//}