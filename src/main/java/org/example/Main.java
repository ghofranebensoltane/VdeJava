package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = new Student();
            student.setName("Mohamed");

            Book book = new Book();
            book.setTitle("Hibernate pour les débutants");

            book.setStudent(student);
            student.getBooks().add(book);

            em.persist(student);
            em.getTransaction().commit();

            System.out.println("Étudiant et livre enregistrés !");
        } finally {
            em.close();
            emf.close();
        }
    }
}
