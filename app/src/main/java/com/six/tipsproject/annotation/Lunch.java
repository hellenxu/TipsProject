package com.six.tipsproject.annotation;

import ca.six.example.SixAnnotation;

/**
 * Created by xiaolin on 24/01/17.
 */
@SixAnnotation(id = "Lunch", type = Meal.class)
public class Lunch implements Meal {
    public Lunch(){

    }

    @Override
    public float getPrice() {
        return 3f;
    }
}
