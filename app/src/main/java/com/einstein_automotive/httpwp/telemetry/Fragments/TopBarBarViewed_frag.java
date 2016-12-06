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

    BarElement barMotorTemp,barReturnTemp,barOilTemp,barFuelTemp,barLambdaTemp,barOilPressure, barWaterPressure,barFuelPressure, barBatteryConsumption,barBatteryCurrentVoltage;
    ImageButton btnEmergencyStop;
    CommunicatorStop comm;
    boolean barCreated = false;
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
        barFuelTemp = (BarElement) getActivity().findViewById(R.id.bvFuel);
        barLambdaTemp = (BarElement) getActivity().findViewById(R.id.bvLambda);
        barFuelPressure = (BarElement) getActivity().findViewById(R.id.bvFuelPressure);
        barOilPressure = (BarElement) getActivity().findViewById(R.id.bvOilPressure);
        barWaterPressure = (BarElement) getActivity().findViewById(R.id.bvWaterPressure);
        barBatteryConsumption = (BarElement) getActivity().findViewById(R.id.bvBatteryConsumption);
        barBatteryCurrentVoltage = (BarElement) getActivity().findViewById(R.id.bvBatteryCurrentVoltage);

        btnEmergencyStop = (ImageButton) getActivity().findViewById(R.id.btn_TopBar_EmergencyStop);
        btnEmergencyStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comm.emergencyStop(true);
            }
        });
        // (String unit, float bottom, float top, float minimum, float critical)
    }

    private boolean configurateBarViews(ConcurrentHashMap<ValueName, Data> concurrentHashMap){

        Data tempData = concurrentHashMap.get(ValueName.MotorTemp);
        barMotorTemp.configure(tempData.getUnit().getName(),0,tempData.getMax(),tempData.getMin(),tempData.getCritical());
        tempData = concurrentHashMap.get(ValueName.AirTemp);
        barReturnTemp.configure(tempData.getUnit().getName(),0,tempData.getMax(),tempData.getMin(),tempData.getCritical());
        tempData = concurrentHashMap.get(ValueName.OilTemp);
        barOilTemp.configure(tempData.getUnit().getName(),0,tempData.getMax(),tempData.getMin(),tempData.getCritical());
        // Fuel temp dazu
        tempData = concurrentHashMap.get(ValueName.FuelTemp);
        barFuelTemp.configure(tempData.getUnit().getName(),0,tempData.getMax(),tempData.getMin(),tempData.getCritical());
        // eventuell unnötig
        tempData = concurrentHashMap.get(ValueName.LambdaTemp);
        barLambdaTemp.configure(tempData.getUnit().getName(),0,tempData.getMax(),tempData.getMin(),tempData.getCritical());

        // bis min = rot, ab min = grün, ab max = rot
        tempData = concurrentHashMap.get(ValueName.FuelPressure);
        barFuelPressure.configure(tempData.getUnit().getName(),0,tempData.getMax(),tempData.getMin(),tempData.getCritical());
        tempData = concurrentHashMap.get(ValueName.OilPressure);
        barOilPressure.configure(tempData.getUnit().getName(),0,tempData.getMax(),tempData.getMin(),tempData.getCritical());

        // keiner verbaut
        tempData = concurrentHashMap.get(ValueName.WaterPressure);
        barWaterPressure.configure(tempData.getUnit().getName(),0,tempData.getMax(),tempData.getMin(),tempData.getCritical());

        // Battery Voltage dazu
        tempData = concurrentHashMap.get(ValueName.BatteryVoltage);
        barBatteryCurrentVoltage.configure(tempData.getUnit().getName(),0,tempData.getMax(),tempData.getMin(),tempData.getCritical());
        tempData = concurrentHashMap.get(ValueName.BatteryVoltage);
        barBatteryConsumption.configure(tempData.getUnit().getName(),0,tempData.getMax(),tempData.getMin(),tempData.getCritical());
        return barCreated = true;
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
    public void setFuelTemp(float temp){
        if (barFuelTemp != null){
            barFuelTemp.setValue(temp);
            barFuelTemp.postInvalidate();
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
    public void setBarBatteryCurrentVoltage(float watt){
        if (barBatteryCurrentVoltage != null){
            barBatteryCurrentVoltage.setValue(watt);
            barBatteryCurrentVoltage.postInvalidate();
        }
    }
    public void updateData(ConcurrentHashMap<ValueName, Data> concurrentHashMap) {
        if (barCreated) {
            setMotorTemp(concurrentHashMap.get(ValueName.MotorTemp).getValue());
            setReturnTemp(concurrentHashMap.get(ValueName.AirTemp).getValue());
            setOilTemp(concurrentHashMap.get(ValueName.OilTemp).getValue());
            setFuelTemp(concurrentHashMap.get(ValueName.FuelTemp).getValue());
            setFuelPressure(concurrentHashMap.get(ValueName.FuelPressure).getValue());
            setbarOilPressure(concurrentHashMap.get(ValueName.OilPressure).getValue());
            setWaterPressure(concurrentHashMap.get(ValueName.WaterPressure).getValue());
            setLambdaTemp(concurrentHashMap.get(ValueName.LambdaTemp).getValue());
            setBatteryConsumption(concurrentHashMap.get(ValueName.BatteryVoltage).getValue());
            setBarBatteryCurrentVoltage(concurrentHashMap.get(ValueName.BatteryVoltage).getValue());
        }else{
            if (configurateBarViews(concurrentHashMap)){
                setMotorTemp(concurrentHashMap.get(ValueName.MotorTemp).getValue());
                setReturnTemp(concurrentHashMap.get(ValueName.AirTemp).getValue());
                setOilTemp(concurrentHashMap.get(ValueName.OilTemp).getValue());
                setFuelTemp(concurrentHashMap.get(ValueName.FuelTemp).getValue());
                setFuelPressure(concurrentHashMap.get(ValueName.FuelPressure).getValue());
                setbarOilPressure(concurrentHashMap.get(ValueName.OilPressure).getValue());
                setWaterPressure(concurrentHashMap.get(ValueName.WaterPressure).getValue());
                setLambdaTemp(concurrentHashMap.get(ValueName.LambdaTemp).getValue());
                setBatteryConsumption(concurrentHashMap.get(ValueName.BatteryVoltage).getValue());
                setBarBatteryCurrentVoltage(concurrentHashMap.get(ValueName.BatteryVoltage).getValue());
            }
        }
    }
}
