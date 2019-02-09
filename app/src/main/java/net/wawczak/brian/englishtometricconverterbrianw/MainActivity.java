package net.wawczak.brian.englishtometricconverterbrianw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tableTitle;
    Button next;
    TextView txtTable;
    RadioButton rbTemp;
    RadioButton rbWeight;
    RadioButton rbLength;
    RadioButton rbVolume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        next = findViewById(R.id.btnNext);
        txtTable = findViewById(R.id.txtTable);
        rbTemp = findViewById(R.id.rbTemp);
        rbWeight = findViewById(R.id.rbWeight);
        rbLength= findViewById(R.id.rbLength);
        rbVolume= findViewById(R.id.rbVolume);

        rbTemp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                txtTable.setText(getString(R.string.tempChart));
            }
        });

        rbWeight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                txtTable.setText(getString(R.string.weightChart));
            }
        });

        rbLength.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                txtTable.setText(getString(R.string.lengthChart));
            }
        });

        rbVolume.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                txtTable.setText(getString(R.string.volumeChart));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbTemp.isChecked()){

                }else if (rbWeight.isChecked()){

                }else if (rbLength.isChecked()){

                }else if (rbVolume.isChecked()){

                }
            }
        });


    }
}
