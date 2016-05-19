package com.example.iraltman.buzr;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoresFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Deal> deals;

    public StoresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoresFragment newInstance(String param1, String param2) {
        StoresFragment fragment = new StoresFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stores, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        DealsAdapter adapter = new DealsAdapter(getActivity(), R.layout.list_view_item, deals);
//        ListView listView = (ListView) view.findViewById(R.id.deals);
//        listView.setAdapter(adapter);

        ListView listView = (ListView) view.findViewById(R.id.stores);
        listView.setAdapter(adapter);

//        GridView gridView = (GridView) view.findViewById(R.id.stores);
//        gridView.setAdapter(adapter);
    }

    public void setArguments(int category) {
//        call DB with the relevant type
        API api = new API(getResources().getString(R.string.endpoint));
        deals = api.getDeals(category);
        for (Deal deal : deals) {
            Log.i("DEALS", deal.description);
        }
    }
}
