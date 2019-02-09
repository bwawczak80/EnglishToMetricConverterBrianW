package net.wawczak.brian.englishtometricconverterbrianw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button next;
    TextView txtTable;
    RadioButton rbTemp;
    RadioButton rbWeight;
    RadioButton rbLength;
    RadioButton rbVolume;
    RadioGroup rbGroup;

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
        rbGroup = findViewById(R.id.idRadGroup);

        //Changes conversion chart text
        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbTemp:
                        txtTable.setText(getString(R.string.tempChart));
                        break;
                    case R.id.rbLength:
                        txtTable.setText(getString(R.string.lengthChart));
                        break;
                    case R.id.rbWeight:
                        txtTable.setText(getString(R.string.weightChart));
                        break;
                    case R.id.rbVolume:
                        txtTable.setText(getString(R.string.volumeChart));
                        break;
                }
            }
        });

        // creates a radio button identifier and passes it to the new intent method
        // this is used to determine which conversion to apply
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbTemp.isChecked()){
                    newIntent(1);
                }else if (rbWeight.isChecked()){
                    newIntent(2);
                }else if (rbLength.isChecked()){
                    newIntent(3);
                }else if (rbVolume.isChecked()){
                    newIntent(4);
                }
            }
        });

    }

    // creates a new intent and passes the radio button identifier
   public void newIntent(int id){
       Intent conversion = new Intent(MainActivity.this, Converter.class);
       conversion.putExtra("UserChoice",id);
       startActivity(conversion);
    }

}
