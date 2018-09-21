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
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.a58070067.healthy.WeightAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class WeightFragment extends Fragment{
    ArrayList<Weight> weights = new ArrayList<>();
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
        String user_id = mAuth.getCurrentUser().getUid();
        docRef = mdb.collection("myfitness").document(user_id)
                .collection("weight").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots,
                                        @javax.annotation.Nullable FirebaseFirestoreException e) {
                        for(QueryDocumentSnapshot doc:queryDocumentSnapshots){
                            if(doc.get("weight") != null){
                                weights.add(doc.toObject(Weight.class));
                            }
                        }
                    }
                });
        addWeightBtn();
        weights.add(new Weight("01 Jan 2018", 63, "UP"));
        weights.add(new Weight("02 Jan 2018", 64, "DOWN"));
        weights.add(new Weight("03 Jan 2018", 63, "UP"));

        ListView _weightList = getView().findViewById(R.id.weight_list);
        WeightAdapter weightAdapter = new WeightAdapter(
                getActivity(),
                R.layout.fragment_custom_listview,
                weights
        );
        _weightList.setAdapter(weightAdapter);


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
