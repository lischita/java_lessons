package ru.lischita.les.addressbook.tests;

import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.ContactData;

public class ContactEditTest extends TestBase {

  @Test
  public void testContactEdit() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initEditContact();
    app.getContactHelper().fillContactForm(new ContactData("Дмитрий", "Тестович", "Петрович", "Тестер", "Тестировщик", "Тест-Комплект", "г.Москва Тестовая дом 13", "8-495-123-56-78", "8-976-456-67-87", "test@test_complect.ru", "11", "May", "1980", null,"г. Москва, ул. Тестиррования  дом 13"),false);
    app.getContactHelper().submitEditForm();
    app.getNavigationHelper().gotoHomePage();
  }
}

