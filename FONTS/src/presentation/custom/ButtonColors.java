package presentation.custom;

public class ButtonColors {
    private String border;
    private String background;
    private String text;

    private String borderHover;
    private String backgroundHover;
    private String textHover;

    public ButtonColors(String border, String background, String text, String borderHover, String backgroundHover, String textHover)
    {
        this.border = border;
        this.background = background;
        this.text = text;

        this.backgroundHover = backgroundHover;
        this.borderHover = borderHover;
        this.textHover = textHover;
    }

    public String getBorder() {
        return border;
    }
    public String getBackground() {
        return background;
    }
    public String getText() {
        return text;
    }

    public String getBorderHover() {
        return borderHover;
    }
    public String getBackgroundHover() {
        return backgroundHover;
    }
    public String getTextHover() {
        return textHover;
    }
}
