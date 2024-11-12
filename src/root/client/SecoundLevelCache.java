package root.client;

import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import root.entity.E_Employee;

public class SecoundLevelCache {
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure().addAnnotatedClass(E_Employee.class);
		SessionFactory factory = cfg.buildSessionFactory();
		

		// First Session
		Session session1 = factory.openSession();

		try {
			// Fetch an Employee object (it will be cached in the second-level cache)
			E_Employee emp1 = session1.get(E_Employee.class, 1);
			System.out.println("First fetch (Session 1): " + emp1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session1.close();
		}

		// Second Session
		Session session2 = factory.openSession();

		try {
			// Fetch the same Employee object in a new session (should hit second-level cache)
			E_Employee emp2 = session2.get(E_Employee.class, 1);
			System.out.println("First fetch (Session 2): " + emp2);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session2.close();
			factory.close();
		}
	}
}
