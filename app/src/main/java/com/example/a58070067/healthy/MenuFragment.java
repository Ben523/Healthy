package com.example.a58070067.healthy;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MenuFragment extends Fragment{
    private FirebaseAuth mAuth;
    ArrayList<String> menu = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //SQLiteDatabase myDB = openOrCreateDatabase("my.db",null,MODE_PRIVATE);
        mAuth = FirebaseAuth.getInstance();
        menu.add("BMI");
        menu.add("Weight");
        menu.add("sleep");
        menu.add("Sign OUT");

        final ArrayAdapter<String> menuAdapter = new ArrayAdapter<>(
                getActivity(),android.R.layout.simple_list_item_1,menu
        );

        ListView menuList = getView().findViewById(R.id.menu_list);
        menuList.setAdapter(menuAdapter);

        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("MENU","Select "+menu.get(i));
                switch (menu.get(i))
                {
                    case "BMI":getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_view,new BMIFragment())
                                .commit();
                                Log.d("USER","GO TO BMI");
                                menu.clear();

                    break;
                    case "Weight":getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view,new WeightFragment())
                            .commit();
                            Log.d("USER","GO TO Weight");
                            break;
                    case "sleep":getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view,new SleepFragment())
                            .commit();
                        Log.d("USER","GO TO Weight");
                        break;

                    case "Sign OUT":mAuth.signOut();
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_view,new LoginFragment())
                                .commit();
                    break;
                    default:break;

                }


            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu,container,false);
    }
}
