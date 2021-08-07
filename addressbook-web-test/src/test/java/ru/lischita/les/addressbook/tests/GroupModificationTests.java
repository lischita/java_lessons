package ru.lischita.les.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.GroupData;
import ru.lischita.les.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if(app.group().all().size()==0)
    {
      app.group().create(new GroupData().withName("test1").withFooter("test2").withHeader("test3"));
    }
  }

  @Test
  public void testGroupModification ()
  {
    Groups before=app.group().all();
    GroupData modifydGroup=before.iterator().next();
    GroupData group=new GroupData().withId(modifydGroup.getId()).withName("test1").withFooter("test2").withHeader("test3");
    app.group().modify(group);
    Groups after=app.group().all();
    Assert.assertEquals(after.size(),before.size());
    assertThat(after,equalTo(before.withOut(modifydGroup).withAdded(group)));
  }
}
