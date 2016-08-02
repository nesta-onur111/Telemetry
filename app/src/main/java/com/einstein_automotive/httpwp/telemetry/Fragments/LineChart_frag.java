package com.einstein_automotive.httpwp.telemetry.Fragments;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.einstein_automotive.httpwp.telemetry.DataStorage.Data;
import com.einstein_automotive.httpwp.telemetry.DataStorage.LineChartConfig;
import com.einstein_automotive.httpwp.telemetry.DataStorage.ValueName;
import com.einstein_automotive.httpwp.telemetry.R;
import com.einstein_automotive.httpwp.telemetry.customViews.LineChart;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Fabian Naaß on 12.11.2015.
 * Einstein Motorsport 2015.
 * + Copyrights by Einstein Motorsport e.V
 */
public class LineChart_frag extends Fragment implements View.OnClickListener, View.OnFocusChangeListener{

    private View view;
    private LineChart lineChart;
    private boolean hasFocus = false;
    Communicator2Telemetry_frag comm;
    private LineChartConfig config;
    private TaskTimer taskTimer;
    private boolean recording;

    private Data steering,gforce, speed, rpm, gear, throttle, brake;
    public interface Communicator2Telemetry_frag{
        public void sendConfig(LineChartConfig config);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_linechart, null);
            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
            lineChart = (LineChart) view.findViewById(R.id.lineChartView);
            lineChart.setOnClickListener(this);
            lineChart.setOnFocusChangeListener(this);
            comm = (Communicator2Telemetry_frag) getParentFragment();
            taskTimer = new TaskTimer();
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        v.setBackgroundColor(Color.GREEN);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            this.hasFocus =true;
            v.setBackgroundColor(Color.YELLOW);
            comm.sendConfig(config);
        }
        else{
            this.hasFocus =false;
            v.setBackgroundColor(Color.WHITE);
        }
    }

    private void updateLineChart(double time){
        if (recording && config.getSize() >0) {
            for (Map.Entry<ValueName, Paint> lcc : config.getLineConfig().entrySet()) {
                switch (lcc.getKey()) {
                    case Speed:
                        lineChart.updateSpeed(speed, lcc.getValue());
                        break;
                    case RPM:
                        lineChart.updateRPM(rpm, lcc.getValue());
                        break;
           /*         case Throttle:
                        lineChart.updateThrottle(throttle, lcc.getValue());
                        break;
                    case Brake:
                        lineChart.updateBrake(brake, lcc.getValue());
                        break; */
                    case Gear:
                        lineChart.updateGear(gear, lcc.getValue());
                        break;
                    case GForce:
                        lineChart.updateGForce(gforce, lcc.getValue());
                        break;
                    case Steering:
                        lineChart.updateSteering(steering, lcc.getValue());
                        break;
                }
            }
            lineChart.updateTime(time);
        }
    }


    public void updateData(ConcurrentHashMap<ValueName, Data> concurrentHashMap) {
        if (recording && config.getSize() >0) {
            if (true) { // Time
                for (Map.Entry<ValueName, Paint> lcc : config.getLineConfig().entrySet()) {
                    switch (lcc.getKey()) {
                        case Speed:
                           // lineChart.updateSpeed(concurrentHashMap.get(ValueName.Speed), lcc.getLineColor());
                            speed = concurrentHashMap.get(ValueName.Speed);
                            break;
                        case RPM:
//                            lineChart.updateRPM(concurrentHashMap.get(ValueName.RPM), lcc.getLineColor());
                            rpm = concurrentHashMap.get(ValueName.RPM);
                            break;
                      //  case Throttle:
//                            lineChart.updateThrottle(concurrentHashMap.get(ValueName.Throttle), lcc.getLineColor());
                     //       throttle = concurrentHashMap.get(ValueName.Throttle);
                       //     break;
                       // case Brake:
//                            lineChart.updateBrake(concurrentHashMap.get(ValueName.Brake), lcc.getLineColor());
                         //   brake = concurrentHashMap.get(ValueName.Brake);
                          //  break;
                        case Gear:
//                            lineChart.updateGear(concurrentHashMap.get(ValueName.Gear), lcc.getLineColor());
                            gear = concurrentHashMap.get(ValueName.Gear);
                            break;
                        case GForce:
//                            lineChart.updateGForce(concurrentHashMap.get(ValueName.GForce), lcc.getLineColor());
                            gforce =  concurrentHashMap.get(ValueName.GForce);
                            break;
                        case Steering:
//                            lineChart.updateSteering(concurrentHashMap.get(ValueName.Steering), lcc.getLineColor());
                            steering = concurrentHashMap.get(ValueName.Steering);
                            break;
                    }
                }
            }
           // lineChart.updateTime(taskTimer.getTime());
        }
    }
    Random rnd;
    public void test(){
        rnd = new Random();

//        for (int i = 0; i < 25; i++) {
//            lineChart.updateSpeed(new Data(rnd.nextInt(130),Unit.Speed,140f,0f,140f),lcc.getLineColor(), taskTimer.getTime());
//        }

    }

    class TaskTimer extends Thread {
        double time =0;

        @Override
        public void run() {
            while (!isInterrupted()|| recording) {
                try {
                    Thread.sleep(100);

                   if(true)
                        updateLineChart(time);
//                        updateDemoData();
                    time+=0.1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    interrupt();
                }
            }
        }

        public double getTime(){
            return time;
        }

        public void reset(){
            time = 0;
        }
    }


    public void startRecord(){
        recording = true;
        if (!taskTimer.isAlive()){
            taskTimer.start();
        }
    }

    public void stopRecord() {
        recording = false;
        taskTimer.reset();
    }

    public boolean isRecording(){
        return recording;
    }


