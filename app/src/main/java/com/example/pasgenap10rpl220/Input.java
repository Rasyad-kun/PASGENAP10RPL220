package com.example.pasgenap10rpl220;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Input extends AppCompatActivity {

    private String name, email, phone, about, gender;
    private int age;
    private ArrayList<File> fileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        fileList = new ArrayList<>();

        // Casting Component
        TextInputEditText txt_name = findViewById(R.id.txt_name);
        TextInputEditText txt_age = findViewById(R.id.txt_age);
        TextInputEditText txt_email = findViewById(R.id.txt_email);
        TextInputEditText txt_phone = findViewById(R.id.txt_phone);
        TextInputEditText txt_about = findViewById(R.id.txt_about);

        AutoCompleteTextView txt_gender = findViewById(R.id.txt_gender);

        Button btn_add = findViewById(R.id.btn_add);
        Button btn_intent = findViewById(R.id.btn_intent);

        // Spinner Adapter
        String[] items = new String[]{"Male", "Female", "Non-binary"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, items);
        txt_gender.setAdapter(arrayAdapter);

        // Spinner OnItemClick
        txt_gender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gender = items[position];
                Toast.makeText(getApplicationContext(), gender, Toast.LENGTH_SHORT).show();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txt_name.getText().toString();
                String ageString = txt_age.getText().toString();
                email = txt_email.getText().toString();
                phone = txt_phone.getText().toString();
                about = txt_about.getText().toString();

                if (name.trim().isEmpty() || ageString.trim().isEmpty() || email.trim().isEmpty() || phone.trim().isEmpty() || about.trim().isEmpty() || gender.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields!", Toast.LENGTH_SHORT).show();
                } else {
                    age = Integer.parseInt(txt_age.getText().toString());

//                    fileList.add(new File(name, about, R.drawable.contact, phone, email, age, gender));

                    txt_name.setText("");
                    txt_age.setText("");
                    txt_email.setText("");
                    txt_phone.setText("");
                    txt_about.setText("");
                    txt_gender.clearListSelection();

                    Toast.makeText(getApplicationContext(), "Data has been successfully added!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("fileList", fileList);
                startActivity(intent);
            }
        });
    }
}