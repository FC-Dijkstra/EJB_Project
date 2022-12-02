/**
 * JOnAS: Java(TM) Open Application Server
 * Copyright (C) 2007 Bull S.A.S.
 * Contact: jonas-team@ow2.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 *
 * --------------------------------------------------------------------------
 * $Id: Compte.java 17350 2009-05-13 14:15:43Z fornacif $
 * --------------------------------------------------------------------------
 */

package org.ow2.jonas.examples.ear.entity;

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

/**
 * Author of a book.
 * @author Florent Benoit
 */
@Entity
@NamedQueries({@NamedQuery(name=ALL_COMPTES, query="select o FROM Compte o"),
               @NamedQuery(name=FIND_COMPTE, query="select o FROM Compte o WHERE o.id = :ID")})
public class Compte implements Serializable {
    /**
     * Store Query names.
     */
    public static interface QN {
        /**
         * Search all authors.
         */
        String ALL_COMPTES = "Compte.allComptes";

        /**
         * Search a named author.
         */
        String FIND_COMPTE = "Compte.findCompte";
    }

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 0L;

    /**
     * Primary key (will be auto generated).
     */
    private long id;

    /**
     * Name of the author.
     */
    private double solde = 0.0;

    /**
     * Default constructor.
     */
    public Compte() {
        this.solde = 0.0;
    }

    /**
     * Constructor with a given author name.
     * @param name - the name of the author
     */
    public Compte(final double s) {
        this();
        setSolde(s);
    }


    /**
     * @return name of the author
     */
    public double getSolde() {
        return solde;
    }
    /**
     * Sets the name of the author.
     * @param name - the name of this author
     */
    public void setSolde(final double s) {
        this.solde = s;
    }

    /**
     * @return an id for this object (incremented automatically)
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getId() {
        return this.id;
    }

    /**
     * Sets the id of this author object.
     * @param id the given id of this author
     */
    public void setId(final long id) {
        this.id = id;
    }


    /**
     * @return String representation of this entity object.
     */
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
