package com.example.moviestvorganizer.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Bine ai venit in organizatorul tau de filme!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}