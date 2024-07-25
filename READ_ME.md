# Project MemePosting

## Endpoints
   To check endpoints in swagger
   http://localhost:8081/swagger-ui


# Meme Collection
    Memes Collection can be populated with dummy data by changing the NUMBER_OF_DUMMY_MEME_DOCUMENTS you can decide     
    the number of documents. Remove brackets too.

    Note -> each time this command runs it drops the "memes" collection and refill it
```
   python .\sample_input_data\populate_meme_collection.py <NUMBER_OF_DUMMY_MEME_DOCUMENTS>
```

# Flows
    These are the mandatory product flows that are expected in the app

    1. Users will post Memes by providing these inputs
    
        Name of the person posting the meme    
        Caption for the Meme    
        URL of the Meme image
    
    2. Users will view the latest 100 memes posted

    3. Each meme should display the name of the person who posted the meme, the caption for the meme and the image pulled from the meme URL.**
    
    4. The first flow is implemented using POST api and the second flow is implemented using GET api.


# Mandatory Requirements
    1. The backend shall be capable of receiving the posted meme inputs from the frontend and store them in MongoDB.    
    2. The backend shall be capable of fetching the list of memes from the database and send them to the frontend.    
    3. The interaction between the frontend and backend shall be based on REST API with support for the below 3 endpoints.

## *Endpoint to send a meme to the backend*

    HTTP Method - POST    
    Endpoint - /memes/    
    Json Body contains these inputs - name, url, caption    
    The backend should allocate a unique id for the meme and return it as a json response.
    
    Error:


**_Example request**_

    curl --location --request POST 'http://<Server_URL>/memes/' \    
    --header 'Content-Type: application/json' \    
    --data-raw '{"name": "ashok kumar","url": "https://images.pexels.com/photos/3573382/pexels-photo-3573382.jpeg","caption": "This is a meme"}'

**_Sample response**_

    {
    "id": "1"
    }

## *Endpoint to fetch the latest 100 memes created from the backend*

    HTTP Method - GET    
    Endpoint - /memes/

    Error:    
    If there are no memes available, an empty array shall be returned.

_**Example request**_

    curl --location --request GET 'http://<Server_URL>/memes/'


**_Response body_**

    [
        {
            "id": "1",    
            "name": "MS Dhoni",    
            "url": "https://images.pexels.com/photos/3573382/pexels-photo-3573382.jpeg",    
            "caption": "Meme for my place"    
        },    
        {    
            "id": "2",    
            "name": "Viral Kohli",    
            "url": "https://images.pexels.com/photos/1078983/pexels-photo-1078983.jpeg",    
            "caption": "Another home meme"    
        }
    
    ]

## *Endpoint to specify a particular id (identifying the meme) to fetch a single Meme.*

    HTTP Method - GET    
    Endpoint - /memes/<id>    

    Error:    
    If a meme with that Id doesn’t exist, a 404 HTTP response code should be returned.
    Example request and sample response

**_Example request_**

    curl --location --request GET 'http://<Server_URL>/memes/<id>'

**_Response body_**

    {
        "id": "1",        
        "name": "MS Dhoni",        
        "url": "https://images.pexels.com/photos/3573382/pexels-photo-3573382.jpeg",        
        "caption": "Meme for my place"
    }

**_NOTE -> The database shall be designed to store and retrieve the meme content._**

# _Quality_
1. Incorrect requests shall return appropriate 4xx HTTP status codes. Follow HTTP status code standards
    - Duplicate POST requests with the same payload (name/url/caption) shall return 409.
    - If trying to GET a meme with an id that doesn’t exist, a 404 HTTP response code should be returned.

2. Write code with clear Comments and Documentation
3. Write code with good modularity and layering following the MVCS architecture.


# _Bonus Features_
1. Create a Dockerized solution for your server; the dockerfile should be added to the root folder of your project,
2. Document your existing APIs using Swagger. It should be exposed on port 8081 and be accessible through
   http://localhost:8081/swagger-ui

