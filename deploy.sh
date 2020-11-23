DOCKERCOMPOSE=./docker-compose.yml
if test -f "$DOCKERCOMPOSE"; then
	docker-compose build springbackend reactfrontend
	docker-compose up
else
	echo "$DOCKERCOMPOSE does not exit."
fi
