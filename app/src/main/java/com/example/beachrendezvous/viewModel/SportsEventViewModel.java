package com.example.beachrendezvous.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.beachrendezvous.database.SportsEntity;

public class SportsEventViewModel extends AndroidViewModel {

    public ObservableField<SportsEntity> product = new ObservableField<>();

    public SportsEventViewModel(@NonNull Application application) {
        super(application);
    }
}
