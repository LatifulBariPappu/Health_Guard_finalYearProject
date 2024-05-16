package com.example.healthguard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthguard.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailActivity extends AppCompatActivity {
    private String[][] doctor_details1=
            {
                    {"Doctor Name : Abdul Hakim","Hospital Address : Savar","Exp : 4years","Mobile No : 01300000010","700"},
                    {"Doctor Name : Ajit kumar","Hospital Address : Dhaka","Exp : 5years","Mobile No : 01700000000","600"},
                    {"Doctor Name : Deepok Khondakar","Hospital Address : Rajshahi","Exp : 1years","Mobile No : 01500000100","400"},
                    {"Doctor Name : Ajit Bissas","Hospital Address : Khulna","Exp : 3years","Mobile No : 01700001000","500"},
                    {"Doctor Name : Mrs Fariha","Hospital Address : Dhaka","Exp : 5years","Mobile No : 01600010000","600"}
            };
    private String[][] doctor_details2=
            {
                    {"Doctor Name : Ajit kumar","Hospital Address : Dhaka","Exp : 5years","Mobile No : 01700000000","600"},
                    {"Doctor Name : Abdur Razzak","Hospital Address : Savar","Exp : 4years","Mobile No : 01300000010","700"},
                    {"Doctor Name : Deepok Khondakar","Hospital Address : Rajshahi","Exp : 1years","Mobile No : 01500000100","400"},
                    {"Doctor Name : Ajit Bissas","Hospital Address : Khulna","Exp : 3years","Mobile No : 01700001000","500"},
                    {"Doctor Name : Mrs Fariha","Hospital Address : Dhaka","Exp : 5years","Mobile No : 01600010000","600"}
            };
    private String[][] doctor_details3=
            {
                    {"Doctor Name : Ajit kumar","Hospital Address : Dhaka","Exp : 5years","Mobile No : 01700000000","600"},
                    {"Doctor Name : Abdur Razzak","Hospital Address : Savar","Exp : 4years","Mobile No : 01300000010","700"},
                    {"Doctor Name : Deepok Khondakar","Hospital Address : Rajshahi","Exp : 1years","Mobile No : 01500000100","400"},
                    {"Doctor Name : Ajit Bissas","Hospital Address : Khulna","Exp : 3years","Mobile No : 01700001000","500"},
                    {"Doctor Name : Mrs Fariha","Hospital Address : Dhaka","Exp : 5years","Mobile No : 01600010000","600"}
            };
    private String[][] doctor_details4=
            {
                    {"Doctor Name : Ajit kumar","Hospital Address : Dhaka","Exp : 5years","Mobile No : 01700000000","600"},
                    {"Doctor Name : Abdur Razzak","Hospital Address : Savar","Exp : 4years","Mobile No : 01300000010","700"},
                    {"Doctor Name : Deepok Khondakar","Hospital Address : Rajshahi","Exp : 1years","Mobile No : 01500000100","400"},
                    {"Doctor Name : Ajit Bissas","Hospital Address : Khulna","Exp : 3years","Mobile No : 01700001000","500"},
                    {"Doctor Name : Mrs Fariha","Hospital Address : Dhaka","Exp : 5years","Mobile No : 01600010000","600"}
            };
    private String[][] doctor_details5=
            {
                    {"Doctor Name : Ajit kumar","Hospital Address : Dhaka","Exp : 5years","Mobile No : 01700000000","600"},
                    {"Doctor Name : Abdur Razzak","Hospital Address : Savar","Exp : 4years","Mobile No : 01300000010","700"},
                    {"Doctor Name : Deepok Khondakar","Hospital Address : Rajshahi","Exp : 1years","Mobile No : 01500000100","400"},
                    {"Doctor Name : Ajit Bissas","Hospital Address : Khulna","Exp : 3years","Mobile No : 01700001000","500"},
                    {"Doctor Name : Mrs Fariha","Hospital Address : Dhaka","Exp : 5years","Mobile No : 01600010000","600"}
            };
    TextView tv;
    Button btn;
    String[][] doctor_details={};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_detail);

        tv=findViewById(R.id.textViewDDTitle);
        btn=findViewById(R.id.buttonDDBack);

        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Top Family Physicians")==0)
            doctor_details=doctor_details1;
        else
        if(title.compareTo("Top Dietitians")==0)
            doctor_details=doctor_details2;
        else
        if(title.compareTo("Top Dentists")==0)
            doctor_details=doctor_details3;
        else
        if(title.compareTo("Top Surgeons")==0)
            doctor_details=doctor_details4;
        else
            doctor_details=doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailActivity.this,FindDoctorActivity.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees : "+doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        ListView listView=findViewById(R.id.listViewDD);
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(DoctorDetailActivity.this,BookAppoinmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}