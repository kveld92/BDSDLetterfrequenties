# Kafka-Consumer te gebruiken bij opdracht "Letterfrequenties"
Deze code moet je gebruiken als Kafka-Consumer bij het oplossen van de opdracht "Letterfrequenties" (https://canvas.hu.nl/courses/1111/assignments/25365). De producer gaat er van uit dat er al een topic bestaat in Kafka met de naam `sentences`. Je moet dat dus aanmaken, of in de code van de consumer aanpassen dat er een ander topic gebruikt wordt.

## Code uitvoeren
Zorg dat je de Consumer(trainer) eerst runt en DAARNA de Producer(sourcer of jouw producer code).
De volgende code bouwt en voert de consumer uit.

`mvn package && mvn exec:java -Dexec.mainClass="nl.hu.bdsd.BDSDKafkaConsumerRunner"`

## Voorbeeld-run:

### info
![Info](images/0.png?raw=true "Info")

### Matrices
![Matrices](images/1.png?raw=true "matrix")

![Matrix EN & IT](images/2.png?raw=true "matrix1")

![Matrices IT & NL](images/3.png?raw=true "matrix2")

![Matrices ES](images/4.png?raw=true "matrix3")

### Sentences
![Sentences EN](images/5.png?raw=true "sentences1")

![Sentences NL](images/6.png?raw=true "sentences2")
