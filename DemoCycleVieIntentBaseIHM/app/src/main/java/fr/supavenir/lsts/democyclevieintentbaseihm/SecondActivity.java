package fr.supavenir.lsts.democyclevieintentbaseihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private String message = "aucun message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("SecondActivity","-- onCreate");
        setContentView(R.layout.activity_second);
        message = this.getIntent().getStringExtra( "message");
        TextView tv = this.findViewById( R.id.tvquestion);
        tv.setText( message );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("SecondActivity","-- onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("SecondActivity","-- onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("SecondActivity","-- onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("SecondActivity","-- onStop()");
    }

    @Override
    protected void onDestroy() {

        Log.i("SecondActivity","-- onDestroy()");
        super.onDestroy();
    }


    @Override
    public void finish() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("message" , "From " + message);
        setResult( RESULT_OK , resultIntent );
        super.finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("SecondActivity","-- onRestart()");

    }
}