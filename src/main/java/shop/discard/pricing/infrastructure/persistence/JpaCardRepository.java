package shop.discard.pricing.infrastructure.persistence;

import org.springframework.stereotype.Repository;
import shop.discard.pricing.domain.Card;
import shop.discard.pricing.domain.CardRepository;

import java.util.Collection;

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
	public Collection<Card> findByPartOfName(String partOfName) {
		return null;
	}

	@Override
	public long save(Card card) {
		System.out.println(card);
		return 0;
	}

	@Override
	public void update(long id, Card card) {

	}
}
