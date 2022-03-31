package com.javahand.ep1sode

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.javahand.ep1sode.viewmodel.MainViewModel
import com.javahand.ep1sode.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity()
{
    private val KEY_LAST_FETCH = "LAST_FETCH"
    private val MILLIS_24_HOURS = 24L * 60 * 60 * 1000

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as Ep1sodeApplication).repository)
    } // by viewModels

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}

        // logo
        findViewById<TextView>(R.id.text_ep1sode_logo).text =
            SpannableString(getString(R.string.ep1sode)).apply {

                setSpan(
                    ForegroundColorSpan(Color.WHITE),
                    2,
                    3,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                ) // setSpan
            } // also

        // list
        val recyclerEp1sode = findViewById<RecyclerView>(R.id.recycler_ep1sode)
        val ep1sodeAdapter = Ep1sodeAdapter()

        ep1sodeAdapter.setHasStableIds(true)

        recyclerEp1sode.adapter = ep1sodeAdapter
        recyclerEp1sode.layoutManager = LinearLayoutManager(this)

        // Ads
        findViewById<AdView>(R.id.adview_banner_main)
            .loadAd(AdRequest.Builder().build())

        mainViewModel.allEp1sodes.observe(this) { ep1List ->

            ep1List.let {

                ep1sodeAdapter.submitList(it)
            } // let
        } // observe
    }

    override fun onResume()
    {
        super.onResume()

        val sharedPrefs = getPreferences(MODE_PRIVATE)
        val lastFetchMillis = sharedPrefs.getLong(KEY_LAST_FETCH, 0)

        if (lastFetchMillis + MILLIS_24_HOURS < System.currentTimeMillis())
        {
            mainViewModel.fetchFromKbro()
        } // if

        sharedPrefs.edit()
            .putLong( KEY_LAST_FETCH, System.currentTimeMillis()).apply()
    } // fun onResume()
} // class MainActivity