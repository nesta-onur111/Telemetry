package com.einstein_automotive.httpwp.telemetry.Fragments;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.einstein_automotive.httpwp.telemetry.DataStorage.Data;

/**
 * Created by Fabian Naaß on 05.08.2015,
 * for Einstein Mitorsport 2015
 */
public class CustomTextView implements View.OnClickListener {

    private TextView textView;
    private EditText editText;
    private String name;
    private boolean editable;
    private Data[] data;

    public CustomTextView(String name, TextView textView, EditText editText, boolean editable, Data[] data) {
        this.name = name;
        this.textView = textView;
        this.editText = editText;

        this.textView.setOnClickListener(this);
//        this.editText.setOnClickListener(this);
        this.editable = editable;
        this.data = data;
    }



    public EditText getEditText() {
        return editText;
    }

    public String getName() {
        return name;
    }

    public TextView getTextView() {
        return textView;
    }

    public boolean editValue(float value) {
        return editable;
    }

    public void setDatum(Data datum) {
        if (data.length > 1){
            if (data[0].getUnit().equals(datum.getUnit())){
                data[0] = datum;
                updateView();
            }else{
                data[1] = datum;
                updateView();
            }
        }else{
            if (data[0].getUnit().equals(datum.getUnit())){
                data[0] = datum;
                updateView();
            }
        }
    }

    private void updateView(){
        // get currentUnit
        String unit = (String) textView.getText();

        // compare to all in data[], if equals get value and display it
        for (int i = 0; i < data.length; i++) {
            if (unit.equals(data[i].getUnit().toString())) {
                final int x = i;
                editText.post(new Runnable() {
                    @Override
                    public void run() {
                        if (data[x].getValue() >= data[x].getMax() || data[x].getValue() >= data[x].getCritical())
                            editText.setTextColor(Color.RED);
                        else if (data[x].getValue() >= data[x].getMin())
                            editText.setTextColor(Color.BLUE);
                        else
                            editText.setTextColor(Color.BLACK);
                        editText.setText(data[x].getStringValue());
                    }
                });
            }
        }
    }


    public void changeUnit() {
        //get Actual Unit
        String actualUnit = textView.getText().toString();

        if(data.length >1) {
            if (actualUnit.equals(data[0].getUnit().toString())) {
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(data[1].getUnit().toString());
                    }
                });
                editText.post(new Runnable() {
                    @Override
                    public void run() {
                        editText.setText(data[1].getStringValue());
                    }
                });
            } else {
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(data[0].getUnit().toString());
                    }
                });
                editText.post(new Runnable() {
                    @Override
                    public void run() {
                        editText.setText(data[0].getStringValue());
                    }
                });
            }
        }

    }

    @Override
    public void onClick(View v) {
        if (v == textView && !editable && data.length > 1)
            changeUnit();
    }
}
