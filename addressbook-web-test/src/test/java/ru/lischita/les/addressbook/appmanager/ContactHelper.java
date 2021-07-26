package ru.lischita.les.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.lischita.les.addressbook.model.ContactData;
import ru.lischita.les.addressbook.model.GroupData;


public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContacform() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("email"), contactData.getEmail());

    select(By.name("bday"), contactData.getBday());
    select(By.name("bmonth"), contactData.getBmonth());

    type(By.name("byear"), contactData.getByear());

    if (creation){select(By.name("new_group"), contactData.getGroup());}
    else { Assert.assertFalse(IsElementPresent(By.name("new_group")));}

    type(By.name("phone2"), contactData.getHomeaddress());

  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteSelectionContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void confirmContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void initEditContact() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitEditForm() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void crateContact(ContactData group) {
    initContactCreation();
    fillContactForm(group,true);
    submitContacform();

  }

  public boolean isAThereContact() {
    return IsElementPresent(By.name("selected[]"));
  }
}
