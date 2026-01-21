package edu.birzeit.todo3;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
public class MainActivity extends AppCompatActivity {
    private Button buttonProfile, buttonNotes;
    private profileFragment profileFragment;
    private notesFragment notesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupFragments();
        setNavigationListeners();

        switchToFragment(profileFragment, buttonProfile);
    }

    private void initViews() {
        buttonProfile = findViewById(R.id.buttonProfile);
        buttonNotes = findViewById(R.id.buttonNotes);

        profileFragment = new profileFragment();
        notesFragment = new notesFragment();
    }

    private void setupFragments() {
    }

    private void setNavigationListeners() {
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToFragment(profileFragment, buttonProfile);
            }
        });

        buttonNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToFragment(notesFragment, buttonNotes);
            }
        });
    }

    private void switchToFragment(Fragment fragment, Button activeButton) {
        updateButtonColors(activeButton);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void updateButtonColors(Button activeButton) {
        buttonProfile.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        buttonNotes.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        activeButton.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
    }
}

