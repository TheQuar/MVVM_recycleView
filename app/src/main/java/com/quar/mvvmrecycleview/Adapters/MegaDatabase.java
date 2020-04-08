package com.quar.mvvmrecycleview.Adapters;

import android.content.Context;

import com.quar.mvvmrecycleview.Database.TinyDB;
import com.quar.mvvmrecycleview.Models.NicePlace;

import java.util.ArrayList;

public class MegaDatabase {

    Context mContext;
    TinyDB tinyDB;

    public MegaDatabase(Context mContext) {
        this.mContext = mContext;
        tinyDB = new TinyDB(mContext);
    }

    public void setTinyDb(ArrayList<NicePlace> newData){
        ArrayList<Object> objects = new ArrayList<>();
        for (NicePlace nicePlace : newData) {
            objects.add((Object)nicePlace);
        }
        tinyDB.putListObject("data", objects);
    }

    public ArrayList<NicePlace> getTinyDb(){
        ArrayList<Object> objects = tinyDB.getListObject("data", NicePlace.class);
        ArrayList<NicePlace> mbb= new ArrayList<>();
        for (Object obj: objects){
            mbb.add((NicePlace)obj);
        }

        return mbb;
    }
}
