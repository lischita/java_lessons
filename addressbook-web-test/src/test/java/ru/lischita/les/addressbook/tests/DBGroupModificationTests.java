package ru.lischita.les.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.GroupData;
import ru.lischita.les.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DBGroupModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
     if(app.db().groups().size()==0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withFooter("test2").withHeader("test3"));
    }
  }

  @Test
  public void testGroupModificationDB ()
  {
    Groups before=app.db().groups();
    GroupData modifydGroup=before.iterator().next();
    GroupData group=new GroupData().withId(modifydGroup.getId()).withName("test1").withFooter("test2").withHeader("test3");
    app.goTo().groupPage();
    app.group().modify(group);
    assertThat(app.group().count(),equalTo(before.size()));
    Groups after=app.db().groups();
    assertThat(after,equalTo(before.withOut(modifydGroup).withAdded(group)));
  }
}
