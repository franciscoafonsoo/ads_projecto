package business;


import dataaccess.EmployeeMapper;
import dataaccess.PersistenceException;
import dataaccess.TransferMapper;
import dataaccess.VacancyMapper;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.*;

public class CatalogTransfers {

    /**
     * Inserts a new request for transfer
     *
     * @param vacancy a Vacancy object representing the vacancy to which the employee is requesting transfer
     * @param employee an Employee object representing the employee requesting transfer
     * @return in Integer representing the request id
     * @throws ApplicationException if the vacancy is null, or if the insert of the request failed
     */
    public static int requestTransfer(Vacancy vacancy, Employee employee) throws ApplicationException{
        try {
            if (vacancy != null)
                return TransferMapper.insert(vacancy.getId(), employee.getId(), employee.getScore());
            else
                throw new ApplicationException("Choose a valid vacancy");
        } catch (PersistenceException e1) {
            throw new ApplicationException("Unable to request Transfer", e1);
        }
    }

    /**
     * Pego numa vaga, vou buscar as transferencias das vagas,
     *
     * Se o numero de vagas livres for igual ao inferior ao numero de transferencias,
     * é só fazer update das tabelas, e retornar.
     *
     * Se não, vou calcular os scores, e fazer update conforme.
     *
     * @param employeeCatalog an EmployeeCatalog
     * @param vacanciesCatalog a VacanciesCatalog
     * @return Transferencias processadas automaticamente.
     * @throws ApplicationException Se algo falhar
     */

    public static List<Transfer> processTransfers(CatalogEmployee employeeCatalog, CatalogVacancies vacanciesCatalog) throws ApplicationException {
        try {
            List<Transfer> printTransfers = new ArrayList<>();
            List<Vacancy> vacancies = vacanciesCatalog.getAllVacancies();

            for(Vacancy vacancy: vacancies) {
                List<Transfer> transfers = TransferMapper.getTransfersByVacancyId(vacancy.getId());

                // caso vagas sejam menores ou iguais a transferencias, é só processar
                if(!transfers.isEmpty()) {

                    if (vacancy.getFree() >= transfers.size()) {

                        vacanciesCatalog.updateVacancies(vacancy.getId(), (vacancy.getFree() - transfers.size()), transfers.size());

                        for (Transfer t : transfers) {
                            TransferMapper.update(t.getId(), true);
                            // chamar catalogo
                            employeeCatalog.changeEmployee(t.getEmployee_id(), vacancy.getStore_id(), vacancy.getSection_id());
                            printTransfers.add(t);
                        }
                    } else {
                        for (Transfer t : transfers) {
                            // chamar catalog
                            Employee e = employeeCatalog.getEmployee(t.getEmployee_id());
                            if (e.getSection() == vacancy.getSection_id()) {
                                t.setScore(t.getScore() * 1.5);
                            }
                        }

                        // A MAIOR JARDA DE JAVA ALGUMA VEZ VISTA

                        // lambda para organizar as transferencias por score e por data.
                        // transfers.sort(Comparator.comparingDouble(Transfer::getScore));
                        transfers.sort((Transfer o1, Transfer o2) -> {
                            int cmt = Double.compare(o1.getScore(), o2.getScore());
                            if (cmt == 0)
                                cmt = o1.getEntry_date().compareTo(o2.getEntry_date());
                            return cmt;
                        });


                        transfers.subList(vacancy.getFree(), transfers.size()).clear();
                        vacanciesCatalog.updateVacancies(vacancy.getId(), (vacancy.getFree() - transfers.size()), transfers.size());

                        for (Transfer t : transfers) {
                            TransferMapper.update(t.getId(), true);
                            printTransfers.add(t);
                        }
                    }
                }
            }

            // retorna as transferencias processadas.
            return printTransfers;

        } catch (PersistenceException e) {
            throw new ApplicationException("Unable to process Transfers", e);
        }
    }
}
