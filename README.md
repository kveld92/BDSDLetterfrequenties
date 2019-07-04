# Kafka-Consumer te gebruiken bij opdracht "Letterfrequenties"
Deze code moet je gebruiken als Kafka-Consumer bij het oplossen van de opdracht "Letterfrequenties" (https://canvas.hu.nl/courses/1111/assignments/25365). De producer gaat er van uit dat er al een topic bestaat in Kafka met de naam `sentences`. Je moet dat dus aanmaken, of in de code van de consumer aanpassen dat er een ander topic gebruikt wordt.

## Code uitvoeren
Zorg dat je de Consumer(trainer) eerst runt en DAARNA de Producer(sourcer of jouw producer code).
De volgende code bouwt en voert de consumer uit.

`mvn package && mvn exec:java -Dexec.mainClass="nl.hu.bdsd.BDSDKafkaConsumerRunner"`

## Voorbeeld-run:
![Info](images/0.png?raw=true "Info")
