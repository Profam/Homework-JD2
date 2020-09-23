package by.rabtsevich.loader;

import by.rabtsevich.pojo.Person;
import by.rabtsevich.util.HibernateUtil;

import javax.persistence.EntityManager;


public class PersonLoader {
    public static void main(String[] args) throws Exception {

        Person person = new Person(null, 26, "Paul", "Rabtsevich");
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        HibernateUtil.close();
    }
}
