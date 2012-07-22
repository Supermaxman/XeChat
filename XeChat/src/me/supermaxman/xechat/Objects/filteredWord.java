package me.supermaxman.xechat.Objects;

/**
 * User: Benjamin
 * Date: 22/07/12
 * Time: 15:55
 */
public class filteredWord {
    String word;
    String replacement;

    /*
   * Simple obj to hold for the config.
   * */
    public filteredWord(String word, String replacement) {
        this.word = word;
        this.replacement = replacement;
    }
}
