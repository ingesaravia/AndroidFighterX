package com.ils.androidfighterx;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Laura on 29/07/2017.
 */

public class MainItemAdapter extends BaseAdapter{

    private Context miContexto;
    private List<MainItem> items;

    public MainItemAdapter(Context miContexto, List<MainItem> items){
        this.miContexto = miContexto;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (convertView == null){
            //crea una nueva vista en la lista
            LayoutInflater inflater = (LayoutInflater) miContexto
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.mainlist_item, parent, false);
        }
        //agregar la info en la vista
        TextView miOpcion = (TextView) rowView.findViewById(R.id.opMenu);
        String descrip = items.get(position).getOpcion();
        miOpcion.setText(descrip);
        MainItem item = this.items.get(position);

        return rowView;
    }
}
