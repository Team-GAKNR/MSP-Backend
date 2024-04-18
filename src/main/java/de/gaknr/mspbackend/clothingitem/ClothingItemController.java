package de.gaknr.mspbackend.clothingitem;

import de.gaknr.mspbackend.clothingitem.dtos.AddClothingItemDTO;
import de.gaknr.mspbackend.clothingitem.dtos.GetClothingItemDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.bson.types.ObjectId;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
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
    @GetMapping("/clothing-items")
    public ResponseEntity<List<GetClothingItemDTO>> getAllClothingItems() {
        List<GetClothingItemDTO> list = new ArrayList<>();
        for (ClothingItemEntity entity : this.service.getAll()) {
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
    @GetMapping("/clothing-item")
    public ResponseEntity<GetClothingItemDTO> getGetClothingItemDtoById(
        @RequestParam("id") ObjectId id) {
        return new ResponseEntity<>(mapper.mapClothingItemEntityToGetClothingItemDTO(service.getById(id)), HttpStatus.OK);
    }

    @Operation(summary = "add clothing item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = AddClothingItemDTO.class))})
    })
    @PostMapping("/clothing-item")
    public ResponseEntity<GetClothingItemDTO> addClothingItem(
        @RequestBody @Valid AddClothingItemDTO addClothingItemDTO
    ) {
        service.save(mapper.mapAddClothingItemDtoToEntity(addClothingItemDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/clothing-item")
    public ResponseEntity<GetClothingItemDTO> updateClothingItem(
        @RequestParam("id") ObjectId id,
        @RequestBody @Valid AddClothingItemDTO updatedClothingItemDTO
    ) {
        ClothingItemEntity updatedEntity = this.mapper.mapAddClothingItemDtoToEntity(updatedClothingItemDTO);
        updatedEntity.setId(id);
        this.service.save(updatedEntity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
