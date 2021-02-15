/**
 * Function called when page loads
 */
$( document ).ready(function() {
    setBasketCounterValue();
});

/**
 * Add product id to the basket
 * @param id Id of product to add to the basket
 */
function addToBasket(id) {
    if (typeof id == 'undefined') {
        return;
    }

    let basketContent = Cookies.get('basket-content');
    if (typeof basketContent == 'undefined' || basketContent === "") {
        Cookies.set('basket-content', id.toString());
    } else {
        Cookies.set('basket-content', basketContent + ";" + id.toString());
    }

    setBasketCounterValue();
}

function adjustBasketContentCookie(obj) {
    let productQuantitiesMap = convertBasketCookieToProductQuantitiesMap();
    let objId = $(obj).attr('id').split('_')[1];
    let objVal = $(obj).val();

    productQuantitiesMap.set(objId, objVal);
    Cookies.set('basket-content', convertProductQuantitiesMapToBasketCookieString(productQuantitiesMap));
    setBasketCounterValue();
}

/**
 * Sets text value to the basket counter based on number of products in the basket-content cookie
 */
function setBasketCounterValue() {
    let basketContent = Cookies.get('basket-content');
    $('#basket-counter').text(
        typeof basketContent == 'undefined'
            ? 0
            : $(basketContent.split(';')).not([""]).get().length);
}

function convertBasketCookieToProductQuantitiesMap() {
    let basketContent = Cookies.get('basket-content');
    let productQuantities = new Map();
    if (typeof basketContent != 'undefined' && basketContent !== "") {
        $.each(basketContent.split(';'), function(index, productId) {
            if (productQuantities.has(productId)) {
                let current = productQuantities.get(productId);
                productQuantities.set(productId, current + 1);
            } else if (productId !== "") {
                productQuantities.set(productId, 1)
            }
        });
    }
    return productQuantities;
}

function convertProductQuantitiesMapToBasketCookieString(quantitiesMap) {
    if (typeof quantitiesMap == 'undefined') {
        return;
    }

    let basketCookieString = '';
    for (let [key, value] of quantitiesMap) {
        for (let i = 0; i < value; i++) {
            basketCookieString += key.toString() + ';';
        }
    }
    // remove last ';'
    basketCookieString = basketCookieString.slice(0, -1);
    return basketCookieString;
}