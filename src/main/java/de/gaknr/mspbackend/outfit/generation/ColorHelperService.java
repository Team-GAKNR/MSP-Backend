package de.gaknr.mspbackend.outfit.generation;

import de.gaknr.mspbackend.clothingitem.ClothingItemEntity;
import de.gaknr.mspbackend.clothingitem.ClothingItemService;
import de.gaknr.mspbackend.clothingitem.enums.Color;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorHelperService {

    private final ClothingItemService clothingItemService;

    public List<ObjectId> findMatchingItems(Color color, List<ObjectId> items) {

        List<ObjectId> matchingItems = new ArrayList<>();
        Color colorOfItem;

        if(color.isBlack(color) || color.isWhite(color)) {
            return items;

        } else if(color.isBlue(color)) {
            for(ObjectId item : items) {

                ClothingItemEntity itemEntity = clothingItemService.getById(item);
                colorOfItem = itemEntity.getColor();
                if(color.isBlue(colorOfItem) ||
                    color.isRed(colorOfItem) ||
                    color.isYellow(colorOfItem) ||
                    color.isOrange(colorOfItem) ||
                    color.isBrown(colorOfItem)) {
                    matchingItems.add(item);
                }
            }
        } else if(color.isRed(color)) {
            for(ObjectId item : items) {
                ClothingItemEntity itemEntity = clothingItemService.getById(item);
                colorOfItem = itemEntity.getColor();

                if(color.isBlue(colorOfItem) ||
                    color.isRed(colorOfItem) ||
                    color.isYellow(colorOfItem) ||
                    color.isBlack(colorOfItem) ||
                    color.isWhite(colorOfItem)) {
                    matchingItems.add(item);
                }
            }
        } else if(color.isGreen(color)) {
            for(ObjectId item : items) {
                ClothingItemEntity itemEntity = clothingItemService.getById(item);
                colorOfItem = itemEntity.getColor();

                if(color.isGreen(colorOfItem) ||
                    color.isYellow(colorOfItem) ||
                    color.isOrange(colorOfItem) ||
                    color.isBrown(colorOfItem) ||
                    color.isBlack(colorOfItem) ||
                    color.isWhite(colorOfItem)) {
                    matchingItems.add(item);
                }
            }
        } else if(color.isYellow(color)) {
            for(ObjectId item : items) {
                ClothingItemEntity itemEntity = clothingItemService.getById(item);
                colorOfItem = itemEntity.getColor();

                if(color.isBlue(colorOfItem) ||
                    color.isRed(colorOfItem) ||
                    color.isGreen(colorOfItem) ||
                    color.isYellow(colorOfItem) ||
                    color.isOrange(colorOfItem) ||
                    color.isBrown(colorOfItem) ||
                    color.isBlack(colorOfItem) ||
                    color.isWhite(colorOfItem)) {
                    matchingItems.add(item);
                }
            }
        } else if(color.isPink(color)) {
            for(ObjectId item : items) {
                ClothingItemEntity itemEntity = clothingItemService.getById(item);
                colorOfItem = itemEntity.getColor();

                if(color.isYellow(colorOfItem) ||
                    color.isPink(colorOfItem) ||
                    color.isBlack(colorOfItem) ||
                    color.isWhite(colorOfItem)) {
                    matchingItems.add(item);
                }
            }
        } else if(color.isOrange(color)) {
            for(ObjectId item : items) {
                ClothingItemEntity itemEntity = clothingItemService.getById(item);
                colorOfItem = itemEntity.getColor();

                if(color.isRed(colorOfItem) ||
                    color.isGreen(colorOfItem) ||
                    color.isYellow(colorOfItem) ||
                    color.isBrown(colorOfItem) ||
                    color.isBlack(colorOfItem) ||
                    color.isWhite(colorOfItem)) {
                    matchingItems.add(item);
                }
            }
        } else if(color.isBrown(color)) {
            for(ObjectId item : items) {
                ClothingItemEntity itemEntity = clothingItemService.getById(item);
                colorOfItem = itemEntity.getColor();

                if(color.isRed(colorOfItem) ||
                    color.isYellow(colorOfItem) ||
                    color.isOrange(colorOfItem) ||
                    color.isBrown(colorOfItem) ||
                    color.isBlack(colorOfItem) ||
                    color.isWhite(colorOfItem)) {
                    matchingItems.add(item);
                }
            }
        }
        return matchingItems;

    }

}
