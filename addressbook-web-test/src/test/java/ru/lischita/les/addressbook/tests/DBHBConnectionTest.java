package ru.lischita.les.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.GroupData;

import java.util.List;

public class DBHBConnectionTest {

  private SessionFactory sessionFactory;

  @BeforeClass
  protected void setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
    catch (Exception e) {
      e.printStackTrace(); // выводим сообщение об ошиибке на консоль
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy( registry );
    }
  }


  @Test
  public void testHbConnection (){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List result = session.createQuery( "from GroupData" ).list(); // извелкаем объект OQL ( object query language )
    for ( GroupData group : (List<GroupData>) result ) // можно убрать преобразование типа, указав ранее тип result kak  List<GroupData>, а не List
    {
      System.out.println(group);

    }
    session.getTransaction().commit();
    session.close();

  }

}
