package com.example.braintrainer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 26.03.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.tv_timer)
    TextView textViewTimer;
    @Nullable
    @BindView(R.id.tv_task)
    TextView textViewTask;
    @Nullable
    @BindView(R.id.tv_interim_result)
    TextView textViewInterimResult;
    @Nullable
    @BindView(R.id.tv_selection1)
    TextView textViewSelection1;
    @Nullable
    @BindView(R.id.tv_selection2)
    TextView textViewSelection2;
    @Nullable
    @BindView(R.id.tv_selection3)
    TextView textViewSelection3;
    @Nullable
    @BindView(R.id.tv_selection4)
    TextView textViewSelection4;
    @Nullable
    @BindView(R.id.tv_score)
    TextView textViewScore;
    @Nullable
    @BindView(R.id.btn_play_again)
    Button buttonPlayAgain;

    private ArrayList<Integer> answers = new ArrayList<>();
    private Unbinder unbinder;
    private CountDownTimer countDownTimer;
    private int locationOfCorrectAnswer;
    private int score = 0;
    private int numberOfQuestions = 0;


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

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private void setListener() {
        Log.d(TAG, "TAG - MainActivity - setListener()");

        if (buttonPlayAgain != null) {
            buttonPlayAgain.setOnClickListener(view -> playAgain());
        }

        if (textViewSelection1 != null) {
            textViewSelection1.setOnClickListener(this::chooseAnswer);
        }

        if (textViewSelection2 != null) {
            textViewSelection2.setOnClickListener(this::chooseAnswer);
        }

        if (textViewSelection3 != null) {
            textViewSelection3.setOnClickListener(this::chooseAnswer);
        }

        if (textViewSelection4 != null) {
            textViewSelection4.setOnClickListener(this::chooseAnswer);
        }
    }

    private void playAgain() {
        Log.d(TAG, "TAG - MainActivity - playAgain()");
        score = 0;
        numberOfQuestions = 0;

        if (textViewScore != null) {
            String sScore = score + getResources().getString(R.string.txt_slash) + numberOfQuestions;
            textViewScore.setText(sScore);
        }

        if (textViewTimer != null) {
            textViewTimer.setText(getResources().getString(R.string.txt_30_secunden));
        }

        if (buttonPlayAgain != null) {
            buttonPlayAgain.setVisibility(View.INVISIBLE);
        }

        if (textViewScore != null) {
            textViewScore.setText(getResources().getString(R.string.txt_0_0));
        }

        newQuestion();
        startCountDownTimer();
    }

    public void newQuestion() {
        Log.d(TAG, "TAG - MainActivity - newQuestion()");
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        if (textViewTask != null) {
            String sTask = a + getResources().getString(R.string.txt_plus) + b;
            textViewTask.setText(sTask);
        }

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a + b) {
                    wrongAnswer = rand.nextInt(41);
                }

                answers.add(wrongAnswer);
            }
        }

        if (textViewSelection1 != null && textViewSelection2 != null && textViewSelection3 != null && textViewSelection4 != null) {
            textViewSelection1.setText(getResources().getString(R.string.txt_leer, answers.get(0)));
            textViewSelection2.setText(getResources().getString(R.string.txt_leer, answers.get(1)));
            textViewSelection3.setText(getResources().getString(R.string.txt_leer, answers.get(2)));
            textViewSelection4.setText(getResources().getString(R.string.txt_leer, answers.get(3)));
        }
    }

    private void startCountDownTimer() {
        Log.d(TAG, "TAG - MainActivity - startCountDownTimer()");
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                if (textViewTimer != null) {
                    String sTimer = l / 1000 + getResources().getString(R.string.txt_s);
                    textViewTimer.setText(sTimer);
                }
            }

            @Override
            public void onFinish() {
                if (textViewInterimResult != null) {
                    textViewInterimResult.setText(getResources().getString(R.string.txt_done));
                }

                if (buttonPlayAgain != null) {
                    buttonPlayAgain.setVisibility(View.VISIBLE);
                }
            }
        }.start();
    }

    public void chooseAnswer(View view) {
        Log.d(TAG, "TAG - MainActivity - chooseAnswer()");
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            if (textViewInterimResult != null) {
                textViewInterimResult.setText(getResources().getString(R.string.txt_correct));
            }

            score++;
        } else {
            if (textViewInterimResult != null) {
                textViewInterimResult.setText(getResources().getString(R.string.txt_wrong));
            }
        }

        numberOfQuestions++;
        if (textViewScore != null) {
            String sScore = score + getResources().getString(R.string.txt_slash) + numberOfQuestions;
            textViewScore.setText(sScore);
        }

        newQuestion();
    }
}
