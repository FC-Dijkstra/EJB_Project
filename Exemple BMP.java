package entitybeansample;

import java.sql.*;

import javax.ejb.*;
import javax.naming.*;
import javax.sql.*;

public class EmployeeBMPBean implements EntityBean {
 	EntityContext entityContext;

	public java.lang.Short ejbCreate(java.lang.Short empNo) throws CreateException {
  setEmpNo(empNo);

  Connection con = null;

  try {
   InitialContext initial = new InitialContext();
   DataSource ds = (DataSource)initial.lookup("java:comp/env/jdbc/EmployeeData");
   con = ds.getConnection();
   PreparedStatement ps = con.prepareStatement("INSERT INTO employee (empno)" + "values (?)");
   ps.setShort(1,empNo.shortValue());
   ps.executeUpdate();
   return empNo;
  }
  catch (SQLException ex) {
   ex.printStackTrace();
  }catch (NamingException ex) {
   ex.printStackTrace();
   throw new CreateException();
  }finally {
   if (con!=null){
    try {
     con.close();
    }
    catch (SQLException ex) {
     ex.printStackTrace();
    }
   }
  }
  return null;
 }
 public void ejbPostCreate(java.lang.Short empNo) throws CreateException {
  /**@todo Complete this method*/
 }
 public void ejbRemove() throws RemoveException {
  Connection con = null;
  try {
   InitialContext initial = new InitialContext();
   DataSource ds = (DataSource)initial.lookup(
"java:comp/env/jdbc/EmployeeData");
   con = ds.getConnection();
   PreparedStatement ps = con.prepareStatement("DELETE " +
     "FROM EMPLOYEE WHERE empno = ?");
   ps.setShort(1,getEmpNo().shortValue());
   ps.executeUpdate();
  }
  catch (SQLException ex) {
   ex.printStackTrace();
  }catch (NamingException ex) {
   ex.printStackTrace();
   throw new RemoveException();
  }finally {
   if (con!=null){
    try {
     con.close();
    }
    catch (SQLException ex) {
     ex.printStackTrace();
    }
   }
  }
 }
//Getters and Setters for all members
 public void setEmpNo(java.lang.Short empNo) {
  this.empNo = empNo;
 }
 public void setFirstName(java.lang.String firstName) {
  this.firstName = firstName;
 }
 public void setLastName(java.lang.String lastName) {
  this.lastName = lastName;
 }
 public void setPhoneExt(java.lang.String phoneExt) {
  this.phoneExt = phoneExt;
 }
 public void setHireDate(java.sql.Timestamp hireDate) {
  this.hireDate = hireDate;
 }
 public void setDeptNo(java.lang.String deptNo) {
  this.deptNo = deptNo;
 }
 public void setJobCode(java.lang.String jobCode) {
  this.jobCode = jobCode;
 }
 public void setJobGrade(java.lang.Short jobGrade) {
  this.jobGrade = jobGrade;
 }
 public void setJobCountry(java.lang.String jobCountry) {
  this.jobCountry = jobCountry;
 }
 public void setSalary(java.math.BigDecimal salary) {
  this.salary = salary;
 }
 public void setFullName(java.lang.String fullName) {
  this.fullName = fullName;
 }
 public java.lang.Short getEmpNo() {
  return empNo;
 }
 public java.lang.String getFirstName() {
  return firstName;
 }
 public java.lang.String getLastName() {
  return lastName;
 }
 public java.lang.String getPhoneExt() {
  return phoneExt;
 }
 public java.sql.Timestamp getHireDate() {
  return hireDate;
 }
 public java.lang.String getDeptNo() {
  return deptNo;
 }
 public java.lang.String getJobCode() {
  return jobCode;
 }
 public java.lang.Short getJobGrade() {
  return jobGrade;
 }
 public java.lang.String getJobCountry() {
  return jobCountry;
 }
 public java.math.BigDecimal getSalary() {
  return salary;
 }
 public java.lang.String getFullName() {
  return fullName;
 }
//Find an individual instance and return the primary key
 public java.lang.Short ejbFindByPrimaryKey(java.lang.Short empNo)
 throws FinderException {
  Connection con = null;
  try {
   InitialContext initial = new InitialContext();
   DataSource ds = (DataSource)initial.lookup(
"java:comp/env/jdbc/EmployeeData");
   con = ds.getConnection();
   PreparedStatement ps = con.prepareStatement("SELECT id FROM EMPLOYEE" +
     "WHERE empno = ?");
   ps.setShort(1,empNo.shortValue());
   ResultSet rs = ps.executeQuery();

   if (!rs.next()){
    throw new ObjectNotFoundException();
   }
   return empNo;
  }
  catch (SQLException ex) {
   ex.printStackTrace();
  }catch (NamingException ex) {
   ex.printStackTrace();
   throw new EJBException(ex);
  } finally {
   if (con!=null){
    try {
     con.close();
    }
    catch (SQLException ex) {
     ex.printStackTrace();
    }
   }
  }
  return null;
 }
//Load a single instance from the datasource
 public void ejbLoad() {
  Connection con = null;
  try {
   InitialContext initial = new InitialContext();
   DataSource ds = (DataSource)initial.lookup(
"java:comp/env/jdbc/EmployeeData");
   con = ds.getConnection();
   PreparedStatement ps = con.prepareStatement(
"SELECT EmpNo,DeptNo,FirstName," +
     "FullName,HireDate,JobCode,JobCountry,JobGrade,LastName,
PhoneExt,Salary " +
     "FROM EMPLOYEE WHERE empno = ?");
   ps.setShort(1,getEmpNo().shortValue());
   ResultSet rs = ps.executeQuery();
   if (!rs.next()){
    throw new EJBException("Object not found!");
   }

   setDeptNo(rs.getString(2));
   setFirstName(rs.getString(3));
   setFullName(rs.getString(4));
   setHireDate(rs.getTimestamp(5));
   setJobCode(rs.getString(6));
   setJobCountry(rs.getString(7));
   setJobGrade(new java.lang.Short(rs.getShort(8)));
   setLastName(rs.getString(9));
   setPhoneExt(rs.getString(10));
   setSalary(rs.getBigDecimal(11));

  }
  catch (SQLException ex) {
   ex.printStackTrace();
  }catch (NamingException ex) {
   ex.printStackTrace();
   throw new EJBException(ex);
  } finally {
   if (con!=null){
    try {
     con.close();
    }
    catch (SQLException ex) {
     ex.printStackTrace();
    }
   }
  }
 }
//Pasivate data to the datasource
 public void ejbStore() {
  Connection con = null;

  try {
   InitialContext initial = new InitialContext();
   DataSource ds = (DataSource)initial.lookup(
"java:comp/env/jdbc/EmployeeData");
   con = ds.getConnection();
   PreparedStatement ps = con.prepareStatement("Update employee " +
     "set DeptNo = ?, FirstName = ?, FullName = ?, HireDate = ?," +
     "JobCode = ?, JobCountry = ?, JobGrade = ?, LastName = ?," +
     "PhoneExt = ?, Salary = ? where empno = ?");
   ps.setString(1,getDeptNo());
   ps.setString(2,getFirstName());
   ps.setString(3,getFirstName());
   ps.setString(4,getFullName());
   ps.setTimestamp(5,getHireDate());
   ps.setString(6,getJobCode());
   ps.setString(7,getJobCountry());
   ps.setShort(8,getJobGrade().shortValue());
   ps.setString(9,getLastName());
   ps.setString(10,getPhoneExt());
   ps.setBigDecimal(11,getSalary());

   ps.setShort(12,empNo.shortValue());
   ps.executeUpdate();
  }
  catch (SQLException ex) {
   ex.printStackTrace();
  }catch (NamingException ex) {
   ex.printStackTrace();
   throw new EJBException();
  }finally {
   if (con!=null){
    try {
     con.close();
    }
    catch (SQLException ex) {
     ex.printStackTrace();
    }
   }
  }
 }
 public void ejbActivate() {
 }
 public void ejbPassivate() {
 }
 public void unsetEntityContext() {
  this.entityContext = null;
 }
 public void setEntityContext(EntityContext entityContext) {
  this.entityContext = entityContext;
 }
}