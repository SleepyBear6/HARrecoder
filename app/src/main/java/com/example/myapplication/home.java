package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;

public class home extends AppCompatActivity {

    private static final String TAG = "10";
    private boolean isPermissionPassed = false;
    private OperateTable mytable =null;
    private SQLiteOpenHelper helper=null;
    private FloatingActionButton add=null;
    private String info=null;
    private String name = null;
    SwipeRefreshLayout swipeRefreshLayout;
    MyListAdapter myListAdapter;
    List<HashMap<String,Object>> List;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getPermission();

        helper=new SQLite(this);
        helper.getWritableDatabase();
        home.this.mytable=new OperateTable(home.this.helper.getWritableDatabase());


        add= findViewById(R.id.add);

        //nameList = new ArrayList<tip>();

        List = mytable.getdata();

        RecyclerView recyclerView = findViewById(R.id.vi);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myListAdapter = new MyListAdapter();

        recyclerView.setAdapter(myListAdapter);
        //recyclerView.setOnItemClickListener(new OnItemClick());
        //recyclerView.setOnItemLongClickListener(new OnItemLongClick());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveName();
            }
        });
        swipeRefreshLayout = findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(()->{
            Log.d(TAG, "onCreate: ok");
            List = mytable.getdata();
            myListAdapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);

        });
    }

    private class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{


        class ViewHolder extends RecyclerView.ViewHolder{
            private TextView tvId,tvName;
            private View mView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                //tvId = itemView.findViewById(R.id.id);
                tvName = itemView.findViewById(R.id.data);
                mView  = itemView;
            }
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.addapter,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            //holder.tvId.setText(List.get(position).get("id").toString());
            holder.tvName.setText( List.get(position).get("tt").toString());

            holder.mView.setOnClickListener((v)->{

                info=List.get(position).get("id").toString();
                name=List.get(position).get("tt").toString();
                Intent it=new Intent(home.this,MainActivity.class);
                it.putExtra("info",info);
                it.putExtra("name",name);
                home.this.startActivity(it);
            });
            holder.mView.setOnLongClickListener((v)-> {
                String name1;
                info = List.get(position).get("id").toString();
                name1 = List.get(position).get("tt").toString();
                AlertDialog myAlertDialog = new AlertDialog.Builder(home.this)
                        .setTitle("確認")
                        .setMessage("確定刪除“" + name1 + "”嗎？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                mytable.delete(info);
                                Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
                                onResume();
                                List = mytable.getdata();
                                myListAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("否", null)
                        .show();
                return true;
            });


        }
        @Override
        public int getItemCount() {
            return List.size();
        }
    }

    private void saveName(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(home.this);
        View v = getLayoutInflater().inflate(R.layout.set_dialog_layout,null);
        alertDialog.setView(v);
        TextView save = v.findViewById(R.id.save);
        TextView back  = v.findViewById(R.id.back);
        EditText editText = v.findViewById(R.id.edit_person_name);

        AlertDialog dialog = alertDialog.create();
        dialog.show();
        save.setOnClickListener((v1 -> {
            name = editText.getText().toString();
            mytable=new OperateTable(helper.getWritableDatabase());
            mytable.insert(name,"activity,activity ID,time");
            dialog.dismiss();
            List = mytable.getdata();
            myListAdapter.notifyDataSetChanged();
        }));
        back.setOnClickListener((v1 -> {
            dialog.dismiss();
        }));

    }
    private class OnItemClick implements AdapterView.OnItemClickListener
    { public void onItemClick(AdapterView<?>arg0, View arg1, int arg2, long arg3){
        ListView list = (ListView) findViewById(R.id.vi);


    }
    }
    private class OnItemLongClick implements AdapterView.OnItemLongClickListener {
        public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

            ListView list = (ListView) findViewById(R.id.vi);
            HashMap<String, Object> map = (HashMap<String, Object>) list.getItemAtPosition(arg2);
            String name1;
            info = map.get("id").toString();
            name1 = map.get("tt").toString();
            AlertDialog myAlertDialog = new AlertDialog.Builder(home.this)
                    .setTitle("確認")
                    .setMessage("確定刪除“" + name1 + "”嗎？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            mytable.delete(info);
                            Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
                            onResume();
                        }
                    })
                    .setNegativeButton("否", null)
                    .show();

            return true;

        }
    }
    /**
     * 取得將檔案寫入手機的權限
     */
    private void getPermission () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        } else {
            isPermissionPassed = true;
        }
    }

    /**
     * 回傳使用者所做的權限選擇(接受/拒絕)
     */
    @Override
    public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
                                             @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                /**如果用戶同意*/
                isPermissionPassed = true;
            } else {
                /**如果用戶不同意*/
                if (ActivityCompat.shouldShowRequestPermissionRationale(this
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Toast.makeText(this, "請允許權限", Toast.LENGTH_SHORT).show();
                    getPermission();
                }
            }
        }
    }
}