package ru.lischita.les.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.ContactData;
import ru.lischita.les.addressbook.model.Contacts;
import ru.lischita.les.addressbook.model.GroupData;
import ru.lischita.les.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class DBContactDeleteTest extends TestBase{

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
  public void testContactDelete()
  {
    Contacts before=app.db().contacts();
    ContactData deleteContact=before.iterator().next();
    app.contact().delete(deleteContact);
    app.goTo().HomePage();
    Assert.assertEquals(app.contact().all().size(), before.size() - 1);
    Contacts after=app.db().contacts();
    assertThat(after, equalTo(before.withOut(deleteContact)));
    verifyInContactListInUI();
  }
}



