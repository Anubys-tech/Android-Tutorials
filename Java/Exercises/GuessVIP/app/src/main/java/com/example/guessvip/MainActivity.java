package com.example.guessvip;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 20.03.2020 */


//TODO Zähler einbaun und bei ca. 12 Fragen, das Ergebnis anzeigen. [richtig/gesamt]
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.iv_picture)
    ImageView imageViewPicture;
    @Nullable
    @BindView(R.id.btn_1)
    Button button1;
    @Nullable
    @BindView(R.id.btn_2)
    Button button2;
    @Nullable
    @BindView(R.id.btn_3)
    Button button3;
    @Nullable
    @BindView(R.id.btn_4)
    Button button4;

    private Unbinder unbinder;
    private ArrayList<String> celebURLs = new ArrayList<>();
    private ArrayList<String> celebNames = new ArrayList<>();
    private String[] answers = new String[4];
    private int locationOfCorrectAnswer = 0;
    private int chosenCeleb = 0;


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "TAG - MainActivity - onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        initTask();
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

        if (button1 != null) {
            button1.setOnClickListener(this::onClickListenerButton);
        }

        if (button2 != null) {
            button2.setOnClickListener(this::onClickListenerButton);
        }

        if (button3 != null) {
            button3.setOnClickListener(this::onClickListenerButton);
        }

        if (button4 != null) {
            button4.setOnClickListener(this::onClickListenerButton);
        }
    }

    private void onClickListenerButton(View view) {
        Log.d(TAG, "TAG - MainActivity - onClickListenerButton()");
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.txt_correct), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.txt_wrong) + celebNames.get(chosenCeleb), Toast.LENGTH_SHORT).show();
        }

        newQuestion();
    }

    private void initTask() {
        Log.d(TAG, "TAG - MainActivity - initTask()");
        try {
            DownloadTask task = new DownloadTask(getResources());
            String result = task.execute("http://www.posh24.se/kandisar").get();
            String[] splitResult = result.split("<div class=\"listedArticles\">");

            Pattern searchImage = Pattern.compile("img src=\"(.*?)\"");
            Matcher findImage = searchImage.matcher(splitResult[0]);

            while (findImage.find()) {
                celebURLs.add(findImage.group(1));
            }

            searchImage = Pattern.compile("alt=\"(.*?)\"");
            findImage = searchImage.matcher(splitResult[0]);

            while (findImage.find()) {
                celebNames.add(findImage.group(1));
            }

            newQuestion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newQuestion() {
        Log.d(TAG, "TAG - MainActivity - newQuestion()");
        try {
            Random random = new Random();
            chosenCeleb = random.nextInt(celebURLs.size());
            locationOfCorrectAnswer = random.nextInt(4);

            ImageDownloader imageTask = new ImageDownloader();
            if (imageViewPicture != null) {
                imageViewPicture.setImageBitmap(imageTask.execute(celebURLs.get(chosenCeleb)).get());
            }

            for (int i = 0; i < 4; i++) {
                int incorrectAnswerLocation;

                if (i == locationOfCorrectAnswer) {
                    answers[i] = celebNames.get(chosenCeleb);
                } else {
                    incorrectAnswerLocation = random.nextInt(celebURLs.size());

                    while (incorrectAnswerLocation == chosenCeleb) {
                        incorrectAnswerLocation = random.nextInt(celebURLs.size());
                    }

                    answers[i] = celebNames.get(incorrectAnswerLocation);
                }
            }

            if (button1 != null) {
                button1.setText(answers[0]);
            }

            if (button2 != null) {
                button2.setText(answers[1]);
            }

            if (button3 != null) {
                button3.setText(answers[2]);
            }

            if (button4 != null) {
                button4.setText(answers[3]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
