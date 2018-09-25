package com.example.bauwensn.appollutiontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bauwensn.appollutiontest.models.api.PollutionInfo;
import com.example.bauwensn.appollutiontest.webapi.RequestAPI;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements RequestAPI.IRequestEvent {

    private SeekBar seekbar;
    private TextView progressTextView, resultTextView;
    private Button btnSearch;
    private RequestAPI requestAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeElements();
        seekBarListener();


    }

    public void initializeElements() {
        seekbar = findViewById(R.id.sb_km);
        seekbar.setMax(25);
        seekbar.setProgress(1);

        progressTextView = findViewById(R.id.tv_kmchoice);
        btnSearch = findViewById(R.id.search_btn);
        resultTextView = findViewById(R.id.result_tv);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayResult();
            }
        });
    }

    public void seekBarListener() {
        // perform seek bar change listener event used for getting the progress value
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                progressTextView.setText("Your current progress is " + progressChangedValue);
                Toast.makeText(MainActivity.this, "Seek bar progress is :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void displayResult() {
        requestAPI = new RequestAPI();
        requestAPI.setCallback(this);
        requestAPI.execute();
    }

    @Override
    public void onRequestResult(JSONObject data) {

        Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show();
    }
}
