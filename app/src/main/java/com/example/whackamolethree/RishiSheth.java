package com.example.whackamolethree;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whackamolethree.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {




    TextView gameOverText;

    ImageView[] moles;

    ImageView[] moleRow;


    ImageView mole1;
    ImageView mole2;
    ImageView mole3;
    ImageView mole4;
    ImageView mole5;
    ImageView mole6;
    ScaleAnimation scaleAnimation;
    ImageView mole;
    int finalScore;
    TextView numberedScore;

    GameThread gameThread;

    TextView timer;
    int gameTime = 30;
    int score = 0;
    Random random = new Random();

    @SuppressLint({"MissingInflatedId", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize mole ImageViews into an array for easier access
        moles = new ImageView[] {
                findViewById(R.id.id_MoleOne),
                findViewById(R.id.id_MoleTwo),
                findViewById(R.id.id_MoleThree),
                findViewById(R.id.id_MoleFour),
                findViewById(R.id.id_MoleFive),
                findViewById(R.id.id_MoleSix)
        };
        moleRow = new ImageView[] {
                findViewById(R.id.id_scoreMole1),
                findViewById(R.id.id_scoreMole2),
                findViewById(R.id.id_scoreMole3),
                findViewById(R.id.id_scoreMole4),
                findViewById(R.id.id_scoreMole5),
                findViewById(R.id.id_scoreMole6),
                findViewById(R.id.id_scoreMole7),
                findViewById(R.id.id_scoreMole8),
                findViewById(R.id.id_scoreMole9),
                findViewById(R.id.id_scoreMole10),
                findViewById(R.id.id_scoreMole11),
                findViewById(R.id.id_scoreMole12),
                findViewById(R.id.id_scoreMole13),
                findViewById(R.id.id_scoreMole14),
                findViewById(R.id.id_scoreMole15),

        };
        mole1 = findViewById(R.id.id_MoleOne);
        mole2 = findViewById(R.id.id_MoleTwo);
        mole3 = findViewById(R.id.id_MoleThree);
        mole4 = findViewById(R.id.id_MoleFour);
        mole5 = findViewById(R.id.id_MoleFive);
        mole6 = findViewById(R.id.id_MoleSix);
        gameOverText = findViewById(R.id.id_GameOver);
        numberedScore = findViewById(R.id.id_finalScoreNumber);







        timer = findViewById(R.id.id_time);

        // Initialize moles as invisible
        for (ImageView mole : moles) {
            mole.setVisibility(View.INVISIBLE);
            moleA(mole);
        }

        for(ImageView moleh: moleRow) {
            moleh.setVisibility(View.INVISIBLE);
        }






        // Start game thread
        new GameThread().start();


    }

    class GameThread extends Thread {
        public void run() {

            //first thread
            for (int i = gameTime; i >= 0; i--) {
                int finalI = i;


                runOnUiThread(() -> timer.setText(String.valueOf(finalI)));
                if(i == 0) {
                    for (ImageView mole : moles) {
                        mole.setVisibility(View.INVISIBLE);
                        //mole.setClickable(false);

                    }
                    mole.setClickable(false);
                    finalScore = score;
                    gameOverText.setText("GAME OVER FINAL SCORE IS: ");
                    numberedScore.setText(String.valueOf(score));


                }

                int moleIndex = random.nextInt(moles.length);
                 mole = moles[moleIndex];


                runOnUiThread(() -> showAndHideMole(mole));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }



        }
    }

    private void showAndHideMole(ImageView mole) {

        mole.clearAnimation();


        mole.setVisibility(View.INVISIBLE);
        mole.setClickable(false);

        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 1.0f,
                0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1.0f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(Animation.REVERSE);

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                mole.setVisibility(View.VISIBLE);
                mole.setClickable(true);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                mole.setVisibility(View.INVISIBLE);
                mole.setClickable(false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        mole.startAnimation(scaleAnimation);
    }


    private void moleA(ImageView mole){
        mole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mole.setClickable(false);
                score++;
                moleRow[score].setVisibility(View.VISIBLE);
            }
        });
    }
}
