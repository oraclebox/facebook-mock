# facebook-mock
Mock services mimic facebook login API.
You can easily create any number of user profiles before integrating with Facebook.
Application/Client only need to provide a UUID (as FB AccessToken in the real case), this service will mimic facebook profile service and return a mock user profile to you. 

This is a mock server written by spring-boot. 
You can run it in your local PC or deploy as docker image.
All create profile are stored in local as a json file, no DB is required.

## Run in IDEA
Import as gradle project

Select Application > Run 

## Run in Docker
Build docker image 

docker build -t localhost/facebook-mock:lastest .

docker run -it --name facebook-mock -p 9800:9800 localhost/facebook-mock

### Mock get facebook user by facebook token:
Get http://localhost:9800/mock/graph/me?access_token=[UUID mimic facebook token]

Return facebook like profile

**Same UUID will get same profile**, new UUID will random generate a new profile.


### Mock get profile picture by facebook token:
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

## 中文
一個Facebook登錄API的模擬服務。這個服務為你產生不同的用戶帳戶，可以使你的應用程式在與Facebook集成之前，您可以輕鬆得到任意數量的用戶。
應用程序/客戶端只需提供一個UUID（實際案例中則是FB的AccessToken），此服務將模仿Facebook個人資料服務並返回模擬用戶個人資料給您。

這是一個由spring-boot編寫的模擬服務器。
您可以在本地PC中運行它或部署為docker鏡像。
所有創建配置文件都作為json文件存儲在本地不需要DB。
