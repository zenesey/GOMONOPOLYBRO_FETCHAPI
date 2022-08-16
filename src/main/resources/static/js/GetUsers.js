//TODO ВСЕ ЮЗЕРЫ
let allUsername = [];
let userNameForDelete;

async function getUsers() {
    const responce = await fetch("api/admin");
    if (responce.ok) {
        let json = await responce.json()
            .then(data => replaceTable(data))
    } else {
        alert()
    }

    function replaceTable(data) {
        const placement = document.getElementById("usersTable");
        placement.innerHTML = "";

        data.forEach(({id, name, surName, age, username: email, roles}) => {
            let userRoles = "";
            roles.forEach((role) => {
                userRoles = userRoles + role.name.substring(5) + " ";
            })
            const element = document.createElement("tr");
            allUsername.push(email);
            element.innerHTML = `
            <th scope="row">${id}</th>
            <td>${name}</td>
            <td>${surName}</td>
            <td>${age}</td>
            <td>${email}</td>
            <td>${userRoles}</td>
            <td>
                <button type="button" class="btn btn-info text-white" data-bs-userId=${id}
                    data-bs-userName=${name} data-bs-userSurname=${surName} data-bs-userAge=${age}
                    data-bs-userEmail=${email} data-bs-toggle="modal"
                    data-bs-target="#ModalEdit">Edit</button>
            </td>

            <td>
                <button type="button" class="btn btn-danger" data-bs-userId=${id}
                    data-bs-userName=${name} data-bs-userSurname=${surName} data-bs-userAge=${age}
                    data-bs-userEmail=${email} data-bs-toggle="modal"
                    data-bs-target="#ModalDelete">Delete</button>
            </td>`
            placement.append(element)
        })
    }
}


//TODO КОНЕЦ ВСЕХ ЮЗЕРОВ


//TODO PRINCIPAL;
async function auth() {
    let authObj;

    fetch("api/admin/auth").then(res => {
        res.json().then(
            user => {
                authObj = {
                    id: user.id,
                    name: user.name,
                    surname: user.surName,
                    age: user.age,
                    username: user.username,
                    userRoles: ""
                }
                user.roles.forEach((role) => {
                    authObj.userRoles += role.name.substring(5) + " ";
                })
                console.log(authObj);
                infoBar(authObj);
                navBar(authObj.userRoles, authObj.username)
                badContent(user.age)

            }
        )
    })
}


function badContent(age) {
    const placement = document.getElementById("18+");

    if (age < 18) {
    placement.innerHTML =
        `<form id="18+" action="https://youtu.be/vrLu-gdkG6I">
            <button class="btn btn-outline-warning position-absolute top-10 start-50 translate-middle-x"
                type="submit">
                    <img src="https://di.phncdn.com/www-static/images/pornhub_logo_straight.svg?cache=2022081102"
                    alt="">
            </button>
       </form>`
    }
    console.log("sas")
}

function infoBar(obj) {
    const placement = document.getElementById("infoTable")

    placement.innerHTML = `
    <td>${obj.id}</td>
    <td>${obj.name}</td>
    <td>${obj.surname}</td>
    <td>${obj.age}</td>
    <td>${obj.username}</td>
    <td>${obj.userRoles}</td>
    `
}

function navBar(roles, email) {
    console.log(roles, email)
    const placement = document.getElementById("navBar");
    const element = document.createElement("div");
    element.innerHTML = `
            <b class="navbar-brand"> ${email} </b>
            <a class="navbar-brand" href="#"> with roles:</a>
            <a class="navbar-brand" href="#" > ${roles}</a>
                        `
    console.log(roles, email)
    placement.append(element)

}

//TODO PRINCIPAL ends


//TODO МОДАЛКА DELETE
const formDelete = document.getElementById('formDelete')
formDelete.addEventListener('submit', e => {
    e.preventDefault();
    const formData = new FormData(formDelete);
    afterRemoveCheckEmail()
    console.log(userNameForDelete)

    fetch("api/admin/" + formData.get("id"), {
        method: "DELETE"
    })
        .then(() => getUsers());
    $("#ModalDelete").modal("hide");
    formDelete.reset();
})

//TODO МОДАЛКА DELETE END

