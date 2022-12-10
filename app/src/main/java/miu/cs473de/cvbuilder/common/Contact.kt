package miu.cs473de.cvbuilder.common

import java.io.Serializable

class Contact (var phone: String,
               var email: String,
               var facebook: String,
               var twitter: String,
               var github: String,
               var instagram:String) : Serializable {
}