package com.example.project;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.view.CountDownView;

public class MainActivity extends AppCompatActivity {

    private TextView bindView;
    private CountDownView countDownView;
    private EditText ed_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView = (TextView) findViewById(R.id.bind_view);
        countDownView = new CountDownView(this);
        countDownView.bindCountdown2View(bindView);


        addContentView(countDownView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));


        ed_content = (EditText) findViewById(R.id.ed_content);
        ed_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                Log.e("xie","beforeTextChanged"+s+"   "+start+"    "+count+"   "+after);
                // s:之前的文字内容
                // start:添加文字的位置(从0开始)
                // count:不知道 一直是0
                // after:添加的文字总数



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("xie","onTextChanged"+s+"   "+start+"    "+"     "+before+"   "+count+"   ");
                // s:之后的文字内容
                // start:添加文字的位置(从0开始)
                // before:不知道 一直是0
                // before:添加的文字总数
                if (s.length() > 6){
                    Toast.makeText(MainActivity.this,"输入长度不允许超过6位",Toast.LENGTH_LONG).show();
                    s = s.toString().substring(0,s.length()-1).trim();
                    ed_content.setText(s.toString());
                    ed_content.setSelection(s.length());  //设置输入光标的位置
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("xie","afterTextChanged"+s+"   ");
                // s:之后的文字内容

            }
        });

    }
}
