package in.codingtimes.spinnerscale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> mylist;
    ImageView imageView;
    Integer currentIndex = 0;
    Spinner spinner;
    String[] scaletype;
    Boolean eventTriggered = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button undo = findViewById(R.id.undo);
        Button redo = findViewById(R.id.redo);

        undo.setOnClickListener(this);
        redo.setOnClickListener(this);

        spinner = findViewById(R.id.spinner);

        scaletype = getResources().getStringArray(R.array.scaletype);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, scaletype);
        spinner.setAdapter(arrayAdapter);

        imageView = findViewById(R.id.imageView);

        mylist = new ArrayList<String>();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(eventTriggered.equals(true)){
                    if (mylist.size() >= 5) {
                        mylist.remove(0);
                    }
                    mylist.add(spinner.getSelectedItem().toString());
                }

                setScaleType(spinner.getSelectedItem().toString());

                if(eventTriggered.equals(false)) eventTriggered = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void setScaleType(String scaleType){
        switch(scaleType){
            case "CENTER":
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
            case "CENTER_CROP":
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                break;
            case "CENTER_INSIDE":
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                break;
            case "FIT_CENTER":
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;
            case "FIT_START":
                imageView.setScaleType(ImageView.ScaleType.FIT_START);
                break;
            case "FIT_END":
                imageView.setScaleType(ImageView.ScaleType.FIT_END);
                break;
            case "FIT_XY":
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                break;
            case "MATRIX":
                imageView.setScaleType(ImageView.ScaleType.MATRIX);
                break;
        }
    }

    @Override
    public void onClick(View v) {

        if(mylist.isEmpty()){
            return;
        }

        currentIndex= mylist.indexOf(imageView.getScaleType().name());

        switch(v.getId()){
            case R.id.undo:
                if(currentIndex > 0)
                    currentIndex--;
                break;
            case R.id.redo:
                if(currentIndex < mylist.size()-1)
                    currentIndex++;
                break;
        }
        eventTriggered = false;
        spinner.setSelection(Arrays.asList(scaletype).indexOf(mylist.get(currentIndex)));


    }
}