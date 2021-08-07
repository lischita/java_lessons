package ru.lischita.les.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

  private Set<ContactData> delegate;

  public Contacts(Contacts contactData) {
    this.delegate=new HashSet<ContactData>(contactData.delegate);
  }

  public Contacts() {
    this.delegate=new HashSet<ContactData>();
  }

  @Override
  protected Set<ContactData> delegate() {
    return delegate;
  }

  public Contacts withAdded(ContactData contact)
  {
    Contacts contacts=new Contacts(this);
    contacts.add(contact);
    return contacts;
  }
  public Contacts withOut(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
  public Contacts withModify(ContactData contactGroup, ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contactGroup);
    contacts.add(contact);
    return contacts;
  }

}

