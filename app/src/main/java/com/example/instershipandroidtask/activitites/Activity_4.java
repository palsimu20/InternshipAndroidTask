package com.example.instershipandroidtask.activitites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instershipandroidtask.Interface.ApiInterfaceGraph;
import com.example.instershipandroidtask.Interface.ApiInterfaceWebview;
import com.example.instershipandroidtask.R;
import com.example.instershipandroidtask.modelclass.GraphData;
import com.example.instershipandroidtask.modelclass.WebViewData;
import com.example.instershipandroidtask.utility.Constants;
import com.example.instershipandroidtask.utility.MyNotificationManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity_4 extends AppCompatActivity implements View.OnClickListener {
    // TextView to Show Login User Email and Name.

     private TextView title;
    private WebView webView;
    private Dialog dialog=null;

    private Button next,back;
    private  String[] amount;
    private String[] weburl;
    private List<WebViewData> heroList;
    Bundle bundle;
    int c=0;
    private  TextView go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        title=findViewById(R.id.title1);
        go =  findViewById(R.id.go);
        go.setOnClickListener(this);
        webView=findViewById(R.id.web);
        next=findViewById(R.id.next);
        back=findViewById(R.id.back);
        next.setOnClickListener(this);
        back.setOnClickListener(this);
        // notification display

        dialog = new Dialog(this);
        bundle = getIntent().getExtras();
        if(bundle == null) {
            dialog.hide();
            // dialog.show();
        }


        dialog.setContentView(R.layout.custom);

        dialog.setTitle("Title...");


        // set the custom dialog components - text, image and button
        TextView text =  dialog.findViewById(R.id.title);
        TextView text1 =  dialog.findViewById(R.id.msg);


        Button dialogButton = (Button) dialog.findViewById(R.id.ok);
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                if (key.equals("title"))
                    text.setText(bundle.getString(key));

                else if (key.equals("message"))
                    text1.setText(bundle.getString(key));
            }
        }
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();


        WebViewData();
        if (isNetworkStatusAvialable(getApplicationContext()) && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }



        /*
         * Displaying a notification locally
         */
        MyNotificationManager.getInstance(this).displayNotification("Thank You", "I got notification");
    }

    private static boolean isNetworkStatusAvialable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if (netInfos != null) {
                return netInfos.isConnected();
            }
        }
        return false;
    }

    private void WebViewData() {

        //Creating a retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterfaceWebview.BASE_URLW)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        //creating the api interface
        ApiInterfaceWebview api = retrofit.create(ApiInterfaceWebview.class);

        //now making the call object
        //Here we are using the api method that we created inside the api interface
        Call<List<WebViewData>> call = api.getWebViewData();
        call.enqueue(new Callback<List<WebViewData>>() {
            @Override
            public void onResponse(Call<List<WebViewData>> call, Response<List<WebViewData>> response) {
                heroList = response.body();
                amount = new String[heroList.size()];
                weburl = new String[heroList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    amount[i] = String.valueOf(heroList.get(i).getTitle());
                    weburl[i] = String.valueOf(heroList.get(i).getContent());
                    //  title.append(amount[i]);
                    final String mimeType = "text/html";
                    final String encoding = "UTF-8";
                    title.setText(String.valueOf(amount[i]));
                    webView.loadDataWithBaseURL("", weburl[i], mimeType, encoding, "");
                }
            }

            @Override
            public void onFailure(Call<List<WebViewData>> call, Throwable t) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back:
                c--;

                if (c < heroList.size() - 1){

                } else {
                   c=0;
                }
                final String mimeType = "text/html";
                final String encoding = "UTF-8";
                title.setText(String.valueOf(amount[c]));
                webView.loadDataWithBaseURL("", weburl[c], mimeType, encoding, "");


                break;
            case R.id.next:

                if (c < heroList.size() - 1){
                    c++;
                } else {
                   c=0;
                }
                final String mimeType1 = "text/html";
                final String encoding1 = "UTF-8";
                title.setText(String.valueOf(amount[c]));
                webView.loadDataWithBaseURL("", weburl[c], mimeType1, encoding1, "");


                break;
            case R.id.go:
                startActivity(new Intent(Activity_4.this,Activity_3.class));
        }

    }
}
