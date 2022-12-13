package reservation;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author mugajin
 */
public class DDBRecipientAdapter implements RecipientAdapterInterface {
    private final String PK_PREFIX = "recipient#";
    private final String DYNAMODB_EMAIL_KEY = "email";
    private final String DYNAMODB_FIRST_NAME_KEY = "first_name";
    private final String DYNAMODB_LAST_NAME_KEY = "last_name";
    private final String DYNAMODB_AGE_KEY = "age";
    private final String DYNAMODB_SLOTS_KEY = "slots";
    // table name by environment variable
    private final String tableName = Optional.of(System.getenv("TABLE_NAME")).orElse("VACCINATION_RESERVATION");
    DynamoDbClient dynamoDbClient = DynamoDbClient.builder().build();

    @Override
    public Recipient load(String recipientId) {
        // TODO : 例外処理についても検討する
        HashMap<String, AttributeValue> key = new HashMap<>();
        key.put("pk", AttributeValue.builder().s(PK_PREFIX + recipientId).build());

        Map<String, AttributeValue> item = dynamoDbClient
                .getItem(b -> b.tableName(tableName).key(key))
                .item();
        Recipient recipient = new Recipient(recipientId, item.get(DYNAMODB_EMAIL_KEY).s(), item.get(DYNAMODB_FIRST_NAME_KEY).s(), item.get(DYNAMODB_LAST_NAME_KEY).s(), Integer.parseInt(item.get(DYNAMODB_AGE_KEY).n()));
        if (item.containsKey(DYNAMODB_SLOTS_KEY)) {
            // TODO : この部分、元のサンプルではItemに入っているデータを入れてるけど、slotIDのリストから再度データを取ってくる必要がありそう
            // TODO : Adapterから他のAdapter呼び出したい時は直接呼び出してもOKなのか
            SlotAdapterInterface slotAdapter = new DDBSlotAdapter();
            item.get(DYNAMODB_SLOTS_KEY).l().forEach(slotId -> recipient.addReserveSlot(slotAdapter.load(slotId.s())));
        }
        return recipient;
    }

    @Override
    public boolean save(Recipient recipient) {
        HashMap<String, AttributeValue> item = new HashMap<>();
        item.put("pk", AttributeValue.builder().s(PK_PREFIX + recipient.recipientId).build());
        item.put(DYNAMODB_EMAIL_KEY, AttributeValue.builder().s(recipient.email).build());
        item.put(DYNAMODB_FIRST_NAME_KEY, AttributeValue.builder().s(recipient.firstName).build());
        item.put(DYNAMODB_LAST_NAME_KEY, AttributeValue.builder().s(recipient.lastName).build());
        item.put(DYNAMODB_AGE_KEY, AttributeValue.builder().n(String.valueOf(recipient.age)).build());
        item.put(DYNAMODB_SLOTS_KEY, AttributeValue.builder().l(List.of()).build());
        return dynamoDbClient.putItem(b -> b.tableName(tableName).item(item)).sdkHttpResponse().isSuccessful();
    }
}
