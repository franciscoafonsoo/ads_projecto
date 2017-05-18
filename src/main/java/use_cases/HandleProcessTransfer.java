package use_cases;

import business.*;

import java.util.List;


public class HandleProcessTransfer {
    private CatalogEmployee     employeeCatalog;
    private CatalogTransfers    transfersCatalog;
    private CatalogVacancies    vacanciesCatalog;

    public HandleProcessTransfer(CatalogEmployee employeeCatalog, CatalogTransfers transfersCatalog, CatalogVacancies vacanciesCatalog) {
        this.employeeCatalog    = employeeCatalog;
        this.transfersCatalog   = transfersCatalog;
        this.vacanciesCatalog   = vacanciesCatalog;
    }

    public List<Transfer> processTransfers() throws ApplicationException {
        return CatalogTransfers.processTransfers(employeeCatalog, vacanciesCatalog);
    }

}
