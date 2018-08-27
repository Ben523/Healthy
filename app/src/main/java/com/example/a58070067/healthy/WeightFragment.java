package com.example.a58070067.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class WeightFragment extends Fragment{
    ArrayList<Integer> weight_list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addWeightBtn();

        weight_list.add(60);
        weight_list.add(55);

        final ArrayAdapter<Integer> menuAdapter = new ArrayAdapter<>(
                getActivity(),android.R.layout.simple_list_item_1,weight_list
        );

        ListView menuList = getView().findViewById(R.id.weight_list);
        menuList.setAdapter(menuAdapter);
    }

    private void addWeightBtn()
    {
        Button addWeight = getView().findViewById(R.id.weight_add);
        addWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view,new WeightFormFragment())
                        .commit();
            }
        });
    }
}
