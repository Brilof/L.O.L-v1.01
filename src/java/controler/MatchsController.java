package controler;

import entities.Matchs;
import controler.util.JsfUtil;
import controler.util.PaginationHelper;
import sessionbean.MatchsFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("matchsController")
@SessionScoped
public class MatchsController implements Serializable {

    private Matchs current;
    private DataModel items = null;
    @EJB
    private sessionbean.MatchsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public MatchsController() {
    }

    public Matchs getSelected() {
        if (current == null) {
            current = new Matchs();
            selectedItemIndex = -1;
        }
        return current;
    }

    private MatchsFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        try {
            if (!getAllMatchsList().isEmpty()) {
                current = (Matchs) getItems().getRowData();
                selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
            }
        } catch (Exception e) {
            // Protection
        }
        return "View";
    }


    public String prepareCreate() {
        current = new entities.Matchs();
        items = null;
        selectedItemIndex = -1;
        return "Create";
    }


    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MatchsCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        try {
            // Sécurité : évite l'appel à getItems().getRowData() si la liste est vide
            if (getAllMatchsList().isEmpty()) {
                current = new entities.Matchs();
            } else {
                current = (Matchs) getItems().getRowData();
            }
            selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        } catch (Exception e) {
            current = new entities.Matchs();
        }
        return "Edit";
    }



    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MatchsUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        try {
            if (!getAllMatchsList().isEmpty()) {
                current = (Matchs) getItems().getRowData();
                selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
                performDestroy();
                recreatePagination();
                recreateModel();
            }
        } catch (Exception e) {
            // Protection
        }
        return "List";
    }


    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MatchsDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            // Utilise notre liste saine pour initialiser le modèle sans passer par la pagination brisée
            items = new ListDataModel(getAllMatchsList());
        }
        return items;
    }


    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }
    public java.util.List<entities.Matchs> getAllMatchsList() {
        return ejbFacade.findAll();
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Matchs getMatchs(java.lang.Integer id) {
        return ejbFacade.find(id);
    }
    public String validerEtCloturerMatch() {
        try {
            // 1. Appel de la procédure via le Bean Session EJB injecté par NetBeans
            // On récupère l'EntityManager natif pour exécuter la requête native MySQL
            javax.persistence.EntityManager em = (javax.persistence.EntityManager) javax.persistence.Transient.class.cast(null); // Ou ajoutez un getter public getEntityManager() dans MatchsFacade.java

            
            // 2. Exécution sécurisée de l'appel CALL CloturerMatch(id, buts_dom, buts_ext)
            em.createNativeQuery("CALL CloturerMatch(?, ?, ?)")
              .setParameter(1, current.getIdMatch())
              .setParameter(2, current.getButsDomicile())
              .setParameter(3, current.getButsExterieur())
              .executeUpdate();
            
            // 3. Notification de succès à l'écran
            JsfUtil.addSuccessMessage("Match clôturé avec succès ! Le classement LOL a été mis à jour.");
            
            // 4. On réinitialise le modèle pour rafraîchir l'affichage sur le site web
            recreateModel();
            
            return "List"; // Redirige l'utilisateur vers la frise des matchs
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Erreur technique lors de l'appel de la procédure : " + e.getMessage());
            return null;
        }
    }

    @FacesConverter(forClass = Matchs.class)
    public static class MatchsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MatchsController controller = (MatchsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "matchsController");
            return controller.getMatchs(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Matchs) {
                Matchs o = (Matchs) object;
                return getStringKey(o.getIdMatch());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Matchs.class.getName());
            }
        }

    }

}
