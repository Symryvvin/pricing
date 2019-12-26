package shop.discard.pricing.domain;

import java.util.Collection;

public interface CardRepository {

	Card findById(long id);

	Card findByName(String name);

	long save(Card card);

	void update(long id, Card card);

	Collection<CardName> getUniqueCardNames();
}