//    public void updateDemoData(){
//        //   public Data(float value, float min, float max) {
//
//        if (recording) {
//            if (true) { // Time
//                for (LineChartConfig lcc : config) {
//                    switch (lcc.getValueName()) {
//                        case Speed:
//                            lineChart.updateSpeed(new Data(speed,0,140), lcc.getLineColor(), taskTimer.getTime());
//                            break;
//                        case RPM:
//                            lineChart.updateRPM(new Data(rpm,0,14000), lcc.getLineColor(), taskTimer.getTime());
//                            break;
//                        case Throttle:
//                            lineChart.updateThrottle(new Data(throttle,0,100), lcc.getLineColor(), taskTimer.getTime());
//                            break;
//                        case Brake:
//                            lineChart.updateBrake(new Data(brake,0,100), lcc.getLineColor(), taskTimer.getTime());
//                            break;
//                        case Gear:
//                            lineChart.updateGear(new Data(gear,0,6), lcc.getLineColor(), taskTimer.getTime());
//                            break;
//                        case GForce:
//                            lineChart.updateGForce(new Data(gforce,0,6), lcc.getLineColor(), taskTimer.getTime());
//                            break;
//                        case Steering:
//                            lineChart.updateSteering(new Data(steering,-180,180), lcc.getLineColor(), taskTimer.getTime());
//                            break;
//                    }
//                }
//            }
//        }
//    }
//
//    public void updateSpeed(int speed){
//        this.speed = speed;
//    }
//
//    public void updateRPM(int rpm){
//        this.rpm = rpm;
//    }
//
//    public void updateGear(int gear){
//        this.gear = gear;
//    }
//
//    public void updateThrottle(int throttle){
//        this.throttle = throttle;
//    }
//
//    public void updateBrake(int brake){
//        this.brake = brake;
//    }
//
//    public void updateSteering(int steering){
//        this.steering = steering;
//    }
//
//    public void updateGForce(int gforce){
//        this.gforce = gforce;
//    }



    public void setConfig(LineChartConfig lcConfig){
        this.config = lcConfig;
    }

    public boolean isFocused(){
        return hasFocus;
    }
}
