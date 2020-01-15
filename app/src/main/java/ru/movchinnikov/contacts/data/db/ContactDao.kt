package ru.movchinnikov.contacts.data.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import ru.movchinnikov.contacts.data.db.model.Contact
import ru.movchinnikov.contacts.data.db.model.Contact.Companion.DELETE_BY_ID
import ru.movchinnikov.contacts.data.db.model.Contact.Companion.QUERY_ALL
import ru.movchinnikov.contacts.data.db.model.Contact.Companion.QUERY_BY_ID

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(contacts: Contact): Completable

    @Update
    fun update(contact: Contact): Completable

    @Query(DELETE_BY_ID)
    fun deleteContactById(id: Long): Maybe<Int>

    @Query(QUERY_BY_ID)
    fun getContactById(id: Long): Single<Contact>

    @Query(QUERY_ALL)
    fun observeAll(): Observable<List<Contact>>
}