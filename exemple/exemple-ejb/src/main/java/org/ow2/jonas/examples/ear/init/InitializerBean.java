package org.ow2.jonas.examples.ear.init;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.ow2.jonas.examples.ear.entity.Compte;
import org.ow2.jonas.examples.ear.reader.LocalReader;
import org.ow2.jonas.examples.ear.writer.LocalWriter;

/**
 * The {@link InitializerBean} EJB is here to initialize only once
 * the Database/Entities. It simply checks if there is some {@link Author}s
 * already persisted; if none are found, we will inject defaults values.
 * @author Guillaume Sauthier
 */
@Stateless(mappedName="myInitializerBean")
@Remote(Initializer.class)
public class InitializerBean implements Initializer {

    /**
     * Injected reference to the {@link org.ow2.jonas.examples.ear.writer.Writer} EJB.
     */
    @EJB
    private LocalWriter writer;

    /**
     * Injected reference to the {@link org.ow2.jonas.examples.ear.reader.Reader} EJB.
     */
    @EJB
    private LocalReader reader;

    /**
     * Initialize the minimal set of entities needed by the sample.
     * @see org.ow2.jonas.examples.ear.init.Initializer#initializeEntities()
     */
    public void initializeEntities() {

        if (reader.findCompte(1) == null) {
            Compte c1 = new Compte(60.0);

            // Persists the Author and all of his books
            writer.addCompte(c1);
        }

        if (reader.findCompte(2) == null) {
            // Hugo was not persited, add it now.
            Compte c2 = new Compte(2);
            // Store
            writer.addCompte(c2);
        }
    }

}
