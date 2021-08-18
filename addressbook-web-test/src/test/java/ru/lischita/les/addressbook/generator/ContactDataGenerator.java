package ru.lischita.les.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.thoughtworks.xstream.XStream;
import ru.lischita.les.addressbook.model.ContactData;
import ru.lischita.les.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c",description = ("Contact Count"))
  public int count;
  @Parameter(names = "-f",description = ("Target File"))
  public String file;
  @Parameter(names = "-d",description = ("Data Format"))
  public String format;

  public static void main (String[] args) throws IOException {
    ContactDataGenerator generator=new ContactDataGenerator();
    JCommander jCommander=new JCommander(generator);
    try{
      jCommander.parse(args);}
    catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
   }

  private void run() throws IOException {
    List<ContactData> contacts=generateContacts(count);
    if (format.equals("xml")) {saveAsXML(contacts,new File(file));}
    else if (format.equals("json")){saveAsJSON(contacts,new File(file));}
    else System.out.println(("Unrecognized format data"+format));
  }

  private List<ContactData> generateContacts(int count){
   File photo=new File("src/test/resources/photo_0.jpg");

    List<ContactData> contacts=new ArrayList<>();
    for (int i=0;i<count;i++){
      contacts.add(new ContactData()
              .withName(String.format("Дмитрий %s",i))
              .withMiddlename(String.format("Тестович %s",i))
              .withLastname(String.format("Петрович%s",i))
              .withNickname(String.format("Тестер%s",i))
              .withTitle(String.format("Тестировщик%s",i))
              .withCompany(String.format("Тест-Комплект%s",i))
              .withAddress(String.format("г.Москва Тестовая дом 13 %s",i))
              .withHomephone(String.format("8-495-123-56-78 %s",i))
              .withMobilephone(String.format("8-976-456-67-87 %s",i))
              .withEmail(String.format("test@test_complect.ru %s",i))
              .withBday("11")
              .withBmonth("May")
              .withByear("1980")
              .withGroup("test1")
              .withHomeaddress(String.format("г. Москва, ул. Тестиррования  дом 13 %s",i))
              .withWorkphone(String.format("495-123-45-67 %s",i))
              .withPhoto(photo));
    }
    return contacts;
  }

    private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
    XStream xStream=new XStream();
    xStream.processAnnotations(ContactData.class);
    String xml=xStream.toXML(contacts);
    try( Writer writer= new FileWriter(file)) { writer.write(xml);}// вместо writer.close(); используем параметризированный try

  }

  private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
    //Gson gson= new GsonBuilder().setPrettyPrinting().create(); // сохранили все поля  в файл json
    //Gson gson= new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create(); // Сохранили только те поля, что помечены аннотаций Expose работает для простых типов

    JsonSerializer<File> serializer = (src, typeOfSrc, context) -> new JsonPrimitive(src.getPath()); // Сохраняем только те поля что помечены
    Gson gson = new GsonBuilder()                                                                   //аннотаций Expose но уже для поля типа File
            .registerTypeAdapter(File.class, serializer)
            .setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    String json=gson.toJson(contacts);
    try (Writer writer= new FileWriter(file)) { writer.write(json);}

  }


}

