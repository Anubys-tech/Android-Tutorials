package com.anubys.example.dynamicallygridlayoutdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 17.02.2020 */


public class MainActivity extends AppCompatActivity implements OnToggledListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.gridLayout)
    GridLayout gridLayout;

    private MyView[] myViews;
    private Unbinder unbinder;


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "TAG - MainActivity - onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        int numOfCol = gridLayout != null ? gridLayout.getColumnCount() : 0;
        int numOfRow = gridLayout != null ? gridLayout.getRowCount() : 0;

        myViews = new MyView[numOfCol * numOfRow];

        for (int yPos = 0; yPos < numOfRow; yPos++) {
            for (int xPos = 0; xPos < numOfCol; xPos++) {
                MyView tView = new MyView(this, xPos, yPos);
                tView.setOnToggledListener(this);
                myViews[yPos * numOfCol + xPos] = tView;
                gridLayout.addView(tView);
            }
        }

        //TODO auslagern
        if (gridLayout != null) {
            gridLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
                @Override
                public void onGlobalLayout() {
                    final int MARGIN = 5;

                    int pWidth = gridLayout.getWidth();
                    int pHeight = gridLayout.getHeight();
                    int numOfCol = gridLayout.getColumnCount();
                    int numOfRow = gridLayout.getRowCount();
                    int w = pWidth / numOfCol;
                    int h = pHeight / numOfRow;

                    for (int yPos = 0; yPos < numOfRow; yPos++) {
                        for (int xPos = 0; xPos < numOfCol; xPos++) {
                            GridLayout.LayoutParams params = (GridLayout.LayoutParams) myViews[yPos*numOfCol + xPos].getLayoutParams();
                            params.width = w - 2*MARGIN;
                            params.height = h - 2*MARGIN;
                            params.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);
                            myViews[yPos*numOfCol + xPos].setLayoutParams(params);
                        }
                    }
                }});
        }
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
    //*                 O V E R R I D E                  *
    //* ************************************************ *
    @Override
    public void OnToggled(MyView v, boolean touchOn) {
        Log.d(TAG, "TAG - MainActivity - OnToggled()");

        //get the id string
        String idString = v.getIdX() + ":" + v.getIdY();

        Toast.makeText(MainActivity.this, getResources().getString(R.string.txt_toggled) + idString + "\n" + touchOn, Toast.LENGTH_SHORT).show();
    }
}
