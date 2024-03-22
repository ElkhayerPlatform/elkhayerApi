package ma.youcode.elkhayer.stripe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Checkout {
	private String priceId;
	private String successUrl;
	private String cancelUrl;

	public Checkout() {
		super();
	}
}