package com.example.studytime;


import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private EditText name,email_,mdp;
    private Button enregister;
    private static String chosen;
    public static String  setChosen() {
        return chosen;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_register);

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.departement, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = parent.getItemAtPosition(position).toString();

                Toast.makeText(getBaseContext(),  spinnerValue, Toast.LENGTH_SHORT).show();
                chosen = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mAuth = FirebaseAuth.getInstance();
        enregister = (Button) findViewById(R.id.enregister);
        enregister.setOnClickListener(this);

        name = (EditText) findViewById(R.id.fullname);
        email_ = (EditText) findViewById(R.id.email2);
        mdp = (EditText) findViewById(R.id.Password2);


    }

    @Override
    public void onClick(View v) {
        registerUser();

    }



    private void registerUser() {
            String email= email_.getText().toString().trim();
            String password = mdp.getText().toString().trim();
            String fullname = name.getText().toString().trim();



            if(fullname.isEmpty()){
                name.setError("champs est vide!");
                name.requestFocus();
                return;
            }

            if(chosen.isEmpty()){
                name.setError("champs est vide!");
                name.requestFocus();
                return;
            }


            if(email.isEmpty()){
                email_.setError("champs est vide!");
                email_.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                email_.setError("email non valid");
                email_.requestFocus();
                return;
            }
            if(password.isEmpty()){
                mdp.setError("champs est vide!");
                mdp.requestFocus();
                return;
            }
            if(password.length()<6){
                mdp.setError("mdp doit avoir au min 6 characters!");
                mdp.requestFocus();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Users users = new Users(fullname,chosen,email);

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(Register.this, "vous vous êtes enregistré avec succès", Toast.LENGTH_LONG).show();

                                        }else{
                                            Toast.makeText(Register.this,"oups! essayez encore une fois", Toast.LENGTH_LONG).show();
                                        }

                                    }
                                });

                            }else {
                                Toast.makeText(Register.this," oups! essayez encore une fois", Toast.LENGTH_LONG).show();


                            }
                        }
                    });


        }
}