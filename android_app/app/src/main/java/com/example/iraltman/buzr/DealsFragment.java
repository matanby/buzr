package com.example.iraltman.buzr;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DealsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DealsFragment extends Fragment {
    private List<Deal> deals;

    public DealsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DealsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DealsFragment newInstance() {
        DealsFragment fragment = new DealsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();

        API api = new API(getResources().getString(R.string.endpoint));
        deals = api.getDeals(1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deals, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        DealsAdapter adapter = new DealsAdapter(getActivity(), R.layout.list_view_item, deals);
        ListView listView = (ListView) view.findViewById(R.id.deals);
        listView.setAdapter(adapter);

        getActivity().setTitle(R.string.all_stores);
    }
}
