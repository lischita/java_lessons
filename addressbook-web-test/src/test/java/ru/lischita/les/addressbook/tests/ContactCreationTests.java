package ru.lischita.les.addressbook.tests;

import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.ContactData;
import ru.lischita.les.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase
{
    @DataProvider // читаем данные из файла xml
    public Iterator<Object[]> validContactsFromFileXML () throws IOException {
    //try (BufferedReader reader= new BufferedReader(new FileReader("src/test/resources/contacts.xml")))
    try (BufferedReader reader = new BufferedReader(new FileReader(app.properties.getProperty("contactsDataXML")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

    @DataProvider // читаем данные из файла json
    public Iterator<Object[]> validContactsFromFileJSON () throws IOException {
    //try (BufferedReader reader= new BufferedReader(new FileReader("src/test/resources/contacts.json")))
      JsonDeserializer<File> deserializer = (json, typeOfT, context) -> new File(json.getAsJsonPrimitive().getAsString());//для чтения типа File в том числе
     try (BufferedReader reader = new BufferedReader(new FileReader(app.properties.getProperty("contactsDataJSON")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      //Gson gson = new Gson(); // для простых типов
      Gson gson = new GsonBuilder().registerTypeAdapter(File.class, deserializer).create(); //  для чтения типа File в том числе
      List<ContactData> groups = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }


    @Test(dataProvider = "validContactsFromFileXML")
    public void testContactCreationTestsXML (ContactData contacts) throws Exception
    {
      app.goTo().HomePage();
      Contacts before = app.contact().all();
      app.contact().crate(contacts);
      app.goTo().HomePage();
      Contacts after = app.contact().all();
      Assert.assertEquals(after.size(), before.size() + 1);
      Assert.assertEquals(before.withAdded(contacts.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt())).hashCode(), after.hashCode());
      assertThat(after, equalTo(before.withAdded(contacts.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test(dataProvider = "validContactsFromFileJSON")
    public void testContactCreationTestsJSON (ContactData contacts) throws Exception
    {
      app.goTo().HomePage();
      Contacts before = app.contact().all();
      app.contact().crate(contacts);
      app.goTo().HomePage();
      Contacts after = app.contact().all();
      Assert.assertEquals(after.size(), before.size() + 1);
      Assert.assertEquals(before.withAdded(contacts.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt())).hashCode(), after.hashCode());
      assertThat(after, equalTo(before.withAdded(contacts.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }


    @Test(enabled = false)
    public void testContactCreationTests () throws Exception
    {
      File photo = new File("src/test/resources/photo_0.jpg");
      app.goTo().HomePage();
      Contacts before = app.contact().all();
      ContactData contacts = new ContactData().withName("Дмитрий").withMiddlename("Тестович").withLastname("Петрович").withNickname("Тестер")
              .withTitle("Тестировщик").withCompany("Тест-Комплект").withAddress("г.Москва Тестовая дом 13").withHomephone("8-495-123-56-78")
              .withMobilephone("8-976-456-67-87").withEmail("test@test_complect.ru").withBday("11").withBmonth("May").withByear("1980")
              .withGroup("test1").withHomeaddress("г. Москва, ул. Тестиррования  дом 13").withWorkphone("495-123-45-67").withPhoto(photo);
      app.contact().crate(contacts);
      app.goTo().HomePage();
      Contacts after = app.contact().all();
      Assert.assertEquals(after.size(), before.size() + 1);
      Assert.assertEquals(before.withAdded(contacts.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt())).hashCode(), after.hashCode());
      assertThat(after, equalTo(before.withAdded(contacts.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }


}
