package ru.lischita.les.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.ContactData;
import ru.lischita.les.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEditTest extends TestBase {



  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().HomePage();
    if(app.contact().all().size()==0)
    {
      app.contact().crate(new ContactData().withName("Дмитрий").withMiddlename("Тестович").withLastname("Петрович").withNickname("Тестер").withTitle("Тестировщик").withCompany("Тест-Комплект").withAddress("г.Москва Тестовая дом 13").withHomephone("8-495-123-56-78").withMobilephone("8-976-456-67-87").withEmail("test@test_complect.ru").withBday("11").withBmonth("May").withByear("1980").withGroup("test1").withHomeaddress("г. Москва, ул. Тестиррования  дом 13").withWorkphone("495-123-45-67"));
    }
  }

@Test
  public void testContactEdit()
  {

    app.goTo().HomePage();
    Contacts before=app.contact().all();
    ContactData contactGroup=before.iterator().next();
    ContactData contact =new ContactData().withId(contactGroup.getId()).withName("Яков").withLastname("Ронинсон");
    app.contact().modify(contact);
    app.goTo().HomePage();
    Contacts after=app.contact().all();
    Assert.assertEquals(after.size(),before.size());
    //assertThat(after,equalTo(before.withOut(contactGroup).withAdded(contact)));
    Assert.assertEquals(before.withModify(contactGroup,contact).hashCode(),after.hashCode());
    assertThat(after,equalTo(before.withModify(contactGroup,contact)));

  }
}

