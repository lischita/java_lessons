package ru.lischita.les.addressbook.tests;

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
      app.contact().crate(new ContactData().withName("Дмитрий").withMiddlename("Тестович").withLastname("Петрович").withNickname("Тестер").withTitle("Тестировщик").withCompany("Тест-Комплект").withAddress("г.Москва Тестовая дом 13").withHomephone("8-495-123-56-78").withMobilephone("8-976-456-67-87").withEmail("test@test_complect.ru").withBday("11").withBmonth("May").withByear("1980").withGroup("test1").withHomeaddress("г. Москва, ул. Тестиррования  дом 13"));
    }
  }

@Test
  public void testContactEdit()
  {
    app.goTo().HomePage();
   // List<ContactData> before=app.contact().getContactList();
    Contacts before=app.contact().all();
    //app.contact().initEditContact((before.size()-1));
    ContactData contactGroup=before.iterator().next();
    ContactData contact =new ContactData().withId(contactGroup.getId()).withName("Яков").withLastname("Ронинсон");
    //app.contact().fillContactForm(group,false);
   // app.contact().submitEditForm();
    app.contact().modify(contact);
    app.goTo().HomePage();
    //List<ContactData> after=app.contact().getContactList();
    Contacts after=app.contact().all();
    Assert.assertEquals(after.size(),before.size());
     //before.remove(before.size()-1);
     //before.add(group);
     //Comparator<? super ContactData> byId=(g1, g2)->Integer.compare(g1.getId(), g2.getId());
     //before.sort(byId);
     //after.sort(byId);
     //Assert.assertEquals(after,before);
     assertThat(after,equalTo(before.withOut(contactGroup).withAdded(contact)));
  }
}

