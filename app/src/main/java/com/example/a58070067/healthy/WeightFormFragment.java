package com.example.a58070067.healthy;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.Calendar;

public class WeightFormFragment extends Fragment{

    private FirebaseFirestore  mdb;
    private FirebaseAuth mAuth;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_form,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mdb = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        mdb.setFirestoreSettings(settings);
        saveWeight();
        backBtn();
    }



    private void backBtn()
    {
        Button back = getView().findViewById(R.id.weight_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view,new WeightFragment())
                        .commit();
            }
        });
    }

    private void saveWeight()
    {
        Button addWeight = getView().findViewById(R.id.weight_save);
        addWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText date = getView().findViewById(R.id.weight_date1);
                EditText weight = getView().findViewById(R.id.weight_value);


                String dateTxt = date.getText().toString();
                String weightTxt = weight.getText().toString();
                int weight_value = Integer.parseInt(weightTxt);
                String user_id = mAuth.getCurrentUser().getUid();
                Weight weight1 = new Weight(dateTxt,weight_value,"UP");
                Log.d("USER",dateTxt);
                Log.d("USER",weightTxt);
                Toast.makeText(getActivity(),dateTxt,Toast.LENGTH_SHORT).show();
                mdb.collection("myfitness").document(user_id)
                        .collection("weight").document(dateTxt).set(weight1)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity(),"SAVE WEIGHT",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "ERROR :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }
}
