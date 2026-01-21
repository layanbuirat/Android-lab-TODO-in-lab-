package edu.birzeit.todo3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.animation.AnimationUtils;

public class profileFragment extends Fragment {

    private EditText editTextFullName, editTextStudentId, editTextEmail,
            editTextPhone, editTextDepartment, editTextYear;
    private Button buttonSave, buttonClear;
    private SharedPrefManager sharedPrefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        initViews(view);
        sharedPrefManager = SharedPrefManager.getInstance(getActivity());
        loadProfileData();

        TextView titleText = view.findViewById(R.id.textViewTitle);
        titleText.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearProfile();
            }
        });

        return view;
    }

    private void initViews(View view) {
        editTextFullName = view.findViewById(R.id.editTextFullName);
        editTextStudentId = view.findViewById(R.id.editTextStudentId);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPhone = view.findViewById(R.id.editTextPhone);
        editTextDepartment = view.findViewById(R.id.editTextDepartment);
        editTextYear = view.findViewById(R.id.editTextYear);
        buttonSave = view.findViewById(R.id.buttonSaveProfile);
        buttonClear = view.findViewById(R.id.buttonClearProfile);
    }

    private void loadProfileData() {
        editTextFullName.setText(sharedPrefManager.readString(SharedPrefManager.KEY_FULL_NAME, ""));
        editTextStudentId.setText(sharedPrefManager.readString(SharedPrefManager.KEY_STUDENT_ID, ""));
        editTextEmail.setText(sharedPrefManager.readString(SharedPrefManager.KEY_EMAIL, ""));
        editTextPhone.setText(sharedPrefManager.readString(SharedPrefManager.KEY_PHONE, ""));
        editTextDepartment.setText(sharedPrefManager.readString(SharedPrefManager.KEY_DEPARTMENT, ""));
        editTextYear.setText(sharedPrefManager.readString(SharedPrefManager.KEY_YEAR, ""));
    }

    private void saveProfile() {
        String fullName = editTextFullName.getText().toString().trim();
        String studentId = editTextStudentId.getText().toString().trim();

        if (fullName.isEmpty() || studentId.isEmpty()) {
            Toast.makeText(getActivity(), "Name and Student ID are required", Toast.LENGTH_SHORT).show();
            return;
        }

        buttonSave.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.scale_button));
        sharedPrefManager.writeString(SharedPrefManager.KEY_FULL_NAME, fullName);
        sharedPrefManager.writeString(SharedPrefManager.KEY_STUDENT_ID, studentId);
        sharedPrefManager.writeString(SharedPrefManager.KEY_EMAIL, editTextEmail.getText().toString().trim());
        sharedPrefManager.writeString(SharedPrefManager.KEY_PHONE, editTextPhone.getText().toString().trim());
        sharedPrefManager.writeString(SharedPrefManager.KEY_DEPARTMENT, editTextDepartment.getText().toString().trim());
        sharedPrefManager.writeString(SharedPrefManager.KEY_YEAR, editTextYear.getText().toString().trim());

        Toast.makeText(getActivity(), "Profile saved successfully", Toast.LENGTH_SHORT).show();
    }

    private void clearProfile() {
        editTextFullName.setText("");
        editTextStudentId.setText("");
        editTextEmail.setText("");
        editTextPhone.setText("");
        editTextDepartment.setText("");
        editTextYear.setText("");
        sharedPrefManager.clearProfile();
        Toast.makeText(getActivity(), "Profile cleared", Toast.LENGTH_SHORT).show();
    }
}
