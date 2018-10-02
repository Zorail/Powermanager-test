package com.zorail.powermanager.CreateAccount;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zorail.powermanager.Nav_Drawer;
import com.zorail.powermanager.R;
import com.zorail.powermanager.Util.SessionManager;

public class CreateAccountFragment extends Fragment implements CreateAccountContract.View{

    private ProgressBar progressBar;
    private ImageButton submit;
    private EditText accountId, phone;
    CreateAccountPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(presenter == null) {
            presenter = new CreateAccountPresenter(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_account_fragment, container, false);
        accountId = v.findViewById(R.id.accountId);
        phone = v.findViewById(R.id.phone);
        submit = v.findViewById(R.id.signUpButton);
        progressBar = v.findViewById(R.id.createProgress);
        progressBar.setVisibility(View.GONE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendVerificationCode(phone.getText().toString());
            }
        });
        return v;
    }

    @Override
    public void setPresenter(CreateAccountContract.Presenter presenter) {
        this.presenter = (CreateAccountPresenter) presenter;
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void makeToast(int stringId) {
        Toast.makeText(getActivity(), getString(stringId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressIndicator(Boolean show) {
        if(show) {
            progressBar.setVisibility(View.VISIBLE);
            submit.setVisibility(View.INVISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            submit.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setCodeAutomatically(String code) {

    }

    @Override
    public void createAccount(Credentials credentials) {

    }

    @Override
    public void startHomeActivity(Boolean bool) {
        if(getActivity() != null) {
            SessionManager sessionManager = new SessionManager(getActivity());
            sessionManager.setLogin(true);
            Intent i = new Intent(getActivity(), Nav_Drawer.class);
            startActivity(i);
        }
    }
}
