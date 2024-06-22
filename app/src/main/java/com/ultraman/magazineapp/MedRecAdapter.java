package com.ultraman.magazineapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MedRecAdapter extends BaseAdapter {
    private Context context;
    private List<ReadWriteMedRec> medRecList;

    public MedRecAdapter(Context context, List<ReadWriteMedRec> medRecList) {
        this.context = context;
        this.medRecList = medRecList;
    }

    @Override
    public int getCount() {
        return medRecList.size();
    }

    @Override
    public Object getItem(int position) {
        return medRecList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_med_rec, parent, false);
        }

        TextView tvNomor = convertView.findViewById(R.id.tv_nomor);
        TextView tvDiagnosa = convertView.findViewById(R.id.tv_diagnosa);

        ReadWriteMedRec medRec = medRecList.get(position);
        tvNomor.setText(medRec.nomor);
        tvDiagnosa.setText(medRec.diagnosa);

        return convertView;
    }
}
