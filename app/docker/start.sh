docker pull taskManagementApp/taskManagementApp:$1
docker container stop taskManagementApp
docker run --detach --rm --name taskManagementApp --env-file ./env.list \
  -e "SPRING_PROFILES_ACTIVE=staging,docker" \
  -p 8080:8080 -p 9000:9000 taskManagementApp/taskManagementApp:$1
