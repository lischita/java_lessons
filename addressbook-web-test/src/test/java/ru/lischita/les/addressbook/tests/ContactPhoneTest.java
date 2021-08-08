package ru.lischita.les.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import ru.lischita.les.addressbook.model.ContactData;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class ContactPhoneTest extends TestBase
{
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().HomePage();
    if(app.contact().all().size()==0)
    {
      app.contact().crate(new ContactData().withName("Дмитрий").withMiddlename("Тестович").withLastname("Петрович").withNickname("Тестер").withTitle("Тестировщик")
              .withCompany("Тест-Комплект").withAddress("г.Москва Тестовая дом 13").withHomephone("8-495-123-56-78").withMobilephone("8-976-456-67-87")
              .withEmail("test@test_complect.ru").withEmail2("test2@test2_complect2.ru").withEmail3("test3@test3_complect3.ru").withBday("11")
              .withBmonth("May").withByear("1980").withGroup("test1").withHomeaddress("г. Москва,ул. Тестиррования  дом 13").withWorkphone("495-123-45-67"));
    }
  }


  @Test
  public void testContactPhone()

  {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoEditForm(contact);
    /*Версия теста когда строку с телефонами резали в all()
    assertThat(contact.getHomephone(),equalTo(cleaned(contactInfoFromEditForm.getHomephone())));
    assertThat(contact.getMobilephone(),equalTo(cleaned(contactInfoFromEditForm.getMobilephone())));
    assertThat(contact.getWorkphone(),equalTo(cleaned(contactInfoFromEditForm.getWorkphone())));*/
    assertThat(contact.getAllphones().hashCode(),equalTo(mergePhones(contactInfoFromEditForm).hashCode()));
    assertThat(contact.getAllphones(),equalTo(mergePhones(contactInfoFromEditForm))); // Вариант когда склеиваем строки

  }

  @Test
  public void testContactEmails()

  {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoEditForm(contact);
    assertThat(contact.getAllemails().hashCode(),equalTo(mergeEmails(contactInfoFromEditForm).hashCode()));
    assertThat(contact.getAllemails(),equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  @Test
  public void testContactAddress()

  {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoEditForm(contact);
    assertThat(contact.getAddress().hashCode(),equalTo(contactInfoFromEditForm.getAddress().hashCode()));
    assertThat(contact.getAddress(),equalTo(contactInfoFromEditForm.getAddress()));
  }



  private String mergeEmails(ContactData contact)
  {
    return  Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
            .stream().filter(s->!s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  private String mergePhones(ContactData contact)
  {
    return  Arrays.asList(contact.getHomephone(),contact.getMobilephone(),contact.getWorkphone(),contact.getHomeaddress())
            .stream().filter(s->!s.equals(""))
            .map(ContactPhoneTest::cleaned)
            .collect(Collectors.joining("\n"));
  }

  static String cleaned (String phone)
  {
    return phone.replaceAll("\\s","").replaceAll("[-().]","");
  }


}
