package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.util.ToastUtil;

public class PersonActivity extends AppCompatActivity {

    private RadioGroup mRG_sex;
    private CheckBox mCB_Food;
    private CheckBox mCB_Flower;
    private CheckBox mCB_Qms;
    private Button mbtn_commit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        mRG_sex = findViewById(R.id.rg_sex);
        mCB_Food = findViewById(R.id.cb_food);
        mCB_Flower = findViewById(R.id.cb_flower);
        mCB_Qms = findViewById(R.id.cb_qms);

        mbtn_commit = findViewById(R.id.btn_commit);


        mRG_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                Toast.makeText(PersonActivity.this,radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        mCB_Food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(PersonActivity.this,isChecked?"选中":"未选中",Toast.LENGTH_SHORT).show();
            }
        });

        mCB_Flower.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(PersonActivity.this,isChecked?"选中":"未选中",Toast.LENGTH_SHORT).show();
            }
        });

        mCB_Qms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(PersonActivity.this,isChecked?"选中":"未选中",Toast.LENGTH_SHORT).show();
            }
        });

        mbtn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;

                //封装好的类
                //ToastUtil.showMsg(getApplicationContext(),ok);
                ToastUtil.showMsg(PersonActivity.this, "提交成功");

                //如果正确进行跳转
                intent = new Intent(PersonActivity.this, SlideActivity.class);
                startActivity(intent);
            }
        });

    }
}

