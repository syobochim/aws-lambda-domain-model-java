package reservation;


import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author mugajin
 */
public class DDBSlotAdapter implements SlotAdapterInterface {
    private final String PK_PREFIX = "slot#";
    private final String DYNAMODB_DATE_KEY = "reservation_date";
    private final String DYNAMODB_LOCATION_KEY = "location";
    // table name by environment variable
    private final String tableName = Optional.of(System.getenv("TABLE_NAME")).orElse("VACCINATION_RESERVATION");
    DynamoDbClient dynamoDbClient = DynamoDbClient.builder().build();

    @Override
    public Slot load(String slotId) {
        HashMap<String, AttributeValue> key = new HashMap<>();
        key.put("pk", AttributeValue.builder().s(PK_PREFIX + slotId).build());

        Map<String, AttributeValue> slot = dynamoDbClient
                .getItem(b -> b.tableName(tableName).key(key))
                .item();
        return new Slot(slotId, LocalDateTime.parse(slot.get(DYNAMODB_DATE_KEY).s()), slot.get(DYNAMODB_LOCATION_KEY).s());
    }

    @Override
    public boolean save(Slot slot) {
        // TODO : DDBへのsaveは未実装
        return false;
    }
}
