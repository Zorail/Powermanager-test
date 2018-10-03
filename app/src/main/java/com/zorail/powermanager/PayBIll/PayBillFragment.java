package com.zorail.powermanager.PayBIll;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.zorail.powermanager.R;

import org.json.JSONObject;

public class PayBillFragment extends Fragment implements PaymentResultListener {

    private static final String TAG = PayBillFragment.class.getSimpleName();
    Button pay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.paybill_fragment, container, false);
        pay = v.findViewById(R.id.btn_pay);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            getActivity().setTitle("Pay Bill");
        } catch (Exception e) {
            Log.d("Exception in payBill", e.toString());

        }
        Checkout.preload(getActivity().getApplicationContext());


    }

    private void startPayment() {
        final Activity activity = getActivity();
        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "PowerManager");
            options.put("description", "Pay Bill");
            options.put("currency", "INR");
            options.put("amount", "120");

//            Your json data here
            JSONObject preFill = new JSONObject();
            if(FirebaseAuth.getInstance().getCurrentUser() != null && activity != null) {
                preFill.put("contact", FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
                options.put("prefill", preFill);
                co.open(activity, options);
            }

        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        try {
            Toast.makeText(getActivity(), "Payment Successful: " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        try {
            Toast.makeText(getActivity(), "Payment failed: " + i + " " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
}
