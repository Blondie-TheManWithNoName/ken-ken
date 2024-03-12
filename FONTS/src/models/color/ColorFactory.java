package models.color;

import java.awt.*;
import java.util.List;

public class ColorFactory {
	private final static Color FIRST_COLOR = new Color(124, 203, 223);
	private final static int CONTRAST_THRESHOLD = 128;
	private final static int GRAY_THRESHOLD = 50;
	private final static int BRIGHTNESS_THRESHOLD = 180;
	private final static int[] STEP = {32, 28, 43};
	private final static int MAX_ITERATIONS = 100;

	public static Color nextColor(List<Color> colors) {
		if (colors.isEmpty())
			return FIRST_COLOR;
		Color lastColor = colors.get(colors.size() - 1);
		Color nextColor = new Color(
				(lastColor.getRed() + STEP[0]) % 256,
				(lastColor.getGreen() + STEP[1]) % 256,
				(lastColor.getBlue() + STEP[2]) % 256
		);
		for (int i = 0; i < MAX_ITERATIONS; i++) {
			if (isValidColor(nextColor))
				return nextColor;
			nextColor = new Color(
					(nextColor.getRed() + STEP[0]) % 256,
					(nextColor.getGreen() + STEP[1]) % 256,
					(nextColor.getBlue() + STEP[2]) % 256
			);
		}
		return FIRST_COLOR;
	}

	private static boolean isValidColor(Color color) {
		if (calculateContrast(color) < CONTRAST_THRESHOLD)
			return false;
		if (closeToGray(color) < GRAY_THRESHOLD)
			return false;
		return brightness(color) <= BRIGHTNESS_THRESHOLD;
	}

	private static int calculateContrast(Color color) {
		return (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
	}

	private static int closeToGray(Color color) {
		return Math.abs(color.getRed() - color.getGreen()) + Math.abs(color.getRed() - color.getBlue()) + Math.abs(color.getGreen() - color.getBlue());
	}

	private static int brightness(Color color) {
		return (int) Math.sqrt(
				color.getRed() * color.getRed() * .241 +
				color.getGreen() * color.getGreen() * .691 +
				color.getBlue() * color.getBlue() * .068
		);
	}
}
