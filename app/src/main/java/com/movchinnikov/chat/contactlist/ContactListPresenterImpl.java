package com.movchinnikov.chat.contactlist;

import com.movchinnikov.chat.contactlist.events.ContactListEvent;
import com.movchinnikov.chat.contactlist.ui.ContactListView;
import com.movchinnikov.chat.entities.User;
import com.movchinnikov.chat.lib.EventBus;
import com.movchinnikov.chat.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;


public class ContactListPresenterImpl implements ContactListPresenter {
    private EventBus eventBus;
    private ContactListView contactListView;
    private ContactListSessionInteractor contactListSessionInteractor;
    private ContactListInteractor contactListInteractor;


    public ContactListPresenterImpl(ContactListView contactListView) {
        this.eventBus = GreenRobotEventBus.INSTANCE;
        this.contactListView = contactListView;
        this.contactListSessionInteractor = new ContactListSessionInteractorImpl();
        this.contactListInteractor = new ContactListInteractorImpl();
    }

    @Override
    public void signOff() {
        contactListSessionInteractor.changeConnectionStatus(User.OFFLINE);
        contactListInteractor.destroyContactListListener();
        contactListInteractor.unSubscribeForContactEvents();
        contactListSessionInteractor.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return contactListSessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void removeContact(String email) {
        contactListInteractor.removeContact(email);
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onResume() {
        contactListSessionInteractor.changeConnectionStatus(User.ONLINE);
        contactListInteractor.subscribeForContactEvents();
    }

    @Override
    public void onPause() {
        contactListSessionInteractor.changeConnectionStatus(User.OFFLINE);
        contactListInteractor.unSubscribeForContactEvents();
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        contactListInteractor.destroyContactListListener();
        contactListView = null;
    }

    @Override
    @Subscribe
    public void onEventMainThread(ContactListEvent event) {
        User user = event.getUser();
        switch (event.getEventType()) {
            case ContactListEvent.onContactAdded:
                onContactAdded(user);
                break;
            case ContactListEvent.onContactChanged:
                onContactChanged(user);
                break;
            case ContactListEvent.onContactRemoved:
                onContactRemoved(user);
                break;
        }
    }

    private void onContactAdded(User user) {
        if (contactListView != null) {
            contactListView.onContactAdded(user);
        }
    }

    private void onContactChanged(User user) {
        if (contactListView != null) {
            contactListView.onContactChanged(user);
        }
    }

    private void onContactRemoved(User user) {
        if (contactListView != null) {
            contactListView.onContactRemoved(user);
        }
    }
}
