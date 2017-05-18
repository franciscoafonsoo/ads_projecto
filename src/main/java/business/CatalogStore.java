package business;


import dataaccess.EmployeeMapper;
import dataaccess.PersistenceException;
import dataaccess.StoreMapper;

public class CatalogStore {

    /***
     * Adds an Employee to a section in a store
     *
     * @param e The employee
     * @param storeId Id of store
     * @param sectionId Id of section
     * @throws ApplicationException if the store, or the section doesn't exist
     */
    public Store addEmployeeToStore(Employee e, int storeId, int sectionId) throws ApplicationException {
        try {
            // basicamente tenho que encontrar uma loja por id
            // adicionar o empregado la
            // e actualizar no empregado a sua loja e id

            Store store = StoreMapper.getStoreById(storeId);

            EmployeeMapper.update(e.getId(), storeId, sectionId);

            return store;

        } catch (PersistenceException e1) {
            throw new ApplicationException("Unable to get Store", e1);
        }
    }

}
