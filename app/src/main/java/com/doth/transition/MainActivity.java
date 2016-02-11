package com.doth.transition;

import android.content.Intent;
//import android.media.session.PlaybackState;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
//import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import com.doth.transition;

//import android.app.Activity;
//import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

//    private Button buttonSegue;
//    private Button toastButton;

    TextView tvOutput;
    Button btOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvOutput = (TextView) findViewById(R.id.tv_output);
        btOpen = (Button) findViewById(R.id.bt_open);

        btOpen.setOnClickListener(new View.OnClickListener() {
            // ボタン押下時
            public void onClick(View v) {
                // ダイアログの表示
                final CustomizedDialog dialog = CustomizedDialog.newInstance();
                dialog.setOnOkButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvOutput.setText(String.format("%1$,3d", dialog.getInputValue()));
                        dialog.dismiss();
                    }
                });
            }
        });

//        buttonSegue = (Button)findViewById(R.id.buttonSegue);
//        buttonSegue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Intent intent = new Intent();
//                intent.setClassName("com.doth.transition", "com.doth.transition.SubActivity");
//                // intentへ添え字付で値を保持させる
//                intent.putExtra( "name", "hogehoge" );
//                startActivityForResult(intent, 0);
//            }
//        });
//
//        toastButton = (Button)findViewById(R.id.toastButton);
//        toastButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "aaaa", Toast.LENGTH_SHORT).show();
//            }
//        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}