# todo-list-scheduler

Spring Boot application with two modules - Spring Scheduler and Spring AMQP.

The task of the service is to iterate all users once a day, generate reports for them about the day, and generate emails for sending. The generated emails are sent to the RabbitMQ queue.
