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
    public String addEmployeeToStore(Employee e, int storeId, int sectionId) throws ApplicationException {
        try {
            // basicamente tenho que encontrar uma loja por id
            // adicionar o empregado la
            // e actualizar no empregado a sua loja e id

            Store store = StoreMapper.getStoreById(storeId);

            if(store.getEmployeeList().contains(e)) {
                return "employee already in this store";
            }
            else {
                store.addEmployeeToStore(e);
                EmployeeMapper.update(e.getId(), storeId, sectionId);
            }

            System.out.println(store);

        } catch (PersistenceException e1) {
            throw new ApplicationException("Unable to get Store", e1);
        }


        return null;
    }

}
