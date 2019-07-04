# Kafka-Consumer te gebruiken bij opdracht "Letterfrequenties"
Deze code moet je gebruiken als Kafka-producer bij het oplossen van de opdracht "Letterfrequenties" (https://canvas.hu.nl/courses/1111/assignments/25365). De producer gaat er van uit dat er al een topic bestaat in Kafka met de naam `sentences`. Je moet dat dus aanmaken, of in de code van de consumer aanpassen dat er een ander topic gebruikt wordt.

## Code uitvoeren
Zorg dat je de Consumer(trainer) eerst runt en DAARNA de Producer(sourcer of jouw code).
De volgende code bouwt en voert de consumer uit.

`mvn package && mvn exec:java -Dexec.mainClass="nl.hu.bdsd.BDSDKafkaConsumerRunner"`

## Voorbeeld-run:

sudo mvn package && mvn exec:java -Dexec.mainClass="nl.hu.bdsd.BDSDKafkaConsumerRunner"
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by com.google.inject.internal.cglib.core.$ReflectUtils$1 (file:/usr/share/maven/lib/guice.jar) to method java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain)
WARNING: Please consider reporting this to the maintainers of com.google.inject.internal.cglib.core.$ReflectUtils$1
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
[INFO] Scanning for projects...
[INFO]
[INFO] ---------------------< nl.hu.bdsd:kafka-consumer >----------------------
[INFO] Building kafka-consumer 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-resources-plugin:3.0.2:resources (default-resources) @ kafka-consumer ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 20 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ kafka-consumer ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 4 source files to /home/kevin/Programs/java/bdsd-letterfrequenties/trainer/target/classes
[INFO] /home/kevin/Programs/java/bdsd-letterfrequenties/trainer/src/main/java/nl/hu/bdsd/LanguageAnalyzer.java: /home/kevin/Programs/java/bdsd-letterfrequenties/trainer/src/main/java/nl/hu/bdsd/LanguageAnalyzer.java uses or overrides a deprecated API.
[INFO] /home/kevin/Programs/java/bdsd-letterfrequenties/trainer/src/main/java/nl/hu/bdsd/LanguageAnalyzer.java: Recompile with -Xlint:deprecation for details.
[INFO]
[INFO] --- maven-resources-plugin:3.0.2:testResources (default-testResources) @ kafka-consumer ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/kevin/Programs/java/bdsd-letterfrequenties/trainer/src/test/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.0:testCompile (default-testCompile) @ kafka-consumer ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /home/kevin/Programs/java/bdsd-letterfrequenties/trainer/target/test-classes
[INFO]
[INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @ kafka-consumer ---
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running nl.hu.bdsd.AppTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.065 s - in nl.hu.bdsd.AppTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- maven-jar-plugin:3.1.0:jar (default-jar) @ kafka-consumer ---
[INFO] Building jar: /home/kevin/Programs/java/bdsd-letterfrequenties/trainer/target/kafka-consumer-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  8.638 s
[INFO] Finished at: 2019-07-04T17:57:50+02:00
[INFO] ------------------------------------------------------------------------
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by com.google.inject.internal.cglib.core.$ReflectUtils$1 (file:/usr/share/maven/lib/guice.jar) to method java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain)
WARNING: Please consider reporting this to the maintainers of com.google.inject.internal.cglib.core.$ReflectUtils$1
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
[INFO] Scanning for projects...
[INFO]
[INFO] ---------------------< nl.hu.bdsd:kafka-consumer >----------------------
[INFO] Building kafka-consumer 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- exec-maven-plugin:1.6.0:java (default-cli) @ kafka-consumer ---
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.

###################
#	     INFO	    	#
###################
This is a list of all the text-files that are being used to train the models for each language
To add a new language, simply create a new directory in the resources folder with the corresponding abbreviation.
For example: For dutch text files, create a directory (NL). Afterwards fill this directory with text-files of the dutch language.
The program will automagically pick up the text-files and creates a new model for this language.
----------------------------------------------------------------
FILES:
	-> de (3437)
		- rapunzel.de.txt
		- katze_und_mause_in_gesellschaft.de.txt
		- das_lumpengesindel.de.txt
	-> en (44977)
		- sentences.en.txt
		- alice_in_wonderland.en.txt
	-> it (9472)
		- cappucetto_rosso.it.txt
		- il_corvo.it.txt
	-> nl (14604)
		- de_vliegende_hollander.nl.txt
		- wolf_zeven_geitjes.nl.txt
		- lichaam_valt_uit_vliegtuig.nl.txt
		- holle_bolle_gijs.nl.txt
		- sentences.nl.txt
		- financiele_problemen_bij_yarden.nl.txt
		- de_woeste_hoeve.nl.txt
		- eu_leiders_over_topbanen.nl.txt
		- vos_en_moeder_van_petekind.nl.txt
		- meisje_met_doodshoofd.nl.txt
	-> es (6093)
		- la_serpiente_blanca.es.txt
		- caperucita_roja.es.txt

