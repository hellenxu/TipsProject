package com.six.tipsproject.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.six.tipsproject.R;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-08-24.
 */
public class LoadingDialog extends DialogFragment {
    private static int mLayoutResId;
    public View view;

    public static LoadingDialog getInstance(int layoutResId){
        LoadingDialog dialog = new LoadingDialog();
        mLayoutResId = layoutResId;
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.Theme_LoadingDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("ssss");
        this.view = inflater.inflate(mLayoutResId, container, false);
//        view.setLayoutParams(new ViewGroup.LayoutParams(600, 600));
        return this.view;
    }
}
