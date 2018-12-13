package io.indrian16.mvvmexample.util

import android.content.Context
import android.net.ConnectivityManager
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun AppCompatActivity.replaceFragment(@IdRes containerId: Int, fragment: Fragment) {

    supportFragmentManager.beginTransaction()
        .replace(containerId, fragment)
        .commit()
}

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {

    add(disposable)
}

fun Context.isConnAvailable(): Boolean {

    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val actInfo = cm.activeNetworkInfo

    return actInfo != null && actInfo.isConnected
}

fun String.firstCapital(): String {

    return this.substring(0, 1).toUpperCase() + this.substring(1).toLowerCase()
}

fun Fragment.fastToa(message: String) {

    Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
}

fun View.toVisible() {

    this.visibility = View.VISIBLE
}

fun View.toGone() {

    this.visibility = View.GONE
}