####################
#	    MATRICES  	 #
####################

This is a list of bigram matrices, one for each language. Each bigram has a value between 0 - 100%.
These models are used to determine the language of the received sentences.
----------------------------------------------------------------


Lang: de
  a	    b	    c	    d	    e	    f     g	    h	    i	    j	    k	    l	    m     n	    o	    p	    q	    r	    s	    t	    u	    v	    w	    x	    y	    z	     _
a	0.03	0.44	0.49	0.12	0.00	0.06	0.38	0.17	0.00	0.00	0.00	0.47	0.15	0.64	0.00	0.00	0.00	0.47	0.49	0.29	0.99	0.00	0.00	0.00	0.00	0.00	0.20
b	0.09	0.00	0.00	0.00	0.84	0.00	0.00	0.00	0.06	0.00	0.00	0.09	0.00	0.00	0.06	0.00	0.00	0.03	0.03	0.09	0.00	0.00	0.00	0.00	0.00	0.00	0.12
c	0.00	0.00	0.00	0.00	0.00	0.00	0.00	2.85	0.00	0.00	0.41	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
d	0.84	0.00	0.00	0.00	1.19	0.00	0.00	0.00	0.81	0.00	0.00	0.12	0.03	0.00	0.00	0.00	0.00	0.03	0.06	0.06	0.12	0.00	0.00	0.00	0.00	0.00	1.05
e	0.00	0.20	0.29	0.17	0.00	0.15	0.26	0.35	1.89	0.00	0.06	0.47	0.29	3.32	0.00	0.00	0.00	2.07	0.84	0.23	0.12	0.03	0.12	0.00	0.00	0.03	3.00
f	0.15	0.00	0.00	0.00	0.23	0.00	0.03	0.00	0.03	0.00	0.00	0.03	0.00	0.00	0.09	0.00	0.00	0.06	0.00	0.06	0.06	0.00	0.00	0.00	0.00	0.00	0.70
g	0.00	0.00	0.00	0.00	1.19	0.00	0.00	0.03	0.20	0.00	0.00	0.03	0.00	0.06	0.00	0.00	0.00	0.00	0.06	0.20	0.03	0.00	0.00	0.00	0.00	0.00	0.38
h	0.61	0.00	0.00	0.03	0.52	0.00	0.00	0.03	0.06	0.00	0.00	0.23	0.23	0.17	0.15	0.00	0.00	0.32	0.00	0.76	0.00	0.00	0.09	0.00	0.00	0.00	1.31
i	0.00	0.09	1.25	0.09	2.24	0.06	0.15	0.17	0.00	0.00	0.00	0.12	0.17	1.80	0.00	0.00	0.00	0.32	0.12	0.32	0.00	0.00	0.00	0.00	0.00	0.00	0.09
j	0.03	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
k	0.12	0.00	0.00	0.00	0.17	0.00	0.00	0.00	0.03	0.00	0.00	0.03	0.00	0.12	0.17	0.00	0.00	0.00	0.00	0.15	0.09	0.00	0.00	0.00	0.00	0.00	0.17
l	0.09	0.03	0.03	0.00	0.58	0.00	0.00	0.00	0.29	0.00	0.06	0.49	0.00	0.00	0.09	0.00	0.00	0.00	0.17	0.29	0.09	0.00	0.00	0.00	0.00	0.00	0.58
m	0.20	0.03	0.00	0.00	0.41	0.00	0.00	0.00	0.20	0.00	0.00	0.00	0.23	0.00	0.03	0.03	0.00	0.00	0.06	0.00	0.06	0.00	0.00	0.00	0.00	0.00	0.67
n	0.41	0.00	0.00	1.54	0.90	0.03	0.32	0.00	0.44	0.00	0.06	0.00	0.03	0.29	0.12	0.00	0.00	0.00	0.26	0.26	0.20	0.00	0.03	0.00	0.00	0.03	3.99
o	0.00	0.12	0.23	0.03	0.00	0.03	0.03	0.09	0.00	0.00	0.00	0.38	0.09	0.12	0.00	0.09	0.00	0.47	0.06	0.03	0.00	0.00	0.00	0.00	0.00	0.00	0.29
p	0.15	0.00	0.00	0.00	0.06	0.12	0.00	0.00	0.03	0.00	0.00	0.03	0.00	0.00	0.03	0.00	0.00	0.09	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
q	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
r	0.20	0.03	0.00	0.17	0.58	0.06	0.12	0.06	0.17	0.00	0.00	0.00	0.00	0.29	0.06	0.00	0.00	0.09	0.17	0.44	0.03	0.00	0.03	0.00	0.00	0.09	1.86
s	0.29	0.00	0.70	0.00	0.55	0.00	0.00	0.03	1.37	0.00	0.00	0.00	0.00	0.00	0.32	0.23	0.00	0.00	0.17	0.61	0.00	0.03	0.00	0.00	0.00	0.00	1.66
t	0.09	0.00	0.00	0.00	1.80	0.00	0.00	0.00	0.12	0.00	0.00	0.00	0.00	0.00	0.06	0.00	0.00	0.20	0.23	0.26	0.12	0.00	0.03	0.00	0.00	0.17	1.37
u	0.00	0.00	0.26	0.00	0.06	0.52	0.03	0.06	0.00	0.00	0.00	0.03	0.20	1.28	0.00	0.00	0.00	0.15	0.35	0.15	0.00	0.00	0.00	0.00	0.00	0.00	0.35
v	0.00	0.00	0.00	0.00	0.12	0.00	0.00	0.00	0.09	0.00	0.00	0.00	0.00	0.00	0.38	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
w	0.55	0.00	0.00	0.00	0.49	0.00	0.00	0.00	0.44	0.00	0.00	0.00	0.00	0.00	0.32	0.00	0.00	0.00	0.00	0.00	0.06	0.00	0.00	0.00	0.00	0.00	0.00
x	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
y	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
z	0.03	0.00	0.00	0.00	0.15	0.00	0.00	0.00	0.06	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.06	0.49	0.00	0.03	0.00	0.00	0.00	0.06
_	1.51	0.47	0.00	2.15	1.77	0.41	0.87	0.67	0.70	0.03	0.47	0.29	0.49	0.81	0.17	0.15	0.00	0.17	2.88	0.20	0.99	0.52	1.54	0.00	0.00	0.55	0.00

