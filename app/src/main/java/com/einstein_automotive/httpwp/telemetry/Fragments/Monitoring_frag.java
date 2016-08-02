package com.einstein_automotive.httpwp.telemetry.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.einstein_automotive.httpwp.telemetry.DataStorage.Data;
import com.einstein_automotive.httpwp.telemetry.DataStorage.ValueName;
import com.einstein_automotive.httpwp.telemetry.R;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Fabian Naaß on 27.05.2015,
 * for Einstein Motorsport 2015
 */
public class Monitoring_frag extends Fragment {

    private static View view;
    private boolean a = false;
    private EditText et_monitoring_Dashboard_DownShiftCounter, et_monitoring_Dashboard_State, et_monitoring_Dashboard_Temp, et_monitoring_Dashboard_UpShiftCounter;

    private EditText et_monitoring_MS6_FuelPressure, et_monitoring_MS6_FuelTemp, et_monitoring_MS6_LambdaTemp, et_monitoring_MS6_MotorTemp, et_monitoring_MS6_OilPressure,
            et_monitoring_MS6_OilTemp, et_monitoring_MS6_WaterPressure, et_monitoring_MS6_Ath, et_monitoring_MS6_BatteryVoltage, et_monitoring_MS6_Speed, et_monitoring_MS6_RPM,
            et_monitoring_MS6_Gear, et_monitoring_MS6_Lambda, et_monitoring_MS6_TCSwitch, et_monitoring_MS6_TCState;

    private EditText et_monitoring_DDSC_GForce, et_monitoring_DDSC_GForceX, et_monitoring_DDSC_GForceY;

    private EditText et_monitoring_SCU_State, et_monitoring_SCU_GearSensorVoltage, et_monitoring_SCU_Gear, et_monitoring_SCU_Temp, et_monitoring_SCU_Settings_UpShift,
            et_monitoring_SCU_Settings_DownShift, et_monitoring_SCU_Settings_ClutchOpen, et_monitoring_SCU_Settings_GearcutActive, et_monitoring_SCU_Settings_WaitDownShift;

    private EditText et_monitoring_Telemetry_CANState, et_monitoring_Telemetry_AliveCounter, et_monitoring_Telemetry_SGCAN, et_monitoring_Telemetry_BrakePressureFront,
            et_monitoring_Telemetry_BrakePressureRear, et_monitoring_Telemetry_SteeringAngle, et_monitoring_Telemetry_GearDetection, et_monitoring_Telemetry_Sensor1, et_monitoring_Telemetry_DIP;

