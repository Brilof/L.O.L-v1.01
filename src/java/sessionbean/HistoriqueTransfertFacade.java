/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.HistoriqueTransfert;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BRILOF
 */
@Stateless
public class HistoriqueTransfertFacade extends AbstractFacade<HistoriqueTransfert> {

    @PersistenceContext(unitName = "LOLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoriqueTransfertFacade() {
        super(HistoriqueTransfert.class);
    }
    
}
