package pl.monopoly.model;

public class SettingsState {
    private static final SettingsState settingsState = new SettingsState();
    private int playersNumber = 4;
    private boolean soundPlaying = true;

    private SettingsState() {}

    public static SettingsState getInstance() {
        return settingsState;
    }

    //get/set
    public int getPlayersNumber() {
        return playersNumber;
    }

    public void setPlayersNumber(int playersNumber) {
        this.playersNumber = playersNumber;
    }

    public void setSoundPlaying(boolean soundPlaying) {
        this.soundPlaying = soundPlaying;
    }

    public boolean isSoundPlaying() {
        return soundPlaying;
    }
}
