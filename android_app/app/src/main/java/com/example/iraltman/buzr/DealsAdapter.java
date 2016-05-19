package com.example.iraltman.buzr;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

public class DealsAdapter extends ArrayAdapter<Deal> {
    private API api = new API(getContext().getResources().getString(R.string.endpoint));

    public DealsAdapter(Context context, int resource, List<Deal> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, parent, false);
        }

        String url = api.buildUrl(getItem(position).photoUrl);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.deal_img);
        Picasso.with(getContext()).load(url).into(imageView);

        return convertView;
    }
}
