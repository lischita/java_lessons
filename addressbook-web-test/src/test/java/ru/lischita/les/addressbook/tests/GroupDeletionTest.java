package ru.lischita.les.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if(app.group().list().size()==0)
    {
      app.group().create(new GroupData("test1", "test2", "test3"));
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
    List<GroupData> before=app.group().list();
    int index=before.size()-1;
    app.group().delete(index);
    List<GroupData> after=app.group().list();
    Assert.assertEquals(after.size(),before.size()-1);
    before.remove(index);
    Assert.assertEquals(after,before);

  }



}
