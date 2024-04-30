package de.gaknr.mspbackend.clothingitem.enums;

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
        return color == Color.Navy_Blue ||
            color == Color.Turquoise_Blue ||
            color == Color.Blue;
    }

    public Boolean isRed(Color color) {
        return color == Color.Maroon ||
            color == Color.Red ||
            color == Color.Burgundy;
    }

    public Boolean isGreen(Color color) {
        return color == Color.Sea_Green ||
            color == Color.Olive ||
            color == Color.Khaki ||
            color == Color.Teal ||
            color == Color.Lime_Green ||
            color == Color.Green;
    }

    public Boolean isYellow(Color color) {
        return color == Color.Yellow ||
            color == Color.Gold;
    }

    public Boolean isPink(Color color) {
        return color == Color.Pink ||
            color == Color.Purple ||
            color == Color.Magenta ||
            color == Color.Mauve ||
            color == Color.Nude ||
            color == Color.Rose ||
            color == Color.Lavender;
    }

    public Boolean isOrange(Color color) {
        return color == Color.Peach ||
            color == Color.Orange ||
            color == Color.Rust;
    }

    public Boolean isBrown(Color color) {
        return color == Color.Copper ||
            color == Color.Bronze ||
            color == Color.Beige ||
            color == Color.Coffee_Brown ||
            color == Color.Tan ||
            color == Color.Cream ||
            color == Color.Mushroom_Brown ||
            color == Color.Brown ||
            color == Color.Taupe;
    }

    public Boolean isBlack(Color color) {
        return color == Color.Black ||
            color == Color.Charcoal ||
            color == Color.NA ||
            color == Color.Multi;
    }

    public Boolean isWhite(Color color) {
        return color == Color.Steel ||
            color == Color.Metallic ||
            color == Color.Silver ||
            color == Color.Grey_Melange ||
            color == Color.Grey ||
            color == Color.Off_White ||
            color == Color.White;
    }

}
