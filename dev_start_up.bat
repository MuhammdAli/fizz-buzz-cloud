CALL mvn clean package -DskipTests

CALL docker-compose -f docker-compose.yml -f docker-compose.dev.yml up