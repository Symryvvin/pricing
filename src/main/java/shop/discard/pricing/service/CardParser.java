package shop.discard.pricing.service;

import shop.discard.pricing.domain.Card;

import java.util.Collection;

public interface CardParser<T> {

	Collection<Card> parse(T data) throws CardImportException;

}