Lang: en
  a	    b	    c	    d	    e	    f     g	    h	    i	    j	    k	    l	    m     n	    o	    p	    q	    r	    s	    t	    u	    v	    w	    x	    y	    z	     _
a	0.00	0.20	0.15	0.32	0.00	0.06	0.11	0.02	0.53	0.01	0.08	0.78	0.19	1.23	0.00	0.06	0.00	0.64	0.78	0.82	0.04	0.12	0.07	0.00	0.18	0.00	0.59
b	0.06	0.08	0.00	0.00	0.31	0.00	0.00	0.00	0.12	0.00	0.00	0.12	0.00	0.00	0.16	0.00	0.00	0.06	0.00	0.00	0.10	0.00	0.00	0.00	0.07	0.00	0.00
c	0.26	0.00	0.01	0.00	0.60	0.00	0.00	0.33	0.01	0.00	0.12	0.03	0.00	0.00	0.24	0.00	0.00	0.13	0.00	0.06	0.09	0.00	0.00	0.00	0.00	0.00	0.01
d	0.06	0.01	0.01	0.07	0.38	0.01	0.03	0.01	0.15	0.00	0.00	0.06	0.00	0.00	0.35	0.01	0.00	0.07	0.09	0.02	0.05	0.01	0.01	0.00	0.03	0.00	2.32
e	0.60	0.04	0.10	0.77	0.36	0.07	0.09	0.04	0.07	0.00	0.02	0.33	0.20	0.75	0.04	0.09	0.00	1.33	0.49	0.32	0.00	0.12	0.05	0.06	0.14	0.01	4.27
f	0.10	0.00	0.00	0.00	0.11	0.09	0.00	0.00	0.13	0.00	0.00	0.04	0.00	0.00	0.26	0.00	0.00	0.09	0.00	0.09	0.06	0.00	0.00	0.00	0.00	0.00	0.63
g	0.19	0.00	0.00	0.00	0.25	0.00	0.02	0.24	0.06	0.00	0.00	0.07	0.00	0.01	0.18	0.00	0.00	0.13	0.04	0.01	0.02	0.00	0.00	0.00	0.00	0.00	0.78
h	0.72	0.01	0.00	0.00	3.09	0.00	0.00	0.00	0.55	0.00	0.00	0.00	0.01	0.00	0.37	0.00	0.00	0.08	0.00	0.17	0.06	0.00	0.00	0.00	0.02	0.00	0.44
i	0.03	0.02	0.51	0.49	0.14	0.08	0.17	0.00	0.00	0.00	0.08	0.19	0.14	1.65	0.14	0.02	0.00	0.18	0.41	1.00	0.00	0.03	0.00	0.00	0.00	0.03	0.21
j	0.01	0.00	0.00	0.00	0.02	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.08	0.00	0.00	0.00	0.00	0.00	0.00
k	0.01	0.00	0.00	0.00	0.32	0.00	0.00	0.00	0.18	0.00	0.00	0.00	0.00	0.08	0.00	0.00	0.00	0.00	0.01	0.00	0.00	0.00	0.00	0.00	0.01	0.00	0.25
l	0.28	0.00	0.00	0.19	0.59	0.12	0.00	0.00	0.69	0.00	0.05	0.47	0.00	0.00	0.28	0.01	0.00	0.00	0.03	0.04	0.01	0.01	0.00	0.00	0.30	0.00	0.43
m	0.23	0.05	0.00	0.00	0.49	0.00	0.00	0.00	0.14	0.00	0.00	0.00	0.02	0.02	0.23	0.06	0.00	0.00	0.03	0.00	0.08	0.00	0.00	0.00	0.05	0.00	0.22
n	0.09	0.00	0.12	1.03	0.41	0.02	0.89	0.00	0.13	0.00	0.08	0.07	0.00	0.04	0.36	0.00	0.01	0.00	0.11	0.35	0.04	0.02	0.00	0.01	0.06	0.00	1.43
o	0.02	0.01	0.04	0.08	0.01	0.52	0.04	0.02	0.11	0.00	0.19	0.13	0.26	0.68	0.44	0.10	0.02	0.52	0.11	0.34	0.99	0.08	0.44	0.02	0.00	0.00	0.94
p	0.12	0.00	0.00	0.00	0.21	0.00	0.00	0.00	0.11	0.00	0.00	0.13	0.00	0.00	0.13	0.07	0.00	0.07	0.02	0.02	0.04	0.00	0.00	0.00	0.00	0.00	0.17
q	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.18	0.00	0.00	0.00	0.00	0.00	0.00
r	0.25	0.01	0.05	0.16	0.76	0.00	0.08	0.02	0.29	0.00	0.05	0.03	0.06	0.07	0.34	0.07	0.00	0.07	0.26	0.15	0.05	0.01	0.01	0.00	0.23	0.00	1.17
s	0.47	0.00	0.03	0.00	0.59	0.00	0.01	0.76	0.20	0.00	0.03	0.05	0.03	0.02	0.26	0.05	0.00	0.00	0.20	0.49	0.11	0.00	0.04	0.00	0.01	0.00	1.59
t	0.21	0.00	0.04	0.01	0.54	0.01	0.00	2.63	0.36	0.00	0.00	0.22	0.03	0.02	0.79	0.00	0.00	0.13	0.15	0.30	0.10	0.00	0.06	0.00	0.05	0.00	2.20
u	0.01	0.02	0.14	0.05	0.14	0.00	0.10	0.00	0.06	0.00	0.00	0.18	0.04	0.16	0.00	0.16	0.00	0.32	0.32	0.40	0.00	0.00	0.00	0.00	0.00	0.01	0.20
v	0.01	0.00	0.00	0.00	0.44	0.00	0.00	0.00	0.05	0.00	0.00	0.00	0.00	0.00	0.06	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
w	0.56	0.00	0.00	0.02	0.24	0.01	0.00	0.34	0.29	0.00	0.00	0.02	0.00	0.13	0.14	0.00	0.00	0.01	0.01	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.23
x	0.01	0.00	0.01	0.00	0.01	0.00	0.00	0.00	0.01	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.04	0.00	0.00	0.00	0.00	0.00	0.00	0.01
y	0.02	0.00	0.01	0.00	0.06	0.00	0.00	0.00	0.04	0.00	0.00	0.01	0.01	0.00	0.25	0.00	0.00	0.00	0.02	0.02	0.00	0.00	0.01	0.00	0.00	0.00	0.95
z	0.00	0.00	0.00	0.00	0.04	0.00	0.00	0.00	0.01	0.00	0.00	0.01	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.01	0.00
_	2.68	0.62	0.69	0.56	0.26	0.62	0.44	1.12	1.23	0.09	0.18	0.58	0.62	0.40	1.09	0.39	0.14	0.35	1.87	3.19	0.19	0.17	1.30	0.00	0.26	0.00	0.00

