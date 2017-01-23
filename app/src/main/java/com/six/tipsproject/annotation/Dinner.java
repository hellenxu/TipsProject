package com.six.tipsproject.annotation;

import ca.six.example.SixAnnoation;

/**
 * Created by xiaolin on 23/01/17.
 */

@SixAnnoation(id = "Dinner", type = Meal.class)
public class Dinner implements Meal {
    @Override
    public float getPrice() {
        return 0.9f;
    }
}
