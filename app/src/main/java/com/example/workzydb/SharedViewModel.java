package com.example.workzydb;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> selectedText = new MutableLiveData<>();

    public void setText(String input) {
        selectedText.setValue(input);
    }

    public LiveData<String> getText() {
        return selectedText;
    }
}
