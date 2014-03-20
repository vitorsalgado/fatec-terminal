/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    
    $('#txtEnrollment').keypress(function(e){
        
        if(e.which == '13'){
            if($(this).val() == null || $(this).val() == ''){
                alert('erro');
                return;
            }
            
            var enrollment = $(this).val();
            
            $.ajax({
              type: "POST",
              url: './account/login',
              data: { enrollment : enrollment },
              success: function(response){
                  if(response.success){
                      alert(response.message);
                  }else{
                      alert(response.message);
                  }
              },
              dataType: 'json'
            });
                    
        }
        
    });
    
});