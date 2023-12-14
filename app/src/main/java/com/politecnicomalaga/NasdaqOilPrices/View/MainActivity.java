package com.politecnicomalaga.NasdaqOilPrices.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.politecnicomalaga.NasdaqOilPrices.Control.JornadaAdapter;
import com.politecnicomalaga.NasdaqOilPrices.Control.MainController;
import com.politecnicomalaga.NasdaqOilPrices.Model.Price;
import com.politecnicomalaga.NasdaqOilPrices.R;
import com.politecnicomalaga.NasdaqOilPrices.mainGraphview;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinkedList<Price> mList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private JornadaAdapter mAdapter;

    private static MainActivity myActiveActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.rv_prices);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new JornadaAdapter(this, mList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button generar = (Button) findViewById(R.id.b_getData);
        Button generar2 = (Button) findViewById(R.id.b_getData2);
        Button generar3 = (Button) findViewById(R.id.b_getData3);
        generar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainController.setURL();
                Toast.makeText(MainActivity.this, "Getting data from Nasdaq Servers...", Toast.LENGTH_LONG).show();
                MainController.getSingleton().requestDataFromNasdaq();
            }
        });
        generar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainController.setURL2();
                Toast.makeText(MainActivity.this, "Getting data from Nasdaq Servers...", Toast.LENGTH_LONG).show();
                MainController.getSingleton().requestDataFromNasdaq();
            }
        });

        MainActivity.myActiveActivity = this;
        MainController.setActivity(this);
        generar3.setOnClickListener (new View.OnClickListener() {
            public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, mainGraphview.class);
                    startActivity(intent);
            }
        });
    }

    public void accessData() {
        //Get data from servers throgh controller-model classes
        List<Price> nuevaLista = MainController.getSingleton().getDataFromNasdaq();

        //Put data in adapter
        mList.clear();
        for (Price item:nuevaLista) {
            mList.add(item);
        }
        mAdapter.notifyDataSetChanged();
        TextView tv = (TextView) findViewById(R.id.tv_oilDesc);
        tv.setText("Nasdaq Oil Prices: 30 rows");
    }

    public void errorData(String error) {
        TextView tv = (TextView) findViewById(R.id.tv_oilDesc);
        tv.setText(error);

    }


}
