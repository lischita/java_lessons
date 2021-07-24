package ru.lischita.les.addressbook.tests;

import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.ContactData;
import ru.lischita.les.addressbook.model.GroupData;

public class ContactDeleteTest extends TestBase{

  @Test
  public void testContactDelete()
  {
     if(!app.getContactHelper().isAThereContact())
    {
      app.getContactHelper().crateContact(new ContactData("Дмитрий", "Тестович", "Петрович", "Тестер", "Тестировщик","Тест-Комплект", "г.Москва Тестовая дом 13", "8-495-123-56-78", "8-976-456-67-87", "test@test_complect.ru", "11", "May", "1980","test1" ,"г. Москва, ул. Тестиррования  дом 13"));
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectionContact();
    app.getContactHelper().confirmContactDeletion();
    app.getNavigationHelper().gotoHomePage();
  }
}
