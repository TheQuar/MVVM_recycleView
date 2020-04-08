package com.quar.mvvmrecycleview;

import com.quar.mvvmrecycleview.Adapters.APIAdapter;
import com.quar.mvvmrecycleview.Adapters.RecyclerAdapter;
import com.quar.mvvmrecycleview.Models.NicePlace;

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

public class GetData {
    static ArrayList<NicePlace> MegaData = new ArrayList<>();
    static Boolean RetroConnectError = false;
    static String RetroConnectErrorString = "";

    public GetData() {
    }


}