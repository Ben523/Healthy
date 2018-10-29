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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class SleepFormFragment extends Fragment {
    private DBHelper mHelper;
    private FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep_form,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHelper = new DBHelper(getActivity());
        mAuth = FirebaseAuth.getInstance();


        saveSleep();
        backBtn();
    }

    private void backBtn()
    {
        Button back = getView().findViewById(R.id.sleep_back1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view,new SleepFragment())
                        .commit();
            }
        });
    }

    private void saveSleep()
    {
        Button addTime = getView().findViewById(R.id.sleep_save);
        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText date = getView().findViewById(R.id.sleep_date1);
                EditText sleep_time = getView().findViewById(R.id.sleep_time);
                EditText wake_up_time = getView().findViewById(R.id.wake_up_time);

                String dateTxt = date.getText().toString();
                String sleep_time_txt = sleep_time.getText().toString();
                String wake_up_time_txt = wake_up_time.getText().toString();
                String user_id = mAuth.getCurrentUser().getUid();
                if(dateTxt.isEmpty()||sleep_time_txt.isEmpty()||wake_up_time_txt.isEmpty()){
                    Log.d("USER","PLEASE WRITE ALL FIELD");
                }
                else
                {
                    Sleep sleep = new Sleep(dateTxt,sleep_time_txt,wake_up_time_txt);
                    //mHelper.addFriend(sleep,user_id);
                    Toast.makeText(getActivity(),"SAVED TIME"+sleep.getDate(),Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
