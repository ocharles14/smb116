package fr.supavenir.lsts.democyclevieintentbaseihm;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 *  @author : Olivier CHARLES
 *  @date : Février 2022
 *
 *  Permet d'illustrer le concept d'intention et d'appel d'une autre d'activité sans ou avec
 *  attente d'un résultat. Initiation aux FLAGS. Initiation aux layouts.
 *
 */

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> secondActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        String activityName = result.getData().getStringExtra("message");
                        TextView tvReponse = findViewById( R.id.tvReponse );
                        tvReponse.setText( tvReponse.getText() + "\n" + activityName );
                        Log.i("MainActivity","-- onActivityResult() from launcher");
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.i("MainActivity","-- onCreate");
        setContentView(R.layout.activity_main);

        Intent sansResultatExplicite = new Intent( this , SecondActivity.class );
        sansResultatExplicite.putExtra("message" , "SecondActivity 1");
        startActivity( sansResultatExplicite );

        Intent sansResultatImplicite = new Intent(  );
        sansResultatImplicite.setAction("second.ACTION");
        sansResultatImplicite.putExtra("message" , "SecondActivity 2");
        // Activité dans une nouvelle application ?
        sansResultatImplicite.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity( sansResultatImplicite );

        Intent avecResultatExplicite = new Intent( this , SecondActivity.class );
        avecResultatExplicite.putExtra("message" , "SecondActivity 3");
        // Deprecated mais fonctionne toujours
        startActivityForResult( avecResultatExplicite , 1 );

        Intent avecResultatEtLauncher = new Intent( this , SecondActivity.class );
        avecResultatEtLauncher.putExtra("message" , "SecondActivity 4");

        secondActivityLauncher.launch(  avecResultatEtLauncher );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String activityName = data.getStringExtra("message");
        TextView tvReponse = findViewById( R.id.tvReponse );
        if ( ! activityName.equals("From SecondActivity 4") )
            tvReponse.setText( tvReponse.getText() + "\n" + activityName );
        Log.i("MainActivity","-- onActivityResult classical");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity","-- onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity","-- onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity","-- onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity","-- onStop()");
    }

    @Override
    protected void onDestroy() {
        Log.i("MainActivity","-- onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity","-- onRestart()");
    }
}