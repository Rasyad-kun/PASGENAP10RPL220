package com.example.pasgenap10rpl220;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private String BASE_URL = "https://gist.githubusercontent.com/autotrof/172eb06313bebaefbc88ec1b04da4fef/raw/22d8a976db0efba60b7fdd19ac8555880d76daf2/doa-sehari-hari";
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FileAdapter fileAdapter;
    private List<File> fileList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Contact List");

//        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.rvData);
        //menambah data
        addData();

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //menambah fileAdaptor dan menjalankan proses yang ada di adaptor dan mengembalikannya kembali ke Main Activity
        fileAdapter = new FileAdapter(this, fileList, new FileAdapter.Callback() {
            @Override
            public void onClick(int position) {
                //mengirim data ke DetailFile.class
                Toast.makeText(MainActivity.this, "Kontak ke "+(position+1), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), DetailFile.class);
                File file = fileList.get(position);
                intent.putExtra("icon", file.getIcon());
                intent.putExtra("name", file.getName());
                intent.putExtra("telp", file.getTelp());
                intent.putExtra("email", file.getEmail());
                intent.putExtra("address", file.getAddress());
                intent.putExtra("universitas", file.getUniversitas());
                intent.putExtra("company", file.getCompany());
                intent.putExtra("gender", file.getGender());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(fileAdapter);
        fileAdapter.notifyDataSetChanged();
    }

    //Method khusu untuk menambahkan data kedalam array list
    void addData(){

        String[] name = {"Raquela Vakhlov", "Tansy Roussel", "Nanette Allright", "Emanuel Schuh", "Hillie Paulino", "Querida Hurst", "Tito Duran", "Caesar Charity"};
        String[] telp = {"921-137-3372", "478-965-4815", "206-774-2809", "286-951-6069", "587-542-6892", "337-197-0253", "217-835-7317", "740-434-1886"};
        String[] email = {"rvakhlov0@com.com", "troussel1@vimeo.com", "nallright2@jimdo.com", "eschuh3@freewebs.com", "hpaulino4@china.com.cn", "qhurst5@npr.org", "tduran6@blogs.com", "ccharity7@umn.edu"};
        String[] address = {"577 Caliangt Drive", "744 Washington Way", "603 Lyons Street", "3067 Cardinal Circle", "726 Blue Bill Park Point", "24938 Coolidge Pass", "05 Talmadge Point", "41961 Del Mar Trail"};
        String[] universitas = {"Palmer College of Chiropractic", "Josai University", "Fachhochschule St. Pölten", "Towson University", "University of Applied Science and Technology", "Elmergib University", "University of Hull", "Wolaita Sodo University"};
        String[] company = {"Eidel", "Yakitri", "Babblestorm", "Kamba", "Youtags", "Demivee", "Podcat", "Ntag"};
        String[] gender = {"Bigender", "Non-binary", "Male", "Genderfluid", "Agender", "Non-binary", "Non-binary", "Polygender"};

        //memasukkan data ke arraylist dengan referensi File.class
        fileList = new ArrayList<>();
            int i = 0;
            while(i < name.length){
                fileList.add(new File(R.drawable.contact, name[i], telp[i], email[i], address[i], universitas[i], company[i], gender[i]));
                i++;
            }

//        fileList.add(new File("Dimas Maulana", "ini Dimas", R.drawable.contact));
//        fileList.add(new File("Budi", "ini Budi", R.drawable.contact));
//        fileList.add(new File("Ani", "Ini Ani", R.drawable.contact));
//        File Sari = new File("Sari", "xxx");
//        fileList.add(Sari);
    }

    //Dialog Saat Ingin Keluar dari aplikasi
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Keluar ?")
                .setMessage("Apa kau benar benar ingin keluar?")
                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }

//@Override
//public boolean onKeyDown(int keyCode, KeyEvent event) {
//    //Handle the back button
//    if(keyCode == KeyEvent.KEYCODE_BACK && keyCode == KeyEvent.KEYCODE_HOME) {
//        //Ask the user if they want to quit
//        new AlertDialog.Builder(this)
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .setTitle("Closing Activity")
//                .setMessage("Are you sure you want to close this activity?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        //Stop the activity
//                        MainActivity.this.finish();
//                    }
//
//                })
//                .setNegativeButton("No", null)
//                .show();
//
//        return true;
//    }
//    else {
//        return super.onKeyDown(keyCode, event);
//    }
//
//}
}