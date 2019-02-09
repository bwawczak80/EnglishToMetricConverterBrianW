package net.wawczak.brian.englishtometricconverterbrianw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.DecimalFormat;

public class Converter extends AppCompatActivity {


    double standardInput;
    String standardSpinnerArray[];
    String metricSpinnerArray[];
    String roundedValue;
    String standardStr;
    String metricStr;
    double convertedValue;
    int id;

    Spinner spinStandard;
    Spinner spinMetric;

    DecimalFormat roundConversion = new DecimalFormat("###,###.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        TextView converterTitle = findViewById(R.id.idConverterTitle);
        final TextView displayConversion = findViewById(R.id.idDisplayConversion);
        final EditText inputStandard = findViewById(R.id.idInputStandard);
        Button convert = findViewById(R.id.btnConvert);


        // gets an int from MainActivity.java, determined by the users radio button choice
        Bundle idNum = getIntent().getExtras();
        if (idNum != null) {
            id = idNum.getInt("UserChoice");
        } else {
            id = 0;
        }
        //users MainActivity radioButton choice determines the case.  The appropriate spinner arrays
        //are referenced from strings.xml, and passed to the setSpinner method.
        //Title text is set
        switch (id) {
            case 0:
                converterTitle.setText(getString(R.string.error));
                break;
            case 1:
                standardSpinnerArray = getResources().getStringArray(R.array.standardTempArray);
                metricSpinnerArray = getResources().getStringArray(R.array.metricTempArray);
                converterTitle.setText(getString(R.string.temp));
                setSpinner(standardSpinnerArray, metricSpinnerArray);
                break;
            case 2:
                standardSpinnerArray = getResources().getStringArray(R.array.standardWeightArray);
                metricSpinnerArray = getResources().getStringArray(R.array.metricWeightArray);
                converterTitle.setText(getString(R.string.weight));
                setSpinner(standardSpinnerArray, metricSpinnerArray);
                break;
            case 3:
                standardSpinnerArray = getResources().getStringArray(R.array.standardDistanceArray);
                metricSpinnerArray = getResources().getStringArray(R.array.metricDistanceArray);
                setSpinner(standardSpinnerArray, metricSpinnerArray);
                converterTitle.setText(getString(R.string.length));
                break;
            case 4:
                standardSpinnerArray = getResources().getStringArray(R.array.standardVolumeArray);
                metricSpinnerArray = getResources().getStringArray(R.array.metricVolumeArray);
                converterTitle.setText(getString(R.string.volume));
                setSpinner(standardSpinnerArray, metricSpinnerArray);
                break;

        }

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //get user input and parse to a Double
                try {
                    standardInput = Double.parseDouble(inputStandard.getText().toString());

                    // passes spinner data and user input to appropriate conversion method.

                    standardStr = spinStandard.getSelectedItem().toString();
                    metricStr = spinMetric.getSelectedItem().toString();

                    switch (id) {
                        case 1:
                            convertedValue = tempConversion(standardStr, standardInput);
                            break;
                        case 2:
                            convertedValue = weightConversion(standardStr, metricStr, standardInput);
                            break;
                        case 3:
                            convertedValue = distanceConversion(standardStr, metricStr,standardInput);
                            break;
                        case 4:
                            convertedValue = volumeConversion(standardStr, metricStr, standardInput);
                            break;

                    }
                    // rounds the converted value and sets the textView
                    roundedValue = roundConversion.format(convertedValue);
                    displayConversion.setText(String.format("%s %s = %s %s", standardInput, standardStr, roundedValue, metricStr));
                } catch (NumberFormatException e) {
                    displayConversion.setText(getString(R.string.errMsg));
                }

            }
        });


    }

    //accepts 2 arrays referenced from strings.xml and applies them to the spinners.
    public void setSpinner(String[] stanSpinnerArray, String[] metSpinnerArray) {
        spinStandard = findViewById(R.id.idSpinStandard);
        spinMetric = findViewById(R.id.idSpinMetric);
        //creates a spinner adapter, specifies spinner layout, applies string-array to the
        //standard measurement drop down menu.
        ArrayAdapter<String> standardAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, stanSpinnerArray);
        standardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinStandard.setAdapter(standardAdapter);
        //creates a spinner adapter, specifies spinner layout, applies string-array to the
        //metric measurement drop down menu.
        ArrayAdapter<String> metricAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, metSpinnerArray);
        metricAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinMetric.setAdapter(metricAdapter);

    }

    //converts temps and returns converted value
    public double tempConversion(String selector, double standardDegrees) {
        double convertedTemp = 0;
        if (selector.equals("Fahrenheit")) { //Fahrenheit
            convertedTemp = (standardDegrees - 32) * .5556;
        } else if (selector.equals("Kelvin")) { //Kelvin
            convertedTemp = (standardDegrees - 273.15);

        }
        return convertedTemp;
    }

    //converts weight and returns converted value
    public double weightConversion(String standardSelector, String metricSelector, double standardWeight) {
        double convertedWeight = 0;

        switch(standardSelector) {
            case "Pounds":
                if (metricSelector.equals("Grams")){ //grams
                    convertedWeight = standardWeight * 453.592;
                }else if (metricSelector.equals("Kilograms")){ //kilograms
                    convertedWeight = standardWeight * .453592;
                }
                break;
            case "Ounces":
                if (metricSelector.equals("Grams")){
                    convertedWeight = standardWeight * 28.3495;
                }else if (metricSelector.equals("Kilograms")){
                    convertedWeight = standardWeight * .0283495;
                }
                break;

        }

        return convertedWeight;
    }

    //converts distance and returns converted value
    public double distanceConversion(String standardSelector, String metricSelector, double standardDistance){
        double convertedWeight = 0;
        switch (standardSelector) {

            case "Feet":
                switch (metricSelector){
                    case "Meters":
                        convertedWeight = standardDistance * .3048;
                        break;
                    case "Centimeters":
                        convertedWeight = standardDistance * 30.48;
                        break;
                    case "Millimeters":
                        convertedWeight = standardDistance * 304.8;
                        break;
                    case "Kilometers":
                        convertedWeight = standardDistance * 0.0003048;
                        break;
                }
                break;

            case "Inches":
                switch (metricSelector){
                    case "Meters":
                        convertedWeight = standardDistance * .0254;
                        break;
                    case "Centimeters":
                        convertedWeight = standardDistance * 2.54;
                        break;
                    case "Millimeters":
                        convertedWeight = standardDistance * 25.4;
                        break;
                    case "Kilometers":
                        convertedWeight = standardDistance * 0.0000254;
                        break;
                }
                break;

            case "Yards":
                switch (metricSelector){
                    case "Meters":
                        convertedWeight = standardDistance * .9144;
                        break;
                    case "Centimeters":
                        convertedWeight = standardDistance * 91.44;
                        break;
                    case "Millimeters":
                        convertedWeight = standardDistance * 914.4;
                        break;
                    case "Kilometers":
                        convertedWeight = standardDistance * .0009144;
                        break;
                }
                break;

            case "Miles": //miles
                switch (metricSelector){
                    case "Meters": // meters
                        convertedWeight = standardDistance * 1609.34;
                        break;
                    case "Centimeters": //centimeters
                        convertedWeight = standardDistance * 160934;
                        break;
                    case "Millimeters": //millimeters
                        convertedWeight = standardDistance * 1609340;
                        break;
                    case "Kilometers": // kilometers
                        convertedWeight = standardDistance * 1.60934;
                        break;
                }
                break;
        }
        return convertedWeight;
    }

    //converts volume and returns converted value
    public double volumeConversion(String standardSelector, String metricSelector, double standardVolume){
        double convertedVolume = 0;
        switch(standardSelector){
            case "Ounces":
                if (metricSelector.equals("Millilitres")){
                    convertedVolume = standardVolume * 29.5735;
                }else if (metricSelector.equals("Liters")){
                    convertedVolume = standardVolume * .0295735;
                }break;
            case "Gallons":
                if (metricSelector.equals("Millilitres")){
                    convertedVolume = standardVolume * 3785.41;
                }else if (metricSelector.equals("Liters")){
                    convertedVolume = standardVolume * 3.78541;
                }break;
        }
        return convertedVolume;
    }


}
