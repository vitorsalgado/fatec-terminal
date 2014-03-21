/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){

    $('#txtEnrollment').val('');
    $('#txtEnrollment').focus();
    
    $('#txtEnrollment').keypress(function(e){
        if(e.which == '13'){
            if($(this).val() == null || $(this).val() == ''){
                $('#login-errors').html('Informe um número de matrícula válido!').fadeIn();
                return;
            }
            
            var enrollment = $(this).val();
            
            $.ajax({
              type: "POST",
              url: './account/login',
              data: { enrollment : enrollment },
              success: function(response){
                  if(response.success){
                      $('#login-errors').hide();
                      $('#login-overlay').fadeOut();
                  }else{
                      $('#login-errors').html(response.message).fadeIn();
                  }
              },
              dataType: 'json'
            });
        }
    });
    
    
    $(this).keypress(function(e){
       switch(e.which){
           
       } 
    });
    
});
