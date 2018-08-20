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

public class RegisterFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRegisterBtn(savedInstanceState);

    }

    private void initRegisterBtn(final Bundle savedInstanceState)
    {
        Button registerBtn = getView().findViewById(R.id.register_register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userID = getView().findViewById(R.id.register_user_id);
                EditText nameText = getView().findViewById(R.id.register_name);
                EditText ageText = getView().findViewById(R.id.register_age);
                EditText passText = getView().findViewById(R.id.register_password);
                String username = userID.getText().toString();
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();
                String password = passText.getText().toString();

                if(username.isEmpty() || name.isEmpty() || age.isEmpty() || password.isEmpty()){
                    Log.d("USER","FIELD NAME IS EMPTY");
                    Toast.makeText(getActivity(), "FIELD NAME IS EMPTY", Toast.LENGTH_SHORT).show();
                }else if(username.equals("admin")){
                    Log.d("USER","USER ALREADY EXIST");
                    Toast.makeText(getActivity(), "USER ALREADY EXIST", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("USER","GO TO BMI");
                    if(savedInstanceState == null){
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_view,new BMIFragment())
                                .commit();
                    }
                }
            }
        });
    }
}
