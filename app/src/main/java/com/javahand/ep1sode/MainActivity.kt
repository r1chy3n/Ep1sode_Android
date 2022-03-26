package com.javahand.ep1sode

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize( this ) {}

        // logo
        findViewById<TextView>( R.id.text_ep1sode_logo ).text =
            SpannableString( getString( R.string.ep1sode )).apply {
                setSpan(
                    ForegroundColorSpan( Color.WHITE ),
                    2,
                    3,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                ) // setSpan
            } // also

        // Ads
        findViewById<AdView>( R.id.adview_main )
            .loadAd( AdRequest.Builder().build())
    }
}