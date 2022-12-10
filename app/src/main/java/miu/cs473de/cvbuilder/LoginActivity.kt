package miu.cs473de.cvbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import miu.cs473de.cvbuilder.common.Person
import kotlinx.android.synthetic.main.activity_login.*
import com.miu.curriculumvitae.common.*
import miu.cs473de.cvbuilder.common.Contact
import miu.cs473de.cvbuilder.common.Project
import miu.cs473de.cvbuilder.common.Work

class LoginActivity : AppCompatActivity() {
    val KEY = "PERSON"
    val PREF = "CVPREF"
    var users = ArrayList<Person>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        createPerson()

        val prefs = getSharedPreferences(PREF, MODE_PRIVATE)

        if (prefs.getBoolean("auth", false)) {
            val email = prefs.getString("email", "")
            if (email != null) {
                val foundUser = findUserByEmail(email)
                if(foundUser != null) {
                    startMainActivity(foundUser)
                }
            }
        }

        login.setOnClickListener {
            val inputEmail = et_email.text.toString()
            if (isValidated(inputEmail, et_password.text.toString())) {
                val editor = prefs.edit()

                editor.putBoolean("auth", true)
                editor.putString("email", inputEmail)
                editor.apply()

                val foundUser = findUserByEmail(inputEmail)
                if(foundUser != null) {
                    startMainActivity(foundUser)
                }


            } else {
                Toast.makeText(applicationContext, "Wrong credential", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun startMainActivity(person: Person) {
        var mainIntent = Intent(applicationContext, MainActivity::class.java)

        mainIntent.putExtra(KEY, person)
        startActivity(mainIntent)
    }

    private fun isValidated(email: String, password: String): Boolean {
        for (user in users) {
            if (user.contact.email == email && user.password == password) {
                return true
            }
        }
        return false
    }

    private fun findUserByEmail(email: String): Person? {
        for (user in users) {
            if (user.contact.email == email) {
                println(user.contact.email)
                return user
            }
        }
        return null
    }

    fun createPerson() {
        //---------------------------------Contact---------------------------------//
        var contact1: Contact = Contact("641-819-2224", "debatjargal@miu.edu.mn","bdeegi","bdeegii","https://github.com/Delgerdalai14","bdeegii")


        //---------------------------------Contact end---------------------------------//
        //---------------------------------Education---------------------------------//
        var education1: Education = Education("Bachelor of Computer Science", "2008-2012","CSMS of Mongolia")
        var education1_1: Education = Education("Master of Computer Science", "2021-2023", "MIU")

        //---------------------------------Education end---------------------------------//
        //---------------------------------Project---------------------------------//
        var project1: Project = Project("2012-2014","Child Money", ".NET developer")
        var project1_1: Project = Project("2018-2021","Core Banking", "Full Stack Developer")
        //---------------------------------Project end---------------------------------//
        //---------------------------------Work---------------------------------//
        var work1: Work = Work("2016-2018", "General office for Labor","Senior Developer")
        var work1_1: Work = Work("2018-2021", "KhanBANK LLC","Senior Developer")

        //---------------------------------Work end---------------------------------//

        //---------------------------------Person1---------------------------------//
        var person1Strength: ArrayList<String> = arrayListOf("Confident", "Creative", "Dedicated", "Determined", "Decisive")
        var person1Weaknesses: ArrayList<String> = arrayListOf("Impatient", "Allows emotions to show", "Close-minded", "Perfectionist", "Likes to take risks")
        var person1Skills: ArrayList<String> = arrayListOf("Flexibility", "NodeJS", "ReactJS", "Angular", "MeteorJS", "VueJS")
        var person1: Person = Person(R.drawable.ava_delgerdalai, "Delgerdalai", "Batjargal", "miu123", "Full Stack Developer", "I am over 8+ years of IT experience which includes 6+ years of Extensive experience as a Full Stack Developer .\n", "www.leetcode.com/bdeegii/", person1Strength, person1Weaknesses, person1Skills, arrayListOf(education1, education1_1),contact1, arrayListOf(project1,project1_1), arrayListOf(work1))


        users.add(person1)

    }
}