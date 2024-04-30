package de.gaknr.mspbackend.outfit.generation;

import de.gaknr.mspbackend.clothingitem.ClothingItemService;
import de.gaknr.mspbackend.clothingitem.enums.SubCategory;
import de.gaknr.mspbackend.outfit.OutfitEntity;
import de.gaknr.mspbackend.outfit.OutfitMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

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

    public List<OutfitEntity> generateOutfitsFromCloset(List<ObjectId> closet) {
        List<OutfitStructureEntity> outfitList = new ArrayList<>();
        List<OutfitEntity> outfitEntityList = new ArrayList<>();
        for(int i = 0; i < closet.size() * 2; i++) {
            outfitList.add(generateSingleOutfit(closet));
        }
        for(OutfitStructureEntity outfitStructure : removeDoubleOutfits(outfitList)) {
            outfitEntityList.add(
                outfitMapper.mapOutfitStructureEntityToOutfitEntity(outfitStructure)
            );
        }
        return outfitEntityList;
    }

    public ObjectId selectRandomItemFromList(List<ObjectId> items) {
        Random rand = new Random();
        return items.get(rand.nextInt(items.size()));
    }

    public List<OutfitStructureEntity> removeDoubleOutfits(List<OutfitStructureEntity> outfitList) {
        Set<OutfitStructureEntity> uniqueOutfits = new HashSet<>();
        List<OutfitStructureEntity> result = new ArrayList<>();

        for(OutfitStructureEntity outfit : outfitList) {
            if(uniqueOutfits.add(outfit)) {
                result.add(outfit);
            }
        }

        return result;
    }

}
