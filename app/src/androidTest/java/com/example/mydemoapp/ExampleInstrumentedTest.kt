package com.example.mydemoapp

import com.example.mydemoapp.ui.login.LoggedInUserView
import androidx.appcompat.app.AppCompatActivity
import com.example.mydemoapp.ui.login.LoginViewModel
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mydemoapp.ui.login.LoginViewModelFactory
import android.widget.EditText
import android.widget.ProgressBar
import com.example.mydemoapp.ui.login.LoginFormState
import com.example.mydemoapp.ui.login.LoginResult
import android.app.Activity
import android.text.TextWatcher
import android.text.Editable
import android.widget.TextView.OnEditorActionListener
import android.widget.TextView
import android.view.inputmethod.EditorInfo
import com.example.mydemoapp.R
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.mydemoapp.data.LoginRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mydemoapp.data.model.LoggedInUser
import com.example.mydemoapp.data.LoginDataSource
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.jvm.Volatile

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.mydemoapp", appContext.packageName)
    }
}