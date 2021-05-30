package com.furqoncreative.semuabisamasak.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.lang.reflect.Field

fun fixInputMethod(context: Context?) {
    if (context == null) {
        return
    }
    var inputMethodManager: InputMethodManager? = null
    try {
        inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    } catch (th: Throwable) {
        th.printStackTrace()
    }
    if (inputMethodManager == null) {
        return
    }
    val declaredFields: Array<Field> = inputMethodManager.javaClass.declaredFields
    for (declaredField in declaredFields) {
        try {
            if (!declaredField.isAccessible) {
                declaredField.isAccessible = true
            }
            val obj: Any = declaredField.get(inputMethodManager)
            if (obj !is View) {
                continue
            }
            val view: View = obj
            if (view.context === context) {
                declaredField.set(inputMethodManager, null)
            } else {
                continue
            }
        } catch (th: Throwable) {
            th.printStackTrace()
        }
    }
}