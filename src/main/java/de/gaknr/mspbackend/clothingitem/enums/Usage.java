package de.gaknr.mspbackend.clothingitem.enums;

public enum Usage {

    NA,
    Formal,
    Casual,
    Sports,
    Home,
    Party,
    Travel,
    Smart_Casual;

    public static Usage convertStringToUsage(String usage) {
        return switch (usage) {
            case "Formal" -> Usage.Formal;
            case "Casual" -> Usage.Casual;
            case "Sports" -> Usage.Sports;
            case "Home" -> Usage.Home;
            case "Party" -> Usage.Party;
            case "Travel" -> Usage.Travel;
            case "Smart_Casual" -> Usage.Smart_Casual;
            default -> Usage.NA;
        };
    }

}
