package com.example.newplanewar;

import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.newplanewar.Bluetooth.BluetoothDialog;
import com.example.newplanewar.Grade.GradeActivity;
import com.example.newplanewar.SQL.MySQLiteHelper;
import com.example.newplanewar.View.MainView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton start;
    private ImageButton result;
    private ImageButton exit;
    private ImageButton bluetooth;
    private MySQLiteHelper SQLhelper;
    private SQLiteDatabase db;


     Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        //

        start = findViewById(R.id.main_startgame);
        result = findViewById(R.id.main_result);
        exit = findViewById(R.id.main_endgame);

        start.setOnClickListener(this);
        result.setOnClickListener(this);
        exit.setOnClickListener(this);

        SQLhelper = new MySQLiteHelper(this,"GradeDB.db",null,1);
        db = SQLhelper.getWritableDatabase();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_startgame:
                Intent startGame = new Intent(MainActivity.this,GameActivity.class);
                startActivity(startGame);
                break;
            case R.id.main_endgame:
                finish();
                break;
            case R.id.main_result:
                Intent showGrade = new Intent(MainActivity.this, GradeActivity.class);
                startActivity(showGrade);
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