    private EditText et_monitoring_PDS_Multiplex, et_monitoring_PDS_State, et_monitoring_PDS_Current, et_monitoring_PDS_Voltage, et_monitoring_PDS_Power,
            et_monitoring_PDS_Settings_DashboardMaxCurrent, et_monitoring_PDS_Settings_TelemetryMaxCurrent, et_monitoring_PDS_Settings_DDSCMaxCurrent,
            et_monitoring_PDS_Settings_MainRelayMaxCurrent, et_monitoring_PDS_Settings_FuelPumpMaxCurrent, et_monitoring_PDS_Settings_FANMaxCurrent,
            et_monitoring_PDS_Settings_ShifterMaxCurrent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_monitoring, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewContent();
    }

    // get Content from the View
    private void getViewContent(){
        et_monitoring_Dashboard_DownShiftCounter = (EditText) getActivity().findViewById(R.id.et_monitoring_Dashboard_DownShiftCounter);
        et_monitoring_Dashboard_State = (EditText) getActivity().findViewById(R.id.et_monitoring_Dashboard_State);
        et_monitoring_Dashboard_Temp = (EditText) getActivity().findViewById(R.id.et_monitoring_Dashboard_Temp);
        et_monitoring_Dashboard_UpShiftCounter = (EditText) getActivity().findViewById(R.id.et_monitoring_Dashboard_UpShiftCounter);

        et_monitoring_MS6_FuelPressure = (EditText) getActivity().findViewById(R.id.et_monitoring_MS6_FuelPressure);
        et_monitoring_MS6_FuelPressure.setKeyListener(null);
        et_monitoring_MS6_FuelTemp = (EditText) getActivity().findViewById(R.id.et_monitoring_MS6_FuelTemp);
        et_monitoring_MS6_LambdaTemp = (EditText) getActivity().findViewById(R.id.et_monitoring_MS6_LambdaTemp);
        et_monitoring_MS6_MotorTemp = (EditText) getActivity().findViewById(R.id.et_monitoring_MS6_MotorTemp);
        et_monitoring_MS6_OilPressure = (EditText) getActivity().findViewById(R.id.et_monitoring_MS6_OilPressure);
        et_monitoring_MS6_OilTemp = (EditText) getActivity().findViewById(R.id.et_monitoring_MS6_OilTemp);
        et_monitoring_MS6_WaterPressure = (EditText) getActivity().findViewById(R.id.et_monitoring_MS6_WaterPressure);
        et_monitoring_MS6_Ath = (EditText) getActivity().findViewById(R.id.et_monitoring_MS6_Ath);
        et_monitoring_MS6_BatteryVoltage = (EditText) getActivity().findViewById(R.id.et_monitoring_MS6_BatteryVoltage);
        et_monitoring_MS6_Speed = (EditText) getActivity().findViewById(R.id.et_monitoring_MS6_Speed);
        et_monitoring_MS6_RPM = (EditText) getActivity().findViewById(R.id.et_monitoring_MS6_RPM);
        et_monitoring_MS6_Gear = (EditText) getActivity().findViewById(R.id.et_monitoring_MS6_Gear);
        et_monitoring_MS6_Lambda = (EditText) getActivity().findViewById(R.id.et_monitoring_MS6_Lambda);
        et_monitoring_MS6_TCSwitch = (EditText) getActivity().findViewById(R.id.et_monitoring_MS6_TCSwitch);
        et_monitoring_MS6_TCState = (EditText) getActivity().findViewById(R.id.et_monitoring_MS6_TCState);

        et_monitoring_DDSC_GForce = (EditText) getActivity().findViewById(R.id.et_monitoring_DDSC_GForce);
        et_monitoring_DDSC_GForceX = (EditText) getActivity().findViewById(R.id.et_monitoring_DDSC_GForceX);
        et_monitoring_DDSC_GForceY = (EditText) getActivity().findViewById(R.id.et_monitoring_DDSC_GForceY);

        et_monitoring_SCU_State = (EditText) getActivity().findViewById(R.id.et_monitoring_SCU_State);
        et_monitoring_SCU_GearSensorVoltage = (EditText) getActivity().findViewById(R.id.et_monitoring_SCU_GearSensorVoltage);
        et_monitoring_SCU_Gear = (EditText) getActivity().findViewById(R.id.et_monitoring_SCU_Gear);
        et_monitoring_SCU_Temp = (EditText) getActivity().findViewById(R.id.et_monitoring_SCU_Temp);
        et_monitoring_SCU_Settings_UpShift = (EditText) getActivity().findViewById(R.id.et_monitoring_SCU_Settings_UpShift);
        et_monitoring_SCU_Settings_DownShift = (EditText) getActivity().findViewById(R.id.et_monitoring_SCU_Settings_DownShift);
        et_monitoring_SCU_Settings_ClutchOpen = (EditText) getActivity().findViewById(R.id.et_monitoring_SCU_Settings_ClutchOpen);
        et_monitoring_SCU_Settings_GearcutActive = (EditText) getActivity().findViewById(R.id.et_monitoring_SCU_Settings_GearcutActive);
        et_monitoring_SCU_Settings_WaitDownShift = (EditText) getActivity().findViewById(R.id.et_monitoring_SCU_Settings_WaitDownShift);

        et_monitoring_Telemetry_CANState = (EditText) getActivity().findViewById(R.id.et_monitoring_Telemetry_CANState);
        et_monitoring_Telemetry_AliveCounter = (EditText) getActivity().findViewById(R.id.et_monitoring_Telemetry_AliveCounter);
        et_monitoring_Telemetry_SGCAN = (EditText) getActivity().findViewById(R.id.et_monitoring_Telemetry_SGCAN);
        et_monitoring_Telemetry_BrakePressureFront = (EditText) getActivity().findViewById(R.id.et_monitoring_Telemetry_BrakePressureFront);
        et_monitoring_Telemetry_BrakePressureRear = (EditText) getActivity().findViewById(R.id.et_monitoring_Telemetry_BrakePressureRear);
        et_monitoring_Telemetry_SteeringAngle = (EditText) getActivity().findViewById(R.id.et_monitoring_Telemetry_SteeringAngle);
        et_monitoring_Telemetry_GearDetection = (EditText) getActivity().findViewById(R.id.et_monitoring_Telemetry_GearDetection);
        et_monitoring_Telemetry_Sensor1 = (EditText) getActivity().findViewById(R.id.et_monitoring_Telemetry_Sensor1);
        et_monitoring_Telemetry_DIP = (EditText) getActivity().findViewById(R.id.et_monitoring_Telemetry_DIP);

        et_monitoring_PDS_Multiplex = (EditText) getActivity().findViewById(R.id.et_monitoring_PDS_Multiplex);
        et_monitoring_PDS_State = (EditText) getActivity().findViewById(R.id.et_monitoring_PDS_State);
        et_monitoring_PDS_Current = (EditText) getActivity().findViewById(R.id.et_monitoring_PDS_Current);
        et_monitoring_PDS_Voltage = (EditText) getActivity().findViewById(R.id.et_monitoring_PDS_Voltage);
        et_monitoring_PDS_Power = (EditText) getActivity().findViewById(R.id.et_monitoring_PDS_Power);
        et_monitoring_PDS_Settings_DashboardMaxCurrent = (EditText) getActivity().findViewById(R.id.et_monitoring_PDS_Settings_DashboardMaxCurrent);
        et_monitoring_PDS_Settings_TelemetryMaxCurrent = (EditText) getActivity().findViewById(R.id.et_monitoring_PDS_Settings_TelemetryMaxCurrent);
        et_monitoring_PDS_Settings_DDSCMaxCurrent = (EditText) getActivity().findViewById(R.id.et_monitoring_PDS_Settings_DDSCMaxCurrent);
        et_monitoring_PDS_Settings_MainRelayMaxCurrent = (EditText) getActivity().findViewById(R.id.et_monitoring_PDS_Settings_MainRelayMaxCurrent);
        et_monitoring_PDS_Settings_FuelPumpMaxCurrent = (EditText) getActivity().findViewById(R.id.et_monitoring_PDS_Settings_FuelPumpMaxCurrent);
        et_monitoring_PDS_Settings_FANMaxCurrent = (EditText) getActivity().findViewById(R.id.et_monitoring_PDS_Settings_FANMaxCurrent);
        et_monitoring_PDS_Settings_ShifterMaxCurrent = (EditText) getActivity().findViewById(R.id.et_monitoring_PDS_Settings_ShifterMaxCurrent);
        a = true;
    }


    public void updateData(ConcurrentHashMap<ValueName, Data> concurrentHashMap) {

        if(a){
            et_monitoring_Dashboard_DownShiftCounter.setText(String.valueOf(concurrentHashMap.get(ValueName.DownShift).getValue()));
            et_monitoring_Dashboard_State.setText(String.valueOf(concurrentHashMap.get(ValueName.DashboardState).getValue()));
            et_monitoring_Dashboard_Temp.setText(String.valueOf(concurrentHashMap.get(ValueName.DashboardTemp).getValue()));
            et_monitoring_Dashboard_UpShiftCounter.setText(String.valueOf(concurrentHashMap.get(ValueName.UpShift).getValue()));

            et_monitoring_MS6_FuelPressure.setText(String.valueOf(concurrentHashMap.get(ValueName.FuelPressure).getValue()));
            et_monitoring_MS6_FuelTemp.setText(String.valueOf(concurrentHashMap.get(ValueName.FuelTemp).getValue()));
            et_monitoring_MS6_LambdaTemp.setText(String.valueOf(concurrentHashMap.get(ValueName.LambdaTemp).getValue()));
            et_monitoring_MS6_MotorTemp.setText(String.valueOf(concurrentHashMap.get(ValueName.MotorTemp).getValue()));
            et_monitoring_MS6_OilPressure.setText(String.valueOf(concurrentHashMap.get(ValueName.OilPressure).getValue()));
            et_monitoring_MS6_OilTemp.setText(String.valueOf(concurrentHashMap.get(ValueName.OilTemp).getValue()));
            et_monitoring_MS6_WaterPressure.setText(String.valueOf(concurrentHashMap.get(ValueName.WaterPressure).getValue()));
            et_monitoring_MS6_Ath.setText(String.valueOf(concurrentHashMap.get(ValueName.Ath).getValue()));
            et_monitoring_MS6_BatteryVoltage.setText(String.valueOf(concurrentHashMap.get(ValueName.BatteryVoltage).getValue()));
            et_monitoring_MS6_Speed.setText(String.valueOf(concurrentHashMap.get(ValueName.Speed).getValue()));
            et_monitoring_MS6_RPM.setText(String.valueOf(concurrentHashMap.get(ValueName.RPM).getValue()));
            et_monitoring_MS6_Gear.setText(String.valueOf(concurrentHashMap.get(ValueName.Gear).getValue()));
            et_monitoring_MS6_Lambda.setText(String.valueOf(concurrentHashMap.get(ValueName.Lambda).getValue()));
            et_monitoring_MS6_TCSwitch.setText(String.valueOf(concurrentHashMap.get(ValueName.TCswitch).getValue()));
            et_monitoring_MS6_TCState.setText(String.valueOf(concurrentHashMap.get(ValueName.TCstate).getValue()));

            et_monitoring_DDSC_GForce.setText(String.valueOf(concurrentHashMap.get(ValueName.GForce).getValue()));
            et_monitoring_DDSC_GForceX.setText(String.valueOf(concurrentHashMap.get(ValueName.GForceX).getValue()));
            et_monitoring_DDSC_GForceY.setText(String.valueOf(concurrentHashMap.get(ValueName.GForceY).getValue()));

            et_monitoring_SCU_State.setText(String.valueOf(concurrentHashMap.get(ValueName.SCUstate).getValue()));
            et_monitoring_SCU_GearSensorVoltage.setText(String.valueOf(concurrentHashMap.get(ValueName.GearSensorVoltage).getValue()));
            et_monitoring_SCU_Gear.setText(String.valueOf(concurrentHashMap.get(ValueName.Gear).getValue()));
            et_monitoring_SCU_Temp.setText(String.valueOf(concurrentHashMap.get(ValueName.SCUtemp).getValue()));
            et_monitoring_SCU_Settings_UpShift.setText(String.valueOf(concurrentHashMap.get(ValueName.TimeUpShift).getValue()));
            et_monitoring_SCU_Settings_DownShift.setText(String.valueOf(concurrentHashMap.get(ValueName.TimeDownShift).getValue()));
            et_monitoring_SCU_Settings_ClutchOpen.setText(String.valueOf(concurrentHashMap.get(ValueName.TimeClutchOpen).getValue()));
            et_monitoring_SCU_Settings_GearcutActive.setText(String.valueOf(concurrentHashMap.get(ValueName.TimeGearcutActive).getValue()));
            et_monitoring_SCU_Settings_WaitDownShift.setText(String.valueOf(concurrentHashMap.get(ValueName.WaitDownShift).getValue()));

            et_monitoring_Telemetry_CANState.setText(String.valueOf(concurrentHashMap.get(ValueName.CanState).getValue()));
            et_monitoring_Telemetry_AliveCounter.setText(String.valueOf(concurrentHashMap.get(ValueName.AliveCounter).getValue()));
            et_monitoring_Telemetry_SGCAN.setText(String.valueOf(concurrentHashMap.get(ValueName.SGCAN).getValue()));
            et_monitoring_Telemetry_BrakePressureFront.setText(String.valueOf(concurrentHashMap.get(ValueName.BrakePressureFront).getValue()));
            et_monitoring_Telemetry_BrakePressureRear.setText(String.valueOf(concurrentHashMap.get(ValueName.BrakePressureRear).getValue()));
            et_monitoring_Telemetry_SteeringAngle.setText(String.valueOf(concurrentHashMap.get(ValueName.Steering).getValue()));
            et_monitoring_Telemetry_GearDetection.setText(String.valueOf(concurrentHashMap.get(ValueName.GearDetVoltage).getValue()));
            et_monitoring_Telemetry_Sensor1.setText(String.valueOf(concurrentHashMap.get(ValueName.SensorVoltage).getValue()));
            et_monitoring_Telemetry_DIP.setText(String.valueOf(concurrentHashMap.get(ValueName.DIPSwitch).getValue()));

            et_monitoring_PDS_Multiplex.setText(String.valueOf(concurrentHashMap.get(ValueName.Multiplex).getValue()));
            et_monitoring_PDS_State.setText(String.valueOf(concurrentHashMap.get(ValueName.StateSG).getValue()));
            et_monitoring_PDS_Current.setText(String.valueOf(concurrentHashMap.get(ValueName.CurrentAverageSG).getValue()));
            et_monitoring_PDS_Voltage.setText(String.valueOf(concurrentHashMap.get(ValueName.VoltageAverageSG).getValue()));
            et_monitoring_PDS_Power.setText(String.valueOf(concurrentHashMap.get(ValueName.PowerAverageSG).getValue()));
            et_monitoring_PDS_Settings_DashboardMaxCurrent.setText(String.valueOf(concurrentHashMap.get(ValueName. CurrentMaxDashboard).getValue()));
            et_monitoring_PDS_Settings_TelemetryMaxCurrent.setText(String.valueOf(concurrentHashMap.get(ValueName.CurrentMaxTelemetrie).getValue()));
            et_monitoring_PDS_Settings_DDSCMaxCurrent.setText(String.valueOf(concurrentHashMap.get(ValueName.CurrentMaxDDSC).getValue()));
            et_monitoring_PDS_Settings_MainRelayMaxCurrent.setText(String.valueOf(concurrentHashMap.get(ValueName.CurrentMaxMainRelay).getValue()));
            et_monitoring_PDS_Settings_FuelPumpMaxCurrent.setText(String.valueOf(concurrentHashMap.get(ValueName.CurrentMaxFuelPump).getValue()));
            et_monitoring_PDS_Settings_FANMaxCurrent.setText(String.valueOf(concurrentHashMap.get(ValueName.CurrentMaxFAN).getValue()));
            et_monitoring_PDS_Settings_ShifterMaxCurrent.setText(String.valueOf(concurrentHashMap.get(ValueName.CurrentMaxShifter).getValue()));
        }
    }
}
