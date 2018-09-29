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
import android.widget.TextView;
import android.widget.Toast;

import com.zorail.powermanager.Data.BoardDetails;
import com.zorail.powermanager.Data.Usage;
import com.zorail.powermanager.Data.database.DataBaseSource;
import com.zorail.powermanager.Data.database.FirebaseDatabaseService;
import com.zorail.powermanager.R;
import com.zorail.powermanager.Util.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

public class HomeFragment extends Fragment implements HomeContract.View {

    private ProgressBar progressBar;
    private View contentContainer;
    private DataBaseSource dataBaseSource = FirebaseDatabaseService.getInstance();
    private CompositeDisposable disposable = new CompositeDisposable();
    private SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();

    private TextView present_units, present_month;

    HomePresenter presenter;

    public HomeFragment(){}

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(presenter == null) {
            presenter = new HomePresenter(this, disposable, dataBaseSource, schedulerProvider);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);
        contentContainer = v.findViewById(R.id.contentContainer);
        progressBar = v.findViewById(R.id.progressBar);
        present_units = v.findViewById(R.id.present_units);
        present_month = v.findViewById(R.id.present_month);
        presenter.getUsersUsage("8658558521");
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
            contentContainer.setVisibility(View.INVISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            contentContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setUsageDetails(Usage usage, BoardDetails boardDetails) {
        present_units.setText(String.valueOf(usage.getP_units()));
        present_month.setText(getMonthFromIndex(usage.getP_month()));
    }

    private String getMonthFromIndex(int index) {
        String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "June", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
        return months[index-1];
    }
}
