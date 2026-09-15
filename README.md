# event-driven-notification-platform


\dn -> to list down all the schemas which are created
SET search_path TO notification, public; -> This is to set the path to notification schema 


<--- docker command to create the topic ---> 
docker exec -it notification-kafka \
/opt/kafka/bin/kafka-topics.sh \
--create \
--topic notification-events \
--bootstrap-server localhost:9092 \
--partitions 3 \
--replication-factor 1 

<--- docker command to check the created topic --->
docker exec -it notification-kafka \
/opt/kafka/bin/kafka-topics.sh \
--list \
--bootstrap-server localhost:9092

