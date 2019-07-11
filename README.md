# TURKCELL
TURKCELL Test Task


The goal of this assignment is to assess your mobile application development knowledge and
general programming style. You should be able to accomplish it in a few hours. It is not
necessary to handle all edge cases; however, please indicate in comments any work which
would still be remaining to “productize” the implementation.

# The Task
Build a simple application that displays the list of products. Things you have to take into
consideration:

 Application structure;

 Performance;

 Image cache;

 Exception handling.

# Optional Components:
 Offline/caching strategies; 

 Detail screen with a larger version of the image, the full title and the description.

 Unit tests

# REST API Information 
You can obtain the list of products from the following REST endpoint with HTTP GET method:

● https://s3-eu-west-1.amazonaws.com/developer-application-test/cart/list

A product can be obtained from the following endpoint with HTTP GET method: 

● https://s3-eu-west-1.amazonaws.com/developer-application-test/cart/{product_id}/detail
