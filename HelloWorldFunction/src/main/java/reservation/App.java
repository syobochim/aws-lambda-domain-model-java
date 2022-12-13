package reservation;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private RecipientInputPort getRecipientInputPort() {
        return new RecipientInputPort(
                new RecipientOutputPort(new DDBRecipientAdapter()),
                new SlotOutputPort(new DDBSlotAdapter()));
    }

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        input.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode json = objectMapper.readTree(input.getBody());
            String recipientId = json.get("recipient_id").textValue();
            String slotId = json.get("slot_id").textValue();

            RecipientInputPort recipientInputPort = getRecipientInputPort();
            Status status = recipientInputPort.makeReservation(recipientId, slotId);

            APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
            return response.withStatusCode(status.statusCode)
                    .withBody(status.message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
