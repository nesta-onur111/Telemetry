package com.einstein_automotive.httpwp.telemetry.Fragments;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.einstein_automotive.httpwp.telemetry.DataStorage.Data;
import com.einstein_automotive.httpwp.telemetry.DataStorage.LineChartConfig;
import com.einstein_automotive.httpwp.telemetry.DataStorage.ValueName;
import com.einstein_automotive.httpwp.telemetry.R;
import com.einstein_automotive.httpwp.telemetry.customViews.LineView;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Fabian Naaß on 27.05.2015,
 * for Einstein Mitorsport 2015
 */
public class Telemetry_frag extends Fragment implements LineChart_frag.Communicator2Telemetry_frag{


    FragmentManager fragmentManager;
    private Button btnSave, btnLoad,btnStart,btnAdd, btnRemove,btnStop;
    private CheckBox cbSpeed,cbRPM,cbSteering,cbThrottle,cbBrake,cbGear,cbGForce;
    private RadioButton rbTime, rbDistance;
    private EditText etTime, etDistance;
    private LineView lvSpeed,lvRPM,lvSteering,lvGear,lvThrottle,lvBrake,lvGForce;
    private LineChartConfig lcConfig;
    int count =1;

    private static View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null){
            view =  inflater.inflate(R.layout.fragment_telemetry, container, false);
        }

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getChildFragmentManager();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        btnSave = (Button) getActivity().findViewById(R.id.btnSave);
        btnLoad = (Button) getActivity().findViewById(R.id.btnLoad);
        btnStart = (Button) getActivity().findViewById(R.id.btnStart);
        btnStop = (Button) getActivity().findViewById(R.id.btnStop);
        btnAdd = (Button) getActivity().findViewById(R.id.btnAdd);
        btnRemove = (Button) getActivity().findViewById(R.id.btnRemove);

        rbTime = (RadioButton) getActivity().findViewById(R.id.rbTime);
        rbDistance = (RadioButton) getActivity().findViewById(R.id.rbDistance);
        etTime = (EditText) getActivity().findViewById(R.id.etTime);
        etDistance = (EditText) getActivity().findViewById(R.id.etDistance);

        cbSpeed = (CheckBox) getActivity().findViewById(R.id.cbSpeed);
        cbRPM = (CheckBox) getActivity().findViewById(R.id.cbRPM);
        cbSteering = (CheckBox) getActivity().findViewById(R.id.cbSteering);
        cbThrottle = (CheckBox) getActivity().findViewById(R.id.cbThrottle);
        cbBrake = (CheckBox) getActivity().findViewById(R.id.cbBrake);
        cbGear = (CheckBox) getActivity().findViewById(R.id.cbGear);
        cbGForce = (CheckBox) getActivity().findViewById(R.id.cbGForce);

        lvSpeed = (LineView) getActivity().findViewById(R.id.lvSpeed);
        lvSpeed.setColor(getResources().getColor(R.color.red));
        lvRPM = (LineView) getActivity().findViewById(R.id.lvRPM);
        lvRPM.setColor(getResources().getColor(R.color.blue));
        lvSteering = (LineView) getActivity().findViewById(R.id.lvSteering);
        lvSteering.setColor(getResources().getColor(R.color.green));
        lvGear = (LineView) getActivity().findViewById(R.id.lvGear);
        lvGear.setColor(getResources().getColor(R.color.aqua));
        lvThrottle = (LineView) getActivity().findViewById(R.id.lvThrottle);
        lvThrottle.setColor(getResources().getColor(R.color.yellow));
        lvBrake = (LineView) getActivity().findViewById(R.id.lvBrake);
        lvBrake.setColor(getResources().getColor(R.color.black));
        lvGForce = (LineView) getActivity().findViewById(R.id.lvGForce);
        lvGForce.setColor(getResources().getColor(R.color.orange));

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Fragment f : fragmentManager.getFragments()) {
                    if (f != null) {
                        LineChart_frag frag = (LineChart_frag) f;
                        if (!frag.isRecording() && frag.isFocused())
                            frag.startRecord();
                    }
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Fragment f : fragmentManager.getFragments()) {
                    if (f != null) {
                        LineChart_frag frag = (LineChart_frag) f;
                        if (frag.isRecording() && frag.isFocused())
                            frag.stopRecord();
                    }
                }
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLineChartConfig();
                if (lcConfig.getSize() > 0 && count <= 4) {
                    LineChart_frag temp = new LineChart_frag();
                    temp.setConfig(lcConfig);
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.group, temp, "LineChart" + String.valueOf(count));
                    transaction.commit();
                    count++;
                }

            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LineChart_frag frag = getFocusedFragment();
                if (frag != null) {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.remove(frag);
                    transaction.commit();
                    count--;
                }
            }
        });

