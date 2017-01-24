package com.six.tipsproject.annotation;

import ca.six.example.SixAnnotation;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-09-22.
 */

@SixAnnotation(id = "Dinner", type = Meal.class)
public class Dinner implements Meal {
    @Override
    public float getPrice() {
        return 0.9f;
    }

    public Dinner(){

    }
}
