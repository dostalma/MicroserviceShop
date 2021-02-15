package cz.dostalma.microserviceshop;

public enum CheckoutStep {

    STEP_ADDRESS("pages/checkout-address", "redirect:/checkout"),
    STEP_PAYMENT("pages/checkout-payment", "redirect:/checkout/payment"),
    STEP_CONFIRMATION("pages/checkout-confirmation", "redirect:/checkout/confirmation"),
    STEP_COMPLETE("pages/checkout-complete", "redirect:/checkout/complete");

    final String page;
    final String redirectionLink;

    CheckoutStep(String page, String redirectionLink) {
        this.page = page;
        this.redirectionLink = redirectionLink;
    }

    public String getPage() {
        return page;
    }

    public String getRedirectionLink() {
        return redirectionLink;
    }
}
