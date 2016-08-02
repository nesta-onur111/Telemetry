package com.einstein_automotive.httpwp.telemetry.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.einstein_automotive.httpwp.telemetry.R;

/**
 * Created by Fabian Naaß on 25.03.2016,
 * for Einstein Motorsport 2016
 */
public class Settings_frag extends Fragment{
    static View view;

    private EditText etUpShiftTime, etDownShiftTime, etClutchOpenTime, etGearCutActive, etDownShiftSleep,
                    etDashboard, etTelemetrie, etDDSC, etMainRelay, etFuelPump, etFAN, etShifer, etMaxTemp, etMinTemp;
    private Button btnSendTime, btnSendTemp, btnSendCurrent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewContent();
    }

    private void getViewContent() {
        etClutchOpenTime = (EditText) getActivity().findViewById(R.id.et_settings_ClutchOpen);
        etDashboard = (EditText) getActivity().findViewById(R.id.et_settings_dashboard);
        etDDSC = (EditText) getActivity().findViewById(R.id.et_settings_DDSC);
        etDownShiftTime = (EditText) getActivity().findViewById(R.id.et_settings_DownShift);
        etFAN = (EditText) getActivity().findViewById(R.id.et_settings_fan);
        etFuelPump = (EditText) getActivity().findViewById(R.id.et_settings_fuelPump);
        etGearCutActive = (EditText) getActivity().findViewById(R.id.et_settings_gearCutOpen);
        etMainRelay = (EditText) getActivity().findViewById(R.id.et_settings_mainRelay);
        etMaxTemp = (EditText) getActivity().findViewById(R.id.et_settings_MaxTemp);
        etMinTemp = (EditText) getActivity().findViewById(R.id.et_settings_minTemp);
        etShifer = (EditText) getActivity().findViewById(R.id.et_settings_shifter);
        etDownShiftSleep = (EditText) getActivity().findViewById(R.id.et_settings_sleepDownShift);
        etTelemetrie = (EditText) getActivity().findViewById(R.id.et_settings_Telemetrie);
        etUpShiftTime = (EditText) getActivity().findViewById(R.id.et_settings_UpShift);

      /*  btnSendCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnSendTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnSendTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    private void sendData(){

    }
}
