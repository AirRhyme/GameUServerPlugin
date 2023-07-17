package dev.gameu.utils;

public class CommandUtils {

    public static boolean QualityOfLifeCommandsEnabled = false;

    public static boolean isQualityOfLifeCommandsEnabled() {
        return QualityOfLifeCommandsEnabled;
    }

    public static void setQualityOfLifeCommandsEnabled(boolean qualityOfLifeCommandsEnabled) {
        QualityOfLifeCommandsEnabled = qualityOfLifeCommandsEnabled;
    }
}
