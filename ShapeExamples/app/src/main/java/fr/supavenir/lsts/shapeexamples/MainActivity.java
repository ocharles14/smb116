package fr.supavenir.lsts.shapeexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private ImageView ivHeart = null;
    private SeekBar pbDuration = null;

    private Button btnFadeIn = null;
    private Button btnZoomIn = null;
    private Button btnRotateLeft = null;

    private Animation animFadeIn = null;
    private Animation animRotateLeft = null;


    private View.OnClickListener btnsListeners = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch ( v.getId() ) {
                case R.id.btnFadeIn :
                    animFadeIn.setDuration( pbDuration.getProgress() );
                    ivHeart.startAnimation( animFadeIn );
                    break;
                case R.id.btnRotateLeft :
                    animRotateLeft.setDuration( pbDuration.getProgress() );
                    ivHeart.startAnimation( animRotateLeft );

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivHeart = findViewById( R.id.ivHeart );
        btnFadeIn = findViewById( R.id.btnFadeIn );
        btnFadeIn.setOnClickListener( btnsListeners );

        btnZoomIn = findViewById(R.id.btnZoomIn);
        btnZoomIn.setOnClickListener( btnsListeners );

        btnRotateLeft = findViewById(R.id.btnRotateLeft);
        btnRotateLeft.setOnClickListener( btnsListeners );

        pbDuration = findViewById( R.id.pbDuration );
        loadAnimations();
    }

    private void loadAnimations() {
        animFadeIn = AnimationUtils.loadAnimation( this, R.anim.fade_in);
        animRotateLeft = AnimationUtils.loadAnimation( this, R.anim.rotate_left);
    }
}