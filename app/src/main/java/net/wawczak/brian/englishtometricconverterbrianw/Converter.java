package net.wawczak.brian.englishtometricconverterbrianw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Converter extends AppCompatActivity {

    int id;

    String standardInput;
    String conversionResult;
    String standardSpinnerArray[];
    String metricSpinnerArray[];
    Spinner spinStandard;
    Spinner spinMetric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        TextView converterTitle = findViewById(R.id.idConverterTitle);
        TextView displayConversion = findViewById(R.id.idDisplayConversion);
        EditText inputStandard = findViewById(R.id.idInputStandard);
        Button convert = findViewById(R.id.btnConvert);


        // gets an int from MainActivity.java, determined by the users radio button choice
        Bundle idNum = getIntent().getExtras();
        if (idNum != null) {
            id = idNum.getInt("UserChoice");
        }else{
            id = 0;
        }
        //users MainActivity radioButton choice determines the case.  The appropriate spinner arrays
        //are referenced from strings.xml, and passed to the setSpinner method.
        switch (id){
            case 0:
                converterTitle.setText(getString(R.string.error));
                break;
            case 1:
                //inputStandard.setHint(getString(R.string.tempFahrenheitHint));
                standardSpinnerArray = getResources().getStringArray(R.array.standardTempArray);
                metricSpinnerArray = getResources().getStringArray(R.array.metricTempArray);
                converterTitle.setText(getString(R.string.temp));
                displayConversion.setText(conversionResult);
                setSpinner(standardSpinnerArray, metricSpinnerArray);
                break;
            case 2:
                standardSpinnerArray = getResources().getStringArray(R.array.standardWeightArray);
                metricSpinnerArray = getResources().getStringArray(R.array.metricWeightArray);
                converterTitle.setText(getString(R.string.weight));
                displayConversion.setText(conversionResult);
                setSpinner(standardSpinnerArray, metricSpinnerArray);
                break;
            case 3:
                standardSpinnerArray = getResources().getStringArray(R.array.standardDistanceArray);
                metricSpinnerArray = getResources().getStringArray(R.array.metricDistanceArray);
                converterTitle.setText(getString(R.string.weight));
                displayConversion.setText(conversionResult);
                setSpinner(standardSpinnerArray, metricSpinnerArray);
                converterTitle.setText(getString(R.string.length));
                break;
            case 4:
                standardSpinnerArray = getResources().getStringArray(R.array.standardVolumeArray);
                metricSpinnerArray = getResources().getStringArray(R.array.metricVolumeArray);
                converterTitle.setText(getString(R.string.volume));
                displayConversion.setText(conversionResult);
                setSpinner(standardSpinnerArray, metricSpinnerArray);
                break;

        }


        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }





    //accepts 2 arrays referenced from strings.xml and applies them to the spinners.

    public void setSpinner(String[] stanSpinnerArray, String[] metSpinnerArray){

        spinStandard = findViewById(R.id.idSpinStandard);
        spinMetric = findViewById(R.id.idSpinMetric);

        //creates a spinner adapter, specifies spinner layout, applies string-array to the
        //drop down menu.
        ArrayAdapter<String> standardAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, stanSpinnerArray);
        standardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinStandard.setAdapter(standardAdapter);

        ArrayAdapter<String> metricAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, metSpinnerArray);
        metricAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinMetric.setAdapter(metricAdapter);

    }
}
