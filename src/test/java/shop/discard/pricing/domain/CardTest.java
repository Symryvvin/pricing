package shop.discard.pricing.domain;

import org.junit.jupiter.api.Test;
import shop.discard.pricing.domain.lang.Language;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {

	@Test
	public void creation() throws Exception {
		Card card = Card.from(
				"00000000-0000-0000-0000-00000000000",
				"test",
				"test",
				"en",
				"2020-01-01"
		);

		assertEquals("test", card.getName());
		assertEquals("test", card.getPrintCode());
		assertEquals(UUID.fromString("00000000-0000-0000-0000-00000000000"), card.getGuid());
		assertEquals(LocalDate.of(2020, 1, 1), card.getReleaseDate());
		assertEquals(Language.EN, card.getLanguage());
	}

	@Test
	public void compareByName() throws Exception {
		Card card1 = Card.from(
				"00000000-0000-0000-0000-00000000000",
				"test1",
				"test",
				"en",
				"2020-01-01"
		);

		Card card2 = Card.from(
				"00000000-0000-0000-0000-00000000000",
				"test2",
				"test",
				"en",
				"2020-01-01"
		);

		assertEquals(-1, card1.compareTo(card2));
	}


}