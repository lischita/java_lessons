package ru.lischita.les.addressbook.tests;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.lischita.les.addressbook.model.GroupData;
import ru.lischita.les.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {


  @DataProvider
  public Iterator<Object[]> validGroups(){
    List<Object[]> list=new ArrayList<Object[]>();
    list.add(new Object[]{"test_1","header_1","footer_1"});
    list.add(new Object[]{"test_2","header_2","footer_2"});
    list.add(new Object[]{"test_3","header_3","footer_3"});
    return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroups_1(){
    List<Object[]> list=new ArrayList<Object[]>();
    list.add(new Object[]{new GroupData().withName("test_1").withHeader("header_1").withFooter("footer_1")});
    list.add(new Object[]{new GroupData().withName("test_2").withHeader("header_2").withFooter("footer_2")});
    list.add(new Object[]{new GroupData().withName("test_3").withHeader("header_3").withFooter("footer_3")});
    return list.iterator();
  }

  @DataProvider // читаем данные из файла csv
  public Iterator<Object[]> validGroupsFromFileCSV() throws IOException {
    List<Object[]> list=new ArrayList<Object[]>();
    //try (BufferedReader reader= new BufferedReader(new FileReader("src/test/resurces/groups.csv")))
    try (BufferedReader reader= new BufferedReader(new FileReader(app.properties.getProperty("groupsDataCSV"))))
    {
      String line = reader.readLine();
      while (line != null) {
        String[] split = line.split(";");
        list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
        line = reader.readLine();
      }
      return list.iterator();
    }
  }

  @DataProvider // читаем данные из файла xml
  public Iterator<Object[]> validGroupsFromFileXML() throws IOException {
   // try (BufferedReader reader= new BufferedReader(new FileReader("src/test/resurces/groups.xml")))
    try (BufferedReader reader= new BufferedReader(new FileReader(app.properties.getProperty("groupsDataXML"))))
    {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }
  @DataProvider // читаем данные из файла json
  public Iterator<Object[]> validGroupsFromFileJSON() throws IOException {
    //try (BufferedReader reader= new BufferedReader(new FileReader("src/test/resurces/groups.json")))
    try (BufferedReader reader= new BufferedReader(new FileReader(app.properties.getProperty("groupsDataJSON"))))
    {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
      }.getType());
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }



  @Test (enabled = false, dataProvider = "validGroups")// параметризованный тест на вход поступают данные их строк
  public void testGroupCreation(String name,String header, String footer) throws Exception
  {
    GroupData group=new GroupData().withName(name).withFooter(footer).withHeader(header);
    app.goTo().groupPage();
    Groups before=app.group().all();
    app.group().create(group);
    Groups after=app.group().all();
    assertThat(after.size(),equalTo(before.size()+1));
    Assert.assertEquals(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt())).hashCode(),after.hashCode());
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false,dataProvider = "validGroups_1")// параметризованный тест на вход поступают объекты
  public void testGroupCreation(GroupData group) throws Exception
  {
    app.goTo().groupPage();
    Groups before=app.group().all();
    app.group().create(group);
    Groups after=app.group().all();
    assertThat(after.size(),equalTo(before.size()+1));
    Assert.assertEquals(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt())).hashCode(),after.hashCode());
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }

  @Test (dataProvider = "validGroupsFromFileJSON")// параметризованный тест на вход поступают данные из файла json
  public void testGroupCreationFileJSON(GroupData group) throws Exception
  {
    app.goTo().groupPage();
    Groups before=app.group().all();
    app.group().create(group);
    Groups after=app.group().all();
    assertThat(after.size(),equalTo(before.size()+1));
    Assert.assertEquals(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt())).hashCode(),after.hashCode());
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

  }




  @Test (dataProvider = "validGroupsFromFileXML")// параметризованный тест на вход поступают данные из файла xml
  public void testGroupCreationFileXML(GroupData group) throws Exception
  {
    app.goTo().groupPage();
    Groups before=app.group().all();
    app.group().create(group);
    Groups after=app.group().all();
    assertThat(after.size(),equalTo(before.size()+1));
    Assert.assertEquals(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt())).hashCode(),after.hashCode());
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }


  @Test (dataProvider = "validGroupsFromFileCSV")// параметризованный тест на вход поступают данные из файла csv
  public void testGroupCreationFileCSV(GroupData group) throws Exception
  {
    app.goTo().groupPage();
    Groups before=app.group().all();
    app.group().create(group);
    Groups after=app.group().all();
    assertThat(after.size(),equalTo(before.size()+1));
    Assert.assertEquals(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt())).hashCode(),after.hashCode());
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }


  @Test (enabled = false)
  public void testGroupCreation() throws Exception
  {
    app.goTo().groupPage();
    Groups before=app.group().all();
    GroupData group=new GroupData().withName("test1").withFooter("test2").withHeader("test3");
    app.group().create(group);
    Groups after=app.group().all();
    assertThat(after.size(),equalTo(before.size()+1));
    Assert.assertEquals(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt())).hashCode(),after.hashCode());
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testGroupCreation_1() throws Exception
  {
    app.goTo().groupPage();
    Groups before=app.group().all();
    GroupData group=new GroupData().withName("test1'");
    app.group().create(group);
    Groups after=app.group().all();
    assertThat(after.size(),equalTo(before.size()));
    Assert.assertEquals(before.hashCode(),after.hashCode());
    assertThat(after, equalTo(before));
  }

}
