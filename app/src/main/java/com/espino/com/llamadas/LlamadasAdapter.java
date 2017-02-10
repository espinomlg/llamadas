package com.espino.com.llamadas;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by espino on 10/02/17.
 */
public class LlamadasAdapter extends CursorAdapter {


    public LlamadasAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater lf = LayoutInflater.from(context);
        View rootView = lf.inflate(R.layout.listitem_llamada, viewGroup, false);
        Holder holder = new Holder();
        holder.tipo = (TextView)rootView.findViewById(R.id.lisitem_tipo);
        holder.numero = (TextView)rootView.findViewById(R.id.lisitem_numero);
        holder.duracion = (TextView)rootView.findViewById(R.id.listitem_duracion);
        rootView.setTag(holder);

        return rootView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Holder holder = (Holder)view.getTag();
        switch (cursor.getInt(0)){
            case CallLog.Calls.INCOMING_TYPE:
                holder.tipo.setText("Entrante");
                break;
            case CallLog.Calls.OUTGOING_TYPE:
                holder.tipo.setText("Saliente");
                break;
            case CallLog.Calls.MISSED_TYPE:
                holder.tipo.setText("Perdida");
                break;
        }
        holder.numero.setText(cursor.getString(1));
        holder.duracion.setText(cursor.getString(2));
    }

    private class Holder{
        private TextView tipo, numero, duracion;
    }
}
