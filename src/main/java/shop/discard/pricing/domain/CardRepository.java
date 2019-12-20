package shop.discard.pricing.domain;

import java.util.Collection;

public interface CardRepository {

	Card findById(long id);

	Card findByName(String name);

	Collection<Card> findByPartOfName(String partOfName, String langCode);

	long save(Card card);

	void update(long id, Card card);
}
