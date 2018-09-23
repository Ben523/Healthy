package com.example.a58070067.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.a58070067.healthy.WeightAdapter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeightFragment extends Fragment{
    private ArrayList<Weight> weights = new ArrayList<>();
    private ArrayList<String> ids = new ArrayList<>();
    private ListenerRegistration docRef;
    private FirebaseFirestore mdb;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight,container,false);
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
        Collections.reverse(weights);
        if(weights.isEmpty())
        {
            Log.d("USER","EMPTY");
        }
        else
        {
            Log.d("USER","FULL");
        }

        ListView _weightList = getView().findViewById(R.id.weight_list);
        WeightAdapter weightAdapter = new WeightAdapter(
                getActivity(),
                R.layout.fragment_custom_listview,
                weights
        );
        _weightList.setAdapter(weightAdapter);
        addWeightBtn();

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
                            ListView _weightList = getView().findViewById(R.id.weight_list);
                            WeightAdapter weightAdapter = new WeightAdapter(
                                    getActivity(),
                                    R.layout.fragment_custom_listview,
                                    weights
                            );
                            _weightList.setAdapter(weightAdapter);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}
