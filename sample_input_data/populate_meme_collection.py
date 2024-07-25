import sys
from pymongo import MongoClient
from datetime import datetime

# MongoDB connection details
MONGO_URI = "mongodb://localhost:27017/memes"  # Replace with your MongoDB URI
DATABASE_NAME = "memes"       # Replace with your database name
COLLECTION_NAME = "memes"                  # Replace with your collection name

def get_arguments():
    if len(sys.argv) != 2:
        print("Usage: python populate_mongodb_with_input.py <name> <url> <caption>")
        sys.exit(1)
    
    number_of_dummy_data = sys.argv[1]
    
    return number_of_dummy_data

def drop_collection():
    # Create a MongoClient instance
    client = MongoClient(MONGO_URI)

    # Select the database
    db = client[DATABASE_NAME]

    # Drop the collection
    db.drop_collection(COLLECTION_NAME)
    print(f"Collection '{COLLECTION_NAME}' has been dropped.")

def populate_collection(number_of_inputs):

    data = []

    for i in range(0, int(number_of_inputs)):
        meme = {
            "name": f"The price is {i}" ,
            "url": f"https://example.com/meme{i}.png",
            "caption": f"Caption for Meme {i}",
            "createdAt": datetime.now()
        }
        data.append(meme)

    # Create a MongoClient instance
    client = MongoClient(MONGO_URI)
        # Select the database and collection
    db = client[DATABASE_NAME]
    collection = db[COLLECTION_NAME]

    # Insert sample data into the collection
    result = collection.insert_many(data)
    # result = collection.insert_one(meme)
    print(f"Inserted IDs: {result.inserted_ids}")  

    

if __name__ == "__main__":
    drop_collection()
    number_of_dummy_collections = get_arguments()
    populate_collection(number_of_dummy_collections)
