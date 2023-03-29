$(document).ready(function () {
  loadPets();
});

function loadPets() {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/api/pets",
    success: function (petArray) {
      console.log(petArray);
      //let featuredTab = $("#innerSidebar")
      console.log(document.getElementsByClassName("innerSidebar")[0]);
      let featuredTab = document.getElementsByClassName("innerSidebar")[0];
      featuredTab.innerHTML = "";
      const headerElemet = $("<h3>").text("Featured Pets");
      featuredTab.append(headerElemet[0]);
      for (let i = 0; i < 5; i++) {
        const randomIndex = Math.floor(Math.random() * petArray.length);
        const randomPet = petArray[randomIndex];
        const petName = randomPet.petName;
        const petId = randomPet.id;
        const link = $("<a>")
          .attr("href", `soloPet.html?petId=${petId}`)
          .text(petName);
        const petElement = $("<p>").append(link[0]);
        featuredTab.append(petElement[0]);
      }
    },
    error: function () {},
  });
}
