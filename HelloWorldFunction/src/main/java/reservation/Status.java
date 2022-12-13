package reservation;

/**
 * @author mugajin
 */
public class Status {
    final int statusCode;
    final String message;

    public Status(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
