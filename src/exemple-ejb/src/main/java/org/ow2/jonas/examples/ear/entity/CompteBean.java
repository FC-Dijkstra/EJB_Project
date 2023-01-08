import java.sql.*;

import javax.ejb.*;
import javax.naming.*;
import javax.sql.*;

public class CompteBean implements EntityBean {
    private EntityContext context;
    private Connection conn;
    private long ID;
    private double solde;

    public long getID() { return this.ID;}
    public double getSolde() { return this.solde; }
    public void setSolde(double solde) {this.solde = solde;}
    public void setEntityContext(EntityContext ctx) {context = ctx;}
    public void unsetEntityContext() {context = null;}

    public void ejbLoad(long ID) {
        Statement s = this.conn.createStatement();
        String req = "SELECT * FROM comptes WHERE id = " + ID;
        ResultSet res  = s.executeQuery(s);
        this.id = (long) res.getInt(0);
        this.solde = (double) res.getFloat(1);
    }
    
    public void ejbStore() {
        try {
            Statement s = this.conn.createStatement();
            String req = "UPDATE comptes SET solde = " + this.solde + " WHERE id = " + this.ID;
            s.executeUpdate(req);
            ResultSet = s.getResultSet();
            return res.toString();
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }

    public void ejbCreate(long id, double solde) throws CreateException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource cd = (DataSource) ctx.lookup("jdbc/database");
            this.conn = ds.getConnection();
            Statement s = this.conn.createStatement();
            String req = "INSERT INTO comptes VALUES (" + solde + ")";
            s.executeUpdate(req);
            ResultSet res = s.getResultSet();

            this.id = (long) res.getInt(0);
            this.solde = (double) res.getFloat(1);
        }
        catch(Exception e) {
            throw new CreateException("Erreur lors de la création du bean");
        }
    }

    public void ejbRemove(long ID) throws Exception {
        Statement s = this.conn.createStatement();
        String req = "DELETE FROM comptes WHERE id = " + ID;
        s.executeUpdate(req);
    }
}