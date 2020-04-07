package com.anubys.example.questiongame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 28.03.2020 */

//TODO Bessere Sympbole für ImageButtons finden
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String RIGHT = "RIGHT";
    private static final String WRONG = "WRONG";

    @Nullable
    @BindView(R.id.tv_points)
    TextView textViewShowPoints;
    @Nullable
    @BindView(R.id.tv_question)
    TextView textViewQuestion;
    @Nullable
    @BindView(R.id.ib_wrong)
    ImageButton imageButtonWrong;
    @Nullable
    @BindView(R.id.ib_right)
    ImageButton imageButtonRight;

    private Unbinder unbinder;
    private ScoreCounter scoreCounter;
    private Questions questions;


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "TAG - MainActivity - onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        initScoreCounter();
        initQuestion();

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
    //*           I N I T I A L I Z A T I O N            *
    //* ************************************************ *
    private void initScoreCounter() {
        Log.d(TAG, "TAG - MainActivity - initScoreCounter()");

        if (textViewShowPoints != null) {
            scoreCounter = new ScoreCounter();
            scoreCounter.setPoints();

            String sPoints = getResources().getString(R.string.txt_points) + " " + scoreCounter.getPoints();
            textViewShowPoints.setText(sPoints);
        }
    }

    private void initQuestion() {
        Log.d(TAG, "TAG - MainActivity - initQuestion()");

        if (textViewQuestion != null) {
            questions = new Questions();

            String sQuestion = getResources().getString(R.string.txt_points) + " " + questions.showTask();
            textViewQuestion.setText(sQuestion);
        }
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private void setListener() {
        Log.d(TAG, "TAG - MainActivity - setListener()");

        if (imageButtonWrong != null) {
            imageButtonWrong.setOnClickListener(view -> wrong());
        }

        if (imageButtonRight != null) {
            imageButtonRight.setOnClickListener(view -> right());
        }
    }

    private void right() {
        Log.d(TAG, "TAG - MainActivity - right()");

        logic(RIGHT);
    }

    private void wrong() {
        Log.d(TAG, "TAG - MainActivity - wrong()");

        logic(WRONG);
    }

    public void logic(String type) {
        Log.d(TAG, "TAG - MainActivity - logic()");
        checkPointLevel();

        if (type.equalsIgnoreCase(RIGHT)) {
            scoreCounter.right();
        } else if (type.equalsIgnoreCase(WRONG)) {
            scoreCounter.wrong();
        }
    }

    public void checkPointLevel() {
        Log.d(TAG, "TAG - MainActivity - checkPointLevel()");

        if (scoreCounter.getPoints() < 0) {
            if (textViewShowPoints != null) {
                String sInfo = getResources().getString(R.string.txt_you_have_less_than_0_points) + " " + scoreCounter.getPoints();
                textViewShowPoints.setText(sInfo);
            }
            if (imageButtonRight != null) {
                imageButtonRight.setClickable(false);
            }
            if (imageButtonWrong != null) {
                imageButtonWrong.setClickable(false);
            }
        } else {
            if (textViewShowPoints != null) {
                String sPoints = getResources().getString(R.string.txt_question) + " " + scoreCounter.getPoints();
                textViewShowPoints.setText(sPoints);
            }
            if (textViewQuestion != null) {
                textViewQuestion.setText(questions.showTask());
            }
        }
    }
}
