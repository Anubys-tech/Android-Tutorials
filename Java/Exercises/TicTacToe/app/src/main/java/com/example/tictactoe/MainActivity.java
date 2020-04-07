package com.example.tictactoe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 17.02.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.gridLayout)
    GridLayout gridLayout;
    @Nullable
    @BindView(R.id.imageView0)
    ImageView imageView0;
    @Nullable
    @BindView(R.id.imageView1)
    ImageView imageView1;
    @Nullable
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @Nullable
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @Nullable
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @Nullable
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @Nullable
    @BindView(R.id.imageView6)
    ImageView imageView6;
    @Nullable
    @BindView(R.id.imageView7)
    ImageView imageView7;
    @Nullable
    @BindView(R.id.imageView8)
    ImageView imageView8;
    @Nullable
    @BindView(R.id.btn_game_reset)
    Button buttonReset;

    private Unbinder unbinder;
    private int activePlayer = 0;
    // 0: Gelb, 1: rot, 2: leer
    private int[] gameState = {2,2,2, 2,2,2, 2,2,2};
    private int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    private boolean gameActive = true;


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "TAG - MainActivity - onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        setListener();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "TAG - MainActivity - onDestroy()");
        super.onDestroy();

        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private void setListener() {
        Log.d(TAG, "TAG - MainActivity - setListener()");

        if (imageView0 != null) {
            imageView0.setOnClickListener(this::dropIn);
        }

        if (imageView1 != null) {
            imageView1.setOnClickListener(this::dropIn);
        }

        if (imageView2 != null) {
            imageView2.setOnClickListener(this::dropIn);
        }

        if (imageView3 != null) {
            imageView3.setOnClickListener(this::dropIn);
        }

        if (imageView4 != null) {
            imageView4.setOnClickListener(this::dropIn);
        }

        if (imageView5 != null) {
            imageView5.setOnClickListener(this::dropIn);
        }

        if (imageView6 != null) {
            imageView6.setOnClickListener(this::dropIn);
        }

        if (imageView7 != null) {
            imageView7.setOnClickListener(this::dropIn);
        }

        if (imageView8 != null) {
            imageView8.setOnClickListener(this::dropIn);
        }

        if (buttonReset != null) {
            buttonReset.setOnClickListener(this::startNewGame);
        }
    }

    private void dropIn(View view) {
        Log.d(TAG, "TAG - MainActivity - dropIn()");

        ImageView imageView = (ImageView) view;
        Log.i(TAG, "tag = " + imageView.getTag().toString());
        int tappedImageView = Integer.parseInt(imageView.getTag().toString());

        // Verhindert das erneute anklicken des Feldes
        if (gameState[tappedImageView] == 2 && gameActive) {
            gameState[tappedImageView] = activePlayer;
            imageView.setTranslationY(-1500);

            if(activePlayer == 0) {
                imageView.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                imageView.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            imageView.animate().translationYBy(1500).rotation(3600).setDuration(300);
            winningPosition();
        }
    }

    private void winningPosition() {
        Log.d(TAG, "TAG - MainActivity - winningPosition()");

        for (int[] winningPosition: winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                String winner;

                gameActive = false;
                if (activePlayer == 0) {
                    winner = getResources().getString(R.string.txt_rot);
                } else {
                    winner = getResources().getString(R.string.txt_gelb);
                }

                if (buttonReset != null) {
                    buttonReset.setVisibility(View.VISIBLE);
                }

                Toast.makeText(this, winner, Toast.LENGTH_LONG).show();
            } else {
                gameActive = false;

                for (int counterState: gameState) {
                    if (counterState == 2) {
                        gameActive = true;
                        break;
                    }
                }

                if (!gameActive) {
                    if (buttonReset != null) {
                        buttonReset.setVisibility(View.VISIBLE);
                        Toast.makeText(this, getResources().getString(R.string.txt_draw), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    private void startNewGame(View view) {
        Log.d(TAG, "TAG - MainActivity - startNewGame()");

        if (buttonReset != null) {
            buttonReset.setVisibility(View.INVISIBLE);
        }

        // GridLayout auf Ursprung wiederherstellen
        if (gridLayout != null) {
            for (int i = 0; i < gridLayout.getChildCount(); i++) {
                ImageView imageView = (ImageView) gridLayout.getChildAt(i);
                imageView.setImageDrawable(null);
            }
        }

        // Die Felder mit "2" wiederherstellen
        Arrays.fill(gameState, 2);

        activePlayer = 0;
        gameActive = true;
    }
}
