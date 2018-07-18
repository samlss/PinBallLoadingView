package com.iigo.pinballloadingview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.iigo.library.PinBallLoadingView;

public class MainActivity extends Activity {

    private PinBallLoadingView pinBallLoadingView1;
    private PinBallLoadingView pinBallLoadingView2;
    private PinBallLoadingView pinBallLoadingView3;

    private PinBallLoadingView pinBallLoadingView4;
    private PinBallLoadingView pinBallLoadingView5;
    private PinBallLoadingView pinBallLoadingView6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pinBallLoadingView1 = findViewById(R.id.pblv_loading1);
        pinBallLoadingView2 = findViewById(R.id.pblv_loading2);
        pinBallLoadingView3 = findViewById(R.id.pblv_loading3);

        pinBallLoadingView4 = findViewById(R.id.pblv_loading4);
        pinBallLoadingView5 = findViewById(R.id.pblv_loading5);
        pinBallLoadingView6 = findViewById(R.id.pblv_loading6);
    }

    public void onStop(View view) {
        Log.e("TAG", "on stop..");
        pinBallLoadingView1.stop();
        pinBallLoadingView2.stop();
        pinBallLoadingView3.stop();

        pinBallLoadingView4.stop();
        pinBallLoadingView5.stop();
        pinBallLoadingView6.stop();
    }

    public void onStart(View view) {
        Log.e("TAG", "on start..");

        pinBallLoadingView1.start();
        pinBallLoadingView2.start();
        pinBallLoadingView3.start();

        pinBallLoadingView4.start();
        pinBallLoadingView5.start();
        pinBallLoadingView6.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //大小改变
        //size changed
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) pinBallLoadingView1.getLayoutParams();
        params.width = 50;
        params.height = 50;
        pinBallLoadingView1.setLayoutParams(params);

        return super.onKeyDown(keyCode, event);
    }
}
