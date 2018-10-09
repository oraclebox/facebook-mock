# facebook-mock
A mock services mimic facebook login

This is mock server written by springboot. 
You can run it in your local PC or deploy as docker image.

## Run in IDEA
Import as gradle project

Select Application > Run 

## Run in Docker
Build docker image 

docker build -t localhost/facebook-mock:lastest .

docker run -it --name facebook-mock -p 9800:9800 localhost/facebook-mock

### Mock get facebook user by id:
Get http://localhost:9800/mock/graph/me?access_token=[UUID mimic facebook token]

Return facebook profile

Same UUID will get same profile, new UUID will random generate a new profile.


### Mock get facebook user by id:
Get http://localhost:9800/mock/graph/me/picture?access_token=[UUID mimic facebook token]

Return profile picture of user

### Add User Picture

All user pictures will be placed under the sample folder. You can add more .png images to enrich the randomness of created profile.

For example, you can add a **.png** photo under sample/female/asian, as result, this profile picture will be in the selection of new profile creation.

### User detail

User detail are defined by these json files

chinese.json

japanese.json

western.json