Lang: it
  a	    b	    c	    d	    e	    f     g	    h	    i	    j	    k	    l	    m     n	    o	    p	    q	    r	    s	    t	    u	    v	    w	    x	    y	    z	     _
a	0.00	0.01	0.18	0.19	0.02	0.06	0.08	0.03	0.22	0.00	0.00	0.77	0.24	1.35	0.00	0.07	0.00	1.19	0.44	0.46	0.01	0.58	0.00	0.00	0.00	0.02	4.27
b	0.16	0.08	0.00	0.00	0.48	0.00	0.00	0.00	0.07	0.00	0.00	0.00	0.00	0.00	0.06	0.00	0.00	0.04	0.00	0.00	0.02	0.00	0.00	0.00	0.00	0.00	0.00
c	0.76	0.00	0.35	0.00	0.32	0.00	0.00	0.87	0.31	0.00	0.00	0.00	0.00	0.00	0.77	0.00	0.00	0.02	0.00	0.00	0.03	0.00	0.00	0.00	0.00	0.00	0.00
d	0.60	0.00	0.00	0.06	0.52	0.00	0.00	0.00	1.14	0.00	0.00	0.00	0.00	0.00	0.65	0.00	0.00	0.05	0.00	0.00	0.08	0.00	0.00	0.00	0.00	0.00	0.03
e	0.02	0.08	0.15	0.29	0.01	0.00	0.31	0.00	0.07	0.00	0.00	0.51	0.05	0.61	0.04	0.00	0.01	1.34	0.46	0.30	0.00	0.59	0.00	0.00	0.00	0.06	4.90
f	0.17	0.00	0.00	0.00	0.08	0.04	0.00	0.00	0.16	0.00	0.00	0.01	0.00	0.00	0.11	0.00	0.00	0.05	0.00	0.00	0.13	0.00	0.00	0.00	0.00	0.00	0.00
g	0.17	0.00	0.00	0.00	0.11	0.00	0.15	0.00	0.72	0.00	0.00	0.53	0.00	0.01	0.00	0.00	0.00	0.14	0.00	0.00	0.03	0.00	0.00	0.00	0.00	0.00	0.05
h	0.02	0.00	0.00	0.00	0.62	0.00	0.00	0.00	0.24	0.00	0.00	0.00	0.00	0.00	0.05	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.05
i	0.77	0.10	0.24	0.12	0.19	0.03	0.20	0.00	0.00	0.00	0.00	0.64	0.11	0.78	0.43	0.04	0.00	0.13	0.61	0.11	0.13	0.05	0.00	0.00	0.00	0.00	2.88
j	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
k	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
l	0.89	0.00	0.10	0.00	0.52	0.00	0.00	0.00	0.76	0.00	0.00	0.99	0.07	0.00	0.80	0.00	0.00	0.00	0.02	0.15	0.07	0.00	0.00	0.00	0.00	0.00	0.82
m	0.84	0.11	0.00	0.00	0.45	0.00	0.00	0.00	0.39	0.00	0.00	0.00	0.03	0.00	0.19	0.02	0.00	0.00	0.00	0.00	0.06	0.00	0.00	0.00	0.00	0.00	0.00
n	0.48	0.00	0.30	0.49	0.45	0.00	0.25	0.00	0.14	0.00	0.00	0.00	0.00	0.13	1.29	0.00	0.03	0.00	0.10	0.71	0.24	0.03	0.00	0.00	0.00	0.10	1.20
o	0.01	0.00	0.04	0.03	0.01	0.06	0.13	0.02	0.23	0.00	0.00	0.37	0.23	1.13	0.00	0.11	0.02	1.01	0.34	0.24	0.00	0.34	0.00	0.00	0.00	0.13	3.58
p	0.11	0.00	0.00	0.00	0.34	0.00	0.00	0.00	0.08	0.00	0.00	0.01	0.00	0.00	0.46	0.03	0.00	0.34	0.00	0.00	0.08	0.00	0.00	0.00	0.00	0.00	0.00
q	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.60	0.00	0.00	0.00	0.00	0.00	0.00
r	1.00	0.05	0.06	0.07	1.09	0.00	0.06	0.00	0.43	0.00	0.00	0.17	0.21	0.23	0.94	0.01	0.00	0.13	0.23	0.21	0.01	0.16	0.00	0.00	0.00	0.03	0.20
s	0.38	0.00	0.16	0.04	1.17	0.03	0.01	0.00	0.77	0.00	0.00	0.00	0.00	0.00	0.42	0.13	0.00	0.00	0.76	0.56	0.26	0.03	0.00	0.00	0.00	0.00	0.00
t	0.61	0.00	0.00	0.00	1.07	0.00	0.00	0.00	0.29	0.00	0.00	0.00	0.00	0.00	0.80	0.00	0.00	0.55	0.00	0.46	0.16	0.00	0.00	0.00	0.00	0.00	0.00
u	0.46	0.01	0.06	0.00	0.23	0.01	0.03	0.00	0.15	0.00	0.00	0.23	0.03	0.71	0.29	0.01	0.00	0.04	0.03	0.13	0.00	0.00	0.00	0.00	0.00	0.00	0.14
v	1.01	0.00	0.00	0.00	0.76	0.00	0.00	0.00	0.32	0.00	0.00	0.00	0.00	0.00	0.56	0.00	0.00	0.05	0.00	0.00	0.02	0.04	0.00	0.00	0.00	0.00	0.00
w	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
x	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
y	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
z	0.21	0.00	0.00	0.00	0.02	0.00	0.00	0.00	0.03	0.00	0.00	0.00	0.00	0.00	0.06	0.00	0.00	0.00	0.00	0.00	0.01	0.00	0.00	0.00	0.00	0.20	0.00
_	1.54	0.48	1.78	1.86	1.35	0.51	0.68	0.07	1.03	0.00	0.00	0.96	1.12	0.97	0.10	1.03	0.54	0.22	1.73	0.61	0.60	0.94	0.00	0.00	0.00	0.00	0.00

