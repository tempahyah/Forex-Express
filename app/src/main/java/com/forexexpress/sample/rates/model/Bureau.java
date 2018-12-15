package com.forexexpress.sample.rates.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class Bureau {
    private String name;
    private Rates buying;
    private Rates selling;

    public Bureau(){

    }
    public Bureau(String BureauName, Rates buying, Rates selling){
        this.buying = buying;
        this.selling = selling;
        this.name = BureauName;
    }

    public String getName() {
        return name;
    }

    public Rates getBuying() {
        return buying;
    }

    public Rates getSelling() {
        return selling;
    }

    @Exclude
    public void setName(String name){
        this.name = name;
    }


}
