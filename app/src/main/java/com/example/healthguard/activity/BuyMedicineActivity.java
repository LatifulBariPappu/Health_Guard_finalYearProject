package com.example.healthguard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import com.example.healthguard.R;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages ={
            {"Ace® Tablet 500mg","","","","35"},
            {"AclitolTM Cozycap","","","","60"},
            {"Afun® Clotrimazole","","","","75"},
            {"Alatrol® Cetirizine HCl","","","","15"},
            {"Anclog® Clopidogrel","","","","35"},
            {"Famotack® Famotidine","","","","37"},
            {"Lamicet™ Lamotrigine","","","","25"},
            {"OriliTM Elagolix","","","","95"},
            {"VigorexTM Sildenafil","","","","70"}
    };
    private String[] package_details={
            "Fever, headache, toothache, earache, bodyache, myalgia, dysmenorrhoea, neuralgia and sprains\n"+
                    "It is indicated for the maintenance treatment of patients with chronic obstructive pulmonary disease (COPD).\n"+
                    "Dermatomycoses due to candida, trichophyton, moulds and other fungi, skin diseases showing superinfections with these fungi e.g. inter digital mycoses, paronychia, candida vulvitis, balanitis, pityriasis versicolor and erythrasma.\n"+
                    "Symptoms treated effectively include sneezing ,rhinorrhea, pruritus, ocular pruritus, tearing and redness of the eyes.\n"+
                    "Atherosclerotic disease (ischemic stroke, myocardial infarction or established peripheral arterial disease)\n"+
                    "Duodenal ulcer, Gastric ulcer, Gastro-esophageal reflux disease and Zollinger-Ellison syndrome, Gastritis.\n"+
                    "Lamicet is indicated to treat Partial onset Seizure Generalized Tonic Clonic Seizure Bipolar disorder \n"+
                    "OriliTM 150 tablet is indicated for the management of moderate to severe endometriosis pain with or without moderate hepatic impairment.\n"+
                    "VigorexTM is indicated for the treatment of Erectile Dysfunction."

    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    Button btnBack,btnGotoCart;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        btnBack=findViewById(R.id.buttonBMBack);
        btnGotoCart=findViewById(R.id.buttonBMGotoCart);
        lst=findViewById(R.id.listViewBM);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,MainActivity.class));
            }
        });
        btnGotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        list=new ArrayList();
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
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);

            }
        });

    }
}