package pl.monopoly.view;

public enum Sound {
    DRAW_CLICK("drawClick"), BUTTON_CLICK("soundClickDefault");

    private static final String AF = "src\\main\\resources\\sounds\\";
    private static final String FILE_TYPE = ".wav";
    private final String name;

    Sound(String name) {
        this.name = name;
    }

    public String getFullPath() {
        return AF+name+FILE_TYPE;
    }
}
