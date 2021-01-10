# WebCrawlerService
Crawls websites to a given depth and retrieves the list of image URLs for each crawled links.

## Setup
1. Install Gradle https://gradle.org/install/
2. Install Git https://git-scm.com/book/en/v2/Getting-Started-Installing-Git
3. Download the project. Go to your desired project location and run `git clone https://github.com/slavilla/WebCrawlerService` in the command line.
4. Go to the WebCrawlerService project directory and run `gradlew bootRun` to start the server.
5. To initiate a crawl, send a POST request with payload to below URL.
```
POST http://localhost:8080/crawl
Content-Type: application/json
{
  "url": "https://wikipedia.org/wiki/Web_crawler",
  "depth": 1
}
```
6. To retrieve the list of crawled image URLs, send a GET request to below URL.
```
GET http://localhost:8080/inventory
Accept: application/json
```
