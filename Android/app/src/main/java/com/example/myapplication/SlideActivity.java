package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.Adapter.Adapter;
import com.example.myapplication.database.MedicineDatabaseHelper;
import com.example.myapplication.util.SlideMenu;
import android.content.ContentValues;

public class SlideActivity extends AppCompatActivity {

    //声明控件
    private ImageView mIvHead;
    private SlideMenu slideMenu;
    private Button mBtnPerson;
    private Button mBtnHistory;
    private Button mBtnCollect;
    private Button mBtnRecommend;
    private Button mBtnCall;

    private EditText mETsearch;
    private Button mBtnsearch;
    private RecyclerView mRVmedicine;


    //调用MyDatabaseHelper （user是创建的数据库的名称）
    private MedicineDatabaseHelper dbHelper1 = new MedicineDatabaseHelper(this,"medicine",null,1);
//    private MedicineDatabaseHelper dbHelper1 = new MedicineDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        //找到控件
        mIvHead = findViewById(R.id.iv_head);
        slideMenu = findViewById(R.id.slideMenu);

        mBtnPerson = findViewById(R.id.btnPerson);
        mBtnHistory = findViewById(R.id.btnHistory);
        mBtnCollect = findViewById(R.id.btnCollect);
        mBtnRecommend = findViewById(R.id.btnRecommend);
        mBtnCall = findViewById(R.id.btnCall);

        mRVmedicine = findViewById(R.id.rv_medicine);
        mBtnsearch = findViewById(R.id.btn_search);
        mETsearch = findViewById(R.id.et_search);


        //搜索查找数据库
        mBtnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = mETsearch.getText().toString();

                SQLiteDatabase db1 = dbHelper1.getWritableDatabase();
                ContentValues values1 = new ContentValues();
//                Cursor c_medicine = db1.query("medicine", null, "Treatment"+"  LIKE ? ",
//                        new String[] { "%" + mETsearch + "%" }, null, null, null);
//                //可以把查询出来的值打印出来在后台显示/查看
//                String[] cols = c_medicine.getColumnNames();
//                while (c_medicine.moveToNext()) {
//                    for (String ColumnName : cols) {
//                        Log.i("info", ColumnName + ":" + c_medicine.getString(c_medicine.getColumnIndex(ColumnName)));
//                    }
//                }
//                c_medicine.close();

//                Cursor c_medicine = db1.query("medicine", new String[]{tab_field02},tab_field02+"  like '%" + mETsearch + "%'", null, null, null, null);
//
//                String sql2 = "select "+ TABLE_COLUMN_NAME +"," +TABLE_COLUMN_SYSTEM_NAME+ " from " + TABLE_NAME
//                        + " where " + TABLE_COLUMN_NAME + " like '%"+mETsearch+"%'";//注意：这里有单引号
//                Cursor cursor2 = db1.rawQuery(sql2,null);
//
//                        new String[] { "%" + str[0] + "%" }, null, null, null);
                String current_sql_sel = "SELECT  * FROM "+"medicine" +" where "+"Treatment"+" like '%"+mETsearch+"%'";

                Cursor c_test = db1.rawQuery(current_sql_sel, null);

                Log.e("tag", "查询完成...");

                while (c_test.moveToNext()) {
                    String name = c_test.getString(c_test.getColumnIndex(search));

//name.contains(str[0]);

// 让集合中的数据不重复;
//
//                    if (!result_list.contains(name)) {
//                        result_list.add(name);

                        Log.e("tag", name);

                    }
                

                c_test.close();

//                return result_list;




//                values1.put("MedicineName","风寒感冒颗粒");
//                values1.put("Combination","麻黄、葛根、紫苏叶、防风、桂枝、白芷、陈皮、苦杏仁、桔梗、甘草、干姜");
//                values1.put("Function","解表发汗，疏风散寒");
//                values1.put("Treatment","风寒感冒，发热，头痛，恶寒，无汗，咳嗽，鼻塞，流清涕，舌苔薄白，脉浮紧。");
//                db1.insert("medicine",null,values1);
//                values1.clear();
//                Log.d("SlideActivity","数据插入成功" );
            }
        });



        //实现侧滑的部分，点击加侧滑
        mIvHead.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                slideMenu.switchMenu();
            }
        });

        setListener();

        //利用adapter实现item
        mRVmedicine.setLayoutManager(new LinearLayoutManager(SlideActivity.this));
        //设置adapter
        //mRV_medicine.setAdapter(new Adapter(SlideActivity.this, pos -> Toast.makeText(SlideActivity.this,"click..." + pos,Toast.LENGTH_SHORT).show()));
        mRVmedicine.setAdapter(new Adapter(SlideActivity.this,new Adapter.OnItemClickListener(){
            @Override
            public void onClick(int pos) {
                Toast.makeText(SlideActivity.this,"click..." + pos,Toast.LENGTH_SHORT).show();
            }
        }));


    }

    private  void  setListener(){
        OnClick onClick = new OnClick();

        //对每一个按钮进行setOnClickListener
        mBtnPerson.setOnClickListener(onClick);
        mBtnHistory.setOnClickListener(onClick);
        mBtnCollect.setOnClickListener(onClick);
        mBtnRecommend.setOnClickListener(onClick);
        mBtnCall.setOnClickListener(onClick);

    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case  R.id.btnPerson:
                    intent = new Intent(SlideActivity.this,PersonActivity.class);
                    break;
                case  R.id.btnHistory:
                    intent = new Intent(SlideActivity.this,HistoryActivity.class);
                    break;
                case  R.id.btnCollect:
                    intent = new Intent(SlideActivity.this,CollectActivity.class);
                    break;
                case  R.id.btnRecommend:
                    intent = new Intent(SlideActivity.this,RecommendActivity.class);
                    break;
                case  R.id.btnCall:
                    intent = new Intent(SlideActivity.this,CallActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}