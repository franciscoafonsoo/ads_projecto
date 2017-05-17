package use_cases;

import business.CatalogEmployee;
import business.CatalogStore;
import business.CatalogTransfers;


public class HandleProcessTransfer {
    private CatalogEmployee     employeeCatalog;
    private CatalogStore        storeCatalog;
    private CatalogTransfers    transfersCatalog;

    public HandleProcessTransfer(CatalogEmployee employeeCatalog, CatalogStore storeCatalog, CatalogTransfers transfersCatalog) {
        this.employeeCatalog    = employeeCatalog;
        this.storeCatalog       = storeCatalog;
        this.transfersCatalog   = transfersCatalog;
    }


}
