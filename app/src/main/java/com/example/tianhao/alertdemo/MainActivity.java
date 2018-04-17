package com.example.tianhao.alertdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String type;
    TextView show;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.eng:
               setEnglish();
                return true;
            case R.id.span:
                seSpanish();
                return true;
            default:
                return  false;

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences("com.example.tianhao.alertdemo", Context.MODE_PRIVATE);
        show = findViewById(R.id.hello);
        type = sharedPreferences.getString("langType", "Error");


        if(type.equals("Error")){
            alertDialog();
        }else {
            show.setText(type);
        }



    }
    public void alertDialog(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Would like to use english or spanish in this app?")
                .setMessage("choose one please!")
                .setPositiveButton("English", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"It's english now",Toast.LENGTH_SHORT).show();
                        setEnglish();
                    }
                })
                .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"It's spanish now",Toast.LENGTH_SHORT).show();
                        seSpanish();
                    }
                })
                .show();
    }
    public void setEnglish(){
        sharedPreferences.edit().putString("langType","English").apply();
        show.setText("English");
    }
    public void seSpanish(){
        sharedPreferences.edit().putString("langType","Spanish").apply();
        show.setText("Spanish");
    }
}
