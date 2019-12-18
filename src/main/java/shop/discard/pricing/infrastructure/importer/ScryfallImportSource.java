package shop.discard.pricing.infrastructure.importer;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import shop.discard.pricing.service.CardImportException;
import shop.discard.pricing.service.CardImportSource;

import java.io.IOException;
import java.io.InputStream;

@Component
public class ScryfallImportSource implements CardImportSource<InputStream> {

	@Value("${scryfall.all.json.url}")
	private String jsonBulkDataUrl;
	@Value("${scryfall.host}")
	private String hostUrl;

	@Override
	public InputStream getSource() throws CardImportException {
		try {
			HttpClient httpClient = HttpClients.createDefault();
			HttpHost host = HttpHost.create(hostUrl);
			HttpRequest request = new HttpGet(jsonBulkDataUrl);

			return getJson(httpClient.execute(host, request));
		} catch (IOException e) {
			throw new CardImportException("Execute request for " + hostUrl + " failed", e);
		}
	}

	private InputStream getJson(HttpResponse response) throws CardImportException {
		try {
			return response.getEntity().getContent();
		} catch (IOException e) {
			throw new CardImportException("Can`t get response as input stream" + hostUrl, e);
		}
	}

}
