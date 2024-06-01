package de.gaknr.mspbackend.clothingitem.enums;

public enum Usage {

    NA,
    Casual,
    Sports,
    Formal,
    Smart_Casual,
    Party,
    Travel;

    public static Usage convertStringToUsage(String usage) {
        return switch (usage) {
            case "Casual" -> Usage.Casual;
            case "Sports" -> Usage.Sports;
            case "Party" -> Usage.Party;
            case "Travel" -> Usage.Travel;
            case "Smart_Casual" -> Usage.Smart_Casual;
            case "Formal" -> Usage.Formal;
            default -> Usage.NA;
        };
    }

}
