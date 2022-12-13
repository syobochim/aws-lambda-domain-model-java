package reservation;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

/**
 * Handler for requests to Lambda function.
 */
public class SearchReservationApp implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private RecipientInputPort getRecipientInputPort() {
        return new RecipientInputPort(
                new RecipientOutputPort(new DDBRecipientAdapter()),
                new SlotOutputPort(new DDBSlotAdapter()));
    }

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        String recipientId = input.getQueryStringParameters().get("recipient_id");

        RecipientInputPort recipientInputPort = getRecipientInputPort();
        Status status = recipientInputPort.searchRecipient(recipientId);

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        return response.withStatusCode(status.statusCode)
                .withBody(status.message);
    }
}
