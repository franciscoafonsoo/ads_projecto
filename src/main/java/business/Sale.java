package business;

import java.util.*;

public class Sale {

	private int id;
	private Date date;
	private String status;		
	private List<SaleProduct> saleProducts;
	
	public static final String OPEN   = "O";
	public static final String CLOSED = "C";

	/**
	 * Creates a new sale given the date it occurred and the customer that
	 * made the purchase.
	 * 
	 * @param date The date that the sale occurred
	 */
	public Sale(int id, Date date) {
		this.id           = id;
		this.date         = date;
		this.status       = OPEN;
		this.saleProducts = new LinkedList<SaleProduct>();
	}

	/**
	 * @return the date when the sale was created
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return the sale's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the sale's product list
	 */
	public List<SaleProduct> getSaleProducts() {
		return saleProducts;
	}

	/**
	 * @return Whether the sale is open
	 */
	public boolean isOpen() {
		return status.equals(OPEN);
	}

	/**
	 * Closes the sale
	 */
	public void close() {
		 status = CLOSED;
	}

	/**
	 * Opens the sale
	 */
	public void open() {
		 status = OPEN;
	}

	/**
	 * @return the sale's status, open or closed
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return The sale's total 
	 */
	public double total() {
		double total = 0.0;
		for (SaleProduct sp : saleProducts)
			total += sp.getSubTotal();
		return total;
	}

	/**
	 * Adds a product to the sale
	 * 
	 * @requires qty >= 0 (zero is useful for database tests)
	 * @param product The product to sale
	 * @param qty The amount of the product being sold
	 * @throws ApplicationException 
	 */
	public void addProductToSale(ProductSpec product, double qty) {
		saleProducts.add(new SaleProduct(product, qty));
	}

	/**
	 * @return a String that represents a sale and the products
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Sale @ " + date.toString() + "; " + (isOpen()?"open":"closed") + "; total of �" + total() + " with products:");
		for (SaleProduct sp : saleProducts)
			sb.append(" [code " + sp.getProduct().getProductCode() + ", " + sp.getQty() + " units]");
		return sb.toString();
	}
}
