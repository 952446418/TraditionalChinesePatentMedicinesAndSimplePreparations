package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.myapplication.database.MyDatabaseHelper;
import com.example.myapplication.util.ToastUtil;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;


//public class MainActivity extends AppCompatActivity implements View.OnClickListener {
public class MainActivity extends AppCompatActivity{
    //声明控件
    private Button mBtnLogin;
    private Button mBtnregister;
    private EditText mEtUser;
    private EditText mETPassword;
    private Button mBtnRepass;

    private MyDatabaseHelper dbHelper;
    //private MyDatabaseHelper dbHelper1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteStudioService.instance().start(this);


        //找到控件
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnregister = findViewById(R.id.btn_register);
        mEtUser = findViewById(R.id.et_1);
        mETPassword = findViewById(R.id.et_2);
        mBtnRepass = findViewById(R.id.btn_repass);


        //调用MyDatabaseHelper （user是创建的数据库的名称）
        dbHelper = new MyDatabaseHelper(this,"user",null,1);
        //dbHelper1 = new MyDatabaseHelper(this,"user1",null,1);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = mEtUser.getText().toString();
                String password = mETPassword.getText().toString();

                String ok = "登陆成功!";
                String fail = "账号和密码有误，请重新登录！";
                Intent intent = null;

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //根据画面上输入的账号/密码去数据库中进行查询（user是表名）
                Cursor c = db.query("user", null, "User=? and Password=?", new String[]{username, password}, null, null, null);

//                String id = c.getString(c.getColumnIndex("id"));
//                String user = c.getString(c.getColumnIndex("User"));
//                String pass = c.getString(c.getColumnIndex("Password"));
//
//                System.out.println("记录" + id + " " + user + " " + pass);
//                Log.d("MainActivity", "User:" + username);
//                Log.d("MainActivity", "Password:" + password);

                //如果有查询到数据
                if (c != null && c.getCount() >= 1) {
//          if (username.equals("wjt")&&password.equals("123456")){
                    //可以把查询出来的值打印出来在后台显示/查看
                    String[] cols = c.getColumnNames();
                    while (c.moveToNext()) {
                        for (String ColumnName : cols) {
                            Log.i("info", ColumnName + ":" + c.getString(c.getColumnIndex(ColumnName)));
                        }
                    }
                    c.close();
                    db.close();

                    MainActivity.this.finish();

                    //封装好的类
                    //ToastUtil.showMsg(getApplicationContext(),ok);
                    ToastUtil.showMsg(MainActivity.this, ok);

                    //如果正确进行跳转
                    intent = new Intent(MainActivity.this, SlideActivity.class);
                    startActivity(intent);
                    } else {
                    //不正确,弹出登录失败toast
                    //提升版，居中显示
                    Toast toastCenter = Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_SHORT);
                    toastCenter.setGravity(Gravity.CENTER, 0, 0);
                    toastCenter.show();
                }
            }
//        }

//        //如果没有查询到数据
//        else{
//            ToastUtil.showMsg(MainActivity.this,fail);
//        }
//
//            }
        });

//        mBtnregister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                values.put("User","zhangsan");
//                values.put("Password","123456");
//                db.insert("user",null,values);
//
//                values.clear();
//
//                values.put("User","lisi");
//                values.put("Password","123456");
//                db.insert("user",null,values);
//
//                values.clear();
//            }
//        });

