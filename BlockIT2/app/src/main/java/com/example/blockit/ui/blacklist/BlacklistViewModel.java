package com.example.blockit.ui.blacklist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BlacklistViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BlacklistViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is blacklist fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}