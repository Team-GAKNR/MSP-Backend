package de.gaknr.mspbackend.user;

import de.gaknr.mspbackend.clothingitem.ClothingItemMapper;
import de.gaknr.mspbackend.clothingitem.ClothingItemService;
import de.gaknr.mspbackend.clothingitem.dtos.GetClothingItemDTO;
import de.gaknr.mspbackend.outfit.OutfitMapper;
import de.gaknr.mspbackend.outfit.OutfitService;
import de.gaknr.mspbackend.user.dtos.AddUserDTO;
import de.gaknr.mspbackend.user.dtos.GetUserDTO;

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
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    private final ClothingItemService clothingItemService;
    private final ClothingItemMapper clothingItemMapper;

    private final OutfitService outfitService;
    private final OutfitMapper outfitMapper;

    @Operation(summary = "create a new user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = AddUserDTO.class))})
    })
    @PostMapping("/user")
    public ResponseEntity<GetUserDTO> createUser(
        @Valid @RequestBody AddUserDTO addUserDTO
    ) {
        this.userService.save(this.userMapper.mapUserDTOToEntity(addUserDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "get all users")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = GetUserDTO.class))})
    })
    @GetMapping("/users")
    public ResponseEntity<List<GetUserDTO>> getAllUsers() {
        List<GetUserDTO> list = new ArrayList<>();
        for (UserEntity entity : this.userService.getAll()) {
            list.add(this.userMapper.mapUserEntityToDTO(entity));
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(summary = "get user by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = GetUserDTO.class))})
    })
    @GetMapping("/user")
    public ResponseEntity<GetUserDTO> getUserById(
        @RequestParam("id") ObjectId id
    ) {
        return new ResponseEntity<>(
            this.userMapper.mapUserEntityToDTO(
                this.userService.getById(id)),
            HttpStatus.OK);
    }

    @Operation(summary = "delete user by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = GetUserDTO.class))})
    })
    @DeleteMapping("/user")
    public ResponseEntity<GetUserDTO> deleteUserById(
        @RequestParam("id") ObjectId id
    ) {
        this.userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "delete clothing item from user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = GetClothingItemDTO.class))})
    })
    @DeleteMapping("/user/clothing-item-of-user")
    public ResponseEntity<GetClothingItemDTO> deleteClothingItemFromUser(
        @RequestParam("clothingItemId") ObjectId clothingItemId,
        @RequestParam("userId") ObjectId userId) {
        clothingItemService.deleteClothingItemFromUserOutfitsAndUser(clothingItemId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
