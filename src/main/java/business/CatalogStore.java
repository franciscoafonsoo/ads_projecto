package business;


import dataaccess.PersistenceException;
import dataaccess.StoreMapper;

public class CatalogStore {
    public void addEmployeeToStore(Employee e, int storeId, int sectionId) throws ApplicationException {
        try {
            // basicamente tenho que encontrar uma loja por id
            // adicionar o empregado la
            // e actualizar no empregado a sua loja e id

            Store store = StoreMapper.getStoreById(storeId);

            System.out.println(store);

        } catch (PersistenceException e1) {
            throw new ApplicationException("Unable to get Store", e1);
        }


    }

}
