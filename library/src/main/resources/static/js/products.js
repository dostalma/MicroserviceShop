/**
 * Function called when page loads
 */
$( document ).ready(function() {
    let productQuantitiesMap = convertBasketCookieToProductQuantitiesMap();

    for (let [key, value] of productQuantitiesMap) {
        $('#quantityFieldProduct_' + key).val(value).text(value);
    }
});
