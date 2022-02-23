package fr.supavenir.lsts.couleurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ActiviteChoixCouleur extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar sbAlpha;
    private SeekBar sbRouge;
    private SeekBar sbVert;
    private SeekBar sbBleu;
    private TextView tvARGB;
    private TextView tvCouleur;
    private int a;
    private int r;
    private int v;
    private int b;
    private int resultat = RESULT_OK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        sbAlpha = findViewById( R.id.sbAlpha );
        sbAlpha.setOnSeekBarChangeListener( this );

        sbRouge = findViewById( R.id.sbRouge );
        sbRouge.setOnSeekBarChangeListener( this );

        sbVert = findViewById( R.id.sbVert );
        sbVert.setOnSeekBarChangeListener( this );

        sbBleu = findViewById( R.id.sbBleu );
        sbBleu.setOnSeekBarChangeListener( this );

        tvARGB = findViewById( R.id.tvARGB );
        tvCouleur = findViewById( R.id.tvCouleur);

        Button btnOk = findViewById( R.id.btnOk );
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnAnnuler = findViewById( R.id.btnAnnuler );
        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultat = RESULT_CANCELED;
                finish();
            }
        });


    }

    public void miseAjour( ) {
        int a = sbAlpha.getProgress();
        int r = sbRouge.getProgress();
        int v = sbVert.getProgress();
        int b = sbBleu.getProgress();
        String texteCouleur = "ARGB( " + a +" , " + r + " , " + v + " , " + b +" )";
        tvARGB.setText( texteCouleur);
        tvCouleur.setBackgroundColor(Color.argb( a , r, v ,b ));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        miseAjour();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void finish() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("a" , a);
        resultIntent.putExtra("r" , r);
        resultIntent.putExtra("v" , v);
        resultIntent.putExtra("b" , b);
        setResult( resultat , resultIntent );
        super.finish();
    }

}