package com.zorail.powermanager.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zorail.powermanager.R;

public class HomeFragment extends Fragment implements HomeContract.View {

    private ProgressBar progressBar;
    private View contentContainer;

    HomePresenter presenter;

    public HomeFragment(){}

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            getActivity().setTitle("Home");
        } catch (Exception e) {
            Log.d("Exception in Home",e.toString());
        }
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = (HomePresenter) presenter;
    }

    @Override
    public void makeToast(int stringId) {
        Toast.makeText(getActivity(), getString(stringId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressIndicator(Boolean show) {
        if(show) {
            progressBar.setVisibility(View.VISIBLE);
            contentContainer.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            contentContainer.setVisibility(View.VISIBLE);
        }
    }
}
