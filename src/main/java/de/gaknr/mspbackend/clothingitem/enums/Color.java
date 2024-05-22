package de.gaknr.mspbackend.clothingitem.enums;

import java.util.Arrays;
import java.util.List;

public enum Color {

    Sea_Green,
    Skin,
    Maroon,
    Red,
    Steel,
    Pink,
    Peach,
    Black,
    Olive,
    Copper,
    Purple,
    Mustard,
    Khaki,
    Orange,
    Bronze,
    Beige,
    Navy_Blue,
    Coffee_Brown,
    Tan,
    Charcoal,
    Metallic,
    Turquoise_Blue,
    Cream,
    Magenta,
    Teal,
    Mauve,
    Nude,
    Silver,
    Grey_Melange,
    Yellow,
    Burgundy,
    Mushroom_Brown,
    Rose,
    Grey,
    Lavender,
    Blue,
    NA,
    Gold,
    Rust,
    Lime_Green,
    Multi,
    Off_White,
    Fluorescent_Green,
    White,
    Green,
    Brown,
    Taupe;

    public Boolean isBlue(Color color) {
        List<Color> blueColors = Arrays.asList(
            Color.Navy_Blue,
            Color.Turquoise_Blue,
            Color.Blue);
        return(blueColors.contains(color));
    }

    public Boolean isRed(Color color) {
        List<Color> redColors = Arrays.asList(
            Color.Maroon,
            Color.Red,
            Color.Burgundy);
        return(redColors.contains(color));
    }

    public Boolean isGreen(Color color) {
        List<Color> greenColors = Arrays.asList(
            Color.Sea_Green,
            Color.Olive,
            Color.Khaki,
            Color.Teal,
            Color.Lime_Green,
            Color.Green
        );
        return greenColors.contains(color);
    }

    public Boolean isYellow(Color color) {
        List<Color> yellowColors = Arrays.asList(
            Color.Yellow,
            Color.Gold
        );
        return yellowColors.contains(color);
    }

    public Boolean isPink(Color color) {
        List<Color> pinkColors = Arrays.asList(
            Color.Pink,
            Color.Purple,
            Color.Magenta,
            Color.Mauve,
            Color.Nude,
            Color.Rose,
            Color.Lavender
        );
        return pinkColors.contains(color);
    }

    public Boolean isOrange(Color color) {
        List<Color> orangeColors = Arrays.asList(
            Color.Peach,
            Color.Orange,
            Color.Rust
        );
        return orangeColors.contains(color);
    }

    public Boolean isBrown(Color color) {
        List<Color> brownColors = Arrays.asList(
            Color.Copper,
            Color.Bronze,
            Color.Beige,
            Color.Coffee_Brown,
            Color.Tan,
            Color.Cream,
            Color.Mushroom_Brown,
            Color.Brown,
            Color.Taupe
        );
        return brownColors.contains(color);
    }

    public Boolean isBlack(Color color) {
        List<Color> blackColors = Arrays.asList(
            Color.Black,
            Color.Charcoal,
            Color.NA,
            Color.Multi
        );
        return blackColors.contains(color);
    }

    public Boolean isWhite(Color color) {
        List<Color> whiteColors = Arrays.asList(
            Color.Steel,
            Color.Metallic,
            Color.Silver,
            Color.Grey_Melange,
            Color.Grey,
            Color.Off_White,
            Color.White
        );
        return whiteColors.contains(color);
    }

}
