package fr.supavenir.lsts.compteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvCompteurs;
    private BroadcastReceiver dateServiceReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();

        tvCompteurs = findViewById( R.id.tvCompteur );

        // Methode 1
        Button btnThread = findViewById( R.id.btnCompteurThread );
        btnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                       for( int i=1; i<=5; i++  ) {
                           try {
                               Thread.sleep(1000);
                               tvCompteurs.setText(""+i+" sec");
                           } catch (Exception e) {
                               e.printStackTrace();
                           }
                       }
                    }
                }).start();
            }
        });

        // Methode 2
        Button btnRunUIThread = findViewById( R.id.btnCompteurRunOnUIThread );
        btnRunUIThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for( int i=1; i<=5; i++  ) {
                            int compteur = i;
                            try {
                                Thread.sleep(1000);
                                runOnUiThread(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      tvCompteurs.setText(""+compteur+" sec");
                                                  }
                                              }
                                );

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }).start();
            }
        });

        // methode 3

        Button btnHandler = findViewById( R.id.btnCompteurHandler );
        btnHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for( int i=1; i<=5; i++  ) {
                            int compteur = i;
                            try {
                                Thread.sleep(1000);
                                handler.post(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      tvCompteurs.setText(""+compteur+" sec");
                                                  }
                                              }
                                );

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }).start();
            }
        });

        // Methode 4 avec AsyncTask
        Button btnAsynTask = findViewById( R.id.btnCompteurAsyncTask );
        btnAsynTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompteurAsyncTask compteurAT = new CompteurAsyncTask();
                compteurAT.execute( 10 , 25 );
            }
        });

        Button btnStartDateService = findViewById( R.id.btnStartDateService );
        btnStartDateService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService( new Intent( MainActivity.this, DateService.class ));
            }
        });

        Button btnStopDateService = findViewById( R.id.btnStopDateService );
        btnStopDateService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService( new Intent( MainActivity.this, DateService.class ));
            }
        });

        dateServiceReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String date = intent.getStringExtra( "date");
                tvCompteurs.setText( date );
            }
        };


    }

    @Override
    protected  void onResume() {
        super.onResume();
        registerReceiver( dateServiceReceiver , new IntentFilter( DateService.ACTION_CURRENT_DATE));
    }

    @Override
    protected  void onPause() {
        super.onPause();
        unregisterReceiver( dateServiceReceiver );
    }


    public class CompteurAsyncTask extends AsyncTask< Integer , Integer , Integer > {

        @Override
        protected Integer doInBackground(Integer... integers) {
            // On peut passer autant de paramètres Integer que l'on veut
            Integer minCompteur = integers[0];
            Integer maxCompteur = integers[1];
            for( int i=minCompteur; i<=maxCompteur; i++  ) {
                try {
                    Thread.sleep(1000);
                    // On affiche la progression via On progressupdate
                    publishProgress( i );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Le resultat final est automatiquement
            // transmisà la methode onPostExecute si elle existe
            return maxCompteur;
        }

        @Override
        public void onProgressUpdate( Integer... integers) {
            tvCompteurs.setText(""+integers[0]+" sec");
        }

        @Override
        public void onPostExecute( Integer resultat ) {
            Log.i("AsyncTask" , "Resultat : "+resultat);
        }
    }










}