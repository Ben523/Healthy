package com.example.a58070067.healthy;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class SleepFragment extends Fragment{
    private ArrayList<Sleep> sleeps = new ArrayList<>();
    private DBHelper mHelper;
    private FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mHelper = new DBHelper(getActivity());
        mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();
        sleeps = mHelper.getFriendList(user_id);

        ListView _sleepList = getView().findViewById(R.id.sleep_list);
        SleepAdapter sleepAdapter = new SleepAdapter(
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
