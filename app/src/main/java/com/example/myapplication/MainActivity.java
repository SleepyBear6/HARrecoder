package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private static final String TAG = "100";
    private SimpleDateFormat date,date1;
    private String dateStr;
    private TextView walk, up_stairs, down_stairs, sit, stand, lie_down, dowload, reset, reduce, others, exercise, time,username;
    private int i = 1;
    private ListView output;
    private ArrayAdapter adapter;
    private List<String> list = new ArrayList<String>();
    private String dataSTR[];
    private String id,name;
    private SQLiteOpenHelper helper=null;
    private OperateTable mytable=null;
    private tip t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //取得當下時間
        date = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss:SSS");
        date1 = new SimpleDateFormat("yyyy-MM-dd");

        walk = findViewById(R.id.walk);
        up_stairs = findViewById(R.id.up_stairs);
        down_stairs = findViewById(R.id.down_stairs);
        sit = findViewById(R.id.sit);
        stand = findViewById(R.id.stand);
        lie_down = findViewById(R.id.lie_down);
        dowload = findViewById(R.id.download);
        reset = findViewById(R.id.reset);
        output = findViewById(R.id.output);

        exercise = findViewById(R.id.excercise);
        others = findViewById(R.id.others);
        username = findViewById(R.id.userName);
        time = findViewById(R.id.EditOld);

        helper=new SQLite(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("info");
        name = intent.getStringExtra("name");
        username.setText(name);

        mytable=new OperateTable(helper.getWritableDatabase());
        t=mytable.t(id);
        Log.d(TAG, "onCreate: "+t.getData());


        list = new ArrayList<>(Arrays.asList(t.getData().split("\r")));
        Log.d(TAG, "onCreate:  "+list);
        adapter = new ArrayAdapter(MainActivity.this, R.layout.dataadapter,list);
        output.setAdapter(adapter);

        output.setOnItemLongClickListener(onItemLongClickListener);


        walk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateStr = date.format(new Date());
                String activestr = "\r\n"+"walk"+","+"1"+","+dateStr;
                mytable.update(id,t.getData()+activestr);

                mytable=new OperateTable(helper.getWritableDatabase());
                t=mytable.t(id);

                list = new ArrayList<>(Arrays.asList(t.getData().split("\r")));
                adapter = new ArrayAdapter(MainActivity.this, R.layout.dataadapter,list);
                output.setAdapter(adapter);

                Toast.makeText(getApplicationContext(),"輸入成功",Toast.LENGTH_SHORT).show();
            }
        });
        up_stairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateStr = date.format(new Date());
                String activestr = "\r\n"+"up stairs"+","+"2"+","+dateStr;
                mytable.update(id,t.getData()+activestr);

                mytable=new OperateTable(helper.getWritableDatabase());
                t=mytable.t(id);

                list = new ArrayList<>(Arrays.asList(t.getData().split("\r")));
                adapter = new ArrayAdapter(MainActivity.this, R.layout.dataadapter,list);
                output.setAdapter(adapter);

                Toast.makeText(getApplicationContext(),"輸入成功",Toast.LENGTH_SHORT).show();
            }
        });
        down_stairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateStr = date.format(new Date());
                String activestr = "\r\n"+"down stairs"+","+"3"+","+dateStr;
                mytable.update(id,t.getData()+activestr);

                mytable=new OperateTable(helper.getWritableDatabase());
                t=mytable.t(id);

                list = new ArrayList<>(Arrays.asList(t.getData().split("\r")));
                adapter = new ArrayAdapter(MainActivity.this, R.layout.dataadapter,list);
                output.setAdapter(adapter);


                Toast.makeText(getApplicationContext(),"輸入成功",Toast.LENGTH_SHORT).show();
            }
        });
        sit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateStr = date.format(new Date());
                String activestr = "\r\n"+"sit"+","+"4"+","+dateStr;
                mytable.update(id,t.getData()+activestr);

                mytable=new OperateTable(helper.getWritableDatabase());
                t=mytable.t(id);

                list = new ArrayList<>(Arrays.asList(t.getData().split("\r")));
                adapter = new ArrayAdapter(MainActivity.this, R.layout.dataadapter,list);
                output.setAdapter(adapter);


                Toast.makeText(getApplicationContext(),"輸入成功",Toast.LENGTH_SHORT).show();
            }
        });
        stand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateStr = date.format(new Date());
                String activestr = "\r\n"+"stand"+","+"5"+","+dateStr;
                mytable.update(id,t.getData()+activestr);

                mytable=new OperateTable(helper.getWritableDatabase());
                t=mytable.t(id);

                list = new ArrayList<>(Arrays.asList(t.getData().split("\r")));
                adapter = new ArrayAdapter(MainActivity.this, R.layout.dataadapter,list);
                output.setAdapter(adapter);


                Toast.makeText(getApplicationContext(),"輸入成功",Toast.LENGTH_SHORT).show();
            }
        });
        lie_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateStr = date.format(new Date());
                String activestr = "\r\n"+"lie down"+","+"6"+","+dateStr;
                mytable.update(id,t.getData()+activestr);

                mytable=new OperateTable(helper.getWritableDatabase());
                t=mytable.t(id);

                list = new ArrayList<>(Arrays.asList(t.getData().split("\r")));
                adapter = new ArrayAdapter(MainActivity.this, R.layout.dataadapter,list);
                output.setAdapter(adapter);

                Toast.makeText(getApplicationContext(),"輸入成功",Toast.LENGTH_SHORT).show();
            }
        });
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateStr = date.format(new Date());
                String activestr = "\r\n"+"exercise"+","+"7"+","+dateStr;
                mytable.update(id,t.getData()+activestr);

                mytable=new OperateTable(helper.getWritableDatabase());
                t=mytable.t(id);

                list = new ArrayList<>(Arrays.asList(t.getData().split("\r")));
                adapter = new ArrayAdapter(MainActivity.this, R.layout.dataadapter,list);
                output.setAdapter(adapter);


                Toast.makeText(getApplicationContext(),"輸入成功",Toast.LENGTH_SHORT).show();
            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateStr = date.format(new Date());
                String activestr = "\r\n"+"others"+","+"8"+","+dateStr;
                mytable.update(id,t.getData()+activestr);

                mytable=new OperateTable(helper.getWritableDatabase());
                t=mytable.t(id);

                list = new ArrayList<>(Arrays.asList(t.getData().split("\r")));
                adapter = new ArrayAdapter(MainActivity.this, R.layout.dataadapter,list);
                output.setAdapter(adapter);

                Toast.makeText(getApplicationContext(),"輸入成功",Toast.LENGTH_SHORT).show();
            }
        });
        dowload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytable=new OperateTable(helper.getWritableDatabase());
                t=mytable.t(id);
                exportFile();
                Log.d(TAG, "onClick: test");
                Toast.makeText(getApplicationContext(),"下載成功",Toast.LENGTH_SHORT).show();
            }
        });
        //清除
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mytable.update(id,"activity,activity ID,time");
                mytable=new OperateTable(helper.getWritableDatabase());
                t=mytable.t(id);
                list = new ArrayList<>(Arrays.asList(t.getData().split("\r")));
                adapter = new ArrayAdapter(MainActivity.this, R.layout.dataadapter,list);
                output.setAdapter(adapter);

                Toast.makeText(getApplicationContext(),"清除成功",Toast.LENGTH_SHORT).show();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                View v = getLayoutInflater().inflate(R.layout.set_dialog_layout2,null);
                alertDialog.setView(v);
                TextView save = v.findViewById(R.id.save);
                TextView back  = v.findViewById(R.id.back);
                EditText editText = v.findViewById(R.id.edit_new_data);
                Spinner spinner=(Spinner)v.findViewById(R.id.spinner);
                String[] active={"walk,1", "up stairs,2", "down stairs,3", "sit,4", "stand,5", "lie down,6", "exercise,7", "others,8"};
                spinner.setAdapter(new ArrayAdapter<>( MainActivity.this,android.R.layout.simple_spinner_dropdown_item, active));
                AlertDialog dialog = alertDialog.create();
                dialog.show();

                save.setOnClickListener((v1 -> {
                    String active2 = (String) spinner.getSelectedItem();
                    String edit = "\r\n"+active2+","+editText.getText().toString();
                    mytable=new OperateTable(helper.getWritableDatabase());
                    mytable.update(id,t.getData()+edit);
                    dialog.dismiss();
                    mytable=new OperateTable(helper.getWritableDatabase());
                    t=mytable.t(id);
                    list = new ArrayList<>(Arrays.asList(t.getData().split("\r")));
                    adapter = new ArrayAdapter(MainActivity.this, R.layout.dataadapter,list);
                    output.setAdapter(adapter);
                }));
                back.setOnClickListener((v1 -> {
                    dialog.dismiss();
                }));
            }
        });
    }
        /**輸出檔案*/
        private void exportFile(){
            new Thread(() -> {
                //fileName = findViewById(R.id.flieName);
                /**決定檔案名稱*/
                String absoluteFilePath =
                        Environment.getExternalStorageDirectory().getAbsolutePath() + "/HARCSV/";
                //設置第一列的內容
                Log.d(TAG, "makeCSV: 12\n");//可在此監視輸出的內容
                /**決定檔案被存放的路徑*/
//      "/storage/emulated/0/BlogExport"
                runOnUiThread(() -> {
                    try {
                        dateStr = date.format(new Date());
                        File file = new File(absoluteFilePath);
                        //boolean document = file.createNewFile();
                        if (file.mkdir()) {
                            System.out.println("新增資料夾");
                        } else {
                            System.out.println("資料夾已存在");
                        }
                        //->遇上exposed beyond app through ClipData.Item.getUri() 錯誤時在onCreate加上這行
                        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                        StrictMode.setVmPolicy(builder.build());
                        builder.detectFileUriExposure();
                        //->遇上exposed beyond app through ClipData.Item.getUri() 錯誤時在onCreate加上這行
                        String fileName = absoluteFilePath+"/"+dateStr.replaceAll(":", ".")+name+"HAR.csv";
                        File fileLocation = new File(fileName);
                        Log.d(TAG, "exportFile: "+fileLocation);
                        FileOutputStream fos = new FileOutputStream(fileLocation,true);
                        fos.write(t.getData().getBytes());
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.w(TAG, "makeCSV: " + e.toString());
                    }
                });
            }).start();
        }
    private AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener(){
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
            String itemSelected=parent.getItemAtPosition(position).toString();
            AlertDialog.Builder alertDialog =
                    new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("刪除這行?");
            alertDialog.setMessage(itemSelected);
            alertDialog.setNeutralButton("確定", new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //adapter.remove(itemSelected);
                    list.remove(position);
                    Log.d(TAG, "onItemLongClick: "+itemSelected);
                    Log.d(TAG, "onItemLongClick:1 "+position);

                    String datastr = list.stream().collect(Collectors.joining("\r"));
                    Log.d(TAG, "onClick:3 "+datastr);
                    mytable.update(id,datastr);
                    mytable=new OperateTable(helper.getWritableDatabase());
                    t=mytable.t(id);

                    list = new ArrayList<>(Arrays.asList(t.getData().split("\r")));
                    adapter = new ArrayAdapter(MainActivity.this, R.layout.dataadapter,list);
                    output.setAdapter(adapter);
                }
            });
            alertDialog.setPositiveButton("否",null);
            alertDialog.setCancelable(false);
            alertDialog.show();

            return true;
        }
    };
    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        Calendar t = Calendar.getInstance();
        int hourOfDay = 0,minute = 0;
        t.set(Calendar.HOUR_OF_DAY, hourOfDay);
        t.set(Calendar.MINUTE, minute);

        dateStr = date1.format(new Date());
        dateStr = dateStr+hourOfDay+":"+minute+":00:000";
    }
}
