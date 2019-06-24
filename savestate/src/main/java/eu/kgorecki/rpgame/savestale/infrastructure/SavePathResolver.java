package eu.kgorecki.rpgame.savestale.infrastructure;

final class SavePathResolver {
    private static final String SAVE_PATH_FORMAT = "%s/kgoreckiRpgame/%s.ser";

    String getSaveStateFilePath(String saveStateName) {
        String userHomeDir = System.getProperty("user.home");

        return String.format(SAVE_PATH_FORMAT, userHomeDir, saveStateName);
    }
}
