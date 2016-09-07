package com.six.tipsproject.fonts;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;

import com.six.tipsproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-09-06.
 */
public class FontsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fonts);

        List<String> testData = new ArrayList<>();
        for(int i = 0; i < 20; i ++){
            testData.add("item" + i);
        }

        Spinner classSpinner = (Spinner) findViewById(R.id.classes_spinner);
        classSpinner.setAdapter(new CSpinnerAdapter(this, R.layout.spinner_item, testData));

        Spinner cateSpinner = (Spinner) findViewById(R.id.category_spinner);
        cateSpinner.setAdapter(new CSpinnerAdapter(this, R.layout.spinner_item, testData));
    }
}
