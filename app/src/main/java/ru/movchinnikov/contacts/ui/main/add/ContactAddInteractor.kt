package ru.movchinnikov.contacts.ui.main.add

import io.reactivex.Completable
import ru.movchinnikov.contacts.data.db.model.Contact
import ru.movchinnikov.contacts.data.db.repository.IContactRepository
import toothpick.InjectConstructor

@InjectConstructor
class ContactAddInteractor(
    private val contactsRepository: IContactRepository
) : IContactAddInteractor {

    override fun addContact(contact: Contact): Completable {
        return contactsRepository.insertContact(contact)
    }
}