package com.example.pasgenap10rpl220;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class DetailFile extends AppCompatActivity {
    ImageView dIcon;
    TextView dTitle, dDeskripsi;
    String name, telp, email, addres, universitas, company, gender;
    int icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_file);

        dIcon = findViewById(R.id.dIcon);
        dTitle = findViewById(R.id.dTitle);
        dDeskripsi = findViewById(R.id.dDeskripsi);

        //menangkap data dari MainActivity
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            icon = bundle.getInt("icon");
            name = bundle.getString("name");
            telp = bundle.getString("telp");
            email = bundle.getString("email");
            addres = bundle.getString("addres");
            universitas = bundle.getString("universitas");
            company = bundle.getString("company");
            gender = bundle.getString("gender");
        }

        //mereplace id item dengan data yang telah ditangkap
        dIcon.setImageResource(icon);
        dTitle.setText(name);
        dDeskripsi.setText("\nNo telp : "+telp+"\nEmail : "+email+"\nAlamat : "+addres+"\nUniversitas : "+universitas+"\nPerusahaan : "+company+"\nGender : "+gender);

        dIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailFile.this, name, Toast.LENGTH_SHORT).show();
            }
        });

        //tombol kembali ke MainActivity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(name);
    }
}