$(document).ready(function () {
    loadInfo();
    const isAdmin = localStorage.getItem("isAdmin")
    if (isAdmin ==="false") {
      $('.soloButtonsContainer').hide();
    }
    
  });




  function loadInfo() {
    const urlParams = new URLSearchParams(window.location.search);
    const locationId = urlParams.get('locationId');
  
    $.ajax({
      type: "GET",
      url: `http://localhost:8080/api/locations/${locationId}`,
      success: function (location) {
        const shelterTextElem = $('.shelterText')
        const locationTextElem = $('.locationText')
        console.log(locationTextElem[0])
        const addressTextElem = $('.addressText')
        const contactTextElem = $('.contactText')
        const descriptionTextElem = $('.descriptionText')

        console.log(location.state)

        shelterTextElem.text(location.locationName)
        locationTextElem.text("Location: "+location.state)
        addressTextElem.text("Address: "+location.address)
        contactTextElem.text("Contact: "+location.contactInfo)
        descriptionTextElem.text("Description: "+location.locationDescription)

      },
      error: function () {},
    });
  
  }