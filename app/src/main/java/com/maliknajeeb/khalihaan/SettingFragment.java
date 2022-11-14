package com.maliknajeeb.khalihaan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class SettingFragment extends Fragment {
    private TextView newProfileName, newEmailAddress;
   private Button saveProfileInfo;


    public SettingFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        saveProfileInfo = view.findViewById(R.id.saveProfileInfo);
        newProfileName = view.findViewById(R.id.newProfileName);
        newEmailAddress = view.findViewById(R.id.newEmailAddress);

        saveProfileInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newProfileName.getText().toString().isEmpty()){
                    newProfileName.setError("Field Empty");
                }
                if (newEmailAddress.getText().toString().isEmpty()){
                    newEmailAddress.setError("Field Empty");
                }

                if (!newProfileName.getText().toString().isEmpty() && !newEmailAddress.getText().toString().isEmpty()){

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                        .setDisplayName(newProfileName.getText().toString()).build();

                user.updateEmail(newEmailAddress.getText().toString());
                user.updateProfile(profileUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(), "Restart App to see Changes.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }}
        });
        return view;
    }
}