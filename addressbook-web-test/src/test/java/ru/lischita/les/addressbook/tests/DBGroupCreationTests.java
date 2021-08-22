package ru.lischita.les.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.lischita.les.addressbook.model.GroupData;
import ru.lischita.les.addressbook.model.Groups;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DBGroupCreationTests extends TestBase {

  @Test
  public void testGroupCreationDB() throws Exception
  {
    app.goTo().groupPage();
    Groups before=app.db().groups();
    GroupData group=new GroupData().withName("test1").withFooter("test2").withHeader("test3");
    app.group().create(group);
    assertThat(app.group().count(),equalTo(before.size()+1));
    Groups after=app.db().groups();
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    verifyInGroupListInUI();
  }
}

