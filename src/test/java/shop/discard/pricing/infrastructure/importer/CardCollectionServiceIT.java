package shop.discard.pricing.infrastructure.importer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.discard.pricing.domain.CardRepository;
import shop.discard.pricing.service.CardCollectionService;
import shop.discard.pricing.service.CardImportException;
import shop.discard.pricing.service.CardImportSource;
import shop.discard.pricing.service.CardParser;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@TestPropertySource(
		locations = "classpath:test-application.properties")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class CardCollectionServiceIT {

	@Value("${json.local.path}")
	private String jsonPath;

	@MockBean
	private CardRepository repository;
	@Autowired
	private CardParser<InputStream> parser;

	@Test
	public void importCards() throws CardImportException {
		CardCollectionService service = new CardCollectionService(getSource(), parser, repository);
		service.importCards();
	}

	private CardImportSource<InputStream> getSource() {
		return () -> {
			try {
				return Files.newInputStream(Paths.get(jsonPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		};
	}
}