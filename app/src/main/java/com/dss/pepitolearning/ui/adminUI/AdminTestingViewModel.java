package com.dss.pepitolearning.ui.adminUI;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdminTestingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdminTestingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is AdminTesting fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}