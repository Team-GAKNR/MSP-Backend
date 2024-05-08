package de.gaknr.mspbackend.outfit.generation;

import de.gaknr.mspbackend.clothingitem.ClothingItemEntity;
import de.gaknr.mspbackend.clothingitem.ClothingItemService;
import de.gaknr.mspbackend.clothingitem.enums.Season;
import de.gaknr.mspbackend.clothingitem.enums.SubCategory;
import de.gaknr.mspbackend.clothingitem.enums.Usage;
import de.gaknr.mspbackend.outfit.OutfitEntity;
import de.gaknr.mspbackend.outfit.OutfitMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OutfitGenerationService {

    private final ClothingItemService clothingItemService;
    private final ColorHelperService colorHelperService;
    private final OutfitMapper outfitMapper;

    public OutfitStructureEntity generateSingleOutfit(List<ObjectId> clothingItems) {

        ObjectId outfitShoesItem;
        ObjectId outfitBottomwearItem;
        ObjectId outfitTopwearItem;

        List<ObjectId> shoesItems = new ArrayList<>();
        List<ObjectId> bottomwearItems = new ArrayList<>();
        List<ObjectId> topwearItems = new ArrayList<>();

        for(ObjectId item : clothingItems) {
            if(clothingItemService.getById(item).getSubCategory() == SubCategory.Shoes) {
                shoesItems.add(item);
            }
        }

        for(ObjectId item : clothingItems) {
            if(clothingItemService.getById(item).getSubCategory() == SubCategory.Bottomwear) {
                bottomwearItems.add(item);
            }
        }

        for(ObjectId item : clothingItems) {
            if(clothingItemService.getById(item).getSubCategory() == SubCategory.Topwear) {
                topwearItems.add(item);
            }
        }

        outfitShoesItem = selectRandomItemFromList(shoesItems);
        outfitBottomwearItem = selectRandomItemFromList(bottomwearItems);

        outfitTopwearItem = selectRandomItemFromList(colorHelperService.findMatchingItems(
            clothingItemService.getById(outfitBottomwearItem).getColor(), topwearItems
        ));

        return new OutfitStructureEntity(
            outfitShoesItem,
            outfitBottomwearItem,
            outfitTopwearItem
        );

    }

    /*public List<OutfitEntity> generateOutfitsFromCloset(List<ObjectId> closet) {
        List<OutfitStructureEntity> outfitList = new ArrayList<>();
        List<OutfitEntity> outfitEntityList = new ArrayList<>();
        for(int i = 0; i <= closet.size(); i++) {
            outfitList.add(generateSingleOutfit(closet));
        }
        for(OutfitStructureEntity outfitStructure : removeDoubleOutfits(outfitList)) {
            outfitEntityList.add(
                outfitMapper.mapOutfitStructureEntityToOutfitEntity(outfitStructure)
            );
        }
        return outfitEntityList;
    }*/

    public ObjectId selectRandomItemFromList(List<ObjectId> items) {
        Random rand = new Random();
        return items.get(rand.nextInt(items.size()));
    }

    public boolean checkUsageAvailability(List<ObjectId> closet, Usage usage) {
        ObjectId shoes = null;
        ObjectId bottomwear = null;
        ObjectId topwear = null;

        for(ObjectId clothingItem : closet) {
            ClothingItemEntity item = clothingItemService.getById(clothingItem);
            if(item.getUsage() == usage && item.getSubCategory() == SubCategory.Shoes) {
                shoes = clothingItem;
                break;
            }
        }
        for(ObjectId clothingItem : closet) {
            ClothingItemEntity item = clothingItemService.getById(clothingItem);
            if(item.getUsage() == usage && item.getSubCategory() == SubCategory.Bottomwear) {
                bottomwear = clothingItem;
                break;
            }
        }
        for(ObjectId clothingItem : closet) {
            ClothingItemEntity item = clothingItemService.getById(clothingItem);
            if(item.getUsage() == usage && item.getSubCategory() == SubCategory.Topwear) {
                topwear = clothingItem;
                break;
            }
        }
        return shoes != null && bottomwear != null && topwear != null;
    }

    public List<String> getAvailableUsagesForCloset(List<ObjectId> closet) {

        List<String> availableUsagesList = new ArrayList<>();

        if(checkUsageAvailability(closet, Usage.Formal)) {
            availableUsagesList.add("Formal");
        }
        if(checkUsageAvailability(closet, Usage.Casual)) {
            availableUsagesList.add("Casual");
        }
        if(checkUsageAvailability(closet, Usage.Sports)) {
            availableUsagesList.add("Sports");
        }
        if(checkUsageAvailability(closet, Usage.Home)) {
            availableUsagesList.add("Home");
        }
        if(checkUsageAvailability(closet, Usage.Party)) {
            availableUsagesList.add("Party");
        }
        if(checkUsageAvailability(closet, Usage.Travel)) {
            availableUsagesList.add("Travel");
        }
        if(checkUsageAvailability(closet, Usage.Smart_Casual)) {
            availableUsagesList.add("Smart_Casual");
        }
        return availableUsagesList;
    }

    /*public List<OutfitStructureEntity> removeDoubleOutfits(List<OutfitStructureEntity> outfitList) {
        Set<OutfitStructureEntity> uniqueOutfits = new HashSet<>();
        List<OutfitStructureEntity> result = new ArrayList<>();

        for(OutfitStructureEntity outfit : outfitList) {
            if(uniqueOutfits.add(outfit)) {
                result.add(outfit);
            }
        }

        return result;
    }*/

    public List<ObjectId> filterClothingItemsBySeason(List<ObjectId> closet, LocalDate date) {
        List<ObjectId> resultList = new ArrayList<>();
        if(date.getMonthValue() >= 3 && date.getMonthValue() <= 5) {
            for(ObjectId clothingItem : closet) {
                if(clothingItemService.getById(clothingItem).getSeason() == Season.Spring) {
                    resultList.add(clothingItem);
                }
            }
        } else if(date.getMonthValue() >= 6 && date.getMonthValue() <= 8) {
            for(ObjectId clothingItem : closet) {
                if(clothingItemService.getById(clothingItem).getSeason() == Season.Summer) {
                    resultList.add(clothingItem);
                }
            }
        } else if(date.getMonthValue() >= 9 && date.getMonthValue() <= 11) {
            for(ObjectId clothingItem : closet) {
                if(clothingItemService.getById(clothingItem).getSeason() == Season.Fall) {
                    resultList.add(clothingItem);
                }
            }
        } else {
            for(ObjectId clothingItem : closet) {
                if(clothingItemService.getById(clothingItem).getSeason() == Season.Winter) {
                    resultList.add(clothingItem);
                }
            }
        }
        return resultList;
    }

    public List<ObjectId> filterClothingItemsByUsage(List<ObjectId> closet, Usage usage) {
        List<ObjectId> filteredList = new ArrayList<>();
        for(ObjectId clothingItem : closet) {
            if(clothingItemService.getById(clothingItem).getUsage() == usage) {
                filteredList.add(clothingItem);
            }
        }
        return filteredList;
    }

    public OutfitStructureEntity generateOutfitByUsage(List<ObjectId> closet, String usage) {

        Usage usageEnum = Usage.convertStringToUsage(usage);
        List<ObjectId> closetFiltered = filterClothingItemsBySeason(closet, LocalDate.now());
        closetFiltered = filterClothingItemsByUsage(closetFiltered, usageEnum);

        return generateSingleOutfit(closetFiltered);

    }

    public List<OutfitStructureEntity> generateOutfitForEachUsage(List<ObjectId> closet) {

        List<OutfitStructureEntity> returnList = new ArrayList<>();

        for(String s : getAvailableUsagesForCloset(closet)) {
            returnList.add(generateOutfitByUsage(closet, s));
        }
        return returnList;
    }

}
