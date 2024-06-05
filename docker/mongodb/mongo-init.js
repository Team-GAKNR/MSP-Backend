db = db.getSiblingDB('admin');
db.auth('mspadmin', 'adminPassword');

db = db.getSiblingDB('MSP');

db.ClothingItem.insertMany([
    {
        _id: ObjectId(),
        name: "T-shirt",
        image: "tshirt.jpg",
        brand: "BrandA",
        color: "red",
        masterCategory: "Tops",
        subCategory: "T-shirts",
        type: "Casual",
        season: "Summer",
        usage: "Everyday",
        isFavorite: false
    },
    {
        _id: ObjectId(),
        name: "Jeans",
        image: "jeans.jpg",
        brand: "BrandB",
        color: "blue",
        masterCategory: "Bottoms",
        subCategory: "Jeans",
        type: "Casual",
        season: "All",
        usage: "Everyday",
        isFavorite: false
    },
    {
        _id: ObjectId(),
        name: "Jacket",
        image: "jacket.jpg",
        brand: "BrandC",
        color: "black",
        masterCategory: "Outerwear",
        subCategory: "Jackets",
        type: "Formal",
        season: "Winter",
        usage: "Special",
        isFavorite: true
    },
    {
        _id: ObjectId(),
        name: "Sneakers",
        image: "sneakers.jpg",
        brand: "BrandD",
        color: "white",
        masterCategory: "Footwear",
        subCategory: "Sneakers",
        type: "Casual",
        season: "All",
        usage: "Everyday",
        isFavorite: true
    }
]);

var tShirtId = db.ClothingItem.findOne({name: "T-shirt"})._id;
var jeansId = db.ClothingItem.findOne({name: "Jeans"})._id;
var jacketId = db.ClothingItem.findOne({name: "Jacket"})._id;
var sneakersId = db.ClothingItem.findOne({name: "Sneakers"})._id;

db.Outfit.insertMany([
    {
        _id: ObjectId(),
        pieces: [tShirtId, jeansId],
        isFavorite: true
    },
    {
        _id: ObjectId(),
        pieces: [jacketId, sneakersId],
        isFavorite: false
    }
]);

var outfit1Id = db.Outfit.findOne({pieces: [tShirtId, jeansId]})._id;
var outfit2Id = db.Outfit.findOne({pieces: [jacketId, sneakersId]})._id;

db.User.insert({
    _id: ObjectId(),
    keycloakUserId: "1",
    closet: [tShirtId, jeansId, jacketId, sneakersId],
    outfits: [outfit1Id, outfit2Id]
});
