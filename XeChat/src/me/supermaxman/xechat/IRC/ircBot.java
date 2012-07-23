package me.supermaxman.xechat.IRC;

import me.supermaxman.xechat.IRC.pircbot.IrcException;
import me.supermaxman.xechat.IRC.pircbot.NickAlreadyInUseException;
import me.supermaxman.xechat.IRC.pircbot.PircBot;
import me.supermaxman.xechat.XeChat;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

public class ircBot extends PircBot {
    ChatColor color = ChatColor.AQUA;
    Plugin herp = null;

    public ircBot(Plugin derp) {
        setName(XeChat.conf.getString("IRC.nick"));
        setLogin(XeChat.conf.getString("IRC.nick"));
        herp = derp;
    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message) {
        sendMessageToMain(((sender.equalsIgnoreCase("MainBot") || sender.equalsIgnoreCase("PixelBot")) ? " " : "<" + sender + ">: ") + message);
    }

    @Override
    protected void onConnect() {
        sendMessage("nickserv", "identify " + XeChat.conf.getString("nsPass"));

    }

    @Override
    protected void onDisconnect() {
        try {
            reconnect();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NickAlreadyInUseException e) {
            e.printStackTrace();
        } catch (IrcException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    protected void onPrivateMessage(String sender, String login, String hostname, String message) {
    }

    @Override
    protected void onAction(String sender, String login, String hostname, String target, String action) {
        sendMessageToMain("*" + sender + " " + action);


    }

    @Override
    protected void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice) {
        if (target.equalsIgnoreCase(XeChat.conf.getString("IRC.nick"))) {
            sendMessageToMain("Notice from " + sourceNick + ": " + notice);
        }
    }

    @Override
    protected void onJoin(String channel, String sender, String login, String hostname) {
        sendMessageToMain("*" + sender + " Joined");

    }

    @Override
    protected void onPart(String channel, String sender, String login, String hostname) {
        sendMessageToMain("*" + sender + " parted the channel");

    }

    @Override
    protected void onNickChange(String oldNick, String login, String hostname, String newNick) {
        sendMessageToMain("*" + oldNick + " is now known as " + newNick);
    }

    @Override
    protected void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {
        sendMessageToMain("*" + recipientNick + " was kicked");

    }

    @Override
    protected void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason) {
        sendMessageToMain("*" + sourceNick + " Quit");

    }

    @Override
    protected void onTopic(String channel, String topic, String setBy, long date, boolean changed) {
        if (changed) {
            sendMessageToMain("* Topic was changed to \"" + topic + "\" by " + setBy);
        }
    }

    @Override
    protected void onMode(String channel, String sourceNick, String sourceLogin, String sourceHostname, String mode) {
        sendMessageToMain("* Channel mode changed to " + mode + " By " + sourceNick);
    }

    @Override
    protected void onUserMode(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String mode) {
        sendMessageToMain("* " + targetNick + "'s mode was set to " + mode + " by " + sourceNick);
    }

    void sendMessageToMain(String message) {
        XeChat.g.sendString("[IRC]" + color + message);
    }
}
