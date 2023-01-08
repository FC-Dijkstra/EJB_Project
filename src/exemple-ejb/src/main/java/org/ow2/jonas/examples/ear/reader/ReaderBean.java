
import static Compte.QN.ALL_COMPTES;
import static Compte.QN.FIND_COMPTE;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import Compte;

/**
 *The {@link ReaderBean} EJB is an unrestricted, read-only, Stateless Bean.
 * @author Guillaume Sauthier
 */
@Stateless
@Local(LocalReader.class)
@Remote(RemoteReader.class)
public class ReaderBean implements LocalReader, RemoteReader {

    /**
     * Entity manager used by this bean.
     */
    @PersistenceContext
    private EntityManager entityManager = null;

    /**
     * Find a given {@link Author} using it's name as a key.
     * @param name {@link Author}'s name.
     * @return the first {@link Author} that matches the given name.
     */
    @SuppressWarnings("unchecked")
    public Compte findCompte(long id) {
        Query query = entityManager.createNamedQuery(FIND_COMPTE);
        query.setParameter("ID", id);
        List<Compte> comptes = query.getResultList();
        if (comptes != null && comptes.size() > 0) {
            return comptes.get(0);
        }
        return null;
    }

    /**
     * @return the list of all the persisted {@link Author}s.
     */
    @SuppressWarnings("unchecked")
    public List<Compte> listAllComptes() {
        return entityManager.createNamedQuery(ALL_COMPTES).getResultList();
    }


}
