import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainJPA {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemploJPA");
        EntityManager em = emf.createEntityManager();

        System.out.println("Hibernate inicializado. A tabela TB_PRODUTO_JPA foi verificada/criada no PostgreSQL.");

        em.close();
        emf.close();
    }
}
