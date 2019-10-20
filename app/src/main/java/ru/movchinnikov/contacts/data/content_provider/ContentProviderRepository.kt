package ru.movchinnikov.contacts.data.content_provider

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.provider.ContactsContract
import androidx.core.content.ContextCompat
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import toothpick.InjectConstructor

@InjectConstructor
class ContentProviderRepository(
    private val applicationContext: Context
) : IContentProviderRepository {

    override fun getContactNames(): Maybe<List<String>> {
        return Maybe.create<List<String>> {
            val contactNames = mutableListOf<String>()

            if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
            ) {
                val cursor = applicationContext.contentResolver.query(
                    ContactsContract.Contacts.CONTENT_URI,
                    null,
                    null,
                    null,
                    null
                )
                if (cursor != null && cursor.moveToFirst()) {

                    while (cursor.moveToNext()) {
                        val contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                        contactNames.add(contactName)
                    }
                    cursor.close()
                }
                it.onSuccess(contactNames)
            } else {
                it.onComplete()
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}