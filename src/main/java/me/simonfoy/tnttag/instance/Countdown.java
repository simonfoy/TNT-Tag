package me.simonfoy.tnttag.instance;

import me.simonfoy.tnttag.GameState;
import me.simonfoy.tnttag.TNT_Tag;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private TNT_Tag tntTag;
    private Game game;

    private boolean isRunning = false;

    private int countdownSeconds;

    public Countdown(TNT_Tag tntTag, Game game) {
        this.tntTag = tntTag;
        this.game = game;
        this.countdownSeconds = 15;
    }

    public void start() {
        game.setState(GameState.PRE_START);
        game.sendMessage(ChatColor.YELLOW + "Game is now in PRE_START State");
        isRunning = true;
        runTaskTimer(tntTag, 0, 20);
    }

    public void stop() {
        if (isRunning) {
            cancel();
            isRunning = false;
        }
    }

    @Override
    public void run() {
        if (countdownSeconds == 0) {
            isRunning = false;
            cancel();
            game.start();
            return;
        }

        if (countdownSeconds <= 10) {
            game.sendMessage(ChatColor.GREEN + "Game will start in " + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s") + ".");
        }

        game.sendTitle(ChatColor.GREEN.toString() + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s"), ChatColor.GRAY + "until game starts");

        tntTag.getGame().getScoreBoardManager().updateCountdown();

        countdownSeconds--;
    }

    public int getCountdownSeconds() {
        return countdownSeconds;
    }
    public boolean isRunning() {
        return isRunning;
    }
}
