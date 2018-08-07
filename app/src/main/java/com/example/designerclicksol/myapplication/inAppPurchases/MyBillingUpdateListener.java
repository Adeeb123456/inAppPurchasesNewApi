package com.example.designerclicksol.myapplication.inAppPurchases;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.Purchase;
import com.example.designerclicksol.myapplication.inAppPurchases.BillingUpdatesListener;

import java.util.List;

public class MyBillingUpdateListener implements BillingUpdatesListener {
    @Override
    public void onBillingClientSetupFinished() {


    }

    @Override
    public void onConsumeFinished(String token, int result) {

        if (result == BillingClient.BillingResponse.OK) {
        }
    }

    @Override
    public void onPurchasesUpdated(List<Purchase> purchases) {

        for (Purchase p : purchases) {

            //update ui

        }



    }
}