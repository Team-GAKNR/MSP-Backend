package de.gaknr.mspbackend.user;

import de.gaknr.mspbackend.clothingitem.ClothingItemMapper;
import de.gaknr.mspbackend.clothingitem.ClothingItemService;
import de.gaknr.mspbackend.clothingitem.dtos.AddClothingItemDTO;
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

    @Operation(summary = "update a user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = AddUserDTO.class))})
    })
    @PutMapping("/user")
    public ResponseEntity<GetUserDTO> updateUserById(
        @Valid @RequestBody AddUserDTO addUserDTO,
        @RequestParam("user-id") String id
    ) {
        this.userService.update(this.userMapper.mapUserDTOToEntity(addUserDTO), id);
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
        @RequestParam("user-id") String id
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
        @RequestParam("user-id") String id
    ) {
        this.userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "get all clothing items")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = GetClothingItemDTO.class))})
    })
    @GetMapping("/user/clothing-items")
    public ResponseEntity<List<GetClothingItemDTO>> getAllClothingItems(
        @RequestParam("user-id") String id
    ) {
        List<GetClothingItemDTO> list = new ArrayList<>();

        for (ObjectId clothingId : this.userService.getById(id).getCloset()) {
            list.add(this.clothingItemMapper.mapClothingItemEntityToGetClothingItemDTO(this.clothingItemService.getById(clothingId)));
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(summary = "get clothing item by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = GetClothingItemDTO.class))}),
        @ApiResponse(responseCode = "404", description = "not found",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = GetClothingItemDTO.class))})
    })
    @GetMapping("/user/clothing-item")
    public ResponseEntity<GetClothingItemDTO> getGetClothingItemDtoById(
        @RequestParam("clothing-item-id") ObjectId clothingId,
        @RequestParam("user-id") String userId
    ) {
        if(this.userService.getById(userId).getCloset().contains(clothingId)) {
            return new ResponseEntity<>(
                this.clothingItemMapper.mapClothingItemEntityToGetClothingItemDTO(
                this.clothingItemService.getById(clothingId)),
                HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "add clothing item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = AddClothingItemDTO.class))})
    })
    @PostMapping("/clothing-item")
    public ResponseEntity<GetClothingItemDTO> addClothingItem(
        @RequestBody @Valid AddClothingItemDTO addClothingItemDTO,
        @RequestParam("user-id") String userId
    ) {
        this.clothingItemService.save(this.clothingItemMapper.mapAddClothingItemDtoToEntity(addClothingItemDTO), userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "update clothing item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = AddClothingItemDTO.class))})
    })
    @PutMapping("/clothing-item")
    public ResponseEntity<GetClothingItemDTO> updateClothingItem(
        @RequestParam("id") ObjectId id,
        @RequestBody @Valid AddClothingItemDTO updatedClothingItemDTO
    ) {
        this.clothingItemService.update(this.clothingItemMapper.mapAddClothingItemDtoToEntity(updatedClothingItemDTO), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "delete clothing item from user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = GetClothingItemDTO.class))})
    })
    @DeleteMapping("/user/clothing-item")
    public ResponseEntity<GetClothingItemDTO> deleteClothingItemFromUserById(
        @RequestParam("clothing-item-id") ObjectId clothingItemId,
        @RequestParam("user-id") String userId
    ) {
        clothingItemService.deleteClothingItemFromUserById(clothingItemId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
