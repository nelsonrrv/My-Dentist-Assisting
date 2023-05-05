package com.example.mydentistassisting;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdaptadorPaciente extends BaseAdapter {

    Activity mActivity;
    DataBaseHelper dataBaseHelper;

    public AdaptadorPaciente(Activity mActivity, DataBaseHelper dataBaseHelper) {
        this.mActivity = mActivity;
        this.dataBaseHelper = dataBaseHelper;
    }

    @Override
    public int getCount() {
        return dataBaseHelper.getEveryone().size();
    }

    @Override
    public Pacientes getItem(int position) {

        return dataBaseHelper.getEveryone().get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View onePacienteLine;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        onePacienteLine = inflater.inflate(R.layout.paciente_unalinea, parent , false);

        TextView nomP = onePacienteLine.findViewById(R.id.nomP);
        TextView ciP = onePacienteLine.findViewById(R.id.ciP);
        TextView edadP = onePacienteLine.findViewById(R.id.edadP);
        TextView celP = onePacienteLine.findViewById(R.id.celP);
        TextView histoN = onePacienteLine.findViewById(R.id.histoN);
        TextView repP = onePacienteLine.findViewById(R.id.repP);

        Pacientes p = this.getItem(position);

        nomP.setText(p.getNombre());
        ciP.setText(Integer.toString(p.getCi()));
        edadP.setText(Integer.toString(p.getEdad()));
        celP.setText(p.getCel());
        histoN.setText(p.getId());
        repP.setText(p.getRepre());

        return onePacienteLine;
    }
}
