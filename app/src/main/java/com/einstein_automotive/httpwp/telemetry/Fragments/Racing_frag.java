package com.einstein_automotive.httpwp.telemetry.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.einstein_automotive.httpwp.telemetry.DataStorage.Data;
import com.einstein_automotive.httpwp.telemetry.DataStorage.ValueName;
import com.einstein_automotive.httpwp.telemetry.R;
import com.einstein_automotive.httpwp.telemetry.customViews.GForceView;
import com.einstein_automotive.httpwp.telemetry.customViews.Point;
import com.einstein_automotive.httpwp.telemetry.customViews.RevolutionsView;
import com.einstein_automotive.httpwp.telemetry.customViews.SpeedView;
import com.einstein_automotive.httpwp.telemetry.customViews.SteeringView;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Fabian Naaß on 27.05.2015,
 * for Einstein Motorsport 2015
 */
public class Racing_frag extends Fragment {

    private SpeedView speedView;
    private RevolutionsView revolutionsView;
    private GForceView gForceView;
    private SteeringView steeringView;
    TextView tvGear;
    private static View view;

    private int speed = 0;
    private float steeringAngle = 0.0f;
    private int rpm = 0;
    private int gear = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Racing frag", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null)
            view =  inflater.inflate(R.layout.fragment_racing, container,false);
        Log.e("Racing frag","onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        speedView = (SpeedView) getActivity().findViewById(R.id.speedView);
        revolutionsView = (RevolutionsView) getActivity().findViewById(R.id.revolutionsView);
        gForceView = (GForceView) getActivity().findViewById(R.id.gForceView);
        steeringView = (SteeringView) getActivity().findViewById(R.id.steeringView);
        tvGear = (TextView) getActivity().findViewById(R.id.tv_racing_Gear);
        Log.e("Racing frag","onSaveInstanceState");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("Racing frag", "onActivityCreated");
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("speed", speed);
        Log.e("Racing frag","onSaveInstanceState");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.e("Racing frag", "onAttach");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Racing frag", "onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("Racing frag", "onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Racing frag", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Racing frag", "onStop");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("Racing frag", "onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Racing frag", "onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Racing frag", "onDestroyView");
    }

    public void sizeChanged(int height){
        if (height >= 600)
            tvGear.setTextSize(443*0.2f);
        else
            tvGear.setTextSize(623*0.2f);

    }

    public void updateData(ConcurrentHashMap<ValueName,Data> data){
        if (speedView != null && revolutionsView!=null && steeringView != null && gForceView != null){
            speedView.setSpeed((int) data.get(ValueName.Speed).getValue());
            revolutionsView.setRPM((int) data.get(ValueName.RPM).getValue());
            steeringView.setAngle(data.get(ValueName.Steering).getValue());
            gForceView.addPoint(new Point(data.get(ValueName.GForceX).getValue(), data.get(ValueName.GForceY).getValue()));
            final int gear = (int) data.get(ValueName.Gear).getValue();
            speedView.postInvalidate();
            revolutionsView.postInvalidate();
            steeringView.postInvalidate();
            gForceView.postInvalidate();
            tvGear.post(new Runnable() {
                @Override
                public void run() {
                    if (gear == 0)
                        tvGear.setText("N");
                    else
                        tvGear.setText(String.valueOf(gear));
                }
            });
        }
    }

    public void update(int speed, int gear, int rpm, float steeringAngle, Point p){
        this.speed = speed;
        this.gear = gear;
        this.rpm = rpm;
        this.steeringAngle = steeringAngle;
        final int tmp = gear;

        if (speedView != null && revolutionsView!=null && steeringView != null && gForceView != null){
            speedView.setSpeed(speed);
            revolutionsView.setRPM(rpm);
            steeringView.setAngle(steeringAngle);
            if (p != null)
                gForceView.addPoint(p);
            else
                gForceView.clearPoints();

            speedView.postInvalidate();
            revolutionsView.postInvalidate();
            steeringView.postInvalidate();
            gForceView.postInvalidate();
            tvGear.post(new Runnable() {
                @Override
                public void run() {
                    if (tmp == 0)
                        tvGear.setText("N");
                    else
                        tvGear.setText(String.valueOf(tmp));
                }
            });
        }
    }

    public void updateGForce(Point p, boolean reset){

        if (gForceView != null){
            if (reset) {
                gForceView.clearPoints();
                gForceView.postInvalidate();
            }
            else {
                gForceView.addPoint(p);
                gForceView.postInvalidate();
            }
        }
    }

    public void updateGear(int gear){
        if (gForceView != null) {
            this.gear = gear;
            final int x = gear;

            tvGear.post(new Runnable() {
                @Override
                public void run() {
                    if (x == 0)
                        tvGear.setText("N");
                    else
                        tvGear.setText(String.valueOf(x));

                }
            });
        }
    }


    public void updateSpeed(int speed){
        this.speed = speed;

        if (speedView != null){
            speedView.setSpeed(speed);
            speedView.postInvalidate();
        }
    }

    public void updateRPM(int rpm){
        this.rpm = rpm;

        if (revolutionsView!=null){
            revolutionsView.setRPM(rpm);
            revolutionsView.postInvalidate();
        }
    }

    public void updateSteeringAngle(float steeringAngle){
        this.steeringAngle = steeringAngle;

        if (steeringView != null){
            steeringView.setAngle(steeringAngle);
            steeringView.postInvalidate();
        }
    }
}
