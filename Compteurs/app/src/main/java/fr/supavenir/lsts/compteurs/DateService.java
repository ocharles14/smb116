package fr.supavenir.lsts.compteurs;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.DateFormat;
import android.util.Log;

import androidx.annotation.Nullable;

public class DateService extends Service {

    public static final String ACTION_CURRENT_DATE =
            "fr.supavenir.lsts.compteurs.dateservice";

    // Par defaut le service n'est pas actif
    // Le thread qui envoie la date non plus
    private boolean active = false;

    private Thread dateThread = null;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("Date Service", "OnBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Date Service", "OnCreate");
        active = true;
        startDateService();
    }

    @Override
    public void onDestroy() {
        Log.i("Date Service", "OnDestroy");
        active = false;
        super.onDestroy();
    }

    private void startDateService() {
        dateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while( active ) {
                    broadcastDate();
                    try {
                        Thread.sleep( 2000 );
                    } catch ( InterruptedException ie ) {
                        ie.printStackTrace();
                    }
                }
            }
        });
        dateThread.start();
    }

    private void broadcastDate() {
        String date = DateFormat.format("dd/MM/yy hh:mm:ss" ,
                System.currentTimeMillis()).toString();
        Intent intent = new Intent( ACTION_CURRENT_DATE );
        intent.putExtra( "date" , date);
        sendBroadcast( intent );
    }


}
