package util;

import java.util.List;
import model.Mulher;
import org.hibernate.Session;


public class MulherDAO {

    private Session session;
    FabricaDeConexao fabrica;
    public void insert(Mulher m) {
        fabrica = new FabricaDeConexao();
        session = fabrica.novaConexao();
        session.save(m);
        session.getTransaction().commit();
        session.close();
        fabrica.getFactory().close();
    }

    public List<Mulher> selectAll() {
        fabrica = new FabricaDeConexao();
        session = fabrica.novaConexao();
        session.getTransaction().commit();
        
        List<Mulher> mulheres = session.createQuery("from Mulher").list();

        session.close();
        fabrica.getFactory().close();
        return mulheres;
    }

}
