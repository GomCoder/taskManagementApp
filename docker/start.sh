docker pull devktest/taskManagementApp
docker container stop devktest/taskManagementApp
docker run --detach --rm --name devktest/taskManagementApp --env-file ./env.list \
  -e "SPRING_PROFILES_ACTIVE=staging,docker" \
  -p 8080:8080 -p 9000:9000 devktest/taskManagementApp