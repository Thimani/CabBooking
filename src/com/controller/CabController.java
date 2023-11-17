
package com.controller;

import com.dao.CabDAO;
import com.model.Cab;
import com.model.CabModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CabController {

    private CabModel cabModel;
    private CabDAO cabDAO;

    public CabController() {
        try {
            this.cabModel = new CabModel();
            try {
                this.cabDAO = new CabDAO();
            } catch (SQLException ex) {
                Logger.getLogger(CabController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CabController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getCabData() throws SQLException {
        return cabModel.getCabData(); 
    }
    
     public Cab getCabById(String cabId) throws SQLException {
        return cabModel.getCabById(cabId); 
    }
     
   public List<String> getAvailableCabIds() throws SQLException {
   
    return cabDAO.getAvailableCabIdsAsString();
}


   public void deleteCab(String cabId) throws SQLException {
        cabModel.deleteCab(cabId); 
    }
   
   public void updateCab(Cab cab) throws SQLException {
        cabModel.updateCab(cab); 
    }
}
