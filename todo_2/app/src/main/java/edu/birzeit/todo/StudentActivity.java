package edu.birzeit.todo;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class StudentActivity extends Activity {

    Button contact, emergency, directions, meeting;
    Spinner spinner;
    String username, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        username = getIntent().getStringExtra("username");
        role = getIntent().getStringExtra("role");

        contact = (Button) findViewById(R.id.contact);
        emergency = (Button) findViewById(R.id.emergency);
        directions = (Button) findViewById(R.id.directions);
        meeting = (Button) findViewById(R.id.meeting);
        spinner = (Spinner) findViewById(R.id.spinner);

        if (!role.equals("systemAdmin")) {
            meeting.setVisibility(View.GONE);
        }

        String[] locations = {"Library", "Cafeteria", "IT_Dep"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locations);
        spinner.setAdapter(adapter);

        contact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:professor@birzeit.edu"));
                email.putExtra(Intent.EXTRA_SUBJECT, "Question from " + username);
                startActivity(email);
            }
        });

        emergency.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(StudentActivity.this, "alert sent!", Toast.LENGTH_SHORT).show();
            }
        });

        directions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String location = spinner.getSelectedItem().toString();
                String geoUri = "geo:31.9038,35.2034";

                if (location.equals("Cafeteria")) {
                    geoUri = "geo:31.9048,35.2044";
                } else if (location.equals("IT_Dep")) {
                    geoUri = "geo:31.9058,35.2054";
                }

                Intent maps = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                startActivity(maps);
            }
        });

        meeting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(StudentActivity.this, "Meeting in 1 hour", Toast.LENGTH_SHORT).show();
            }
        });
    }
}