package com.quar.mvvmrecycleview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quar.mvvmrecycleview.Adapters.APIAdapter;
import com.quar.mvvmrecycleview.Adapters.MegaDatabase;
import com.quar.mvvmrecycleview.Adapters.RecyclerAdapter;
import com.quar.mvvmrecycleview.Models.NicePlace;
import com.quar.mvvmrecycleview.ViewModels.MainActivityViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ProgressBar progressBar;
    ArrayList<NicePlace> arrayList = new ArrayList<>();
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();
        mainActivityViewModel.getNicePlaces().observe(this, new Observer<List<NicePlace>>() {
            @Override
            public void onChanged(List<NicePlace> nicePlaces) {
                recyclerAdapter.notifyDataSetChanged();
            }
        });

        mainActivityViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showProgressBar();
                } else {
                    hideProgressBar();
                    recyclerView.smoothScrollToPosition(mainActivityViewModel.getNicePlaces().getValue().size() - 1);
                }

            }
        });

        initRecyclerView();
        getMyData();
    }

    private void initRecyclerView() {
        recyclerAdapter = new RecyclerAdapter(this, mainActivityViewModel.getNicePlaces().getValue());
        RecyclerView.LayoutManager linerlayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linerlayoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add: {
                addresult();
                return true; }
            case R.id.refresh :{
                ArrayList<NicePlace> mega= new ArrayList<>();
                mega.add(new NicePlace("yangi","yangi","yangi","yangi"));
                MegaDatabase getDataDatabase = new MegaDatabase(MainActivity.this);
                getDataDatabase.setTinyDb(mega);
            }
            case R.id.getdataff:{
                ArrayList<NicePlace> mega= new ArrayList<>();
                MegaDatabase getDataDatabase = new MegaDatabase(MainActivity.this);
                mega.addAll(getDataDatabase.getTinyDb());
                checkOldData(mega.get(0));
            }

        }
        return super.onOptionsItemSelected(item);
    }

    public void addresult() {
        Call<ResponseBody> call = APIAdapter
                .getInstance("https://upitak.uz/api/tp-user/")
                .getApi()
                .GetPassangerBlank("7");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null) {
                    try {
                        String s = response.body().string();
                        if (!s.equals("null")) {
                            JSONObject status = new JSONObject(s);
                            JSONArray data = status.getJSONArray("driver");
                            if (status.getString("status").equals("true")) {
                                for (int i = 0; i < data.length(); i++) {
                                    NicePlace nicePlace = new NicePlace();
                                    nicePlace.setTodo(data.getJSONObject(i).getString("addressfrom"));
                                    nicePlace.setTime(data.getJSONObject(i).getString("leavetime"));
                                    nicePlace.setMoney(data.getJSONObject(i).getString("price"));
                                    nicePlace.setNumber(data.getJSONObject(i).getString("carname"));
                                    checkOldData(nicePlace);
                                }
                                addMyData();
                            }
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Connect API:  " + t.getMessage(), Toast.LENGTH_LONG).show();


            }
        });

    }

    public void checkOldData(NicePlace nicePlace) {
        boolean have = false;
        List<NicePlace> oldData = mainActivityViewModel.getNicePlaces().getValue();
        for (int i = 0; oldData.size() > i; i++) {
            if (oldData.get(i).getTodo().equals(nicePlace.getTodo()) &&
                    oldData.get(i).getMoney().equals(nicePlace.getMoney()) &&
                    oldData.get(i).getTime().equals(nicePlace.getTime()) &&
                    oldData.get(i).getNumber().equals(nicePlace.getNumber())) {
                have = true;
                break;
            }
        }

        if(!have){
            mainActivityViewModel.addNewValue(nicePlace);
        }
    }

    public void getMyData(){
        ArrayList<NicePlace> mData = new ArrayList<>();
        MegaDatabase megaDatabase = new MegaDatabase(MainActivity.this);
        mData.addAll(megaDatabase.getTinyDb());
        for (int i = 0; i < mData.size(); i++) {
            mainActivityViewModel.addNewValue(mData.get(i));
        }
    }

    public void addMyData(){
        ArrayList<NicePlace> arrayList = new ArrayList<>();
        arrayList.addAll(mainActivityViewModel.getNicePlaces().getValue());
        MegaDatabase megaDatabase = new MegaDatabase(MainActivity.this);
        megaDatabase.setTinyDb(arrayList);
    }
}


