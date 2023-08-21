import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

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

                //split the input string with 1 to n pieces of spaces
                String[] words = inputStr.split(SPACE_DELIMITER);

                List<WordFrequencyInfo> wordFrequencyInfoList = new ArrayList<>();
                for (String word : words) {
                    WordFrequencyInfo input = new WordFrequencyInfo(word, 1);
                    wordFrequencyInfoList.add(input);
                }

                //get the wordFrequencyMap for the next step of sizing the same word
                Map<String, List<WordFrequencyInfo>> wordFrequencyMap = getListMap(wordFrequencyInfoList);

                List<WordFrequencyInfo> frequencyInfoss = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequencyInfo>> entry : wordFrequencyMap.entrySet()) {
                    WordFrequencyInfo input = new WordFrequencyInfo(entry.getKey(), entry.getValue().size());
                    frequencyInfoss.add(input);

                }
                wordFrequencyInfoList = frequencyInfoss;

                wordFrequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                return generatePrintLines(wordFrequencyInfoList);

            } catch (Exception e) {


                return CALCULATE_ERROR;
            }
        }
    }

    private static String generatePrintLines(List<WordFrequencyInfo> wordFrequencyInfoList) {
        StringJoiner joiner = new StringJoiner(NEWLINE_DELIMETER);
        for (WordFrequencyInfo w : wordFrequencyInfoList) {
            String s = w.getWord() + SPACE_CHAR + w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }


    private Map<String, List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> inputList) {
        Map<String, List<WordFrequencyInfo>> map = new HashMap<>();
        for (WordFrequencyInfo input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getWord(), arr);
            } else {
                map.get(input.getWord()).add(input);
            }
        }


        return map;
    }


}
