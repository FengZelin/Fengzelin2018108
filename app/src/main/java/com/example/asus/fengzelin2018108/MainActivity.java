package com.example.asus.fengzelin2018108;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private WaveView wv;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wv=findViewById(R.id.wv);
        image=findViewById(R.id.image);

        WaveView.OnViewListener listener=new WaveView.OnViewListener() {
            @Override
            public void onChanged(float y) {
                RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) image.getLayoutParams();

                layoutParams.setMargins(0,0,0,(int)y);
                image.setLayoutParams(layoutParams);
            }
        };
        wv.setListener(listener);
    }
}
