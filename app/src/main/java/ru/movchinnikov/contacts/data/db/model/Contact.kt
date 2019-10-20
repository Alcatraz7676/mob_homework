package ru.movchinnikov.contacts.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.movchinnikov.contacts.data.db.model.Contact.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Contact(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: Long,
    @ColumnInfo(name = COLUMN_NAME)
    val name: String,
    @ColumnInfo(name = COLUMN_SURNAME)
    val surname: String,
    @ColumnInfo(name = COLUMN_PATRONYMIC)
    val patronymic: String,
    @ColumnInfo(name = COLUMN_PHONE)
    val phone: String,
    @ColumnInfo(name = COLUMN_EMAIL)
    val email: String
) {

    companion object {
        const val TABLE_NAME = "contacts"

        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_SURNAME = "surname"
        private const val COLUMN_PATRONYMIC = "patronymic"
        private const val COLUMN_PHONE = "phone"
        private const val COLUMN_EMAIL = "email"

        const val QUERY_ALL = "SELECT * FROM $TABLE_NAME"
        const val QUERY_BY_ID = "SELECT * FROM $TABLE_NAME WHERE id = :id"
        const val DELETE_BY_ID = "DELETE FROM $TABLE_NAME WHERE id = :id"
    }
}