package pl.monopoly.view;

public enum Sound {
    DRAW_CLICK("sounds/drawClick"), BUTTON_CLICK("sounds/soundClickDefault");

    private static final String FILE_TYPE = ".wav";
    private final String name;

    Sound(String name) {
        this.name = name;
    }

    public String getFullPath() {
        return "/" + name + FILE_TYPE;
    }
}
