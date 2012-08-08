package me.supermaxman.xechat.Filters;

import java.util.ArrayList;

import me.supermaxman.xechat.XeChat;

public class KickFilter {

    /*
    *
    *   ERMAHGERD NSFW!
    *
    */

    static ArrayList<String> censored = null;

    public static ArrayList<String> getCensored() {
        if (censored == null) {
            censored = new ArrayList<String>();
            if (XeChat.conf.isSet("kickswears")) {
                censored.addAll(XeChat.conf.getStringList("kickswears"));
            } else {
                censored.add("fuck");
                XeChat.conf.set("kickswears", censored);
                XeChat.XE.saveConfig();
            }

        }
        return censored;
    }
}
