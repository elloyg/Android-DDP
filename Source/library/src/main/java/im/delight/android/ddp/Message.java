package im.delight.android.ddp;

/**
 * Created by elloyg on 20/12/2016.
 */

public class Message {

	private String id;
	private String message;

	public Message(String id, String message) {
		this.id = id;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}
}
