# PuppyGram

Cristian Montejano
6/16/2021

Hello and welcome to my PuppyGram app!

This app uses the public Flickr api and gets the latest posts tagged with "puppy" and displays them in a scrollable GridView! You can also click on a container in the grid to get more details about the post.

API: https://api.flickr.com/services/feeds/photos_public.gne?tags=puppy&format=json&nojsoncallback=1

The app itself is a pretty simple MVC architecture demonstration with simple network integration for API calls. 

Third party libraries used:
-Picasso (for images)
-Gson (for json parsing)
-Retrofit (for API calls)

Some problems I ran into:
 -I was specified to use DateFormatter for date strings. Couldn't get this to work for some reason and was taking up too much time debugging so I went with using SimpleDateFormat instead. 
 -I'm not a graphics designer so UI choices are pretty barebones
 -Ran out of time to write some unit tests

 
 Other than that enjoy the app and please provide me any feedback :)
 
 
 
 
 
 
P.S.

What do you call a dog with a surround system?

A sub-woofer.
