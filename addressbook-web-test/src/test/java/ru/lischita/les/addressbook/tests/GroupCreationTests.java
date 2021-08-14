package ru.lischita.les.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.lischita.les.addressbook.model.GroupData;
import ru.lischita.les.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

  @Test (dataProvider = "validGroups_1")// параметризованный тест на вход поступают объекты
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
