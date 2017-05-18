package use_cases;

import business.*;

import java.util.List;


public class HandleProcessTransfer {

    /**
     * attributes
     */
    private CatalogEmployee     employeeCatalog;
    private CatalogTransfers    transfersCatalog;
    private CatalogVacancies    vacanciesCatalog;

    /**
     * Creates a new handle
     *
     * @param employeeCatalog an EmployeeCatalog
     * @param transfersCatalog a TransfersCatalog
     * @param vacanciesCatalog a VacanciesCatalog
     */
    public HandleProcessTransfer(CatalogEmployee employeeCatalog, CatalogTransfers transfersCatalog, CatalogVacancies vacanciesCatalog) {
        this.employeeCatalog    = employeeCatalog;
        this.transfersCatalog   = transfersCatalog;
        this.vacanciesCatalog   = vacanciesCatalog;
    }

    /**
     * Pego numa vaga, vou buscar as transferencias das vagas,
     *
     * Se o numero de vagas livres for igual ao inferior ao numero de transferencias,
     * é só fazer update das tabelas, e retornar.
     *
     * Se não, vou calcular os scores, e fazer update conforme.
     *
     * @return Transferencias processadas automaticamente.
     * @throws ApplicationException Se algo falhar
     */
    public List<Transfer> processTransfers() throws ApplicationException {
        return CatalogTransfers.processTransfers(employeeCatalog, vacanciesCatalog);
    }

}
