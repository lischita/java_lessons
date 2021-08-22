package ru.lischita.les.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lischita.les.addressbook.appmanager.ContactHelper;
import ru.lischita.les.addressbook.model.ContactData;
import ru.lischita.les.addressbook.model.Contacts;
import ru.lischita.les.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DBContactEditTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.db().contacts().size()==0) {
      app.goTo().HomePage();
      app.contact().crate(new ContactData().withName("Дмитрий").withMiddlename("Тестович").withLastname("Петрович")
              .withNickname("Тестер").withTitle("Тестировщик").withCompany("Тест-Комплект").withAddress("г.Москва Тестовая дом 13")
              .withHomephone("8-495-123-56-78").withMobilephone("8-976-456-67-87").withEmail("test@test_complect.ru")
              .withBday("11").withBmonth("May").withByear("1980").withGroup("test1").withHomeaddress("г. Москва, ул. Тестиррования  дом 13")
              .withWorkphone("495-123-45-67").withPhoto(new File("src/test/resources/photo_0.jpg")));
    }

  }

  @Test
  public void testContactEdit()
  {
    Contacts before=app.db().contacts();
    ContactData contactGroup=before.iterator().next();
    ContactData contact =new ContactData().withId(contactGroup.getId()).withName("Яков").withMiddlename("Тестович").withLastname("Ронинсон")
            .withNickname("Тестер").withTitle("Тестировщик").withCompany("Тест-Комплект").withAddress("г.Москва Тестовая дом 13")
            .withHomephone("8-495-123-56-78").withMobilephone("8-976-456-67-87").withEmail("test@test_complect.ru")
            .withBday("11").withBmonth("May").withByear("1980").withGroup("test1").withHomeaddress("г. Москва, ул. Тестиррования  дом 13")
            .withWorkphone("495-123-45-67").withPhoto(new File("src/test/resources/photo_0.jpg"));
    app.contact().modify(contact);
    app.goTo().HomePage();
    Assert.assertEquals(app.contact().all().size(), before.size() );
    Contacts after=app.db().contacts();
    assertThat(after,equalTo(before.withModify(contactGroup,contact)));
    verifyInContactListInUI();
  }
}
