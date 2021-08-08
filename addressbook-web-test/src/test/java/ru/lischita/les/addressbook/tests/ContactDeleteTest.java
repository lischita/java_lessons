package ru.lischita.les.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.ContactData;
import ru.lischita.les.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeleteTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.contact().all().size()==0)
    {
      app.contact().crate(new ContactData().withName("Дмитрий").withMiddlename("Тестович").withLastname("Петрович").withNickname("Тестер").withTitle("Тестировщик").withCompany("Тест-Комплект").withAddress("г.Москва Тестовая дом 13").withHomephone("8-495-123-56-78").withMobilephone("8-976-456-67-87").withEmail("test@test_complect.ru").withBday("11").withBmonth("May").withByear("1980").withGroup("test1").withHomeaddress("г. Москва, ул. Тестиррования  дом 13").withWorkphone("495-123-45-67"));
    }
  }

  @Test
  public void testContactDelete()
  {
    app.goTo().HomePage();
    Contacts before=app.contact().all();
    ContactData contactGroup=before.iterator().next();
    app.contact().delete(contactGroup);
    app.goTo().HomePage();
    Contacts after=app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);
    Assert.assertEquals(before.withOut(contactGroup).hashCode(),after.hashCode());
    assertThat(after, equalTo(before.withOut(contactGroup)));
   }

}


