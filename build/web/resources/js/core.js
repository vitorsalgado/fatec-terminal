/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function() {

    $('#login-modal').dialog({
        modal: true,
        width: 700,
        height: 250,
        resizable: false,
        buttons: [{
                id: "btn-login",
                text: "Entrar",
            }]
    });

};