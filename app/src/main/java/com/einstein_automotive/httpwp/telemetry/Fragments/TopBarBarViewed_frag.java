package com.einstein_automotive.httpwp.telemetry.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.einstein_automotive.httpwp.telemetry.DataStorage.Data;
import com.einstein_automotive.httpwp.telemetry.DataStorage.ValueName;
import com.einstein_automotive.httpwp.telemetry.Fragments.Communicators.CommunicatorStop;
import com.einstein_automotive.httpwp.telemetry.R;
import com.einstein_automotive.httpwp.telemetry.customViews.BarElement;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Fabian Naaß on 27.05.2015,
 * for Einstein Mitorsport 2015
 */
public class TopBarBarViewed_frag extends Fragment {

    BarElement barMotorTemp,barReturnTemp,barOilTemp,barLambdaTemp,barOilPressure, barWaterPressure,barFuelPressure, barBatteryConsumption;
    ImageButton btnEmergencyStop;
    CommunicatorStop comm;
boolean a = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topbarbarviewed, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm = (CommunicatorStop) getActivity();
        barMotorTemp = (BarElement) getActivity().findViewById(R.id.bvMotor);

        barReturnTemp = (BarElement) getActivity().findViewById(R.id.bvReturn);
        barOilTemp = (BarElement) getActivity().findViewById(R.id.bvOil);
        barLambdaTemp = (BarElement) getActivity().findViewById(R.id.bvLambda);
        barFuelPressure = (BarElement) getActivity().findViewById(R.id.bvFuelPressure);
        barOilPressure = (BarElement) getActivity().findViewById(R.id.bvOilPressure);
        barWaterPressure = (BarElement) getActivity().findViewById(R.id.bvWaterPressure);
        barBatteryConsumption = (BarElement) getActivity().findViewById(R.id.bvBatteryConsumption);


        btnEmergencyStop = (ImageButton) getActivity().findViewById(R.id.btn_TopBar_EmergencyStop);
        btnEmergencyStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comm.emergencyStop(true);
            }
        });
        barMotorTemp.configure("°C", 0, 120, 20, 95);
        barReturnTemp.configure("°C", 0, 120, 0, 90);
        barOilTemp.configure("°C",0,120,20,70);
        barLambdaTemp.configure("°C", 0, 120, 0, 110);
        barFuelPressure.configure("bar",0,10,2,8);
        barOilPressure.configure("bar",0,10,2,8);
        barWaterPressure.configure("bar",0,10,2,8);
        barBatteryConsumption.configure("V",0,16,0,11);

        a = true;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setMotorTemp(float temp){
        if (barMotorTemp != null){
            barMotorTemp.setValue(temp);
            barMotorTemp.postInvalidate();
        }
    }

    public void setReturnTemp(float temp){
        if (barReturnTemp != null){
            barReturnTemp.setValue(temp);
            barReturnTemp.postInvalidate();
        }
    }
    public void setOilTemp(float temp){
        if (barOilTemp != null){
            barOilTemp.setValue(temp);
            barOilTemp.postInvalidate();
        }
    }
    public void setFuelPressure(float pressure){
        if (barFuelPressure != null){
            barFuelPressure.setValue(pressure);
            barFuelPressure.postInvalidate();
        }
    }

    public void setbarOilPressure(float pressure){
        if (barOilPressure != null){
            barOilPressure.setValue(pressure);
            barOilPressure.postInvalidate();
        }
    }

    public void setWaterPressure(float pressure){
        if (barWaterPressure != null){
            barWaterPressure.setValue(pressure);
            barWaterPressure.postInvalidate();
        }
    }


    public void setLambdaTemp(float temp){
        if (barLambdaTemp != null){
            barLambdaTemp.setValue(temp);
            barLambdaTemp.postInvalidate();
        }
    }

    public void setBatteryConsumption(float watt){
        if (barBatteryConsumption != null){
            barBatteryConsumption.setValue(watt);
            barBatteryConsumption.postInvalidate();
        }
    }

    public void updateData(ConcurrentHashMap<ValueName, Data> concurrentHashMap) {
        if (a) {
            setMotorTemp(concurrentHashMap.get(ValueName.MotorTemp).getValue());
            setReturnTemp(concurrentHashMap.get(ValueName.AirTemp).getValue());
            setOilTemp(concurrentHashMap.get(ValueName.OilTemp).getValue());
            setFuelPressure(concurrentHashMap.get(ValueName.FuelPressure).getValue());
            setbarOilPressure(concurrentHashMap.get(ValueName.OilPressure).getValue());
            setWaterPressure(concurrentHashMap.get(ValueName.WaterPressure).getValue());
            setLambdaTemp(concurrentHashMap.get(ValueName.LambdaTemp).getValue());
            setBatteryConsumption(concurrentHashMap.get(ValueName.BatteryVoltage).getValue());
        }
    }
}
