package com.test.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    // Player representation
    // 0 = cross
    // 1 = check
    int activePlayer = 0;
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // State meaning
    // 0 = cross
    // 1 = check
    // 2 = null
    int [] [] winningPosition = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7} , {2,5,8}, {0,4,8}, {2,4,6}};
    // On tapping grid box
    public void playerTap(View view){

        ImageView img = (ImageView) view;
        int tappedImg = Integer.parseInt(img.getTag().toString());
        if (!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImg] == 2){
            gameState[tappedImg] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0){
                img.setImageResource(R.drawable.fcross);
                activePlayer = 1;
                ImageView statusImg = findViewById(R.id.statusImg);
                statusImg.setImageResource(R.drawable.fcheck);
                TextView status = findViewById(R.id.status);
                status.setText("Green's Turn - Tap to play");

            }else {
                img.setImageResource(R.drawable.fcheck);
                activePlayer = 0;
                ImageView statusImg = findViewById(R.id.statusImg);
                statusImg.setImageResource(R.drawable.fcross);
                TextView status = findViewById(R.id.status);
                status.setText("Red's Turn - Tap to play");

            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        // Check if any player has won
        for (int[] winningPosition : winningPosition){
            if(gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2){
                String winner= null;
                gameActive = false;
                if(gameState[winningPosition[0]] == 0){
                    gameReset(view);
                    ImageView statusImg = findViewById(R.id.statusImg);
                    statusImg.setImageResource(R.drawable.fcross);
                    winner = "Red has won !";
                }else {
                    gameReset(view);
                    ImageView statusImg = findViewById(R.id.statusImg);
                    statusImg.setImageResource(R.drawable.fcheck);
                    winner = "Green has won !";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winner);

            }
        }
    }
    // Resetting game play after won
    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for (int i =0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView9)).setImageResource(0);
        ImageView statusImg = findViewById(R.id.statusImg);
        statusImg.setImageResource(R.drawable.fcross);
        TextView status = findViewById(R.id.status);
        status.setText("Red's Turn - Tap to play");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}