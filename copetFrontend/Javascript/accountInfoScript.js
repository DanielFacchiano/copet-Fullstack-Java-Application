$(document).ready(function () {
    loadUserPets();
    const userNameTextElem = $('.userName')
    const userName = localStorage.getItem("userName")

    const email = localStorage.getItem("userEmail")
    $("#email").val(email);

    console.log(email)

    const id = localStorage.getItem("userId")


    userNameTextElem.text(userName)
    $('.updateBtn').click(function() {
        let userJson = { userName: userName, email: $("#email").val(), id:id };
        //console.log(JSON.stringify(userJson))
        $.ajax({
            contentType: "application/json",
            type: "PUT",
            url: `http://localhost:8080/api/users/${id}`,
            data: JSON.stringify(userJson),
            success: function (good) {
                console.log(good)
                if(good){
                    localStorage.setItem("userEmail",$("#email").val())
                }
            },
            error: function(){}
        });
    });
})

  
  function loadUserPets() {
    let locationTable = $('#theThing');
    const urlParams = new URLSearchParams(window.location.search);
    const userId = localStorage.getItem("userId")
    console.log(localStorage.getItem("userId"))
  
    $.ajax({
      type: "GET",
      url: `http://localhost:8080/api/users/pets/${userId}`,
      success: function (petsArray) {
        petsArray.forEach(pet => {
          console.log(pet)
          const row = $('<tr>').append(
            $('<td>').text(pet.petName),
            $('<td>').text(pet.species),
            $('<td>').text(pet.age),
            $('<td>').text(pet.location.locationName)
          );
          locationTable.append(row);
        });
      },
      error: function () {},
    });
  }

  