Lang: nl
  a	    b	    c	    d	    e	    f     g	    h	    i	    j	    k	    l	    m     n	    o	    p	    q	    r	    s	    t	    u	    v	    w	    x	    y	    z	     _
a	1.60	0.00	0.14	0.21	0.00	0.08	0.21	0.01	0.00	0.00	0.14	0.45	0.30	1.23	0.00	0.08	0.00	1.12	0.31	0.81	0.04	0.07	0.00	0.00	0.00	0.00	0.18
b	0.04	0.01	0.00	0.00	0.49	0.00	0.00	0.00	0.19	0.00	0.00	0.11	0.00	0.00	0.11	0.00	0.00	0.05	0.00	0.01	0.00	0.00	0.00	0.00	0.00	0.00	0.01
c	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.63	0.03	0.00	0.00	0.00	0.00	0.00	0.01	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
d	0.71	0.00	0.00	0.02	1.81	0.00	0.00	0.01	0.33	0.05	0.00	0.01	0.03	0.00	0.41	0.00	0.00	0.12	0.06	0.01	0.01	0.01	0.02	0.00	0.00	0.00	0.93
e	0.00	0.09	0.08	0.27	1.59	0.18	0.41	0.02	0.40	0.00	0.16	0.83	0.24	3.68	0.00	0.14	0.00	2.15	0.42	1.49	0.10	0.16	0.07	0.00	0.00	0.20	3.58
f	0.01	0.00	0.00	0.25	0.03	0.01	0.01	0.00	0.01	0.01	0.00	0.02	0.02	0.00	0.01	0.00	0.00	0.00	0.04	0.03	0.00	0.00	0.00	0.00	0.00	0.00	0.27
g	0.14	0.00	0.00	0.07	1.26	0.00	0.07	0.01	0.05	0.00	0.01	0.03	0.00	0.00	0.10	0.00	0.00	0.24	0.05	0.01	0.00	0.01	0.00	0.00	0.00	0.04	0.76
h	0.60	0.00	0.00	0.00	1.28	0.00	0.00	0.00	0.29	0.00	0.00	0.00	0.00	0.00	0.29	0.00	0.00	0.02	0.00	0.42	0.09	0.00	0.00	0.00	0.00	0.02	0.03
i	0.01	0.00	0.19	0.11	0.88	0.01	0.30	0.00	0.00	1.10	0.10	0.12	0.02	0.66	0.00	0.01	0.00	0.00	0.33	0.21	0.00	0.04	0.00	0.00	0.00	0.00	0.02
j	0.06	0.00	0.00	0.03	0.64	0.01	0.01	0.00	0.00	0.00	0.18	0.04	0.00	0.31	0.05	0.01	0.00	0.00	0.01	0.00	0.03	0.02	0.00	0.00	0.00	0.03	0.44
k	0.08	0.01	0.00	0.01	0.38	0.00	0.00	0.00	0.08	0.01	0.08	0.05	0.00	0.03	0.15	0.00	0.00	0.10	0.02	0.12	0.05	0.00	0.12	0.00	0.00	0.00	0.49
l	0.38	0.00	0.00	0.17	0.53	0.12	0.02	0.00	0.45	0.00	0.05	0.16	0.00	0.00	0.26	0.02	0.00	0.00	0.07	0.01	0.05	0.02	0.00	0.00	0.00	0.01	0.49
m	0.34	0.00	0.00	0.07	0.81	0.00	0.00	0.01	0.12	0.00	0.00	0.00	0.03	0.00	0.13	0.03	0.00	0.00	0.00	0.08	0.01	0.00	0.01	0.00	0.00	0.01	0.41
n	0.36	0.02	0.00	0.69	0.22	0.00	0.48	0.03	0.22	0.00	0.14	0.04	0.02	0.09	0.14	0.00	0.00	0.01	0.14	0.14	0.09	0.01	0.03	0.00	0.00	0.05	4.37
o	0.01	0.01	0.10	0.14	0.68	0.25	0.14	0.00	0.04	0.00	0.25	0.06	0.32	0.53	1.01	0.41	0.00	0.48	0.08	0.21	0.26	0.14	0.00	0.00	0.00	0.03	0.12
p	0.05	0.00	0.00	0.00	0.21	0.00	0.02	0.00	0.05	0.02	0.00	0.14	0.00	0.01	0.12	0.12	0.00	0.11	0.01	0.05	0.01	0.00	0.01	0.00	0.00	0.00	0.37
q	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
r	0.33	0.05	0.00	0.35	0.51	0.01	0.15	0.15	0.24	0.00	0.03	0.09	0.05	0.10	0.43	0.04	0.00	0.00	0.20	0.12	0.14	0.05	0.03	0.00	0.00	0.11	1.77
s	0.01	0.00	0.15	0.01	0.16	0.00	0.01	0.06	0.00	0.23	0.01	0.17	0.03	0.04	0.01	0.05	0.00	0.00	0.09	0.68	0.00	0.01	0.00	0.00	0.00	0.00	0.79
t	0.11	0.03	0.00	0.00	1.16	0.00	0.01	0.03	0.20	0.22	0.01	0.00	0.01	0.00	0.38	0.00	0.00	0.14	0.16	0.08	0.03	0.00	0.03	0.00	0.00	0.05	2.67
u	0.00	0.00	0.01	0.03	0.00	0.00	0.07	0.00	0.24	0.00	0.04	0.06	0.00	0.05	0.00	0.01	0.00	0.06	0.05	0.01	0.00	0.00	0.23	0.00	0.00	0.00	0.21
v	0.47	0.00	0.00	0.00	0.66	0.00	0.00	0.00	0.14	0.00	0.00	0.03	0.00	0.00	0.24	0.00	0.00	0.13	0.00	0.00	0.01	0.00	0.00	0.00	0.00	0.00	0.00
w	0.85	0.00	0.00	0.02	0.54	0.00	0.00	0.00	0.14	0.00	0.00	0.00	0.00	0.00	0.12	0.00	0.00	0.01	0.01	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.13
x	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
y	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
z	0.26	0.00	0.00	0.00	0.82	0.00	0.00	0.00	0.55	0.00	0.00	0.00	0.00	0.00	0.42	0.00	0.00	0.00	0.00	0.00	0.04	0.00	0.03	0.00	0.00	0.00	0.00
_	0.56	0.79	0.01	2.07	1.60	0.05	0.95	2.09	0.33	0.25	0.58	0.41	0.99	0.53	0.87	0.38	0.00	0.21	0.48	0.83	0.12	1.14	1.25	0.00	0.00	1.57	0.00

