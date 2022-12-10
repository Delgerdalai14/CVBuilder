package miu.cs473de.cvbuilder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ActivityAbout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        title = "About me"
    }
}