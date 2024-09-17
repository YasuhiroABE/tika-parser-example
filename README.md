
# Simple Parser Example for Apache Tika

This project aims to explain how to execute apache tika under a Japanese environemnent.

# Environment

* Ubuntu 22.04.5 LTS
* openjdk version "21.0.4" 2024-07-16 LTS (Eclipse Temurin OpenJDK)
* Apache Maven 3.9.8 (36645f6c9b5079805ea5009217e36f2cffd34256)
* Apache Tika 2.7.0, 2.8.0, 2.9.2

# Issues

Under Apache Tika version 2.7.0, this example works well.

With some recent versions, there are some issues.

## How to reproduce issues

```bash
$ make c
$ make p
$ make run
$ java -jar target/encoding-issue-1.0.0.jar
$ java -jar target/encoding-issue-1.0.0-jar-with-dependencies.jar
```

## Expected results

This is an expected result when executing the ``make run`` command.

```text
builtin: https://www.yadiary.net/notfound
builtin: bodyBytes.length=196

dc:title=404 Not Found Content-Encoding=ISO-8859-1 Content-Type=text/html; charset=ISO-8859-1
...
```

## Some unexpected results

When executing the ``java -jar target/encoding-issue-1.0.0.jar`` command, we will get the following unexpected result.

```text
builtin: https://www.yadiary.net/notfound
builtin: bodyBytes.length=196

Error: Failed to detect the character encoding of a document
```

# References

* [https://stackoverflow.com/questions/51382751/maven-exec-works-but-java-jar-does-not](https://stackoverflow.com/questions/51382751/maven-exec-works-but-java-jar-does-not)