//        Button mquery = (Button) findViewById(R.id.btn_query);
//        mquery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                Cursor cursor = db.query("user",null,null,null,null,null,null);
//                //移到第一行
//                if(cursor.moveToFirst()){
//                    do {
//                        String id = cursor.getString(cursor.getColumnIndex("id"));
//                        String user = cursor.getString(cursor.getColumnIndex("User"));
//                        String pass = cursor.getString(cursor.getColumnIndex("Password"));
//                        Log.d("MainActivity", "ID:" + id);
//                        Log.d("MainActivity", "User:" + user);
//                        Log.d("MainActivity", "Password:" + pass);
//                    }while (cursor.moveToNext());
//                }
//                cursor.close();
//            }
//        });
//
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        //根据画面上输入的账号/密码去数据库中进行查询（user_tb是表名）
//        Cursor cursor = db.query("user_tb",null,"ID=? and Password=?",new String[]{username,password},null,null,null);
//        //如果有查询到数据
//        if(c!=null && c.getCount() >= 1){
//            //可以把查询出来的值打印出来在后台显示/查看
//            String[] cols = c.getColumnNames();
//            while(c.moveToNext()){
//                for(String ColumnName:cols){
//                    Log.i("info",ColumnName+":"+c.getString(c.getColumnIndex(ColumnName)));
//                }
//            }
//            c.close();
//            db.close();
//
//            this.finish();
//        }
//        //如果没有查询到数据
//        else{
//            Toast.makeText(this, "手机号或密码输入错误！", Toast.LENGTH_SHORT).show();
//        }
//    }


        //实现直接跳转--方法一
        mBtnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //实现直接跳转--方法一
        mBtnRepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(MainActivity.this, RepassActivity.class);
                startActivity(intent);
            }
        });

//
//        //匹配对应用户名密码跳转--方法二
//        mBtnLogin.setOnClickListener((View.OnClickListener) this);
//
    }
//
//    public void onClick(View v){
//        //需要获取输入的用户名和密码
//        String username = mEtUser.getText().toString();
//        String password = mETPassword.getText().toString();
//
//        Log.d("MainActivity", "User:" + username);
//        Log.d("MainActivity", "Password:" + password);
//        System.out.println("记录"  + " " + username + " " + password);
//
//        //弹出内容设置
//
//        String ok = "登陆成功!";
//        String fail = "账号和密码有误，请重新登录！";
//        Intent intent = null;
//
//        //假设正确的账号和密码为wjt，123456
//        if (username.equals("wjt")&&password.equals("123456")){
//            //toast普通版
//            //Toast.makeText(getApplicationContext(),ok,Toast.LENGTH_SHORT).show();
//
//            //封装好的类
//            //ToastUtil.showMsg(getApplicationContext(),ok);
//            ToastUtil.showMsg(MainActivity.this,ok);
//
//            //如果正确进行跳转
////            intent = new Intent(MainActivity.this, SlideActivity.class);
////            startActivity(intent);
//        }else{
//            //不正确,弹出登录失败toast
//            //提升版，居中显示
//            Toast toastCenter = Toast.makeText(getApplicationContext(),fail,Toast.LENGTH_SHORT);
//            toastCenter.setGravity(Gravity.CENTER,0,0);
//            toastCenter.show();
//        }
//  }


//    public void OnMyLoginClick(View v){
//        //判断账号/密码是否有输入的处理...
//        String username = mEtUser.getText().toString();
//        String password = mETPassword.getText().toString();
//
//
//
//        //调用DBOpenHelper （user.db是创建的数据库的名称）
//        dbHelper = new MyDatabaseHelper(this,"user.db",null,1);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        //根据画面上输入的账号/密码去数据库中进行查询（user_tb是表名）
//        Cursor c = db.query("user_tb",null,"ID=? and Password=?",new String[]{username,password},null,null,null);
//        //如果有查询到数据
//        if(c!=null && c.getCount() >= 1){
//            //可以把查询出来的值打印出来在后台显示/查看
//        String[] cols = c.getColumnNames();
//        while(c.moveToNext()){
//            for(String ColumnName:cols){
//                Log.i("info",ColumnName+":"+c.getString(c.getColumnIndex(ColumnName)));
//            }
//        }
//            c.close();
//            db.close();
//
//            this.finish();
//        }
//        //如果没有查询到数据
//        else{
//            Toast.makeText(this, "手机号或密码输入错误！", Toast.LENGTH_SHORT).show();
//        }
//    }
}