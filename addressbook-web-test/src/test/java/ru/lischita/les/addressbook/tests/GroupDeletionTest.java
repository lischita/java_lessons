package ru.lischita.les.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.GroupData;
import ru.lischita.les.addressbook.model.Groups;

import java.util.List;
import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if(app.group().all().size()==0)
    {
     // app.group().create(new GroupData("test1", "test2", "test3"));
    app.group().create(new GroupData().withName("test1").withFooter("test2").withHeader("test3"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
   /* был до занятия 5
      app.getNavigationHelper().goToGroupPage();
       if(!app.getGroupHelper().isAThereGroup())
    {
      app.getGroupHelper().crateGroup(new GroupData("test1", "test2", "test3"));
    }*/
    //List<GroupData> before=app.group().list();
   // Set<GroupData> before=app.group().all();
    Groups before=app.group().all();
    GroupData deletedGroup=before.iterator().next();
    //int index=before.size()-1;
    app.group().delete(deletedGroup);
    //app.group().delete(index);
    //List<GroupData> after=app.group().list();
    //Set<GroupData> after=app.group().all();
    Groups after=app.group().all();
    Assert.assertEquals(after.size(),before.size()-1);
   // before.remove(index);
    //before.remove(deletedGroup);
    //Assert.assertEquals(after,before);
    assertThat(after,equalTo(before.withOut(deletedGroup)));

  }



}
