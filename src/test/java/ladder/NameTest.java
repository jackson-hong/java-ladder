package ladder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

  @ParameterizedTest(name = "{0}으로 Name 생성 성공")
  @ValueSource(strings = {"a", "bb", "ccc", "dddd", "eeeee"})
  void Name_생성_성공(String validName) {
    assertDoesNotThrow(() -> new Name(validName));
  }

  @Test
  void equals_성공() {
    assertThat(new Name("test")).isEqualTo(new Name("test"));
  }

  @ParameterizedTest(name = "{0}으로 Name 생성 실패")
  @ValueSource(strings = {"", " ", "123456"})
  void Name_생성_실패(String invalidName) {
    assertThrows(IllegalArgumentException.class, ()-> new Name(invalidName));
  }

  @ParameterizedTest(name = "{0}으로 toString 성공")
  @ValueSource(strings = {"a", "bb", "ccc", "dddd", "eeeee"})
  void toString_성공(String rawName) {
    Name name = new Name(rawName);
    assertThat(name.toString()).isEqualTo(rawName);
  }
}