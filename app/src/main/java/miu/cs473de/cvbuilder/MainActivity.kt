package miu.cs473de.cvbuilder

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.miu.curriculumvitae.ViewPagerAdapter
import miu.cs473de.cvbuilder.common.Person
import miu.cs473de.cvbuilder.fragments.AboutFragment
import miu.cs473de.cvbuilder.fragments.ContactFragment
import miu.cs473de.cvbuilder.fragments.HomeFragment
import miu.cs473de.cvbuilder.fragments.WorkFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val person = intent.getSerializableExtra("PERSON") as Person
        supportActionBar?.title = person.firstName
        supportActionBar?.subtitle = person.profession

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment().newInstance(person), "Home")
        adapter.addFragment(AboutFragment().newInstance(person), "About")
        adapter.addFragment(WorkFragment().newInstance(person), "Work")
        adapter.addFragment(ContactFragment().newInstance(person), "Contact")

        viewPager.adapter = adapter

        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.home)
        tabs.getTabAt(1)!!.setIcon(R.drawable.about)
        tabs.getTabAt(2)!!.setIcon(R.drawable.work)
        tabs.getTabAt(3)!!.setIcon(R.drawable.contact)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.m1 -> {
                startActivity(Intent(this, ActivityAbout::class.java))
                return true;
            }

            R.id.m3 -> {
                val pref = getSharedPreferences("CVPREF", Context.MODE_PRIVATE)
                val edit = pref.edit();
                edit.remove("auth")
                edit.remove("email")
                edit.apply()
                finish()
                return true;
            }
        }
        return super.onOptionsItemSelected(item)
    }
}