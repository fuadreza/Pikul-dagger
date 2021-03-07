package io.github.fuadreza.pikul_dagger.utils

import android.widget.Toast
import io.github.fuadreza.pikul_dagger.PikulApp

fun toast(message: String?) = Toast.makeText(PikulApp().applicationContext, message, Toast.LENGTH_LONG).show()