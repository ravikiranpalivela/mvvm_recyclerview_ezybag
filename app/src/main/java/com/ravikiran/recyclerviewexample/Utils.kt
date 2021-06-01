package com.ravikiran.recyclerviewexample

import android.app.Activity
import android.content.Intent
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(this)
    }
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}

fun String.getRandomString(length: Int) {
    (('a'..'z') + ('A'..'Z') + ('0'..'9')).random().toString().substring(0, length)
}

fun View.snackBar(message: String, action: (() -> Unit)? = null) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)

    action?.let {
        snackBar.setAction("Retry") {
            it()
        }
    }

    snackBar.show()
}


fun ImageView.showOrHidePassword(passwordField: EditText, passwordField2: EditText? = null) {
    setOnClickListener {
        if (passwordField.transformationMethod == PasswordTransformationMethod.getInstance()) {
            setImageResource(R.drawable.ic_eye_invisible)
            passwordField.transformationMethod = HideReturnsTransformationMethod.getInstance()
            passwordField2?.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else if (passwordField.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
            setImageResource(R.drawable.ic_eye_visible)
            passwordField.transformationMethod = PasswordTransformationMethod.getInstance()
            passwordField2?.transformationMethod = PasswordTransformationMethod.getInstance()
        }

        passwordField.setSelection(passwordField.text.length)
        passwordField2?.setSelection(passwordField2.text.length)
    }
}

