package shop.discard.pricing.infrastructure.persistence;

import org.springframework.stereotype.Repository;
import shop.discard.pricing.domain.Card;
import shop.discard.pricing.domain.CardName;
import shop.discard.pricing.domain.CardRepository;

import java.util.Collection;
import java.util.Collections;

@Repository
public class JpaCardRepository implements CardRepository {

	@Override
	public Card findById(long id) {
		return null;
	}

	@Override
	public Card findByName(String name) {
		return null;
	}

	@Override
	public long save(Card card) {
		return 0;
	}

	@Override
	public void update(long id, Card card) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CardName> getUniqueCardNames() {
		return Collections.emptyList();
	}
}
