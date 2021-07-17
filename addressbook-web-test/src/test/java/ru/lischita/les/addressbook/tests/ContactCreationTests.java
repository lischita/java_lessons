package ru.lischita.les.addressbook.tests;

import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreationTests() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Дмитрий", "Тестович", "Тестовый", "Тестер", "Тестировщик","Тест-Комплект", "г.Москва Тестовая дом 13", "8-495-123-56-78", "8-976-456-67-87", "test@test_complect.ru", "11", "May", "1980", "г. Москва, ул. Тестиррования  дом 13"));
    app.getContactHelper().submitContacform();
    app.getNavigationHelper().gotoHomePage();
  }

}
