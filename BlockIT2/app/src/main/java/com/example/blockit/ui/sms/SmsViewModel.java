package com.example.blockit.ui.sms;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SmsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SmsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is sms fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}