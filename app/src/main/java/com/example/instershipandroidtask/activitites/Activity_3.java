package com.example.instershipandroidtask.activitites;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instershipandroidtask.Interface.ApiInterfaceGraph;
import com.example.instershipandroidtask.R;
import com.example.instershipandroidtask.modelclass.GraphData;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity_3 extends AppCompatActivity {
    private  BarChart chart ;
    private ArrayList<BarEntry> BARENTRY ;
    private ArrayList<String> BarEntryLabels ;
    private BarDataSet Bardataset ;
    private BarData BARDATA ;
    private ApiInterfaceGraph apiInterface;
    private TextView listView;
    private  String[] amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        chart = (BarChart) findViewById(R.id.piechart);

       // GraphData1();
//        apiInterface = RetrofitClient.getClient().create(ApiInterfaceGraph.class);
//
//
        BARENTRY = new ArrayList<>();
//
       BarEntryLabels = new ArrayList<String>();
//
       AddValuesToBARENTRY();
//
//
       AddValuesToBarEntryLabels();
//
      Bardataset = new BarDataSet(BARENTRY, "Plot Graph");
//
        BARDATA = new BarData(BarEntryLabels, Bardataset);
//
        Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
//
        chart.setData(BARDATA);
//
       chart.animateY(3000);
//
   }
//
   public void AddValuesToBARENTRY(){
//
       BARENTRY.add(new BarEntry(2f, 0));
      BARENTRY.add(new BarEntry(4f, 1));
       BARENTRY.add(new BarEntry(6f, 2));
      BARENTRY.add(new BarEntry(8f, 3));
       BARENTRY.add(new BarEntry(7f, 4));
        BARENTRY.add(new BarEntry(3f, 5));
//
    }
//
   public void AddValuesToBarEntryLabels(){
//
        BarEntryLabels.add("July");
        BarEntryLabels.add("February");
        BarEntryLabels.add("March");
       BarEntryLabels.add("April");
       BarEntryLabels.add("May");
        BarEntryLabels.add("June");
//
//
//
   }
    private void GraphData1()
    {
        //Creating a retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterfaceGraph.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        //creating the api interface
        ApiInterfaceGraph api = retrofit.create(ApiInterfaceGraph.class);

        //now making the call object
        //Here we are using the api method that we created inside the api interface
        Call<List<GraphData>> call = api.getGraphData();

        //then finallly we are making the call using enqueue()
            call.enqueue(new Callback<List<GraphData>>() {
                @Override
                public void onResponse(Call<List<GraphData>> call, Response<List<GraphData>> response) {
                    List<GraphData> heroList = response.body();
                     amount = new String[heroList.size()];
                    String[] date = new String[heroList.size()];

                    //looping through all the heroes and inserting the names inside the string array
                    for (int i = 0; i < heroList.size(); i++) {
                        amount[i] = String.valueOf(heroList.get(i).getAmount());
                        date[i]= String.valueOf(heroList.get(i).getDate_created());
                        setData(response.body());
                    }
//                  //  Toast.makeText(getApplicationContext(), heroes.length,Toast.LENGTH_LONG).show();
//                    listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,date));
//



                }

                @Override
                public void onFailure(Call<List<GraphData>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        //it takes callback interface as an argument
        //and callback is having two methods onRespnose() and onFailure
        //if the request is successf
    }
    private void setData(List<GraphData> piChart) {

        PieChart pieChartList = findViewById(R.id.piechart);


        /**
         *  get Data for pie chart
         */

        ArrayList<Entry> pieChartData = new ArrayList<Entry>();
        pieChartData.add(new Entry(piChart.get(0).getAmount(), 0));
        //pieChartData.add(new Entry(piChart.get(0).getDate_created(), 1));


        PieDataSet dataSet = new PieDataSet(pieChartData, "");
        dataSet.setValueTextSize(10);


        /**
         *  set segment for pie chart
         */

        ArrayList<String> pieChartSectionName = new ArrayList<String>();
        pieChartSectionName.add("Assigned");



        PieData data = new PieData(pieChartSectionName, dataSet);
        pieChartList.setData(data);


        /**
         *  Segment color added into the pie chart
         */

        dataSet.setColors(new int[]{Color.parseColor("#9c27b0")
                });



        pieChartList.animateXY(500, 500);

    }

}
