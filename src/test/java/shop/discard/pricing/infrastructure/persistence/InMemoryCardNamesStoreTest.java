package shop.discard.pricing.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class InMemoryCardNamesStoreTest {

	@Autowired
	InMemoryCardNamesStore namesStore;

	@Test
	void findByPartOfName() throws Exception {
		String partOfName = "лес";
		System.out.println(namesStore.findByPartOfName(partOfName, "ru"));
		List<String> actual = new ArrayList<>(namesStore.findByPartOfName(partOfName, "ru"));

		List<String> expected = Arrays.asList("Лес", "Лесная Сень", "Лесной Путь", "Снежный лес", "Дух Лесовика", "Лесной Ревун",
				"Лесной Ручей", "Лесные эльфы", "Порченый Лес", "Санитар леса", "Лесная Сыщица", "Лесное Чудище", "Лесной Колосс",
				"Губитель Лесов", "Лесная Теснина");

		assertEquals(expected, actual);
	}

	@Test
	void findByPartOfNameWithHieroglyph() throws Exception {
		String partOfName = "森";
		System.out.println(namesStore.findByPartOfName(partOfName, "ja"));
		List<String> actual = new ArrayList<>(namesStore.findByPartOfName(partOfName, "ja"));

		List<String> expected = Arrays.asList("森", "森の力", "森の熊", "森林狼", "森の再生", "森の占術", "森の報奨", "森の宝球",
				"森の知恵", "森の賢人", "森林の声", "森での迷子", "森のこだま", "森の代言者", "森の伝書使");

		assertEquals(expected, actual);
	}
}