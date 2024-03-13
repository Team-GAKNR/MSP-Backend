package de.gaknr.mspbackend.clothingitem;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ClothingItemController {

    private final ClothingItemService service;
    private final ClothingItemMapper mapper;

    @Operation(summary = "get all clothing items")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = GetClothingItemDTO.class))})
    })
    @GetMapping("/clothingItems")
    public ResponseEntity<List<GetClothingItemDTO>> getAllClothingItems() {
        List<GetClothingItemDTO> list = new ArrayList<>();
        for(ClothingItemEntity entity : this.service.getAll()){
            list.add(this.mapper.mapClothingItemEntityToGetClothingItemDTO(entity));
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(summary = "get clothing item by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = GetClothingItemDTO.class))})
    })
    @GetMapping("/clothingItem")
    public ResponseEntity<GetClothingItemDTO> getGetClothingItemDtoById(
        @RequestParam("id") int id) {
        return new ResponseEntity<>(mapper.mapClothingItemEntityToGetClothingItemDTO(service.getById(id)), HttpStatus.OK);
    }

    @Operation(summary = "delete clothing item by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = GetClothingItemDTO.class))})
    })
    @DeleteMapping("/clothingItem")
    public ResponseEntity<GetClothingItemDTO> deleteClothingItemById(
        @RequestParam("id") int id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
