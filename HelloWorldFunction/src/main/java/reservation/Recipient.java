package reservation;

import java.util.ArrayList;
import java.util.List;

public class Recipient {
    final String recipientId;
    final String email;
    final String firstName;
    final String lastName;
    final int age;
    final List<Slot> slots;

    public Recipient(String recipientId, String email, String firstName, String lastName, int age) {
        this.recipientId = recipientId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.slots = new ArrayList<>();
    }

    boolean addReserveSlot(Slot slot) {  // TODO : 例外じゃなくBooleanを飛ばしているの、ちょっと違和感ある
        if (isSlotsCountEqualOrOverTwo()) return false;
        if (areSlotsSameDate(slot)) return false;

        slots.add(slot);
        slot.useSlot();  // TODO : 副作用っぽい感じがするけどいいのかな・・・
        return true;
    }

    private boolean areSlotsSameDate(Slot slot) {
        return slots.stream().map(s -> s.reservationDate).anyMatch(slot.reservationDate::isEqual);
    }

    private boolean isSlotsCountEqualOrOverTwo() {
        return slots.size() >= 2;
    }
}
