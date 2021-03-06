package shop.discard.pricing.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.discard.pricing.domain.CardName;
import shop.discard.pricing.domain.lang.Language;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class InMemoryCardNamesStoreTest {

	@Autowired
	InMemoryCardNamesStore namesStore;

	@Test
	void findByPartOfName() {
		String partOfName = "лес";

		List<String> actual = namesStore.findByPartOfName(partOfName, Language.RU).stream()
				.map(CardName::getPrintedName)
				.collect(Collectors.toList());

		List<String> expected = Arrays.asList("Лес", "Лесная Сень", "Лесной Путь", "Снежный лес", "Дух Лесовика", "Лесной Ревун",
				"Лесной Ручей", "Лесные эльфы", "Порченый Лес", "Санитар леса", "Балот-Лесовал", "Лесная Сыщица", "Лесное Чудище", "Лесной Колосс",
				"Губитель Лесов");

		assertEquals(expected, actual);
	}

	@Test
	void findByPartOfNameWithHieroglyph() {
		String partOfName = "森";

		List<String> actual = namesStore.findByPartOfName(partOfName, Language.JA).stream()
				.map(CardName::getPrintedName)
				.collect(Collectors.toList());


		List<String> expected = Arrays.asList("森", "森の力", "森の熊", "森林狼", "森の再生", "森の占術", "森の報奨", "森の宝球",
				"森の知恵", "森の賢人", "森林の声", "森での迷子", "森のこだま", "森の代言者", "森の伝書使");

		assertEquals(expected, actual);
	}
}