Lang: es
	a	    b	    c	    d	    e	    f     g	    h	    i	    j	    k	    l	    m     n	    o	    p	    q	    r	    s	    t	    u	    v	    w	    x	    y	    z	     _
a	0.00	0.74	0.16	0.61	0.05	0.02	0.05	0.03	0.02	0.07	0.00	0.48	0.36	0.95	0.00	0.46	0.05	0.98	1.20	0.10	0.00	0.05	0.00	0.00	0.08	0.07	5.45
b	0.20	0.00	0.00	0.00	0.07	0.00	0.00	0.00	0.15	0.00	0.00	0.07	0.00	0.00	0.56	0.00	0.00	0.21	0.00	0.02	0.49	0.00	0.00	0.00	0.00	0.00	0.00
c	1.23	0.00	0.00	0.00	0.39	0.00	0.00	0.08	0.57	0.00	0.00	0.00	0.00	0.00	0.54	0.00	0.00	0.05	0.00	0.10	0.34	0.00	0.00	0.00	0.00	0.00	0.00
d	0.44	0.00	0.00	0.00	1.18	0.00	0.00	0.00	0.36	0.00	0.00	0.00	0.00	0.00	0.87	0.00	0.00	0.07	0.00	0.00	0.07	0.00	0.00	0.00	0.00	0.00	0.03
e	0.03	0.03	0.28	0.20	0.00	0.02	0.20	0.00	0.00	0.16	0.00	1.49	0.25	1.54	0.03	0.00	0.00	1.46	1.17	0.03	0.00	0.11	0.00	0.03	0.00	0.13	3.41
f	0.02	0.00	0.00	0.00	0.07	0.00	0.00	0.00	0.00	0.00	0.00	0.08	0.00	0.00	0.02	0.00	0.00	0.03	0.00	0.00	0.18	0.00	0.00	0.00	0.00	0.00	0.00
g	0.10	0.00	0.00	0.00	0.02	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.16	0.00	0.00	0.18	0.00	0.00	0.16	0.00	0.00	0.00	0.00	0.00	0.00
h	0.21	0.00	0.00	0.00	0.02	0.00	0.00	0.00	0.07	0.00	0.00	0.00	0.00	0.00	0.13	0.00	0.00	0.00	0.00	0.00	0.07	0.00	0.00	0.00	0.00	0.00	0.03
i	0.11	0.02	0.11	0.20	0.71	0.00	0.02	0.00	0.00	0.18	0.00	0.08	0.03	0.41	0.26	0.00	0.00	0.21	0.10	1.07	0.00	0.07	0.00	0.00	0.00	0.05	0.16
j	0.46	0.00	0.00	0.00	0.02	0.00	0.00	0.00	0.05	0.00	0.00	0.00	0.00	0.00	0.36	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
k	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
l	1.49	0.00	0.02	0.00	0.57	0.00	0.07	0.00	0.54	0.00	0.00	0.51	0.00	0.00	0.62	0.00	0.00	0.02	0.02	0.05	0.07	0.03	0.00	0.00	0.00	0.00	0.95
m	0.26	0.03	0.00	0.00	0.36	0.00	0.00	0.00	0.44	0.00	0.00	0.00	0.00	0.00	0.11	0.18	0.00	0.00	0.00	0.00	0.10	0.00	0.00	0.00	0.00	0.00	0.00
n	0.56	0.00	0.26	0.46	0.15	0.03	0.03	0.00	0.02	0.00	0.00	0.00	0.02	0.00	0.72	0.00	0.03	0.00	0.05	0.95	0.03	0.00	0.00	0.00	0.00	0.02	1.26
o	0.00	0.43	0.10	0.11	0.00	0.00	0.02	0.03	0.00	0.48	0.00	0.16	0.08	0.59	0.00	0.02	0.02	0.92	0.69	0.16	0.00	0.00	0.00	0.00	0.05	0.00	3.30
p	0.38	0.00	0.00	0.00	0.66	0.00	0.00	0.00	0.08	0.00	0.00	0.00	0.00	0.00	0.25	0.00	0.00	0.16	0.00	0.00	0.25	0.00	0.00	0.00	0.00	0.00	0.00
q	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	1.33	0.00	0.00	0.00	0.00	0.00	0.00
r	1.40	0.00	0.00	0.02	0.67	0.00	0.02	0.00	0.33	0.00	0.00	0.05	0.16	0.05	1.07	0.00	0.02	0.11	0.07	0.38	0.43	0.02	0.00	0.00	0.00	0.02	1.10
s	0.51	0.00	0.10	0.00	0.79	0.02	0.00	0.00	0.31	0.00	0.00	0.00	0.03	0.00	0.25	0.13	0.10	0.00	0.00	0.39	0.33	0.00	0.00	0.00	0.00	0.00	1.90
t	1.85	0.00	0.00	0.00	0.80	0.00	0.00	0.00	0.23	0.00	0.00	0.00	0.00	0.00	0.64	0.00	0.00	0.61	0.00	0.00	0.13	0.00	0.00	0.00	0.00	0.00	0.00
u	0.23	0.08	0.41	0.05	2.17	0.00	0.02	0.00	0.15	0.00	0.00	0.02	0.00	0.56	0.00	0.02	0.00	0.20	0.16	0.02	0.00	0.03	0.00	0.00	0.07	0.02	0.31
v	0.21	0.00	0.00	0.00	0.20	0.00	0.00	0.00	0.38	0.00	0.00	0.00	0.00	0.00	0.08	0.00	0.00	0.00	0.00	0.00	0.02	0.00	0.00	0.00	0.00	0.00	0.00
w	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
x	0.02	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.02	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00
y	0.10	0.00	0.00	0.00	0.03	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.03	0.00	0.00	0.00	0.00	0.00	0.02	0.00	0.00	0.00	0.00	0.00	1.46
z	0.15	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.07	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.00	0.08
_	2.00	0.43	1.87	1.38	1.67	0.31	0.21	0.38	0.10	0.00	0.00	2.02	0.56	0.49	0.38	0.97	1.10	0.67	1.41	1.00	0.49	0.57	0.00	0.00	1.44	0.00	0.00

####################
#	    LISTENING	   #
####################

Listening for sentences on topic: sentences
----------------------------------------------------------------
(EN)	Why Apollo 10 Stopped Just 47,000 Feet From the Moon In a year when
(EN)	we’ll celebrate Apollo 11’s 50th anniversary, it’s worth remembering
(EN)	the pathfinders who completed the same mission with one critical
(EN)	order: don’t actually land on the moon.  The command service module,
(EN)	dubbed “Charlie Brown” for the Apollo 10 mission to the moon, viewed
(EN)	from the lunar module “Snoopy” in 1969.  Soon we will recognize the
(EN)	50th anniversary of the first humans to walk on the moon.  We remember
(EN)	and celebrate the heroism of the Apollo 11 crew: the humility of Neil
(EN)	Armstrong making those first bootprints; the cool bravado of Buzz
(EN)	Aldrin during the critical moments of the Eagle lander’s final
(EN)	descent; and, the lonely vigil of Michael Collins in orbit above his
(EN)	mates, waiting to bring them back home.  But we also need to celebrate
