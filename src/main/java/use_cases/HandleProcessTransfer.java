package use_cases;

import business.*;

import java.util.ArrayList;
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


    public List<Transfer> confirmTransfers() throws ApplicationException {
        return CatalogTransfers.confirmTransfers();
    }

    public boolean checkForConflits(List<Transfer> transfers) {
        return CatalogTransfers.checkForConflits(transfers);
    }

    public void processTransfers() {
        CatalogTransfers.processTransfers();
    }

    public void processTransfers(List<Transfer> transfers) {

    }
}
