package com.quar.mvvmrecycleview.ViewModels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.quar.mvvmrecycleview.Adapters.MegaDatabase;
import com.quar.mvvmrecycleview.Models.NicePlace;
import com.quar.mvvmrecycleview.repositorie.myRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<NicePlace>> mNicePlaces;
    private myRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init() {
        if (mNicePlaces != null) {
            return;
        }
        mRepo = myRepository.getInstance();
        mNicePlaces = mRepo.getNicePlaces();
    }

    public MutableLiveData<List<NicePlace>> getNicePlaces() {
        return mNicePlaces;
    }

    public void addNewValue(final NicePlace nicePlace) {

        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<NicePlace> currentPlaces = mNicePlaces.getValue();
                currentPlaces.add(nicePlace);
                mNicePlaces.postValue(currentPlaces);
                mIsUpdating.postValue(false);

            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;

            }
        }.execute();
    }

    public LiveData<Boolean> getIsUpdating() {
        return mIsUpdating;
    }

}
