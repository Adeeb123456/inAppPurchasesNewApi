package com.example.designerclicksol.myapplication;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.example.designerclicksol.myapplication.inAppPurchases.BillingManager;
import com.example.designerclicksol.myapplication.inAppPurchases.BillingUpdatesListener;
import com.example.designerclicksol.myapplication.inAppPurchases.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyBillingUpdateListener hh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initInapp();

        billingManager = new BillingManager(MainActivity.this, new BillingUpdatesListener() {
            @Override
            public void onBillingClientSetupFinished() {
            billingManager.queryPurchases();
            }

            @Override
            public void onConsumeFinished(String token, int result) {

            }

            @Override
            public void onPurchasesUpdated(List<Purchase> purchases) {

            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              purchase();
            }
        });
    }

    BillingManager billingManager;
    public void initInappPurchases(){
        billingManager=new BillingManager(MainActivity.this,new MyBillingUpdateListener(){
            @Override
            public void onBillingClientSetupFinished() {
                super.onBillingClientSetupFinished();
            }

            @Override
            public void onConsumeFinished(String token, int result) {
                super.onConsumeFinished(token, result);
            }

            @Override
            public void onPurchasesUpdated(List<Purchase> purchases) {
                super.onPurchasesUpdated(purchases);
            }
        });
    }

    public void purchase(){
        billingManager.initiatePurchaseFlow("1b", null, BillingClient.SkuType.INAPP);
    }



    public void consumeAsyn(){
        billingManager.consumeAsync("token");
    }





















    String adsSKU="";
    int skuId=0;
    BillingClient billingClient;
    public void initInapp(){

        List<String> list=new ArrayList<>();
        list.add(adsSKU);

        billingClient = BillingClient.newBuilder(this).setListener(new PurchasesUpdatedListener() {
            @Override
            public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {
                Log.d("debug"," response purchsse"+responseCode);
            }

        }).build();

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(int responseCode) {
                Log.d("debug","responseCode "+responseCode);
                purchaseItem(null);
            }

            @Override
            public void onBillingServiceDisconnected() {
                Log.d("debug","disconnect ");
            }
        });

     /*   SkuDetailsParams params = SkuDetailsParams.newBuilder().setSkusList(list).setType(BillingClient.SkuType.INAPP).build();
        //params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
        //params.setSkusList(list).setType(BillingClient.SkuType.INAPP);


        billingClient.querySkuDetailsAsync(params, new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(int responseCode, List<SkuDetails> skuDetailsList) {
                Log.d("debug","responseCode sku list "+responseCode);

                Log.d("debug","responseCode sku list ");

                if (responseCode == BillingClient.BillingResponse.OK
                        && skuDetailsList != null) {
                    for (SkuDetails skuDetails : skuDetailsList) {
                        String sku = skuDetails.getSku();
                        String price = skuDetails.getPrice();
                        if ("premium_upgrade".equals(sku)) {
                          //  String mPremiumUpgradePrice = price;
                        } else if ("gas".equals(sku)) {
                         //   mGasPrice = price;
                        }
                    }
                }
            }
        });



        billingClient.queryPurchaseHistoryAsync(adsSKU, new PurchaseHistoryResponseListener() {
            @Override
            public void onPurchaseHistoryResponse(int responseCode, List<Purchase> purchasesList) {

            }
        });*/


//purchase item





       // Log.d("debug"," response purchsse"+response);
    }



    public void purchaseItem(View view){
        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setSku(adsSKU)
                .setType(BillingClient.SkuType.INAPP)
                .build();


        int response=billingClient.launchBillingFlow(this, billingFlowParams);
        //BillingClient.BillingResponse.
    }
}