//TODO МОДАЛКА DELETE
const DeleteModal = document.getElementById('ModalDelete')
DeleteModal.addEventListener('show.bs.modal', event => {

    const dButton = event.relatedTarget

    const dUserId = dButton.getAttribute('data-bs-userId')
    const dUserName = dButton.getAttribute('data-bs-userName')
    const dUserSurname = dButton.getAttribute('data-bs-userSurname')
    const dUserAge = dButton.getAttribute('data-bs-userAge')
    const dUserEmail = dButton.getAttribute('data-bs-userEmail')

    const dModalUserId = DeleteModal.querySelector('#userIdDelete')
    const dModalUserName = DeleteModal.querySelector('#userNameDelete')
    const dModalUserSurname = DeleteModal.querySelector('#userSurnameDelete')
    const dModalUserAge = DeleteModal.querySelector('#userAgeDelete')
    const dModalUserEmail = DeleteModal.querySelector('#userEmailDelete')

    dModalUserId.value = dUserId
    dModalUserName.value = dUserName
    dModalUserSurname.value = dUserSurname
    dModalUserAge.value = dUserAge
    dModalUserEmail.value = dUserEmail
    userNameForDelete = dUserEmail

})
// TODO Update
const formEdit = document.getElementById("formUpdate");
formEdit.addEventListener('submit', e => {
    e.preventDefault();

    const formData = new FormData(formEdit);
    let object = {
        roles: []
    };

    formData.getAll("roles").forEach(e => {
        const roleId = e.split(" ")[0];
        const roleName = e.split(" ")[1]
        const role = {
            id: roleId,
            name: roleName
        };
        object.roles.push(role)

    });
    console.log(object)
    formData.forEach((value, key) => {
        if (key === "roles") {
        } else {
            object[key] = value;
        }
    });

    console.log(formData.getAll("roles"))
    i = 1;
    if (1) {
        fetch("api/admin", {
            method: "PUT",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(object)
        })
            .then(() => getUsers());
        $("#ModalEdit").modal("hide");
        formEdit.reset();
    } else {
        alert("ДЕБИЛ")
    }
})
//TODO МОДАЛКА EDIT ENDS


// TODO MODEL UPDATE
const updateModal = document.getElementById('ModalEdit')
updateModal.addEventListener('show.bs.modal', event => {

    const uButton = event.relatedTarget

    const uUserId = uButton.getAttribute('data-bs-userId')
    const uUserName = uButton.getAttribute('data-bs-userName')
    const uUserSurname = uButton.getAttribute('data-bs-userSurname')
    const uUserAge = uButton.getAttribute('data-bs-userAge')
    const uUserEmail = uButton.getAttribute('data-bs-userEmail')

    const uModalUserId = updateModal.querySelector('#userIdUpdate')
    const uModalUserName = updateModal.querySelector('#userNameUpdate')
    const uModalUserSurname = updateModal.querySelector('#userSurnameUpdate')
    const uModalUserAge = updateModal.querySelector('#userAgeUpdate')
    const uModalUserEmail = updateModal.querySelector('#userEmailUpdate')

    uModalUserId.value = uUserId
    uModalUserName.value = uUserName
    uModalUserSurname.value = uUserSurname
    uModalUserAge.value = uUserAge
    uModalUserEmail.value = uUserEmail

})
// TODO MODEL UPDATE ENDS

// TODO NEW USER

const newUser = document.getElementById("newUser");
newUser.addEventListener('submit', e => {
    e.preventDefault();

    const formData = new FormData(newUser);
    let obj = {
        roles: []
    };

    formData.getAll("roles").forEach(e => {
        const roleId = e.split(" ")[0];
        const roleName = e.split(" ")[1];
        const role = {
            id: roleId,
            name: roleName
        };
        obj.roles.push(role);
    });

    formData.forEach((value, key) => {

        if (key === "roles") {
        } else {
            obj[key] = value;
        }
    });


    if (checkUniqEmail(formData, allUsername)) {
        fetch("api/admin", {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(obj)
        })
            .then(() => getUsers())
            .then(() => newUser.reset());

        return show("Page3", "Page4");
    } else {
        alert("ДЕБИЛ")
    }
})

//TODO NEW USER ENDS


//TODO ANY FUNCTION


function afterRemoveCheckEmail() {
    allUsername.forEach((value, key) => {
        if (value === userNameForDelete) {
            delete allUsername[key];
        }
    })
}


function checkUniqEmail(formData, allUsername) {
    let bool = true;
    let username;
    formData.forEach((value, key) => {
        if (key === "username") {
            username = value;
        }
    });
    allUsername.forEach(e => {
        if (e === username) {
            bool = false
        }
    });

    return bool;

}



