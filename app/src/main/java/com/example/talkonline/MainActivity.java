package com.example.talkonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView signUpText;
    private Button blogin;
    private TextView email,password;
    LoadingClass load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load = new LoadingClass(MainActivity.this);
        signUpText = findViewById(R.id.signUp);
        blogin = findViewById(R.id.blogin);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            load.startLoading();
            Intent intent = new Intent(MainActivity.this,ChatScreenActivity.class);
            startActivity(intent);
            load.dismissLoading();
            finish();
        }

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load.startLoading();
                String semail = String.valueOf(email.getText());
                String spassword = String.valueOf(password.getText());
                FirebaseAuth.getInstance().signInWithEmailAndPassword(semail,spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            load.dismissLoading();
                            Intent intent = new Intent(MainActivity.this,ChatScreenActivity.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            load.dismissLoading();
                            Toast.makeText(MainActivity.this, "Error in login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}