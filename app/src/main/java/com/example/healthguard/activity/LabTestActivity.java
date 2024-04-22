package com.example.healthguard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthguard.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SimpleTimeZone;

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages={
            {"Pakage 1 : Full Body Checkup","","","","2000"},
            {"Pakage 2 : Blood Glucose Fasting","","","","2500"},
            {"Pakage 3 : COVID-19 antibody - IgG","","","","3000"},
            {"Pakage 4 : Thyroid Check","","","","2000"},
            {"Pakage 5 : Immunity Check","","","","1000"},
    };
    private String[] package_details= {
            "Blood Glucose Fasting\n"+
            "  Complete Hemogram\n"+
            "HbA1c\n"+
            "  Iron Studies\n"+
            "Kidney Function Test\n"+
            "LDH Location Dehydrogenase, Serum\n"+
            "Lipid Profile\n"+
            "liver Function Test",
    "Blood Glucose Fasting",
        "COVID-19 Antibody - IgG",
        "Thyroid Profile-Total (T3,t4 & TSH Ultra-sensitive)",
        "Complete Hemogram\n"+
                "CRP (C Reactive Protein) Quantitative, Serum\n" +
                "  Iron Studies\n"+
                "Kidney Function Test\n"+
                "Lipid Profile"};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart,btnBack;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnGoToCart=findViewById(R.id.buttonLTgotocart);
        btnBack=findViewById(R.id.buttonLTBack);
        listView=findViewById(R.id.listViewLT);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,MainActivity.class));
            }
        });
        list =new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost : "+packages[i][4]+"/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(LabTestActivity.this,LabTestDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text1",package_details[i]);
                it.putExtra("text1",packages[i][4]);
                startActivity(it);
            }
        });

    }
}