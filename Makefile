IMAGE_NAME:=soultechnologies/cond-mono/home/math/IdeaProjects/CondMonolithModulelith-module
IMAGE_TAG:=latest
MAIN_MODULE:=application
DOCKERCOMPOSE_LOCATION:=src/main/docker/docker-compose.yml

cb:
	@echo ">> Gradle clean build:"
	./gradlew :$(MAIN_MODULE):clean :$(MAIN_MODULE):build

cbo:
	@echo ">> Gradle clean build bootjar:"
	./gradlew :$(MAIN_MODULE):clean :$(MAIN_MODULE):bootjar

compose:
	@echo ">> Docker compose up:"
	docker-compose --file $(DOCKERCOMPOSE_LOCATION) up -d

delete-containers:
	@echo ">> Docker rm:"
	docker rm -vf $(docker ps -aq)

delete-images:
	@echo ">> Docker rmi:"
	docker rmi -f $(docker images -aq)

delete-everything:
	@echo ">> Docker everything:"
	docker system prune -a --volumes

