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
		CatalogSale saleCatalog = new CatalogSale();
		CatalogEmployee employeeCatalog = new CatalogEmployee();
		CatalogStore storeCatalog = new CatalogStore();
		CatalogTransfers transfersCatalog = new CatalogTransfers();

		// this client deals with the Insert Employee use case
		HandleInsertEmployee hie = new HandleInsertEmployee(employeeCatalog, storeCatalog);

		// this client deals with the Request Transfer
		HandleRequestTransfer hrf = new HandleRequestTransfer(employeeCatalog, transfersCatalog);

		// this client deals with the Process Transfer
		HandleProcessTransfer hpt = new HandleProcessTransfer(employeeCatalog, storeCatalog, transfersCatalog);
		
		// this client deals with the Process Sale use case
		HandlerProcessSale hps = new HandlerProcessSale(saleCatalog); 
		
		// and with the filter sales use case
		HandlerFilterSales hfs = new HandlerFilterSales(saleCatalog);
			
		try { // sample interaction		

			System.out.println("\n-- Add employee and print it ------------------------");

			Employee employee = hie.newEmployee("Empr Um", "password", "01/01/2009", 919122432, 545321456);
			// Employee employee = hie.newEmployee("Empr Dois", "password", "01/02/2009", 919122432, 545321457);

			hie.addEmployeeToStore(employee, 1, 1);

			System.out.println(employee);

			System.out.println("\n-- Consult vacancy ----------------------------------");

			System.out.println("Deseja consultar vagas por loja(1) ou todas(2)?");

			Scanner s = new Scanner(System.in);
			int check = s.nextInt();

			if(check == 1) {
				hrf.consultAllVacancies();
			}
			else if(check == 2) {
				System.out.println("Indique o id da loja (ex: 1): ");
				int storeId = s.nextInt();
				hrf.consultVacanciesByStoreId(storeId);
			}

			System.out.println("\n-- Add sale and print it ----------------------------");
			
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
