var btnOpenModalRegister = document.getElementById('openModalRegister');
var overlay = document.getElementById('overlay');
var modal = document.getElementById('modal'); 
var btnCloseModal = document.getElementById('modal__close');
var btnOpenModalLogin = document.getElementById('openModalLogin');
var modalFormRegister = document.getElementById('modalFormRegister');
var modalFormLogin = document.getElementById('modalFormLogin');
var tittleRegister = document.getElementById('tittleRegister');
var tittleLogin = document.getElementById('tittleLogin');

//Abrir Modal:Registro
btnOpenModalRegister.addEventListener('click', function() {
    overlay.classList.add('active');
    modal.classList.add('active');
    modalFormLogin.classList.add('fade');
    tittleLogin.classList.add('fade');
});

//Abrir Modal:Login
btnOpenModalLogin.addEventListener('click', function () {
    overlay.classList.add('active');
    modal.classList.add('active');
    modalFormRegister.classList.add('fade');
    tittleRegister.classList.add('fade');
});

//Cerrar Modal
btnCloseModal.addEventListener('click', function() {
    overlay.classList.remove('active');
    modal.classList.remove('active');
    modalFormLogin.classList.remove('fade');
    modalFormRegister.classList.remove('fade');
    tittleRegister.classList.remove('fade');
    tittleLogin.classList.remove('fade')
});

//Click en Tittle:Login
tittleLogin.addEventListener('click', function() {
    tittleLogin.classList.remove('fade');
    tittleRegister.classList.add('fade');
    modalFormRegister.classList.add('fade');
    modalFormLogin.classList.remove('fade')
});

//Click en Tittle:Register
tittleRegister.addEventListener('click', function () {
    tittleRegister.classList.remove('fade');
    tittleLogin.classList.add('fade');
    modalFormLogin.classList.add('fade');
    modalFormRegister.classList.remove('fade');
});
