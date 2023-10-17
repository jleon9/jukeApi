# Juke API Documentation
Jukebox REST API to manage the products of a company.

<a name="br1"></a> 

**Juke API Documentation**

## Installation:

1\. Open the terminal

2\. Clone this repository by typing: ```git clone https://github.com/jleon9/jukeApi.git```

3\. At the root of the jukeApi directory, write: ```docker build -t jukeapi-image```

4\. Once the command is executed, call: ```docker run -d -p 8000:8080 --name jukeapi-container jukeapi-image``` 

8000 is the default running port for this container although you can change it to any available port of your choice. 

Likewise, the container and image names can be changed to your preference.

5\. After the execution of this command, the jukeApi application is running.

## Use:

#### 1. Paste the following URL to your browser search bar:

[http://localhost:8000/api/jukeboxes?settingId="aae445bf-72f0-4680-a23e-18fcf7241f8b"](http://localhost:8000/api/jukeboxes?settingId=%22aae445bf-72f0-4680-a23e-18fcf7241f8b%22)

You should see the following output:

![image](https://github.com/jleon9/jukeApi/assets/112955370/474da02e-71c9-4cb5-b1e4-a7563815d541)

Congratulations! You have made your first successful query.

You can change the settingId parameter to any one of your choice. As long as your settingId exists 

in the [jukebox settings API](http://my-json-server.typicode.com/touchtunes/tech-assignment/settings), you will receive the correct output.


<a name="br2"></a> 

If your settingId is not valid (i.e. it does not exist in the the settings API), you will

receive the following error (this error is handled in the source code and should soon be handled with 

proper naming in the docker image):

#### 2. Add a Model Filter to the Query (Optional)

To add a model filter to your query, you can append ```&model=”modelName”``` to the url.

If your query is valid but has no matching results, you will simply receive a

paginated object with {items=[], "numItems":0,"numPages":1,"currentPage":1}

#### 3. Apply an Offset to the Displayed Output (Optional)

You can also decide to apply an offset to your paginated result. The offset starts

from 0. For example, if you make the following query,

```?settingId="aae445bf-72f0-4680-a23e-18fcf7241f8b"&model="fusion"offset=5```

All the jukeboxes that have index lower than 5 in the “items” list will be removed

from your output. The numItems and numPages parameters will however remain

the same since the amount of jukeboxes that matches the settingId and model

filters are unchanged.

#### 4. Apply a Limit to Pages (Optional)

Finally, you can apply a limit to the number of items a page displays. For example, if I append ```&limit=5``` 

to our previous query, we will obtain the following:

![image](https://github.com/jleon9/jukeApi/assets/112955370/e786f39f-830a-4be4-847f-93a3e157d985)


Indeed, the offset makes the output start from index 5 (i.e. the sixth element).

Since each page displays only 5 elements, the first page has been completely

turned; we switched to the second page and the “currentPage” parameter is now 2.



<a name="br3"></a> 

