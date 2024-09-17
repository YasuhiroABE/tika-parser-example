

.PHONY: c
c:
	mvn clean
	mvn compile

.PHONY: run
run:
	mvn exec:java

.PHONY: p
p:
	mvn package

.PHONY: l
l:
	mvn dependency:list

.PHONY: git-push
git-push:
	git push
	git push --tags

.PHONY: clean
clean:
	find . -name '*~' -exec rm {} \; -print

