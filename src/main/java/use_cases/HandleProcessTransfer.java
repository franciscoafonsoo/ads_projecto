package use_cases;

import business.*;

import java.util.List;


public class HandleProcessTransfer {
    private CatalogEmployee     employeeCatalog;
    private CatalogStore        storeCatalog;
    private CatalogTransfers    transfersCatalog;

    public HandleProcessTransfer(CatalogEmployee employeeCatalog, CatalogStore storeCatalog, CatalogTransfers transfersCatalog) {
        this.employeeCatalog    = employeeCatalog;
        this.storeCatalog       = storeCatalog;
        this.transfersCatalog   = transfersCatalog;
    }

    public List<Transfer> processTransfers() throws ApplicationException {
        return CatalogTransfers.processTransfers();
    }

}
