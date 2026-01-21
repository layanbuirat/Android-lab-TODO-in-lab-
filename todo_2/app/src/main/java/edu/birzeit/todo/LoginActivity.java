package edu.birzeit.todo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;
import android.widget.*;
public class LoginActivity extends Activity {
    EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();

            if (!user.isEmpty() && !pass.isEmpty()) {
                Intent intent = new Intent(LoginActivity.this, StudentActivity.class);
                intent.putExtra("username", user);

                if (user.equals("admin")) {
                    intent.putExtra("role", "systemAdmin");
                } else {
                    intent.putExtra("role", "student");
                }

                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "enter username and password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}