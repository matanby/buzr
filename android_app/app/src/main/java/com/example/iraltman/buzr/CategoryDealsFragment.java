package com.example.iraltman.buzr;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;


/**
 * Use the {@link CategoryDealsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryDealsFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CATEGORY_ID = "category_id";

    private int categoryId;

//    private OnFragmentInteractionListener mListener;

    private List<Deal> deals;

    public CategoryDealsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CategoryDealsFragment.
     */
    public static CategoryDealsFragment newInstance(int categoryId) {
        CategoryDealsFragment fragment = new CategoryDealsFragment();
        Bundle args = new Bundle();
        args.putInt(CATEGORY_ID, categoryId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        API api = new API("http://132.65.251.197:8080");
        if (getArguments() != null) {
            categoryId = getArguments().getInt(CATEGORY_ID);

            deals = api.getDeals(categoryId);
        } else {
            deals = new LinkedList<>();
        }
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
    }
}
