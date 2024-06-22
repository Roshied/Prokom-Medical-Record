package com.ultraman.magazineapp;

import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;

public class ReadWriteMedRec implements Serializable {
    public String nomor, nama, tanggal, gender, pekerjaan, pendidikan, alamat, diagnosa, komplikasi, operasi, dokter;

    public ReadWriteMedRec() {
        // Default constructor required for calls to DataSnapshot.getValue(ReadWriteMedRec.class)
    }

    public ReadWriteMedRec(String nomor, String nama, String tanggal, String gender, String pekerjaan, String pendidikan, String alamat, String diagnosa, String komplikasi, String operasi, String dokter) {
        this.nomor = nomor;
        this.nama = nama;
        this.tanggal = tanggal;
        this.gender = gender;
        this.pekerjaan = pekerjaan;
        this.pendidikan = pendidikan;
        this.alamat = alamat;
        this.diagnosa = diagnosa;
        this.komplikasi = komplikasi;
        this.operasi = operasi;
        this.dokter = dokter;
    }



}