package reservation;

import java.time.LocalDateTime;

public class Slot {
    final String slotId;
    final LocalDateTime reservationDate;
    final String location;
    private boolean isVacant;

    public Slot(String slotId, LocalDateTime reservationDate, String location) {
        this.slotId = slotId;
        this.reservationDate = reservationDate;
        this.location = location;
        this.isVacant = true;
    }

    void useSlot() {
        this.isVacant = false;
    }

    boolean isVacant() {
        return isVacant;
    }

    String toSlotIdJson() {
            return "{\"slotId\": \"" + slotId + "\"}";
    }
}
