package com.codevalley.itranslatetest

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class AppController : MultiDexApplication() {
    var mContext: Context? = null
    override fun onCreate() {
        super.onCreate()
        mContext = this
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


    public fun getContext(): Context? {
        return mContext
    } // fun of getContext


}