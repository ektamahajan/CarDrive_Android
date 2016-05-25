package com.example.ekta.apfinalproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class SettingsActivity extends AppCompatActivity {

    Firebase ref = new Firebase("https://apfinalproject.firebaseio.com/");
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button deleteAccount = (Button) findViewById(R.id.deleteAccount);
        Button changePassword = (Button) findViewById(R.id.changePassword);
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog alertDialog = new Dialog(context); //Read Update
                alertDialog.setContentView(R.layout.custom);
                alertDialog.setTitle("Delete Account");
                final EditText editEmail = (EditText) alertDialog.findViewById(R.id.edit_text_email);
                final EditText editPass = (EditText) alertDialog.findViewById(R.id.edit_text_pass);
                Button submitDelete = (Button) alertDialog.findViewById(R.id.submit_delete);

                submitDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = editEmail.getText().toString();
                        String pass = editPass.getText().toString();
                        ref.removeUser(id, pass, new Firebase.ResultHandler() {
                            @Override
                            public void onSuccess() {
                                // password reset email sent
                                Toast.makeText(getApplicationContext(), "User Removed Successfully",
                                        Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(FirebaseError firebaseError) {
                                // error encountered
                                Toast.makeText(getApplicationContext(), "Incorrect User",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                alertDialog.show();

            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(SettingsActivity.this).create(); //Read Update
                alertDialog.setTitle("Enter Email");
                final EditText input = new EditText(SettingsActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);

                alertDialog.setButton("Continue..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // here you can add functions
                        String id = input.getText().toString();
                        ref.resetPassword(id, new Firebase.ResultHandler() {
                            @Override
                            public void onSuccess() {
                                // password reset email sent
                                Toast.makeText(getApplicationContext(), "Email sent Successfully",
                                        Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(FirebaseError firebaseError) {
                                // error encountered
                                Toast.makeText(getApplicationContext(), "Incorrect User",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                alertDialog.show();

            }
        });
        YoYo.with(Techniques.StandUp)
                .duration(1500)
                .playOn(findViewById(R.id.settingsActivity));

    }
}
