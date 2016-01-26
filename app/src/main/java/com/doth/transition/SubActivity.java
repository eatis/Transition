package com.doth.transition;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.util.Log;

import android.app.Activity;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        // 現在のintentを取得する
        Intent intent = getIntent();
        // intentから指定キーの文字列を取得する
        String name = intent.getStringExtra( "name" );

        TextView textView = (TextView) findViewById(R.id.textView2);
        String text1 = textView.getText().toString();
        Log.i("変更前テキスト", text1);

        // テキストビューのテキストを設定します
        textView.setText(name);
        // テキストビューのテキストを取得します
        String text2 = textView.getText().toString();
        Log.i("変更後テキスト", text2);
        //Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

}
