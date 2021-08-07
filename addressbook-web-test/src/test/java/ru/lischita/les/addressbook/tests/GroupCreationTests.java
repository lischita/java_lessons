package ru.lischita.les.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.lischita.les.addressbook.model.GroupData;
import ru.lischita.les.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
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

  @Test
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
