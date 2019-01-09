package com.attivio.platform.piglatin;
 
import java.util.ArrayList;
import java.util.List;

import com.attivio.sdk.AttivioException;
import com.attivio.sdk.ingest.IngestDocument;
import com.attivio.sdk.ingest.IngestField;
import com.attivio.sdk.ingest.IngestFieldValue;
import com.attivio.sdk.server.component.configuration.HasInputListProperty;
import com.attivio.sdk.server.component.ingest.DocumentModifyingTransformer;
import com.attivio.sdk.token.Position;
import com.attivio.sdk.token.Token;
import com.attivio.sdk.token.TokenAnnotation;
import com.attivio.sdk.token.TokenList;
public class TranslateToPigLatin implements DocumentModifyingTransformer, HasInputListProperty{
    protected List<String> input = new ArrayList<String>();
     
    @Override
    public boolean processDocument(IngestDocument doc)throws AttivioException{
        for(String field : input){
            if(doc.containsField(field)){
                IngestField allValues = doc.getField(field);
                 
                for(IngestFieldValue value : allValues){
                    TokenList tokenList = value.getTokenList();
                    if(tokenList == null)
                        continue;
                    for(Position position : tokenList.positions()){
                        final Token token = position.get();
                        String str = token.toString();
                        String pigLatinString = pigLatin(str);
                        if(!pigLatinString.equals(str)){
                            Token newToken = token.clone();
                            newToken.setValue(pigLatinString);
                            newToken.setAnnotation(TokenAnnotation.TOKENIZED);
                            position.add(newToken);
                        }
                    }
                }
            }
        }
        return true;
    }
    /**
     * Method to translate a sentence word by word.
     *
     * @param s
     *            The sentence in English
     * @return The pig latin version
     */
     
    private String pigLatin(String s) {
        String latin = "";
        int i = 0;
        while (i < s.length()) {
            // Take care of punctuation and spaces
            while (i < s.length() && !isLetter(s.charAt(i))) {
                latin = latin + s.charAt(i);
                i++;
            }
            // If there aren't any words left, stop.
            if (i >= s.length())
                break;
            // Otherwise we're at the beginning of a word.
            int begin = i;
            while (i < s.length() && isLetter(s.charAt(i))) {
                i++;
            }
            // Now we're at the end of a word, so translate it.
            int end = i;
            latin = latin + pigWord(s.substring(begin, end));
        }
        return latin;
    }
    /**
     * Method to test whether a character is a letter or not.
     *
     * @param c
     *            The character to test
     * @return True if it's a letter
     */
    private static boolean isLetter(char c) {
        return ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'));
    }
    /**
     * Method to translate one word into pig latin.
     *
     * @param word
     *            The word in english
     * @return The pig latin version
     */
    private static String pigWord(String word) {
        int split = firstVowel(word);
        // return word.substring(split)+"-"+word.substring(0, split)+"ay";
        return word.substring(split) + word.substring(0, split) + "ay";
    }
    /**
     * Method to find the index of the first vowel in a word.
     *
     * @param word
     *            The word to search
     * @return The index of the first vowel
     */
    private static int firstVowel(String word) {
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++)
            if (word.charAt(i) == 'a' || word.charAt(i) == 'e'
                    || word.charAt(i) == 'i' || word.charAt(i) == 'o'
                    || word.charAt(i) == 'u')
                return i;
        return 0;
    }
    @Override
    public List<String> getInput() {
        return input;
    }
    @Override
    public void setInput(List<String> input) {
        this.input = input;
    }
} 