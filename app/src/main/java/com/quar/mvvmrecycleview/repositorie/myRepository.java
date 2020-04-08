package com.quar.mvvmrecycleview.repositorie;

import androidx.lifecycle.MutableLiveData;

import com.quar.mvvmrecycleview.MainActivity;
import com.quar.mvvmrecycleview.Models.NicePlace;

import java.util.ArrayList;
import java.util.List;

public class myRepository extends MainActivity {
    private static myRepository instansce;
    private ArrayList<NicePlace> dataSet = new ArrayList<>();


    public static myRepository getInstance() {
        if (instansce == null) {
            instansce = new myRepository();
        }
        return instansce;
    }

    public MutableLiveData<List<NicePlace>> getNicePlaces() {
        setNicePlaces();
        MutableLiveData<List<NicePlace>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;

    }

    private void setNicePlaces() {

    }
}
