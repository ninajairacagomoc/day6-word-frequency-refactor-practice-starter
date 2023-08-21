import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEWLINE_DELIMETER = "\n";
    public static final String SPACE_CHAR = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputStr) {
        if (inputStr.split(SPACE_DELIMITER).length == 1) {
            return inputStr + " 1";
        } else {
            try {
                List<WordFrequencyInfo> wordFrequencyInfoList = getWordFrequencyInfosList(inputStr);
                return generatePrintLines(wordFrequencyInfoList);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }
    private List<WordFrequencyInfo> getWordFrequencyInfosList(String inputStr) {
        String[] words = inputStr.split(SPACE_DELIMITER);

        List<WordFrequencyInfo> wordFrequencyInfoList = new ArrayList<>();
        for (String word : words) {
            WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(word, 1);
            wordFrequencyInfoList.add(wordFrequencyInfo);
        }
        Map<String, List<WordFrequencyInfo>> wordFrequencyMap = getListMap(wordFrequencyInfoList);
        List<WordFrequencyInfo> frequencyInfos = new ArrayList<>();
        for (Map.Entry<String, List<WordFrequencyInfo>> entry : wordFrequencyMap.entrySet()) {
            WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(entry.getKey(), entry.getValue().size());
            frequencyInfos.add(wordFrequencyInfo);
        }
        wordFrequencyInfoList = frequencyInfos;
        wordFrequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
        return wordFrequencyInfoList;
    }

    private static String generatePrintLines(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .map(wordFrequencyInfo -> wordFrequencyInfo.getWord() + SPACE_CHAR + wordFrequencyInfo.getWordCount())
                .collect(Collectors.joining(NEWLINE_DELIMETER));
    }

    private Map<String, List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        Map<String, List<WordFrequencyInfo>> wordFrequencyMap = new HashMap<>();
        for (WordFrequencyInfo wordFrequencyInfo : wordFrequencyInfoList) {
            if (!wordFrequencyMap.containsKey(wordFrequencyInfo.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequencyInfo);
                wordFrequencyMap.put(wordFrequencyInfo.getWord(), arr);
            } else {
                wordFrequencyMap.get(wordFrequencyInfo.getWord()).add(wordFrequencyInfo);
            }
        }

        return wordFrequencyMap;
    }


}
