package business;


import dataaccess.EmployeeMapper;
import dataaccess.PersistenceException;
import dataaccess.TransferMapper;
import dataaccess.VacancyMapper;

import java.util.*;

public class CatalogTransfers {

    public static List<Vacancy> consultAllVacancies() throws ApplicationException {
        try {
            return VacancyMapper.getAllVacancies();
        } catch (PersistenceException e1) {
            throw new ApplicationException("Unable to get Vacancies", e1);
        }
    }

    public static List<Vacancy> consultVacanciesByStore(int storeId) throws ApplicationException {
        try {
            return VacancyMapper.getAllVacanciesByStoreId(storeId);
        } catch (PersistenceException e1) {
            throw new ApplicationException("Unable to get Vacancies", e1);
        }
    }

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
     * @return Transferencias processadas automaticamente.
     * @throws ApplicationException Se algo falhar
     */

    public static List<Transfer> confirmTransfers() throws ApplicationException {
        try {
            List<Transfer> printTransfers = new LinkedList<>();
            List<Vacancy> vacancies = VacancyMapper.getAllVacancies();

            for(Vacancy vacancy: vacancies) {
                List<Transfer> transfers = TransferMapper.getTransfersByVacancyId(vacancy.getId());

                // caso vagas sejam menores ou iguais a transferencias, é só processar

                if (vacancy.getFree() >= transfers.size()) {

                    VacancyMapper.update(vacancy.getId(), (vacancy.getFree() - transfers.size()), transfers.size());

                    for (Transfer t : transfers) {
                        TransferMapper.update(t.getId(), true);
                        EmployeeMapper.update(t.getEmployee_id(), vacancy.getStore_id(), vacancy.getSection_id());
                        printTransfers.add(t);
                    }
                } else {
                    // A MAIOR JARDA DE JAVA ALGUMA VEZ VISTA

                    // lambda para organizar as transferencias por score e por data.
                    // transfers.sort((o1, o2) -> Double.compare(o1.getScore(), o2.getScore()));

                    for(Transfer t: transfers) {
                        Employee e = EmployeeMapper.getEmployeeById(t.getEmployee_id());
                        if(e.getSection() == vacancy.getSection_id()) {
                            t.setScore(t.getScore() * 1.5);
                        }
                    }

                    transfers.sort((Transfer o1, Transfer o2) -> {
                        int cmt = Double.compare(o1.getScore(), o2.getScore());
                        if (cmt == 0)
                            cmt = o1.getEntry_date().compareTo(o2.getEntry_date());
                        return cmt;
                    });

                    transfers.subList(vacancy.getFree(), transfers.size()).clear();

                    VacancyMapper.update(vacancy.getId(), (vacancy.getFree() - transfers.size()), transfers.size());

                    for (Transfer t : transfers) {
                        TransferMapper.update(t.getId(), true);
                        printTransfers.add(t);
                    }
                }
            }

            // retorna as transferencias processadas.
            return printTransfers;

        } catch (PersistenceException e) {
            throw new ApplicationException("Unable to process Transfers", e);
        }
    }

    /**
     * ao receber uma lista de transferencias
     * vai determinar se existem mais candidatos para o numero de vagas
     * se sim, vai comparar as pontuações e excluir os empregados
     * com a pontuação mais baixa.
     * returns a nova lista de trasnferencias.
     */
    public static boolean checkForConflits(List<Transfer> transfers) {
        return false;
    }


    public static void processTransfers() {
    }

}