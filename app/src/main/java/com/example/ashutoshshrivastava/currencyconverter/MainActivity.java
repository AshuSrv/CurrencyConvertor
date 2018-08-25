package com.example.ashutoshshrivastava.currencyconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
EditText Amount;
TextView Result;
Spinner spiner1,spiner2;
Button Convert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Amount=findViewById(R.id.editText);
        Result=findViewById(R.id.textView);
        Convert=findViewById(R.id.button);


        //get the spinner from the xml.
        spiner1 = findViewById(R.id.spinner1);
//create a list of items for the spinner.
        String[] items = new String[]{"AFN", "EUR", "ALL","USD","INR","BDT","BTN","KYD","CRC","DKK","IDR","JPY","KWD","NZD","PHP","SGD","UYU"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        spiner1.setAdapter(adapter);

        //get the spinner from the xml.
        spiner2 = findViewById(R.id.spinner2);
//create a list of items for the spinner.
         items = new String[]{"AFN", "EUR", "ALL","USD","INR","BDT","BTN","KYD","CRC","DKK","IDR","JPY","KWD","NZD","PHP","SGD","UYU"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        spiner2.setAdapter(adapter2);

        Convert.setOnClickListener(new View.OnClickListener() {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://currency-exchange.p.mashape.com/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

            @Override
            public void onClick(View v) {
                String extractedValue = Amount.getText().toString();
                String zero="";
                if(extractedValue.compareTo(zero)==0)
                {
                    extractedValue = "0";
                }
                else {

                }
        final String value=extractedValue;

        CurrConvService service=retrofit.create(CurrConvService.class);
                String spinnervalue1 = spiner1.getSelectedItem().toString();
                final String spinnerVlue2 = spiner2.getSelectedItem().toString();
                Call<String> stringCall=service.Currency("Fm7BtZLe9EmshYcc4HDw7LPN7Ofsp1iZ2OEjsnt5YozbekWyfx",spinnervalue1,value,spinnerVlue2);
                stringCall.enqueue(new Callback<String>() {

                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String responsetring = response.body();
                       Float result = Float.valueOf(responsetring);
                        Float result2 = Float.valueOf(value);
                        Float DisplayResult = result*result2;
                        String S=Float.toString(DisplayResult);

                        Result.setText(S + " " + spinnerVlue2);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Result.setText("Failed to Connect to the Server");
                    }
                });

            }

        });

    }
}
