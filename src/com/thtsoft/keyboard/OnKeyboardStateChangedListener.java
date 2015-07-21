package com.thtsoft.keyboard;

import android.inputmethodservice.KeyboardView;
import android.view.View;

public interface OnKeyboardStateChangedListener
{
    public void OnDisplay(View currentview, KeyboardView currentKeyboard);

    public void OnHide(KeyboardView currentKeyboard);
} 
