package ru.lischita.les.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.GroupData;
import ru.lischita.les.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupModificationTests extends TestBase{

/*  До занятия №5
  @Test
  public void testGroupModification ()
  {
    app.getNavigationHelper().goToGroupPage();
     if(!app.getGroupHelper().isAThereGroup())
    {
      app.getGroupHelper().crateGroup(new GroupData("test1", "test2", "test3"));
    }
    List<GroupData> before=app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().initGroupModification();
    GroupData group=new GroupData(before.get(before.size()-1).getId(),"test1","test2","test3");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returntoGroupPage();
    List<GroupData> after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size());
    before.remove(before.size()-1);
    before.add(group);
    Comparator<? super GroupData> byId=(g1,g2)->Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after,before);
  }
*/
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
   // List<GroupData> before=app.group().list();
   //Set<GroupData> before=app.group().all();
    Groups before=app.group().all();
    GroupData modifydGroup=before.iterator().next();
    //int index=before.size()-1;
    GroupData group=new GroupData().withId(modifydGroup.getId()).withName("test1").withFooter("test2").withHeader("test3");
    app.group().modify(group);
    //List<GroupData> after=app.group().list();
    //Set<GroupData> after=app.group().all();
    Groups after=app.group().all();
    Assert.assertEquals(after.size(),before.size());
   // before.remove(modifydGroup);
    //before.add(group);
   // Comparator<? super GroupData> byId=(g1,g2)->Integer.compare(g1.getId(), g2.getId());
   // before.sort(byId);
   // after.sort(byId);
    //Assert.assertEquals(after,before);
    assertThat(after,equalTo(before.withOut(modifydGroup).withAdded(group)));
  }




}
