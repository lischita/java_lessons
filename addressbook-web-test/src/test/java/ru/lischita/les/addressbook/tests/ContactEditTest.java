package ru.lischita.les.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.ContactData;


import java.util.Comparator;
import java.util.List;

public class ContactEditTest extends TestBase {

  @Test
  public void testContactEdit() {
    app.getNavigationHelper().gotoHomePage();
   if(!app.getContactHelper().isAThereContact())
    {
     app.getContactHelper().crateContact(new ContactData("Дмитрий", "Тестович", "Петрович", "Тестер", "Тестировщик","Тест-Комплект", "г.Москва Тестовая дом 13", "8-495-123-56-78", "8-976-456-67-87", "test@test_complect.ru", "11", "May", "1980","test1" ,"г. Москва, ул. Тестиррования  дом 13"));
    }
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before=app.getContactHelper().getContactList();
    app.getContactHelper().initEditContact((before.size()-1));
    ContactData group =new ContactData(before.get(before.size()-1).getId(),"Петр", null, "Олегов", null, null, null, null, null, null, null, null,null,null,null,null);
    app.getContactHelper().fillContactForm(group,false);
    app.getContactHelper().submitEditForm();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after=app.getContactHelper().getContactList();
     Assert.assertEquals(after.size(),before.size());
     before.remove(before.size()-1);
     before.add(group);
     Comparator<? super ContactData> byId=(g1, g2)->Integer.compare(g1.getId(), g2.getId());
     before.sort(byId);
     after.sort(byId);
     Assert.assertEquals(after,before);
  }
}

