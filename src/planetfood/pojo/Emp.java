/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.pojo;

/**
 *
 * @author hp
 */
public class Emp {

    @Override
    public String toString() {
        return "Emp{" + "empid=" + empid + ", ename=" + ename + ", Jobtype=" + Jobtype + ", sal=" + sal + '}';
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJobtype() {
        return Jobtype;
    }

    public void setJobtype(String Jobtype) {
        this.Jobtype = Jobtype;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

      private String empid;
    private String ename;
    private String Jobtype;
    private double sal;

   
}
