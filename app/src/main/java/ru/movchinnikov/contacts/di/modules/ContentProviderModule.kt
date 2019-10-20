package ru.movchinnikov.contacts.di.modules

import ru.movchinnikov.contacts.data.content_provider.ContentProviderRepository
import ru.movchinnikov.contacts.data.content_provider.IContentProviderRepository
import toothpick.config.Module
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.toClass

class ContentProviderModule : Module() {

    init {
        bind<IContentProviderRepository>().toClass<ContentProviderRepository>().singleton()
    }

}