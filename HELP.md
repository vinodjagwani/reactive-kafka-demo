# Getting Started

### How to run

Both reactive-kafka-producer and reactive-kafka-consumer must build by following maven command to able to create docker images for both projects.

mvn clean install

and after successfully creating docker images for producer and consumer run following command.

docker-compose up



### How to test the result.

Upload json file on following url from producer side.

POST: http://localhost:8081/upload


To check the histogram graphs check the following url (it will show real time graph as msgs will arrive on Kafka topic).

http://localhost:8082/index.html

And click connect button.