package ru.lischita.les.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.ContactData;


import java.util.Comparator;
import java.util.List;

public class ContactEditTest extends TestBase {

  /*@Test (enabled = false)
  public void testContactEdit() {
    app.goTo().gotoHomePage();
   if(!app.contact().isAThereContact())
    {
     app.contact().crate(new ContactData("Дмитрий", "Тестович", "Петрович", "Тестер", "Тестировщик","Тест-Комплект", "г.Москва Тестовая дом 13", "8-495-123-56-78", "8-976-456-67-87", "test@test_complect.ru", "11", "May", "1980","test1" ,"г. Москва, ул. Тестиррования  дом 13"));
    }
    app.goTo().gotoHomePage();
    List<ContactData> before=app.contact().getContactList();
    app.contact().initEditContact((before.size()-1));
    ContactData group =new ContactData(before.get(before.size()-1).getId(),"Яков", null, "Ронинсон", null, null, null, null, null, null, null, null,null,null,null,null);
    app.contact().fillContactForm(group,false);
    app.contact().submitEditForm();
    app.goTo().gotoHomePage();
    List<ContactData> after=app.contact().getContactList();
     Assert.assertEquals(after.size(),before.size());
     before.remove(before.size()-1);
     before.add(group);
     Comparator<? super ContactData> byId=(g1, g2)->Integer.compare(g1.getId(), g2.getId());
     before.sort(byId);
     after.sort(byId);
     Assert.assertEquals(after,before);
  }*/
}

