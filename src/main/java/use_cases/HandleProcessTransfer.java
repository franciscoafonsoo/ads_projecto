package use_cases;

import business.CatalogEmployee;
import business.CatalogStore;
import business.CatalogTransfers;
import business.Transfer;

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


    public List<Transfer> confirmTransfers() {
        CatalogTransfers.confirmTransfers();
        return new ArrayList<Transfer>();
    }

    public boolean checkForConflits(List<Transfer> t) {
        return false;
    }

    public void processTransfers(List<Transfer> transfers) {

    }
}
