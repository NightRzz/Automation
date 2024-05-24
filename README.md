# Automation
Some Rest Assured API tests cases  

<details>
  <summary><b>Reqres</b></summary>  
Test 1  
  
1. Using the service https://reqres.in, get a list of users from the second (2) page 
2. Make sure that the names of the user avatar files match  
3. Make sure that users emails end in regres.in

Test 2
1. Using the service https://reqres.in/, test the user’s registration in the system 
2. It is necessary to create 2 tests:
- Successful registration
- registration with an error due to the lack of a password,  
3. Check the error codes.
  
Test 3
1. Using the https://reqres.in/ service, make sure that the LIST<RESOURCE> operation returns data,
sorted by year.

Test 4
1. Using the service https://reqres.in/ try to delete the second user and compare the status code
   
Test 5
1. Using the https://reqres.in/ service, update user information and compare the update date with the current date on the machine
</details>
<details>
  <summary><b>JSONplaceholder</b></summary>  
Test 1  
  
1. Using the service https://jsonplaceholder.typicode.com/, get first post
2. Make sure that the post title match
3. Make sure that the this post belongs to user with id 1 

Test 2
1. Using the service https://jsonplaceholder.typicode.com/, test the post data update
2. Get the post after the update  
3. Make sure it is updated
  
Test 3
1. Using the https://jsonplaceholder.typicode.com/ service, test the post creation
2. Add post with title "My post" and body "My body" as user with id 3
3. Make sure the post has been added
</details>
