package shop.discard.pricing.infrastructure.importer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import shop.discard.pricing.service.CardImportException;
import shop.discard.pricing.service.CardImportSource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component(value = "system")
public class FileSystemImportSource implements CardImportSource<InputStream> {

	@Value("${json.local.path}")
	private String jsonPath;

	@Override
	public InputStream getSource() throws CardImportException {
		try {
			return Files.newInputStream(Paths.get(jsonPath));
		} catch (IOException e) {
			throw new CardImportException("Error get InputStream from " + jsonPath, e);
		}
	}
}
