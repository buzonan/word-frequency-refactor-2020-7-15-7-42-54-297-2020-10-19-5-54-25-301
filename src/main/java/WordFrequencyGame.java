import java.util.*;

public class WordFrequencyGame {

    public static final String WORDS = "\\s+";
    public static final String NEW_LINE = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence){

        if (isSentenceContainsOneWord(sentence)) {
            return sentence + " 1";
        }
        try {
            List<WordInfo> wordInfoList = calculateWordFrequency(sentence);
            return assembleWordInfoList(wordInfoList);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }

    }

    private boolean isSentenceContainsOneWord(String sentence) {
        return sentence.split(WORDS).length==1;
    }

    private String assembleWordInfoList(List<WordInfo> wordInfoList) {
        wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
        StringJoiner joiner = new StringJoiner(NEW_LINE);
        for (WordInfo wordInfo : wordInfoList) {
            String wordInfoDetail = String.format("%s %d", wordInfo.getWord(), wordInfo.getWordCount());
            joiner.add(wordInfoDetail);
        }
        return joiner.toString();
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        String[] words = sentence.split(WORDS);

        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : words) {
            WordInfo wordInfo = new WordInfo(word, 1);
            wordInfoList.add(wordInfo);
        }

        Map<String, List<WordInfo>> wordInfoMap =getListMap(wordInfoList);

        List<WordInfo> distinctWordInfos = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> wordInfoDetail : wordInfoMap.entrySet()){
            WordInfo wordInfo = new WordInfo(wordInfoDetail.getKey(), wordInfoDetail.getValue().size());
            distinctWordInfos.add(wordInfo);
        }
        wordInfoList = distinctWordInfos;
        return wordInfoList;
    }


    private Map<String,List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> wordInfoMap = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList){
            if (!wordInfoMap.containsKey(wordInfo.getWord())){
                ArrayList wordInfos = new ArrayList<>();
                wordInfos.add(wordInfo);
                wordInfoMap.put(wordInfo.getWord(), wordInfos);
            }

            else {
                wordInfoMap.get(wordInfo.getWord()).add(wordInfo);
            }
        }


        return wordInfoMap;
    }


}
