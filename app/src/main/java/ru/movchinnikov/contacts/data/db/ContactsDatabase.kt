package ru.movchinnikov.contacts.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.movchinnikov.contacts.data.db.model.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactsDao(): ContactDao

    companion object {

        private var INSTANCE: ContactsDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): ContactsDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context)
                }
                return INSTANCE!!
            }
        }

        private fun buildDatabase(context: Context): ContactsDatabase {
            return Room.databaseBuilder(
                context,
                ContactsDatabase::class.java,
                "Contacts.db")
                .build()
        }
    }
}