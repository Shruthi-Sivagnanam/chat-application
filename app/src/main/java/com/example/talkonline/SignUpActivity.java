package com.example.talkonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private TextView loginText;

    private EditText username,email,password,cpassword;

    private Button signup;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Getting the values from the sign up activity.
        loginText = findViewById(R.id.login);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email1);
        password = findViewById(R.id.password1);
        cpassword = findViewById(R.id.confirmPassword1);
        signup = findViewById(R.id.bsignup);

        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent = new Intent(SignUpActivity.this,ChatScreenActivity.class);
            startActivity(intent);
            finish();
        }

        //Moving the activity to main activity.
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //signup check
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String semail = String.valueOf(email.getText());
                String spassword = String.valueOf(password.getText());
                String scpassword = String.valueOf(cpassword.getText());
                String susername = String.valueOf(username.getText());

                if(!semail.isEmpty() && !susername.isEmpty()) {
                    if(spassword.length()==7){
                        if(spassword.equals(scpassword)){
                            FirebaseAuth.getInstance().createUserWithEmailAndPassword(semail,spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        FirebaseDatabase.getInstance().getReference("user/"+FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(new User(susername,semail,""));
                                        Intent intent = new Intent(SignUpActivity.this,ChatScreenActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(SignUpActivity.this,"Signed Up successfully",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(SignUpActivity.this,"Error in signup",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else
                            Toast.makeText(SignUpActivity.this,"Password doesn't match",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(SignUpActivity.this,"Password length should be 7.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}