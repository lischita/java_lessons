package ru.lischita.les.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.lischita.les.addressbook.model.GroupData;
import ru.lischita.les.addressbook.model.Groups;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase{

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returntoGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    click(By.name("group_header"));
    click(By.xpath("//form[@action='/addressbook/group.php']"));
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deletedSelectedGroups() {
    click(By.name("delete"));
  }

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
   // click(By.name("selected[]"));
  }

  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='"+id+ "']")).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
   initGroupCreation();
   fillGroupForm(group);
   submitGroupCreation();
   groupsCache=null;
   returntoGroupPage();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupsCache=null;
    returntoGroupPage();
  }

  public void delete(int index) {
    selectGroup(index);
    deletedSelectedGroups();
    returntoGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deletedSelectedGroups();
    groupsCache=null;
    returntoGroupPage();
  }

  public boolean isAThereGroup() {
  return IsElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> list() {

    List<GroupData> groups=new ArrayList<GroupData>();
    List<WebElement> elements=wd.findElements(By.cssSelector("span.group"));
    for(WebElement element:elements){
      String name=element.getText();
      int id=Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().withId(id).withName(name));
    }
    return groups;

  }

 /* public Set<GroupData> all() {
    Set<GroupData> groups=new HashSet<GroupData>();
    List<WebElement> elements=wd.findElements(By.cssSelector("span.group"));
    for(WebElement element:elements){
      String name=element.getText();
      int id=Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().withId(id).withName(name));
    }
    return groups;

  }
*/

  private Groups groupsCache=null;


 public Groups all() {
   if(groupsCache!=null) {return new Groups(groupsCache);}
   groupsCache=new Groups();
   List<WebElement> elements=wd.findElements(By.cssSelector("span.group"));
   for(WebElement element:elements){
     String name=element.getText();
     int id=Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
     groupsCache.add(new GroupData().withId(id).withName(name));
   }
   return new Groups(groupsCache);
 }

}
