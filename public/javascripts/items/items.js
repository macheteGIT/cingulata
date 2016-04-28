(function() {
  var gallery = document.querySelector(".products-grid");
  window.addEventListener("load", function() {
    cng.item.load();
  });

  cng.item = {};

  /**
   * Loading items into grid.
   */
  cng.item.load = function() {
    cng.ajax.get("/item/filters/by", function(xhr) {
      createList(xhr.response)
    })
  }

  function isEmpty(obj) {
    return Object.keys(obj).length === 0 && JSON.stringify(obj) === JSON.stringify({});
  }

  /**
   * Processes response, containing String-represented Json.
   * @var response {Object}
   */
  function createList(response) {
    var list = JSON.parse(response);
    for (var i = 0; i < list.length; i++) {
      create(list[i]);
    }
  }

  /**
   * Processes item's object.
   * @var item {Object}
   */
  function create(item) {
    var itemLi = document.createElement("li");
    itemLi.style.display = 'none';
    itemLi.className = "item";

    var itemInnerDiv = document.createElement("div");
    itemInnerDiv.className = "item-inner";
    itemInnerDiv.id = item._id.$oid;

    var productImageA = document.createElement("a");
    productImageA.className = "product-image";
    productImageA.href = item.url;
    productImageA.title = item.title;

    var productImageImg = document.createElement("img");
    productImageImg.className = "img";
    productImageImg.src = "/assets/images/test-phone.jpg";
    productImageImg.alt = item.title;

    var productContainerAll = document.createElement("div");
    productContainerAll.className = "product-container-all";

    var productNameContainer = document.createElement("p");
    productNameContainer.className = "product-name-container";

    var productName = document.createElement("a");
    productName.className = "product-name";
    productName.href = item.url;
    productName.title = item.title;
    productName.innerText = item.title;

    var category = document.createElement("div");
    category.className = "category";
    category.innerText = item.category;

    var subCategory = document.createElement("div");
    subCategory.className = "sub-category";
    subCategory.innerText = item.subcategory;

    var grid = document.createElement("div");
    grid.className = "grid";

    var deliveryPeriod = document.createElement("div");
    deliveryPeriod.className = "delivery-period"

    var deliveryText = document.createElement("div");
    deliveryText.className = "delivery-text";
    deliveryText.innerText = "Срок доставки 2-5 дней";

    var deliveryIcon = document.createElement("div");
    deliveryIcon.className = "delivery-icon";

    var deliveryIconCar = document.createElement("span");
    deliveryIconCar.className = "icon delivery-icon-car";
    deliveryIconCar.title = "Курьерская доставка";

    var buyBox = document.createElement("div");
    buyBox.className = "buy-box";

    var priceBox = document.createElement("div");
    priceBox.className = "price-box";

    var regularPrice = document.createElement("span");
    regularPrice.className = "regular-price";

    var priceWrapper = document.createElement("span");
    priceWrapper.className = "price price-sym-7";

    var sum = document.createElement("span");
    sum.className = "sum";
    sum.innerText = item.price;

    var currency = document.createElement("span");
    currency.className = "currency";
    currency.innerText = " грн.";

    var attributesWrapper = document.createElement("div");
    attributesWrapper.className = "attributes por";

    var infoPopup = document.createElement("div");
    infoPopup.className = "info-popup";

    var content = document.createElement("div");
    content.className = "content";

    var attrContainer = document.createElement("div");
    attrContainer.className = "attr-container";

    var attrContent = document.createElement("div");
    attrContent.className = "attr-content";

    /**
     * If item's features object is not empty - display corresponding view on page.
     */
    if (!isEmpty(item.features)) {
      var featuresHolder = document.createElement("p");
      for (var feature in item.features) {
        var key = document.createElement("p");
        key.className = "span1";
        key.innerText = feature;

        var value = document.createElement("span");
        value.className = "span2";
        value.innerText = item.features[feature];

        featuresHolder.appendChild(key);
        featuresHolder.appendChild(value);
      }
        attrContent.appendChild(featuresHolder);
        attrContainer.appendChild(attrContent);
        content.appendChild(attrContainer);
        infoPopup.appendChild(content);
        attributesWrapper.appendChild(infoPopup);
    }

    priceWrapper.appendChild(sum);
    priceWrapper.appendChild(currency);
    regularPrice.appendChild(priceWrapper);
    priceBox.appendChild(regularPrice);
    buyBox.appendChild(priceBox);

    deliveryIcon.appendChild(deliveryIconCar);
    deliveryPeriod.appendChild(deliveryText);
    deliveryPeriod.appendChild(deliveryIcon);
    grid.appendChild(deliveryPeriod);

    productNameContainer.appendChild(productName);
    productContainerAll.appendChild(productNameContainer);

    productImageA.appendChild(productImageImg);

    itemInnerDiv.appendChild(productImageA);
    itemInnerDiv.appendChild(productContainerAll);
    itemInnerDiv.appendChild(category);
    itemInnerDiv.appendChild(subCategory);
    itemInnerDiv.appendChild(grid);
    itemInnerDiv.appendChild(buyBox);
    itemInnerDiv.appendChild(attributesWrapper);

    itemLi.appendChild(itemInnerDiv);

    gallery.appendChild(itemLi);

    window.setTimeout(function() {
      $(itemLi).fadeIn(400);
    });
  }
}());

/**
* Toggle
*/
(function() {
  $('.cd-filter-trigger').on('click', function(){
    triggerFilter(true);
  });
  $('.cd-filter .cd-close').on('click', function(){
    triggerFilter(false);
  });

  function triggerFilter($bool) {
    var elementsToTrigger = $([$('.cd-filter-trigger'), $('.cd-filter'), $('.cd-tab-filter'), $('.cd-gallery')]);
    elementsToTrigger.each(function(){
      $(this).toggleClass('filter-is-visible', $bool);
    });
  }

  //close filter dropdown inside lateral .cd-filter
  $('.cd-filter-block h4').on('click', function(){
    $(this).toggleClass('closed').siblings('.cd-filter-content').slideToggle(300);
  })
}());
