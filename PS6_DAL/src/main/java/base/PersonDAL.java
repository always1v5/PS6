package base;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.PersonDomainModel;
import util.HibernateUtil;

public class PersonDAL  {


	public static PersonDomainModel addPerson(PersonDomainModel Person) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int employeeID = 0;
		try {
			tx = session.beginTransaction();
			session.save(Person);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Person;
	}
	
	
	public static ArrayList<PersonDomainModel> getPersons() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel PersonGet = null;		
		ArrayList<PersonDomainModel> stus = new ArrayList<PersonDomainModel>();
		
		try {
			tx = session.beginTransaction();	
			
			List Person = session.createQuery("FROM PersonDomainModel").list();
			for (Iterator iterator = Person.iterator(); iterator.hasNext();) {
				PersonDomainModel stu = (PersonDomainModel) iterator.next();
				Person.add(Person);

			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Person;
	}		
	
	
	public static PersonDomainModel getPerson(UUID Person) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel PersonGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			Query query = session.createQuery("from PersonDomainModel where PersonId = :id ");
			query.setParameter("id", PersonID.toString());
			
			List<?> list = query.list();
			PersonGet = (PersonDomainModel)list.get(0);
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return PersonGet;
	}		
	
	public static void deletePerson(UUID PersonID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel PersonGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			PersonDomainModel Person = (PersonDomainModel) session.get(PersonDomainModel.class, PersonID);
			session.delete(Person);
		
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}	
	

	public static PersonDomainModel updatePerson(PersonDomainModel Person) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel PersonGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			session.update(Person);
	
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return Person;
	}		
	
	
	
	
	
	
}
