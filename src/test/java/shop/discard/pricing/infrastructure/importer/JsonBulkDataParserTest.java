package shop.discard.pricing.infrastructure.importer;

import org.junit.jupiter.api.Test;
import shop.discard.pricing.domain.Card;

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

			assertEquals(cards.get(0).getGuid(), UUID.fromString("e8b2168d-38e5-429f-8663-f0d258c9c441"));
			assertEquals(cards.get(0).getName(), "Setessan Champion");
			assertEquals(cards.get(0).getPrintCode(), "thb");
			assertEquals(cards.get(0).getLanguage(), "en");

			assertEquals(cards.get(1).getGuid(), UUID.fromString("02a11c22-c4c0-4024-b775-26354337f2fd"));
			assertEquals(cards.get(1).getName(), "기회주의적인 용");
			assertEquals(cards.get(1).getPrintCode(), "eld");
			assertEquals(cards.get(1).getLanguage(), "ko");
		}
	}

}