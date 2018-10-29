package com.example.a58070067.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SleepFragment extends Fragment {
    private ArrayList<Weight> sleeps = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView _sleepList = getView().findViewById(R.id.sleep_list);
        WeightAdapter sleepAdapter = new WeightAdapter(
                getActivity(),
                R.layout.fragment_custom_listview,
                sleeps
        );
        _sleepList.setAdapter(sleepAdapter);
        addSleepBtn();

    }

    private void addSleepBtn()
    {
        Button addWeight = getView().findViewById(R.id.sleep_add);
        addWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view,new SleepFormFragment())
                        .commit();
            }
        });
    }

    private void backBtn()
    {
        Button back = getView().findViewById(R.id.sleep_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view,new MenuFragment())
                        .commit();
            }
        });
    }
}
