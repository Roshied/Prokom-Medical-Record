package com.ultraman.magazineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MedRecDetailActivity extends AppCompatActivity {
    TextView tvNomor, tvNama, tvTanggal, tvGender, tvPekerjaan, tvPendidikan, tvAlamat, tvDiagnosa, tvKomplikasi, tvOperasi, tvDokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_rec_detail);

        tvNomor = findViewById(R.id.tv_nomor);
        tvNama = findViewById(R.id.tv_nama);
        tvTanggal = findViewById(R.id.tv_tanggal);
        tvGender = findViewById(R.id.tv_gender);
        tvPekerjaan = findViewById(R.id.tv_pekerjaan);
        tvPendidikan = findViewById(R.id.tv_pendidikan);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvDiagnosa = findViewById(R.id.tv_diagnosa);
        tvKomplikasi = findViewById(R.id.tv_komplikasi);
        tvOperasi = findViewById(R.id.tv_operasi);
        tvDokter = findViewById(R.id.tv_dokter);

        ReadWriteMedRec medRec = (ReadWriteMedRec) getIntent().getSerializableExtra("medRec");

        if (medRec != null) {
            tvNomor.setText(medRec.nomor);
            tvNama.setText(medRec.nama);
            tvTanggal.setText(medRec.tanggal);
            tvGender.setText(medRec.gender);
            tvPekerjaan.setText(medRec.pekerjaan);
            tvPendidikan.setText(medRec.pendidikan);
            tvAlamat.setText(medRec.alamat);
            tvDiagnosa.setText(medRec.diagnosa);
            tvKomplikasi.setText(medRec.komplikasi);
            tvOperasi.setText(medRec.operasi);
            tvDokter.setText(medRec.dokter);
        }
    }
}
