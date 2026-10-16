/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Saison;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BRILOF
 */
@Stateless
public class SaisonFacade extends AbstractFacade<Saison> {

    @PersistenceContext(unitName = "LOLPU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public SaisonFacade() {
        super(Saison.class);
    }
    
}
