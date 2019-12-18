package shop.discard.pricing.domain;

import java.util.Collection;

public interface CardRepository {

	Card findById(String id);

	Collection<Card> findByName(String name);

	long save(Card card);

	void update(long id, Card card);
}
