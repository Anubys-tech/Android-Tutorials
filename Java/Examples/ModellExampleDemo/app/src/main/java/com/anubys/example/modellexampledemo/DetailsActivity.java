package com.anubys.example.modellexampledemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 29.03.2020 */


public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = DetailsActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.tv_user)
    TextView textViewUser ;
    @Nullable
    @BindView(R.id.tv_name)
    TextView textViewDescription;
    @Nullable
    @BindView(R.id.tv_target)
    TextView textViewTarget ;
    @Nullable
    @BindView(R.id.tv_text)
    TextView textViewInfo;

    private Unbinder unbinder;


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "TAG - DetailsActivity - onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        unbinder = ButterKnife.bind(this);

        loadTravelTipDataSource();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "TAG - DetailsActivity - onDestroy()");
        super.onDestroy();

        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private void loadTravelTipDataSource() {
        Log.d(TAG, "TAG - DetailsActivity - loadTravelTipDataSource()");

        Intent intent = getIntent();
        String sId = intent.getStringExtra("id");
        assert sId != null;
        int id = Integer.parseInt(sId);

        TravelTipDataSource travelTipDataSource = new TravelTipDataSource();
        ArrayList<ModelTip> modelTipArrayList = travelTipDataSource.getData();

        if (textViewUser != null && textViewDescription != null && textViewTarget != null && textViewInfo != null) {
            String sUser = getResources().getString(R.string.txt_user) + " " + modelTipArrayList.get(id).getUser();
            textViewUser.setText(sUser);
            String sDescription = getResources().getString(R.string.txt_description) + " " + modelTipArrayList.get(id).getDescription();
            textViewDescription.setText(sDescription);
            String sTarget = getResources().getString(R.string.txt_target) + " " + modelTipArrayList.get(id).getTarget();
            textViewTarget.setText(sTarget);
            String sInfo = getResources().getString(R.string.txt_info) + " " + modelTipArrayList.get(id).getInfo();
            textViewInfo.setText(sInfo);
        }
    }
}
