package com.ultraman.magazineapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button btn_logout;
    Button btn_addDoc;
    FirebaseUser user;
    ListView doc_list;
    ArrayList<ReadWriteMedRec> medRecList;
    MedRecAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        btn_logout = findViewById(R.id.btn_logout);
        btn_addDoc = findViewById(R.id.btn_addDoc);
        doc_list = findViewById(R.id.document_listview);
        medRecList = new ArrayList<>();
        adapter = new MedRecAdapter(this, medRecList);
        doc_list.setAdapter(adapter);

        user = auth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            fetchMedRecData();
        }

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        btn_addDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), new_document.class);
                startActivity(intent);
                finish();
            }
        });

        doc_list.setOnItemClickListener((parent, view, position, id) -> {
            ReadWriteMedRec selectedMedRec = medRecList.get(position);
            Intent intent = new Intent(MainActivity.this, MedRecDetailActivity.class);
            intent.putExtra("medRec", selectedMedRec);
            startActivity(intent);
        });
    }

    private void fetchMedRecData() {
        FirebaseUser user = auth.getCurrentUser();
        if (user == null) return;

        DatabaseReference referenceMedRec = FirebaseDatabase.getInstance("https://ultra-magazine-firebase-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("Registered Documents").child(user.getUid());

        referenceMedRec.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                medRecList.clear();
                for (DataSnapshot medRecSnapshot : snapshot.getChildren()) {
                    ReadWriteMedRec medRec = medRecSnapshot.getValue(ReadWriteMedRec.class);
                    medRecList.add(medRec);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MainActivity", "Error fetching data", error.toException());
                Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_LONG).show();
            }
        });
    }
}
