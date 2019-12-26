package shop.discard.pricing.infrastructure.importer;

import org.junit.jupiter.api.Test;
import shop.discard.pricing.domain.Card;
import shop.discard.pricing.domain.lang.Language;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonBulkDataParserTest {

	@Test
	public void parse() throws Exception {
		JsonBulkDataParser parser = new JsonBulkDataParser();
		try (InputStream is = getClass().getResourceAsStream("/test.json")) {
			List<Card> cards = new ArrayList<>(parser.parse(is));

			assertEquals(UUID.fromString("e8b2168d-38e5-429f-8663-f0d258c9c441"), cards.get(0).getGuid());
			assertEquals("Setessan Champion", cards.get(0).getName());
			assertEquals("thb", cards.get(0).getPrintCode());
			assertEquals(Language.EN, cards.get(0).getLanguage());

			assertEquals(UUID.fromString("02a11c22-c4c0-4024-b775-26354337f2fd"), cards.get(1).getGuid());
			assertEquals("기회주의적인 용", cards.get(1).getName());
			assertEquals("eld", cards.get(1).getPrintCode());
			assertEquals(Language.KO, cards.get(1).getLanguage());
		}
	}

}