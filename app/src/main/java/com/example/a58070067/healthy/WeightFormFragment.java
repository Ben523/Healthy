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
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeightFormFragment extends Fragment{
    private ArrayList<Weight> weights = new ArrayList<>();
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
        String user_id = mAuth.getCurrentUser().getUid();
        getListItems(user_id);

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
                String userId = mAuth.getCurrentUser().getUid();
                Weight last_record;
                if(!weights.isEmpty()) {
                    last_record = weights.get(weights.size() - 1);
                }
                else
                {
                    last_record = new Weight();
                }
                if(weights.isEmpty())
                {
                    Log.d("USER","EMPTY");
                    Log.d("USER",userId);
                }
                else
                {
                    Log.d("USER","FULL");
                    Log.d("USER",userId);
                }
                String status = "";
                if((weight_value > last_record.getWeight())&&(!weights.isEmpty()))
                {
                    status = "UP";
                }
                else if(weight_value < last_record.getWeight())
                {
                    status = "DOWN";
                }
                else
                {
                    status = "";
                }

                Weight weight1 = new Weight(dateTxt,weight_value,status);
                Log.d("USER",dateTxt);
                Log.d("USER",weightTxt);
                mdb.collection("myfitness").document(userId)
                        .collection("weight").document(dateTxt).set(weight1)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                getActivity().getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.main_view,new WeightFragment())
                                        .commit();
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

    private void getListItems(String user_id) {
        mdb.collection("myfitness").document(user_id).collection("weight").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d("user", "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            // Convert the whole Query Snapshot to a list
                            // of objects directly! No need to fetch each
                            // document.
                            List<Weight> types = documentSnapshots.toObjects(Weight.class);

                            // Add all to your list
                            weights.addAll(types);
                            Log.d("USER", "onSuccess: " + weights);

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

}
