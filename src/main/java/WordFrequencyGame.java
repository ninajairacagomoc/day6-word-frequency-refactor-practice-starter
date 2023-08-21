import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEWLINE_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";


    public String getResult(String inputStr) {
        if (inputStr.split(SPACE_DELIMITER).length == 1) {
            return inputStr + " 1";
        } else {
                List<WordFrequencyInfo> wordFrequencyInfoList = getWordFrequencyInfosList(inputStr);
                return generatePrintLines(wordFrequencyInfoList);
        }
    }

    private List<WordFrequencyInfo> getWordFrequencyInfosList(String inputStr) {
        String[] words = inputStr.split(SPACE_DELIMITER);
        List<WordFrequencyInfo> wordFrequencyInfoList = Arrays.stream(words)
                .map(word -> new WordFrequencyInfo(word, 1))
                .collect(Collectors.toList());

        return wordFrequencyInfoList.stream()
                .collect(Collectors.groupingBy(WordFrequencyInfo::getWord))
                .entrySet().stream()
                .map(entry -> new WordFrequencyInfo(entry.getKey(), entry.getValue().size())).sorted((firstWord,
                  secondWord) -> secondWord.getWordCount() - firstWord.getWordCount()).collect(Collectors.toList());
    }

    private static String generatePrintLines(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .map(wordFrequencyInfo -> wordFrequencyInfo.getWord() + SPACE_CHAR + wordFrequencyInfo.getWordCount())
                .collect(Collectors.joining(NEWLINE_DELIMITER));
    }
}
