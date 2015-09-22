package com.anshul.rayminder.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.anshul.rayminder.MainActivity;
import com.anshul.rayminder.R;
import com.anshul.rayminder.adapter.AddCategoryAdapter;
import com.anshul.rayminder.model.Category;

import java.util.ArrayList;
import java.util.List;

public class AddCategoryFragment extends Fragment implements View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private View rootView;
    private List<Category> categories;

    private String[] categoryName = {"Birthday Dates","Marriage Anniversary Date","Death Anniversary Date","Children School Activity","Husband/ Suppose Event","Meeting Date","Add New"};

    public static AddCategoryFragment newInstance(String param1, String param2) {
        AddCategoryFragment fragment = new AddCategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AddCategoryFragment() {

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
    public void onDetach() {
        super.onDetach();
    }

    private AddCategoryAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_category, container, false);
        GridView grid = (GridView) rootView.findViewById(R.id.gridCategory);
        categories = getCategory();
        adapter = new AddCategoryAdapter(getActivity(), categories, 0);
        grid.setAdapter(adapter);
        EditText editText = (EditText) rootView.findViewById(R.id.lblName);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemIndex(position);
                ((MainActivity)getActivity()).replaceFragment(CreateReminderFragment.newInstance("",""));
            }
        });

        rootView.findViewById(R.id.lblCancle).setOnClickListener(this);
        rootView.findViewById(R.id.lblSave).setOnClickListener(this);

        return rootView;
    }

    private List<Category> getCategory() {

        List<Category> categories = new ArrayList<>();
        for(int i = 0 ; i < categoryName.length ; i++){
            Category item = new Category();
            item.name = categoryName[i];
            categories.add(item);
        }
        return categories;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.lblCancle:
                break;
            case R.id.lblSave:
                ((MainActivity)getActivity()).replaceFragment(CreateReminderFragment.newInstance("",""));
                break;
        }
    }
}
