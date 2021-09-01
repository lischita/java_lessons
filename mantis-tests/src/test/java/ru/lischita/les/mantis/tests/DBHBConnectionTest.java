package ru.lischita.les.mantis.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.lischita.les.mantis.model.UsersData;

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
    List result = session.createQuery( "from UsersData" ).list(); // извелкаем объект OQL ( object query language )
    for ( UsersData users : (List<UsersData>) result ) // можно убрать преобразование типа, указав ранее тип result kak  List<GroupData>, а не List
    {
      System.out.println(users);

    }
    session.getTransaction().commit();
    session.close();

  }

}
