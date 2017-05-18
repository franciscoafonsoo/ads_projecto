package client;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import use_cases.*;

import business.*;

/**
 * A simple application client that uses both services.
 *	
 * @author fmartins
 * @version 1.2 (11/02/2015)
 * 
 */
public class SimpleClient {

	/**
	 * A simple interaction with the application services
	 * 
	 * @param args Command line parameters
	 */
	public static void main(String[] args) {
		
		SaleSys app = new SaleSys();
		
		try {
			app.start();
		} catch (ApplicationException e) {
			System.out.println(e.getMessage());
			System.out.println("Application Message: " + e.getMessage());
			SQLException e1 = (SQLException) e.getCause().getCause();
			System.out.println("SQLException: " + e1.getMessage());
			System.out.println("SQLState: " + e1.getSQLState());
			System.out.println("VendorError: " + e1.getErrorCode());
			return;
		}
		
		// create catalog(s)
		CatalogSale saleCatalog 		= new CatalogSale();
		CatalogEmployee employeeCatalog = new CatalogEmployee();
		CatalogStore storeCatalog 		= new CatalogStore();
		CatalogTransfers transfersCatalog = new CatalogTransfers();

		// this client deals with the Process Sale use case
		HandlerProcessSale hps	= new HandlerProcessSale(saleCatalog);
		
		// and with the filter sales use case
		HandlerFilterSales hfs 	= new HandlerFilterSales(saleCatalog);

		// this client deals with the Insert Employee use case
		HandleInsertEmployee hie 	= new HandleInsertEmployee(employeeCatalog, storeCatalog);

		// this client deals with the Request Transfer
		HandleRequestTransfer hrf 	= new HandleRequestTransfer(employeeCatalog, transfersCatalog);

		// this client deals with the Process Transfer
		HandleProcessTransfer hpt 	= new HandleProcessTransfer(employeeCatalog, storeCatalog, transfersCatalog);
			
		try {

			System.out.println("\n-- Add employee and print it ------------------------");

			/////////////////////////
			// Primeiro caso de Uso /
			/////////////////////////

			Employee employee = hie.newEmployee("Empr Um", "password", "01/01/2009", 919122432, 545321456);

			// a second employee for testing purpose
			Employee employee2 = hie.newEmployee("Empr Dois", "password", "01/02/2009", 919122432, 545321456);

			Store store = hie.addEmployeeToStore(employee, 1, 2);
			Store store2 = hie.addEmployeeToStore(employee2, 2, 1);

			// visto que está a ser actualizado, é necessário obter o employee outra vez.
			// e não vai precisar de query, visto que está em cache.

			employee = hie.getEmployee(employee.getId());
			employee2 = hie.getEmployee(employee2.getId());
			System.out.println(employee2);
			System.out.println(employee);

			// para ver a loja
			// System.out.println(store);

			System.out.println("\n-- Consult vacancy ----------------------------------");

			/////////////////////////
			// Segundo caso de Uso  /
			/////////////////////////

			System.out.println("Deseja consultar vagas por loja(1) ou todas(2)?");
			Scanner s = new Scanner(System.in);
			// to control the options
			boolean check = true;
			// so we can store the value in memory locally.
			List<Vacancy> vacancies = null;

			while(check) {
				int vac = s.nextInt();
				if (vac == 2) {
					vacancies = hrf.consultAllVacancies();

					for (Vacancy v : vacancies)
						System.out.println(v);

					check = false;
				} else if (vac == 1) {
					System.out.println("Indique o id da loja (ex: 1): ");
					int storeId = s.nextInt();
					vacancies = hrf.consultVacanciesByStoreId(storeId);

					for (Vacancy v : vacancies)
						System.out.println(v);

					check = false;
				}
				else {
					System.out.println("Indique um valor válido.");
				}
			}

			System.out.println("Escolha o id da vaga: ");
			int vacancyId = s.nextInt();

			int transfer_id = hrf.requestTransfer(Vacancy.containsId(vacancies, vacancyId), employee);

			System.out.println("Transferencia pedida com o id: " + transfer_id);

			/////////////////////////
			// Terceiro caso de Uso /
			/////////////////////////

			System.out.println("\n-- Process transfers --------------------------------" + '\n');

			List<Transfer> transfers = hpt.processTransfers();

			System.out.println("\n-- FIM DOS CASOS DE USO ----------------------------" + '\n');

			// creates a new sale (returns it)
			Sale sale = hps.newSale();

			// adds two products to the database
			hps.addProductToSale(sale, 101, 10);
			hps.addProductToSale(sale, 102, 25);
			
			// close sale
			hps.closeSale(sale);
			
			//////////////////
			
			sale = saleCatalog.getSale(sale.getId());
			System.out.println(sale);		
			
			//////////////////
			
			System.out.println("\n-- Get all sales with a total higher than 4000 ------");
			
			List<Sale> list = hfs.filterSales(aSale -> aSale.total() > 4000);
			for(Sale aSale : list)
				System.out.println(aSale);			
			
			//////////////////
			
			System.out.println("\n-- Get all sales with more than 2 products ----------");

			list = hfs.filterSales(aSale -> { 
				int length = aSale.getSaleProducts().size();
				return length > 2;
			});
			for(Sale aSale : list)
				System.out.println(aSale);
			
			//////////////////
			
			System.out.println("\n-- Print all sales ----------------------------------");

			System.out.println(saleCatalog);
			
			//////////////////
			
			hps.deleteSale(sale);
			
			System.out.println("\n-- Print all sales after delete ---------------------");

			System.out.println(saleCatalog);			
			
			
			
		} catch (ApplicationException e) {
			System.out.println("Error: " + e.getMessage());
			// for debugging purposes only. Typically, in the application this information 
			// can be associated with a "details" button when the error message is displayed.
			if (e.getCause() != null) 
				System.out.println("Cause: ");
			e.printStackTrace();
		}
	
		app.stop();
	}
}
