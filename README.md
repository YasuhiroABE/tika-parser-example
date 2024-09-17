
# Environment

* Ubuntu 22.04.5 LTS
* openjdk version "21.0.4" 2024-07-16 LTS (Eclipse Temurin OpenJDK)
* Apache Maven 3.9.8 (36645f6c9b5079805ea5009217e36f2cffd34256)

# How to reproduce an issue

```bash
$ make c
$ make p
$ make run
$ java -jar target/encoding-issue-1.0.0.jar
$ java -jar target/encoding-issue-1.0.0-jar-with-dependencies.jar
```

# References

* [https://stackoverflow.com/questions/51382751/maven-exec-works-but-java-jar-does-not](https://stackoverflow.com/questions/51382751/maven-exec-works-but-java-jar-does-not)



