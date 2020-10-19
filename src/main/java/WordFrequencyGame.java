import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.frequency;

public class WordFrequencyGame {

    private static final String WHITE_SPACE = "\\s+";
    private static final String NEW_LINE = "\n";

    public String getResult(String sentence){
        List<WordInfo> wordInfoList = calculateWordFrequency(sentence);

        sortWordInfos(wordInfoList);

        return assembleWordInfoList(wordInfoList);
    }

    private void sortWordInfos(List<WordInfo> wordInfoList) {
        wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
    }

    private String assembleWordInfoList(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner(NEW_LINE);

        for (WordInfo wordInfo : wordInfoList) {
            String wordInfoDetail = String.format("%s %d", wordInfo.getWord(), wordInfo.getWordCount());
            joiner.add(wordInfoDetail);
        }

        return joiner.toString();
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        String[] words = sentence.split(WHITE_SPACE);

        List<String> wordsList = Arrays.asList(words);
        Set<String> wordsSet = new HashSet<>(wordsList);

        return wordsSet.stream()
                .map(word -> new WordInfo(word, frequency(wordsList, word)))
                .collect(Collectors.toList());
    }
}
