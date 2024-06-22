package com.ultraman.magazineapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class new_document extends AppCompatActivity {

    Button buttonSubmit;
    TextInputEditText editTextNomor, editTextNama, editTextTanggal, editTextGender, editTextPekerjaan, editTextPendidikan, editTextAlamat, editTextDiagnosa, editTextKomplikasi, editTextOperasi, editTextDokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_document);

        buttonSubmit = findViewById(R.id.btn_submit);
        editTextNomor = findViewById(R.id.no_rekamedis);
        editTextNama = findViewById(R.id.nama_pasien);
        editTextTanggal = findViewById(R.id.tanggal_lahir);
        editTextGender = findViewById(R.id.gender);
        editTextPekerjaan = findViewById(R.id.pekerjaan);
        editTextPendidikan = findViewById(R.id.pendidikan_terakhir);
        editTextAlamat = findViewById(R.id.alamat);
        editTextDiagnosa = findViewById(R.id.diagnosa_utama);
        editTextKomplikasi = findViewById(R.id.komplikasi);
        editTextOperasi = findViewById(R.id.operasi);
        editTextDokter = findViewById(R.id.dokter_rawat);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user == null) {
                    Log.e("new_document", "User not authenticated");
                    Toast.makeText(new_document.this, "User not authenticated", Toast.LENGTH_LONG).show();
                    return;
                }

                String nomor = editTextNomor.getText().toString().trim();
                String nama = editTextNama.getText().toString().trim();
                String tanggal = editTextTanggal.getText().toString().trim();
                String gender = editTextGender.getText().toString().trim();
                String pekerjaan = editTextPekerjaan.getText().toString().trim();
                String pendidikan = editTextPendidikan.getText().toString().trim();
                String alamat = editTextAlamat.getText().toString().trim();
                String diagnosa = editTextDiagnosa.getText().toString().trim();
                String komplikasi = editTextKomplikasi.getText().toString().trim();
                String operasi = editTextOperasi.getText().toString().trim();
                String dokter = editTextDokter.getText().toString().trim();

                Log.d("new_document", "Data to save: " + nomor + ", " + nama + ", " + tanggal + ", " + gender + ", " + pekerjaan + ", " + pendidikan + ", " + alamat + ", " + diagnosa + ", " + komplikasi + ", " + operasi + ", " + dokter);

                ReadWriteMedRec writeMedRec = new ReadWriteMedRec(nomor, nama, tanggal, gender, pekerjaan, pendidikan, alamat, diagnosa, komplikasi, operasi, dokter);

                // Update the database URL to match the correct region
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://ultra-magazine-firebase-default-rtdb.asia-southeast1.firebasedatabase.app");
                DatabaseReference referenceMedRec = database.getReference("Registered Documents");

                // Generate a unique key for each new document with a meaningful prefix or timestamp
                String documentId = "doc_" + System.currentTimeMillis(); // Using a timestamp
                referenceMedRec.child(user.getUid()).child(documentId).setValue(writeMedRec).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("new_document", "Document successfully recorded");
                            Toast.makeText(new_document.this, "Document successfully recorded", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Exception e = task.getException();
                            if (e != null) {
                                Log.e("new_document", "Error saving document", e);
                                Toast.makeText(new_document.this, "Gagal menyimpan dokumen: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            } else {
                                Log.e("new_document", "Task unsuccessful with no exception");
                                Toast.makeText(new_document.this, "Gagal menyimpan dokumen", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
        });
    }
}