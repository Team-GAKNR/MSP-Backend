db = db.getSiblingDB('MSP');

db.createUser({
   user: 'admin',
   pwd: 'admin',
    roles: [{ role: 'readWrite', db: 'MSP' }]
});

db.User.insert({
    _id: ObjectId(),
    keycloakUserId: "1",
    closet: [],
    outfits: []
});
