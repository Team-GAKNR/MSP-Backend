package de.gaknr.mspbackend.outfit;

import de.gaknr.mspbackend.outfit.dtos.AddOutfitDTO;
import de.gaknr.mspbackend.outfit.dtos.GetOutfitDTO;

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

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class OutfitController {

    private final OutfitService service;
    private final OutfitMapper mapper;

    @Operation(summary = "create new outfit")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = AddOutfitDTO.class))})
    })
    @PostMapping("/outfit")
    public ResponseEntity<GetOutfitDTO> createOutfit(
        @Valid @RequestBody AddOutfitDTO addOutfitDTO
    ) {
        this.service.save(this.mapper.mapOutfitDTOToEntity(addOutfitDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "get all outfits")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = GetOutfitDTO.class))})
    })
    @GetMapping("/outfits")
    public ResponseEntity<List<GetOutfitDTO>> getAllOutfits() {
        List<GetOutfitDTO> list = new ArrayList<>();
        for (OutfitEntity entity : this.service.getAll()) {
            list.add(this.mapper.mapOutfitEntityToDTO(entity));
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(summary = "get outfit by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = GetOutfitDTO.class))})
    })
    @GetMapping("/outfit")
    public ResponseEntity<GetOutfitDTO> getOutfitById(
        @RequestParam("id") ObjectId id
    ) {
        return new ResponseEntity<>(
            this.mapper.mapOutfitEntityToDTO(
                this.service.getById(id)),
            HttpStatus.OK);
    }

    @Operation(summary = "delete outfit by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = GetOutfitDTO.class))})
    })
    @DeleteMapping("/outfit")
    public ResponseEntity<GetOutfitDTO> deleteOutfitById(
        @RequestParam("id") ObjectId id
    ) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
