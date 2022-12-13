package reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Recipient {
    final String recipientId;
    final String email;
    final String firstName;
    final String lastName;
    final int age;
    private final List<Slot> slots;

    public Recipient(String recipientId, String email, String firstName, String lastName, int age) {
        this.recipientId = recipientId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.slots = new ArrayList<>();
    }

    List<Slot> getSlots() {
        return List.copyOf(slots);
    }

    /**
     * Add a slot to the recipient.
     *
     * @param slot Reservation Slot to add
     * @return true : if the slot is added successfully / false : if the slot is added failed
     */
    boolean addReserveSlot(Slot slot) {  // TODO : 例外じゃなくBooleanを飛ばしているの、ちょっと違和感ある
        if (isReservationSlotFull()) return false;
        if (existReservationSameDatetime(slot)) return false;

        slots.add(slot);
        slot.useSlot();
        return true;
    }

    String toJson() {
        String slotList = slots.stream().map(Slot::toSlotIdJson).collect(Collectors.joining(", "));
        return "{\"recipientId\": \"" + recipientId + "\", \"email\": \"" + email + "\", \"firstName\": \"" + firstName + "\", \"lastName\": \""
                + lastName + "\", \"age\": " + age + ", \"slots\": [" + slotList + "]}";
    }

    private boolean existReservationSameDatetime(Slot slot) {
        return slots.stream().map(s -> s.reservationDate).anyMatch(slot.reservationDate::isEqual);
    }

    private boolean isReservationSlotFull() {
        return slots.size() >= 2;
    }
}
