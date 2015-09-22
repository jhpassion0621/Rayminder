package com.anshul.rayminder.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.anshul.rayminder.App;
import com.anshul.rayminder.MainActivity;
import com.anshul.rayminder.R;
import com.anshul.rayminder.adapter.GridAdapter;
import com.anshul.rayminder.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int[] resoucesId = {R.drawable.ic_category_family_fill, R.drawable.ic_category_shopping_fill,
            R.drawable.ic_category_health_fill, R.drawable.ic_category_regilious_fill,
            R.drawable.ic_category_work_fill, R.drawable.ic_category_add_fill};

    private String[] categoryName = {"Family & Friends","Shopping","Health","Religous","Work","Add New"};
    private boolean isSetCompany = false;

    private String mParam1;
    private String mParam2;

    private View rootView;
    private List<Category> categories;

    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CategoryFragment() {

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

        rootView = inflater.inflate(R.layout.fragment_category, container, false);
        GridView grid = (GridView) rootView.findViewById(R.id.gridCategory);
        categories = getCategory();
        grid.setAdapter(new GridAdapter(getActivity(), categories));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == categoryName.length - 1) {
                    ((MainActivity) getActivity()).replaceFragment(AddCategoryFragment.newInstance("", ""));
                }
            }
        });

        ImageView ivSwitch = (ImageView)rootView.findViewById(R.id.imgType);
        ivSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSetCompany){
                    ((ImageView)v).setImageResource(R.drawable.ic_switch_company);
                    isSetCompany = false;
                }else{
                    ((ImageView)v).setImageResource(R.drawable.ic_switch_personal);
                    isSetCompany = true;
                }
            }
        });

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private List<Category> getCategory() {
        List<Category> categories = new ArrayList<>();
        for(int i = 0 ; i < resoucesId.length ; i++){
            Category item = new Category();
            item.imageResource = resoucesId[i];
            item.name = categoryName[i];
            categories.add(item);
        }
        return categories;
    }
}
