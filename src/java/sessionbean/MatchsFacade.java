/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Matchs;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BRILOF
 */
@Stateless
public class MatchsFacade extends AbstractFacade<Matchs> {

    @PersistenceContext(unitName = "LOLPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MatchsFacade() {
        super(Matchs.class);
    }
    
}
