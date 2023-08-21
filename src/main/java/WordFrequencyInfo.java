public class WordFrequencyInfo {
    private final String word;
    private final int count;

    public WordFrequencyInfo(String word, int wordCount) {
        this.word = word;
        this.count = wordCount;
    }

    public String getWord() {
        return this.word;
    }

    public int getWordCount() {
        return this.count;
    }


}
