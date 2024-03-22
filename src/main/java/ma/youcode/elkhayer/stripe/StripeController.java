package ma.youcode.elkhayer.stripe;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api")
@Slf4j
@CrossOrigin
public class StripeController {

    private static void init() {
        Stripe.apiKey = "sk_test_51OXkvZIXxCO91fJ160VAXqo31zdL5WaVTDhitG86vxnnjJ59rE7vLHUlVbHqFvnxPXXURq3f0qrwYr8CG7UrrJxx00r5JxH1TM";
    }

    private static Gson gson = new Gson();

    @PostMapping("/payment")
    /**
     * Payment with Stripe checkout page
     *
     * @throws StripeException
     **/
    public ResponseEntity<?> paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException {
        log.info("in paymentWithCheckoutPage");
        System.out.println(payment.getAmount());
        init();
        SessionCreateParams params = SessionCreateParams.builder().addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD).setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.getSuccessUrl()).setCancelUrl(payment.getCancelUrl()).addLineItem(SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity()).setPriceData(SessionCreateParams.LineItem.PriceData.builder().setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount()).setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder().setName(payment.getName()).build()).build()).build()).build();
        Session session = Session.create(params);
        Map<String, String> responseData = new HashMap<>();
        responseData.put("id", session.getId());

        return ResponseEntity.ok().body(responseData);
    }


    @PostMapping("/subscription")
    /**
     * Used to create a subscription with strpe checkout page
     * @param checkout
     * @return the subscription id
     * @throws StripeException
     */ public String subscriptionWithCheckoutPage(@RequestBody Checkout checkout) throws StripeException {
        init();
        log.info(checkout.getPriceId());
        SessionCreateParams params = new SessionCreateParams.Builder().setSuccessUrl(checkout.getSuccessUrl()).setCancelUrl(checkout.getCancelUrl()).addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD).setMode(SessionCreateParams.Mode.SUBSCRIPTION).addLineItem(new SessionCreateParams.LineItem.Builder().setQuantity(1L).setPrice(checkout.getPriceId()).setPrice(checkout.getPriceId()).build()).build();

        try {
            Session session = Session.create(params);
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("sessionId", session.getId());
            return gson.toJson(responseData);
        } catch (Exception e) {
            Map<String, Object> messageData = new HashMap<>();
            messageData.put("message", e.getMessage());
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("error", messageData);
            return gson.toJson(responseData);
        }
    }


}