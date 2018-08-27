package com.example.a58070067.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BMIFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bmi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        backBtn();
        calculateBMI();
    }

    private void calculateBMI()
    {
        Button calculate = getView().findViewById(R.id.bmi_calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText heightText = getView().findViewById(R.id.bmi_height);
                EditText weightText = getView().findViewById(R.id.bmi_weight);
                String heightTxt = heightText.getText().toString();
                String weightTxt = weightText.getText().toString();
                if(heightTxt.isEmpty()||weightTxt.isEmpty()){
                    Log.d("USER","FIELD NAME IS EMPTY");
                }else{
                    float height = Float.parseFloat(heightTxt);
                    int weight = Integer.parseInt(weightTxt);
                    float summary = weight/(height*height);
                    TextView value = getView().findViewById(R.id.bmi_value);
                    value.setText(Float.toString(summary));
                    Toast.makeText(getActivity(), Float.toString(summary), Toast.LENGTH_SHORT).show();
                    Log.d("USER",Float.toString(summary));
                }


            }
        });
    }

    private void backBtn()
    {
        Button backBtn = getView().findViewById(R.id.bmi_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view,new MenuFragment())
                        .commit();
                Log.d("USER","GO TO MENU");
            }
        });
    }
}
