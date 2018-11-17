package com.example.user.ex41118;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    AlertDialog.Builder adb;
    EditText etfirst;
    EditText etdm;
    LinearLayout mydialog;
    double first;
    double dom;
    TextView tvX, tvN, tvD, tvS;
    ListView lv;
    String [] series = new String[20];
    Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvX= (TextView)findViewById(R.id.tvx);
        tvN=(TextView)findViewById(R.id.tvn);
        tvD=(TextView)findViewById(R.id.tvd);
        tvS=(TextView)findViewById(R.id.tvSn);
        lv=(ListView) findViewById(R.id.lv);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if (id==R.id.menucreds){
            Intent t=new Intent(this, CredsActivity.class);
            startActivity(t);
        }
        return super.onOptionsItemSelected(item);
    }

    public void alrdia(View view) {

        mydialog=(LinearLayout)getLayoutInflater().inflate(R.layout.my_dialog,null);
        etdm=(EditText)mydialog.findViewById(R.id.etdm);
        etfirst=(EditText)mydialog.findViewById(R.id.etfirst1);
        sw=(Switch)mydialog.findViewById(R.id.switch1);
        adb=new AlertDialog.Builder(this);
        adb.setCancelable(false);

        adb.setTitle("Enter Data");
        adb.setView(mydialog);
        adb.setNegativeButton("Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                first=0.0;
                dom=0.0;
                tvD.setText("");
                tvN.setText("");
                tvX.setText("");
                tvS.setText("");
                lv.setAdapter(null);
                dialogInterface.dismiss();
            }
        });
        adb.setNeutralButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        adb.setPositiveButton("Calculate", myclick);

        AlertDialog ad=adb.create();
        ad.show();
    }


    DialogInterface.OnClickListener myclick=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int which) {

                if ((etfirst.getText().toString().equals("")) || (etfirst.getText().toString().equals(".-")) || ((etfirst.getText().toString().equals(".")) || (etfirst.getText().toString().equals("-")) || (etfirst.getText().toString().equals("-."))) ||
                        ((etdm.getText().toString().equals("")) || (etdm.getText().toString().equals(".-")) || ((etdm.getText().toString().equals(".")) || (etdm.getText().toString().equals("-")) || (etdm.getText().toString().equals("-."))))) {
                    dialogInterface.cancel();
                    Toast.makeText(MainActivity.this, "Input is unavailable", Toast.LENGTH_SHORT).show();
                } else {
                    first = Double.parseDouble(etfirst.getText().toString());
                    dom = Double.parseDouble(etdm.getText().toString());
                    tvX.setText(Double.toString(first));
                    tvD.setText(Double.toString(dom));
                    series[0] = Double.toString(first);
                    if (sw.isChecked())
                        //  handasi
                        for (int i = 1; i < 20; i++) {
                            series[i] = Double.toString(Double.parseDouble(series[i - 1]) * dom);
                        }
                    else {
                        //heshboni
                        for (int i = 1; i < 20; i++) {
                            series[i] = Double.toString(Double.parseDouble(series[i - 1]) + dom);
                        }
                    }
                    lv.setOnItemClickListener(MainActivity.this);
                    lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                    ArrayAdapter<String> adp = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, series);
                    lv.setAdapter(adp);
                }

        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int n=i+1;
        tvN.setText(Integer.toString(n)); double sum;
        if (!sw.isChecked())
            sum=((n*((2*first)+dom*(n-1)))/2);
        else{ if ((first!=0)||(dom!=0)||(dom!=1))
            sum=(first*(Math.pow(dom,n)-1))/(dom-1);
        else {
            if ((first == 0) || (dom == 0))
                sum = 0;
            else sum = Double.parseDouble(series[n]);
        }
        }
        tvS.setText(Double.toString(sum));
    }
}

