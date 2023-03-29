$(document).ready(function () {
    loadLocations();
  });
  
  function loadLocations() {
    let locationTable = $('#theThing');
  
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/api/locations",
      success: function (locationsArray) {
        locationsArray.forEach(location => {
          const link = $('<a>').attr('href', 'soloShelter.html?' + $.param({ locationId: location.id })).text(location.locationName);
          const row = $('<tr>').append(
            $('<td>').append(link[0]),
            $('<td>').text(location.contactInfo),
            $('<td>').text(location.address),
            $('<td>').text(location.state)
          );
          locationTable.append(row);
        });
      },
      error: function () {},
    });
  }