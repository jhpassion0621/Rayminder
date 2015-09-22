package com.anshul.rayminder.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.anshul.rayminder.App;
import com.anshul.rayminder.LoginActivity;
import com.anshul.rayminder.MainActivity;
import com.anshul.rayminder.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{
    private View rootView;
    private RadioGroup radioGroup;
    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        initControls();
        return rootView;
    }
    private void initControls(){
        Button btnLogin = (Button)rootView.findViewById(R.id.btnLogin);
        Button btnSignup = (Button)rootView.findViewById(R.id.btnSignup);
        EditText edtEmail = (EditText)rootView.findViewById(R.id.txtEmail);
        EditText edtPass = (EditText)rootView.findViewById(R.id.txtPassword);
        ImageView imgFB = (ImageView)rootView.findViewById(R.id.btnFB);
        ImageView imgGoogle = (ImageView)rootView.findViewById(R.id.btnGoogle);

        radioGroup = (RadioGroup)rootView.findViewById(R.id.radioLanguage);
        rootView.findViewById(R.id.radioEnglish).setOnClickListener(this);
        rootView.findViewById(R.id.radioSpanish).setOnClickListener(this);
        radioGroup.setOnClickListener(this);

        edtEmail.setTypeface(App.tf);
        btnLogin.setTypeface(App.tf);
        btnSignup.setTypeface(App.tf);

        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);

        imgFB.setOnClickListener(this);
        imgGoogle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnLogin:
                startActivity(new Intent(getActivity() , MainActivity.class));
                break;
            case R.id.btnSignup:
                ((LoginActivity)getActivity()).replaceFragment(SignupFragment.newInstance("",""));
                break;
            case R.id.btnFB:
                break;
            case R.id.btnGoogle:
                break;
            case R.id.radioLanguage:
                int selectedId = radioGroup.getCheckedRadioButtonId();
                break;
            case R.id.radioEnglish:
                Toast.makeText(getActivity(), "selected English", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radioSpanish:
                Toast.makeText(getActivity(), "selected Spanish", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