//        cbSpeed.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                resetLineChartConfig();
//            }
//        });
//        cbRPM.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                resetLineChartConfig();
//            }
//        });
//        cbBrake.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                resetLineChartConfig();
//            }
//        });
//        cbThrottle.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                resetLineChartConfig();
//            }
//        });
//        cbSteering.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                resetLineChartConfig();
//            }
//        });
//        cbGear.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                resetLineChartConfig();
//            }
//        });
//        cbGForce.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                resetLineChartConfig();
//            }
//        });
//    }
//
//
//    private void resetLineChartConfig(){
//        LineChart_frag frag = getFocusedFragment();
//        if(frag != null) {
//            getLineChartConfig();
//            frag.setConfig(lcConfig);
//        }
    }


    private void getLineChartConfig(){
        lcConfig = new LineChartConfig();
        if (cbSpeed.isChecked())
            lcConfig.add(lvSpeed.getLinePaint(),ValueName.Speed);
        if (cbRPM.isChecked())
            lcConfig.add(lvRPM.getLinePaint(),ValueName.RPM);
        if (cbGear.isChecked())
            lcConfig.add(lvGear.getLinePaint(),ValueName.Gear);
        if (cbThrottle.isChecked())
     //       lcConfig.add(lvThrottle.getLinePaint(),ValueName.Throttle);
        if (cbBrake.isChecked())
       //     lcConfig.add(lvBrake.getLinePaint(),ValueName.Brake);
        if (cbGForce.isChecked())
            lcConfig.add(lvGForce.getLinePaint(),ValueName.GForce);
        if (cbSteering.isChecked())
            lcConfig.add(lvSteering.getLinePaint(),ValueName.Steering);
        if (rbTime.isChecked()){
            lcConfig.setXValue(Integer.parseInt(etTime.getText().toString()), true);
        }else
            lcConfig.setXValue(Integer.parseInt(etTime.getText().toString()), false);

    }

    private LineChart_frag getFocusedFragment(){
        if (fragmentManager.getFragments() != null) {
            for (Fragment f:fragmentManager.getFragments()){
                if (f !=null){
                    LineChart_frag frag = (LineChart_frag) f;
                    if(frag.isFocused()){
                        return frag;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void sendConfig(LineChartConfig config) {

        cbSpeed.setChecked(false);
        cbRPM.setChecked(false);
        cbThrottle.setChecked(false);
        cbBrake.setChecked(false);
        cbGear.setChecked(false);
        cbGForce.setChecked(false);
        cbSteering.setChecked(false);

        for (Map.Entry<ValueName, Paint> lcc: config.getLineConfig().entrySet()) {
            switch (lcc.getKey()){
                case Speed:
                    cbSpeed.setChecked(true);
                    break;
                case RPM:
                    cbRPM.setChecked(true);
                    break;
          /*      case Throttle:
                    cbThrottle.setChecked(true);
                    break;
                case Brake:
                    cbBrake.setChecked(true);
                    break; */
                case Gear:
                    cbGear.setChecked(true);
                    break;
                case GForce:
                    cbGForce.setChecked(true);
                    break;
                case Steering:
                    cbSteering.setChecked(true);
                    break;
            }
        }
        if(config.isTimeSelected()){
            rbTime.setChecked(true);
            rbDistance.setChecked(false);
        }else{
            rbTime.setChecked(false);
            rbDistance.setChecked(true);
        }
        etTime.setText(String.valueOf(config.getTime()));
        etDistance.setText(String.valueOf(config.getDistance()));
    }

    public void updateData(ConcurrentHashMap<ValueName, Data> concurrentHashMap) {
        if (fragmentManager != null){
            if (fragmentManager.getFragments() != null){
                for (Fragment f:fragmentManager.getFragments()){
                    if (f !=null){
                        LineChart_frag frag = (LineChart_frag) f;
                        frag.updateData(concurrentHashMap);
                    }
                }
            }
        }
    }

//    public void updateSpeed(int speed){
//        if (fragmentManager != null) {
//            if (fragmentManager.getFragments() != null) {
//                for (Fragment f : fragmentManager.getFragments()) {
//                    if (f != null) {
//                        LineChart_frag frag = (LineChart_frag) f;
//                        frag.updateSpeed(speed);
//                    }
//                }
//            }
//        }
//    }
//
//    public void updateRPM(int rpm){
//        if (fragmentManager != null) {
//            if (fragmentManager.getFragments() != null) {
//                for (Fragment f : fragmentManager.getFragments()) {
//                    if (f != null) {
//                        LineChart_frag frag = (LineChart_frag) f;
//                        frag.updateRPM(rpm);
//                    }
//                }
//            }
//        }
//    }
//
//    public void updateGear(int gear){
//        if (fragmentManager != null) {
//            if (fragmentManager.getFragments() != null) {
//                for (Fragment f : fragmentManager.getFragments()) {
//                    if (f != null) {
//                        LineChart_frag frag = (LineChart_frag) f;
//                        frag.updateGear(gear);
//                    }
//                }
//            }
//        }
//    }
//
//    public void updateThrottle(int throttle){
//        if (fragmentManager != null) {
//            if (fragmentManager.getFragments() != null) {
//                for (Fragment f : fragmentManager.getFragments()) {
//                    if (f != null) {
//                        LineChart_frag frag = (LineChart_frag) f;
//                        frag.updateThrottle(throttle);
//                    }
//                }
//            }
//        }
//    }
//
//    public void updateBrake(int brake){
//        if (fragmentManager != null) {
//            if (fragmentManager.getFragments() != null) {
//                for (Fragment f : fragmentManager.getFragments()) {
//                    if (f != null) {
//                        LineChart_frag frag = (LineChart_frag) f;
//                        frag.updateBrake(brake);
//                    }
//                }
//            }
//        }
//    }
//
//    public void updateSteering(int steering) {
//        if (fragmentManager != null) {
//            if (fragmentManager.getFragments() != null) {
//                for (Fragment f : fragmentManager.getFragments()) {
//                    if (f != null) {
//                        LineChart_frag frag = (LineChart_frag) f;
//                        frag.updateSteering(steering);
//                    }
//                }
//            }
//        }
//    }
//
//    public void updateGForce(int gforce){
//        if(fragmentManager != null){
//            if (fragmentManager.getFragments() != null) {
//                for (Fragment f:fragmentManager.getFragments()){
//                    if (f !=null){
//                        LineChart_frag frag = (LineChart_frag) f;
//                        frag.updateGForce(gforce);
//                    }
//                }
//            }
//        }
//    }
}
