package ge.tbcitacademy.util;

public class ColorUtil {
    public static String rgbToHex(String rgbColor) {
        // Extract numeric values from the RGB color string
        String[] rgbValues = rgbColor.replaceAll("[^\\d,]", "").split(",");

        // Convert the extracted values to integers
        int r = Integer.parseInt(rgbValues[0]);
        int g = Integer.parseInt(rgbValues[1]);
        int b = Integer.parseInt(rgbValues[2]);

        // Convert RGB to HEX
        return String.format("#%02X%02X%02X", r, g, b);
    }
}
