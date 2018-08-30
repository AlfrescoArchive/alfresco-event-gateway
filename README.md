# alfresco-event-gateway
Alfresco Event Gateway

The Alfresco Event Gateway is a component of Alfresco Digital Business Platform.

# Configuring the Event Gateway

## External DNS
The Event Gateway's Public ActiveMQ instance can optionally be exposed publically using an external domain or hostname as well as port.

The external hostname and port can be set using standard spring property overrides.  The properties that should overridden are:

`messaging.external.host`

`messaging.external.port`

For example, you can use environment variables

`MESSAGING_EXTERNAL_HOST=my.testdomain.com`

`MESSAGING_EXTERNAL_PORT=5672`

If these properties are not set, the Event Gateway will default to the host and port provided as part of the `To Route` for the ActiveMQ instance.

# API Endpoints

## Event Subscription
As a developer you will want to know how to subscribe to event topics available on the Event Gateway. To subscribe to events and receive the broker URL and topic name a developer can make an HTTP OPTIONS request to

`http://<event-gateway-host>/api/public/events/versions/1/events`

The response to this request will contain the eventTopic name and brokerUri as json. For example

```json
{
  "entry": {
    "eventTopic": "alfresco.events.allEvents",
    "brokerUri": "amqp://localhost:5672"
  }
}
```

The developer can then use this information to connect their client to this Event Gateway topic.

Note: The community version of the Event Gateway provides a single topic for all events.

For an example of how to subscribe to the topic and start consuming events, see [CamelRouteConfig.java](https://github.com/Alfresco/alfresco-anaxes-hello-world-service/blob/master/src/main/java/org/alfresco/deployment/sample/activemq/CamelRouteConfig.java) in the **alfresco-anaxes-hello-world-service project**.
