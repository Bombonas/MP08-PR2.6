package com.example.mp08_pr26.ui;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mp08_pr26.R;

public class AccFragment extends Fragment {

    private AccViewModel mViewModel;
    private SensorManager sensorManager;
    private Sensor sensor;
    SensorEventListener sensorListener;

    public static AccFragment newInstance() {
        return new AccFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_acc, container, false);
        ProgressBar barX = root.findViewById(R.id.barX);
        ProgressBar barY = root.findViewById(R.id.barY);
        ProgressBar barZ = root.findViewById(R.id.barZ);
        sensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // Valors de l'acceleròmetre en m/s^2
                float xAcc = sensorEvent.values[0];
                float yAcc = sensorEvent.values[1];
                float zAcc = sensorEvent.values[2];

                // Processament o visualització de dades...
                barX.setProgress((int) ((Math.abs(xAcc)) * 100));
                barY.setProgress((int) ((Math.abs(xAcc)) * 100));
                barZ.setProgress((int) ((Math.abs(xAcc)) * 100));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
                // Es pot ignorar aquesta CB de moment
            }
        };

        // Seleccionem el tipus de sensor (veure doc oficial)
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        // registrem el Listener per capturar els events del sensor
        if( sensor!=null ) {
            sensorManager.registerListener(sensorListener,sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AccViewModel.class);
        // TODO: Use the ViewModel
    }

}