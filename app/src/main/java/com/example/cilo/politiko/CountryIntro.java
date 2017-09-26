package com.example.cilo.politiko;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.paolorotolo.appintro.AppIntro;

/**
 * Created by cmigayi on 16-Dec-15.
 */
public class CountryIntro extends AppIntro {
    Intent intent;
    @Override
    public void init(@Nullable Bundle bundle) {
        overridePendingTransition(0,0);
        addSlide(SampleSlide.newInstance(R.layout.fragment_country_intro_one));
        addSlide(SampleSlide.newInstance(R.layout.fragment_country_intro_two));
        addSlide(SampleSlide.newInstance(R.layout.fragment_country_intro_three));

        setBarColor(Color.parseColor("#ee0000"));
        setSeparatorColor(Color.parseColor("#ffffff"));

        intent = new Intent(this,ActivityUserActorCreation.class);
    }

    @Override
    public void onSkipPressed() {
        startActivity(intent);
    }

    @Override
    public void onDonePressed() {
        startActivity(intent);
    }
}
