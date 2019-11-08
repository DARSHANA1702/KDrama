package heckfyxe.kdrama.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun View.longSnackbar(@StringRes text: Int) {
    Snackbar.make(this, text, Snackbar.LENGTH_SHORT).show()
}

fun Activity.longSnackbar(@StringRes text: Int) {
    findViewById<View>(android.R.id.content).longSnackbar(text)
}

fun Fragment.longSnackbar(@StringRes text: Int) {
    activity?.longSnackbar(text)
}

fun Context.toast(@StringRes text: Int) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(@StringRes text: Int) {
    context?.toast(text)
}