package com.example.myapplication;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//
//        import java.util.ArrayList;
//        import java.util.List;
//
//        import android.app.Activity;
//        import android.os.Bundle;
//        import android.text.Editable;
//        import android.text.TextWatcher;
//        import android.util.Log;
//        import android.view.View;
//        import android.widget.AdapterView;
//        import android.widget.AdapterView.OnItemClickListener;
//        import android.widget.EditText;
//        import android.widget.ListView;
//        import android.widget.Toast;
//
//
//import com.example.myapplication.Adapter.MyAdapter;
//import com.example.myapplication.util.FilterListener;
//
//public class MysearchviewActivity extends Activity {
//
//    private EditText et_ss;
//    private ListView lsv_ss;
//    private List<String> list = new ArrayList<String>();
//    boolean isFilter;
//    private MyAdapter adapter = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mysearchview);
//        setViews();// 控件初始化
//        setData();// 给listView设置adapter
//        setListeners();// 设置监听
//    }
//
//    /**
//     * 数据初始化并设置adapter
//     */
//    private void setData() {
//        initData();// 初始化数据
//
//        // 这里创建adapter的时候，构造方法参数传了一个接口对象，这很关键，回调接口中的方法来实现对过滤后的数据的获取
//        adapter = new MyAdapter(list, this, new FilterListener() {
//            // 回调方法获取过滤后的数据
//            public void getFilterData(List<String> list) {
//                // 这里可以拿到过滤后数据，所以在这里可以对搜索后的数据进行操作
//                Log.e("TAG", "接口回调成功");
//                Log.e("TAG", list.toString());
//                setItemClick(list);
//            }
//        });
//        lsv_ss.setAdapter(adapter);
//    }
//
//    /**
//     * 给listView添加item的单击事件
//     * @param filter_lists  过滤后的数据集
//     */
//    protected void setItemClick(final List<String> filter_lists) {
//        lsv_ss.setOnItemClickListener(new OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                // 点击对应的item时，弹出toast提示所点击的内容
//                Toast.makeText(MysearchviewActivity.this, filter_lists.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    /**
//     * 简单的list集合添加一些测试数据
//     */
//    private void initData() {
//        list.add("看着飞舞的尘埃   掉下来");
//        list.add("没人发现它存在");
//        list.add("多自由自在");
//        list.add("可世界都爱热热闹闹");
//        list.add("容不下   我百无聊赖");
//        list.add("不应该   一个人 发呆");
//        list.add("只有我   守着安静的沙漠");
//        list.add("等待着花开");
//        list.add("只有我   看着别人的快乐");
//    }
//
//    private void setListeners() {
//        // 没有进行搜索的时候，也要添加对listView的item单击监听
//        setItemClick(list);
//
//        /**
//         * 对编辑框添加文本改变监听，搜索的具体功能在这里实现
//         * 很简单，文本该变的时候进行搜索。关键方法是重写的onTextChanged（）方法。
//         */
//        et_ss.addTextChangedListener(new TextWatcher() {
//
//            /**
//             *
//             * 编辑框内容改变的时候会执行该方法
//             */
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before,
//                                      int count) {
//                // 如果adapter不为空的话就根据编辑框中的内容来过滤数据
//                if(adapter != null){
//                    adapter.getFilter().filter(s);
//                }
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//                // TODO Auto-generated method stub
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                // TODO Auto-generated method stub
//            }
//        });
//    }
//
//    /**
//     * 控件初始化
//     */
//    private void setViews() {
//        et_ss = (EditText) findViewById(R.id.et_search);// EditText控件
//        lsv_ss = (ListView)findViewById(R.id.rv_medicine);// ListView控件
//    }
//
//}
////上面的代码，主要就是界面上有一个搜索框，搜索框下面有一个列表。当在搜索框中输入内容的时候，此时下面的列表显示的内容会自动匹配你输入在搜索框中的内容。


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.database.RecordSQLiteOpenHelper;
import com.example.myapplication.util.MyListView;

import java.util.Date;

public class MysearchviewActivity  extends Activity {
    private EditText et_search;

    private TextView tv_tip;

    private MyListView listView;

    private TextView tv_clear;

    ScrollView scrollView;

    private RecordSQLiteOpenHelper helper = new RecordSQLiteOpenHelper(this);

    private SQLiteDatabase db;

    private BaseAdapter adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mysearchview);

        initView(); // 初始化控件

// 清空搜索历史

        tv_clear.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                deleteData();

                queryData("");

            }

        });

        et_search.setOnKeyListener(new View.OnKeyListener() {// 输入完后按键盘上的搜索键

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {// 修改回车键功能

// 隐藏键盘

                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(

                            getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

// 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存

                    boolean hasData = hasData(et_search.getText().toString().trim());

                    if (!hasData) {
                        insertData(et_search.getText().toString().trim());

                        queryData("");

                    }

                    Toast.makeText(MysearchviewActivity.this, "点击软键盘搜索!", Toast.LENGTH_SHORT).show();

                }

                return false;

            }

        });

        et_search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override

            public void onFocusChange(View view, boolean b) {
                if (b) { //获得

                    scrollView.setVisibility(View.VISIBLE);

                } else {//市区焦点

                    scrollView.setVisibility(View.GONE);

                }

            }

        });

// 搜索框的文本变化实时监听

        et_search.addTextChangedListener(new TextWatcher() {
            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override

            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() == 0) {
                    tv_tip.setText("搜索历史");

                } else {
                    tv_tip.setText("搜索结果");

                }

                String tempName = et_search.getText().toString();

// 根据tempName去模糊查询数据库中有没有数据

                queryData(tempName);

            }

        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(android.R.id.text1);

                String name = textView.getText().toString();

                et_search.setText(name);

                Toast.makeText(MysearchviewActivity.this, name, Toast.LENGTH_SHORT).show();

                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(

                        getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS); //隐藏软键盘

                et_search.clearFocus();

                et_search.setText("");
            }

        });

// 插入测试数据

        Date date = new Date();

        long time = date.getTime();

        insertData("LY" + time);

        queryData(""); // 第一次进入查询所有的历史记录

    }

    /**

     * 插入数据

     */

    private void insertData(String tempName) {
        db = helper.getWritableDatabase();

        db.execSQL("insert into records(name) values('" + tempName + "')");

        db.close();

    }

    /**

     * 模糊查询数据

     */

    private void queryData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(

                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);

// 创建adapter适配器对象

        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{"name"},

                new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

// 设置适配器

        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    /**

     * 检查数据库中是否已经有该条记录

     */

    private boolean hasData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(

                "select id as _id,name from records where name =?", new String[]{tempName});

//判断是否有下一个

        return cursor.moveToNext();

    }

    /**

     * 清空数据

     */

    private void deleteData() {
        db = helper.getWritableDatabase();

        db.execSQL("delete from records");

        db.close();

    }

    private void initView() {
        et_search = (EditText) findViewById(R.id.et_search);

        scrollView = findViewById(R.id.showSearch);

        tv_tip = (TextView) findViewById(R.id.tv_tip);

        listView = (com.cwvs.microlife.MyListView) findViewById(R.id.listView);

        tv_clear = (TextView) findViewById(R.id.tv_clear);

// 调整EditText左边的搜索按钮的大小

        Drawable drawable = getResources().getDrawable(R.drawable.search);

        drawable.setBounds(0, 0, 60, 60);// 第一0是距左边距离，第二0是距上边距离，60分别是长宽

        et_search.setCompoundDrawables(drawable, null, null, null);// 只放左边

    }

}
