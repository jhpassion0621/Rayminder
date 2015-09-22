package com.anshul.rayminder.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anshul.rayminder.MainActivity;
import com.anshul.rayminder.R;

public class AccountFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private View rootView;

    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AccountFragment() {

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

        rootView = inflater.inflate(R.layout.fragment_account, container, false);
        initControls();
        preLoadAccount();

        return rootView;
    }

    private ImageView ivOption;
    private LinearLayout lytSave;
    private EditText edtName, edtPhone, edtEmail;

    private void initControls() {
        if (rootView == null)
            return;
        rootView.findViewById(R.id.imgNav).setOnClickListener(this);
        ivOption = (ImageView) rootView.findViewById(R.id.imgOption);
        ivOption.setOnClickListener(this);
        lytSave = (LinearLayout) rootView.findViewById(R.id.lytSave);
        lytSave.setVisibility(View.GONE);
        edtName = (EditText) rootView.findViewById(R.id.edtSignName);
        edtPhone = (EditText) rootView.findViewById(R.id.edtSignPhone);
        edtEmail = (EditText) rootView.findViewById(R.id.edtSignEmail);
        rootView.findViewById(R.id.lblSave).setOnClickListener(this);
        rootView.findViewById(R.id.lblCancle).setOnClickListener(this);
    }

    private void preLoadAccount() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgNav:
                NavigationDrawerFragment.mDrawerLayout.openDrawer(NavigationDrawerFragment.mFragmentContainerView);
                break;
            case R.id.imgOption:
                ivOption.setVisibility(View.GONE);
                lytSave.setVisibility(View.VISIBLE);
                edtName.setEnabled(true);
                edtEmail.setEnabled(true);
                edtPhone.setEnabled(true);
                break;

            case R.id.lblCancle:
            case R.id.lblSave:
                ((MainActivity) getActivity()).replaceFragment(ReminderFragment.newInstance("", ""));
                break;
        }
    }
}
