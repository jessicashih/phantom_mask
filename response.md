# Response
## API Document (required)
  Import two json file into Postman.
   1. [demo_api](response/json_file/pharmacy.postman_demoApi.json)
   2. [enviroment](response/json_file/Docker.postman_environment.json)

## Import Data Commands (required)
  The program has been packaged with docker, just run docker container. You can directly test the API on Postman.
  Please confirm your authority to access docekr and the project.
  (Don't put the project under `D:/`, if your docker is installed under `C:/`)
  1. Step 1: Download Docker [here](https://www.docker.com/products/docker-desktop).
  2. Step 2: From a terminal navigate to [file](response) and execute `docker-compose up`.
  3. Step 3: Check the `SpringBoot` mark is shown on terminal,then you can test API on Postman.
  p.s. Use `docker-compose down` to remove all container, when you test over.

## Test Coverage Report(optional)
  Please open [file](response/coverage_report/index.html) with chrome or other browser.
  
## Demo Site Url (optional)
  demo ready on [heroku](#demo-site-url-optional)
