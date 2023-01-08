
import static org.ow2.jonas.examples.ear.entity.Compte.QN.ALL_COMPTES;
import static org.ow2.jonas.examples.ear.entity.Compte.QN.FIND_COMPTE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQueries({@NamedQuery(name=ALL_COMPTES, query="select o FROM Compte o"),
               @NamedQuery(name=FIND_COMPTE, query="select o FROM Compte o WHERE o.id = :ID")})
public class Compte implements Serializable {

    public static interface QN {

        String ALL_COMPTES = "Compte.allComptes";
        String FIND_COMPTE = "Compte.findCompte";
    }

    private static final long serialVersionUID = 0L;
    private long id;
    private double solde = 0.0;

    public Compte() {
        this.solde = 0.0;
    }


    public Compte(final double s) {
        this();
        setSolde(s);
    }

    public double getSolde() {
        return solde;
    }
    public void setSolde(final double s) {
        this.solde = s;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getName());
        sb.append("[id=");
        sb.append(getId());
        sb.append(", solde=");
        sb.append(getSolde());
        sb.append("]");
        return sb.toString();
    }
}
