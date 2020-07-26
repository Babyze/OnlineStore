/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhdh.tblproduct;

import java.io.Serializable;

/**
 *
 * @author babyz
 */
public class TblProductDTO implements Serializable {

    private String pid;
    private String pname;

    public TblProductDTO() {
    }

    public TblProductDTO(String pid, String pname) {
        this.pid = pid;
        this.pname = pname;
    }

    /**
     * @return the pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @return the pname
     */
    public String getPname() {
        return pname;
    }

    /**
     * @param pname the pname to set
     */
    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public int hashCode() {
        return pid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        TblProductDTO dto = (TblProductDTO) obj;
        return pid.equals(dto.pid);
    }

}
