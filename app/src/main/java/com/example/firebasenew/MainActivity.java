package com.example.firebasenew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
Button b1;
EditText t1,t2;
ProgressBar p1;
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button);
        t1=(EditText)findViewById(R.id.editText);
        t2=(EditText)findViewById(R.id.editText2);
        t2.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        p1=(ProgressBar)findViewById(R.id.progressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=t1.getText().toString();
                String s2=t2.getText().toString();
                if(s1.isEmpty())
                {
                    t1.setError("Fill Email Id");
                    return;
                }
                else
                {
                    if(s2.isEmpty())
                    {
                        t2.setError("Fill the Password");
                        return;
                    }
                    }
                p1.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Database Updated", Toast.LENGTH_SHORT).show();
                            p1.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Database Not Updated", Toast.LENGTH_SHORT).show();
                            p1.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
    }
}