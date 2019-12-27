package shop.discard.pricing.infrastructure.importer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class JsonCardDeserializer extends StdDeserializer<JsonCardData> {

	public JsonCardDeserializer() {
		this(null);
	}

	protected JsonCardDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public JsonCardData deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException {
		JsonCardData card = new JsonCardData();
		JsonNode node = jsonParser.getCodec().readTree(jsonParser);

		card.setId(node.get("id").textValue());

		JsonNode nameNode = node.get("printed_name");

		if (nameNode == null) {
			card.setPrintedName(node.get("name").textValue());
		} else {
			card.setPrintedName(node.get("printed_name").textValue());
		}
		card.setOracleName(node.get("name").textValue());
		card.setPrintCode(node.get("set").textValue());
		card.setLang(node.get("lang").textValue());
		card.setReleaseDate(node.get("released_at").textValue());

		return card;
	}
}
