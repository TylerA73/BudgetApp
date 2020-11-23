DOCKERCOMPOSE=./docker-compose.yml
if test -f "$DOCKERCOMPOSE"; then
	docker-compose down
	docker-compose build
	docker-compose up
else
	echo "$DOCKERCOMPOSE does not exist."
fi
