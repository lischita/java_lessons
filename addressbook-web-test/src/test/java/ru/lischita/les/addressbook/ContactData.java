package ru.lischita.les.addressbook;

public class ContactData {
  private final String name;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String title;
  private final String company;
  private final String address;
  private final String homephone;
  private final String mobilephone;
  private final String email;
  private final String bday;
  private final String bmonth;
  private final String byear;
  private final String homeaddress;

  public ContactData(String name, String middlename, String lastname, String nickname, String title, String company, String address, String homephone, String mobilephone, String email, String bday, String bmonth, String byear, String homeaddress) {
    this.name = name;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.homephone = homephone;
    this.mobilephone = mobilephone;
    this.email = email;
    this.bday = bday;
    this.bmonth = bmonth;
    this.byear = byear;
    this.homeaddress = homeaddress;
  }

  public String getName() {
    return name;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getEmail() {
    return email;
  }

  public String getBday() {
    return bday;
  }

  public String getBmonth() {
    return bmonth;
  }

  public String getByear() {
    return byear;
  }

  public String getHomeaddress() {
    return homeaddress;
  }
}
