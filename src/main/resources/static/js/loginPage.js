
let allUser = []
async function emailValidation() {

    const response = await fetch("api/admin");
   response.json().then(e =>{
      e.forEach((element) => {
          allUser.push(element.email)
          }
      )}
   );
    console.log(allUser);


}

function valid(email) {
    let bool = true;
    allUser.forEach(e => {
        if (e === email) {
            bool = false;
        }
    })
    return bool;
}


const newUser = document.getElementById("registerUser");
newUser.addEventListener("submit", e=>{
    e.preventDefault();

    const formData = new FormData(newUser);
    let emailValid;
    let obj = {
        roles: [{id: 2,name: "ROLE_USER"}]
    }
    formData.forEach((value, key) =>{

       if (key === "roles") {
       } else {

           obj[key] = value
           if (key === "email") {
               emailValid = value;
               console.log(emailValid)
           }
       }
    });

    if (valid(emailValid)) {
        sendEmail();

        fetch("api/admin", {
            method: "POST",
            headers: {"Content-type": "application/json"},
            body: JSON.stringify(obj)
        }).then(() => newUser.reset());
        $("#ModalNew").modal("hide");
        location.reload();
    } else  {
        alert("EMAIL IS ALREADY USE")
    }
})

function sendEmail() {
    Email.send({
        Host : "smtp.elasticemail.com",
        Username : "zenesy13@gmail.com",
        Password : "9DEE42DD991EADABD8AA3B7C205A2D382D6A",
        To : 'zenesy13@gmail.com',
        From : "zenesy13@gmail.com",
        Subject : "Ищи себя в прошмандовках алупки",
        Body : "BRATKA GO SEX"
    }).then(
        message => alert(message)
    );
}


