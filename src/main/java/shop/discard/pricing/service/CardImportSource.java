package shop.discard.pricing.service;

public interface CardImportSource<T> {

	T getSource() throws CardImportException;

}
