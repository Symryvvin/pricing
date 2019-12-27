package shop.discard.pricing.infrastructure.importer;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import shop.discard.pricing.domain.Card;
import shop.discard.pricing.domain.lang.NotSupportedLanguageException;
import shop.discard.pricing.service.CardImportException;
import shop.discard.pricing.service.CardParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JsonBulkDataParser implements CardParser<InputStream> {

	private static final Logger logger = LoggerFactory.getLogger(JsonBulkDataParser.class);

	@Override
	public Collection<Card> parse(InputStream stream) throws CardImportException {
		return convertToCard(readFromJson(stream));
	}

	private Collection<JsonCardData> readFromJson(InputStream jsonStream) throws CardImportException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			SimpleModule module = new SimpleModule();
			module.addDeserializer(JsonCardData.class, new JsonCardDeserializer());
			mapper.registerModule(module);

			JavaType type = mapper.getTypeFactory().
					constructCollectionType(Collection.class, JsonCardData.class);
			return mapper.readValue(jsonStream, type);
		} catch (IOException e) {
			throw new CardImportException("Error during parse data stream", e);
		}
	}

	private Collection<Card> convertToCard(Collection<JsonCardData> jsonCards) {
		return jsonCards.stream()
				.map(this::convertToCard)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
	}

	private Optional<Card> convertToCard(JsonCardData data) {
		try {
			return Optional.of(
					Card.from(
							data.getId(),
							data.getPrintedName(),
							data.getOracleName(),
							data.getPrintCode(),
							data.getLang(),
							data.getReleaseDate()
					)
			);
		} catch (NotSupportedLanguageException e) {
			logger.warn("Card with id {} has unsupported language code [{}]", data.getId(), data.getLang());
			return Optional.empty();
		}
	}

}
